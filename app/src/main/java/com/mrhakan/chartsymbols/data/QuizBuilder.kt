package com.mrhakan.chartsymbols.data

import kotlin.random.Random

/** A single visual quiz question: the symbol to draw, plus the answer choices. */
data class QuizQuestion(
    val symbol: ChartSymbol,
    val options: List<String>
) {
    val answer: String get() = symbol.title
}

/**
 * Builds visual quiz rounds from a pool of cards.
 *
 * Questions and distractors are drawn at random so a round is never the same
 * twice, and distractors prefer cards from the same category: telling
 * "Derinliği bilinen engel" from "Derinliği bilinmeyen engel" is the part
 * worth practising. Option titles are de-duplicated because the catalogue
 * deliberately carries a few cards that share a title (a general card and its
 * study-sheet counterpart), and two identical options would make a question
 * unanswerable.
 */
object QuizBuilder {
    const val DEFAULT_QUESTION_COUNT = 6
    const val OPTIONS_PER_QUESTION = 4

    fun build(
        pool: List<ChartSymbol>,
        questionCount: Int = DEFAULT_QUESTION_COUNT,
        random: Random = Random.Default
    ): List<QuizQuestion> {
        if (pool.isEmpty() || questionCount <= 0) return emptyList()
        return pool.shuffled(random)
            .take(questionCount)
            .map { answer -> QuizQuestion(answer, optionsFor(answer, pool, random)) }
    }

    private fun optionsFor(
        answer: ChartSymbol,
        pool: List<ChartSymbol>,
        random: Random
    ): List<String> {
        val used = mutableSetOf(answer.title)
        val distractors = mutableListOf<String>()

        // Same category first, then the rest of the pool, then the whole
        // catalogue as a backstop for very small decks.
        val tiers = listOf(
            pool.filter { it.category == answer.category },
            pool,
            SymbolCatalog.symbols
        )
        for (tier in tiers) {
            for (candidate in tier.shuffled(random)) {
                if (distractors.size == OPTIONS_PER_QUESTION - 1) break
                if (candidate.id == answer.id) continue
                if (!used.add(candidate.title)) continue
                distractors += candidate.title
            }
            if (distractors.size == OPTIONS_PER_QUESTION - 1) break
        }

        return (distractors + answer.title).shuffled(random)
    }
}
