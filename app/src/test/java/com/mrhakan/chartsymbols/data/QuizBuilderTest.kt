package com.mrhakan.chartsymbols.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class QuizBuilderTest {
    @Test
    fun everyQuestionHasFourDistinctOptionsIncludingTheAnswer() {
        val questions = QuizBuilder.build(SymbolCatalog.symbols, random = Random(7))

        assertEquals(QuizBuilder.DEFAULT_QUESTION_COUNT, questions.size)
        questions.forEach { question ->
            assertEquals(QuizBuilder.OPTIONS_PER_QUESTION, question.options.size)
            assertEquals(
                "duplicate option in ${question.symbol.id}",
                QuizBuilder.OPTIONS_PER_QUESTION,
                question.options.toSet().size
            )
            assertTrue(question.answer in question.options)
        }
    }

    @Test
    fun questionsAreNotAlwaysTheSameSixCards() {
        val first = QuizBuilder.build(SymbolCatalog.symbols, random = Random(1)).map { it.symbol.id }
        val second = QuizBuilder.build(SymbolCatalog.symbols, random = Random(2)).map { it.symbol.id }

        assertNotEquals(first, second)
        // The old screen always served the first six catalogue entries.
        assertNotEquals(SymbolCatalog.symbols.take(6).map { it.id }, first)
    }

    @Test
    fun aRoundNeverRepeatsTheSameCard() {
        val ids = QuizBuilder.build(SymbolCatalog.symbols, questionCount = 12, random = Random(3))
            .map { it.symbol.id }

        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun deckScopedRoundsOnlyAskAboutThatDeck() {
        val pool = SymbolCatalog.deck(StudyDeck.SEABED_ABBREVIATIONS)
        val questions = QuizBuilder.build(pool, random = Random(11))

        assertTrue(questions.all { it.symbol.deck == StudyDeck.SEABED_ABBREVIATIONS })
        assertTrue(questions.all { it.options.size == QuizBuilder.OPTIONS_PER_QUESTION })
    }

    @Test
    fun smallDeckStillFillsFourOptionsFromTheWiderCatalogue() {
        val pool = SymbolCatalog.deck(StudyDeck.ADMIRALTY_LIMITS)
        val questions = QuizBuilder.build(pool, questionCount = pool.size, random = Random(5))

        assertEquals(pool.size, questions.size)
        questions.forEach { question ->
            assertEquals(QuizBuilder.OPTIONS_PER_QUESTION, question.options.toSet().size)
            assertTrue(question.answer in question.options)
        }
    }

    @Test
    fun emptyOrZeroLengthRoundsAreEmptyRatherThanCrashing() {
        assertTrue(QuizBuilder.build(emptyList(), random = Random(1)).isEmpty())
        assertTrue(QuizBuilder.build(SymbolCatalog.symbols, questionCount = 0, random = Random(1)).isEmpty())
    }

    @Test
    fun aSingleCardPoolIsStillAnswerable() {
        val pool = listOf(SymbolCatalog.find("pdf-sand"))
        val questions = QuizBuilder.build(pool, questionCount = 3, random = Random(9))

        assertEquals(1, questions.size)
        assertEquals(QuizBuilder.OPTIONS_PER_QUESTION, questions.single().options.toSet().size)
        assertTrue(questions.single().answer in questions.single().options)
    }
}
