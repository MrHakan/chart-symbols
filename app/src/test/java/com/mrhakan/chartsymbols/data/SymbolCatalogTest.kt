package com.mrhakan.chartsymbols.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SymbolCatalogTest {
    @Test
    fun categoryFilterReturnsOnlyMatchingSymbols() {
        val result = SymbolCatalog.filter("", SymbolCategory.DEPTH)

        assertTrue(result.size >= 5)
        assertTrue(result.all { it.category == SymbolCategory.DEPTH })
    }

    @Test
    fun searchMatchesTurkishAndEnglishNames() {
        assertTrue(SymbolCatalog.filter("wreck", SymbolCategory.ALL).any { it.title == "Batık" })
        assertTrue(SymbolCatalog.filter("fener", SymbolCategory.ALL).any { it.title == "Deniz feneri" })
    }

    @Test
    fun expandedCatalogueHasUniqueIdsAndAliasSearch() {
        assertTrue(SymbolCatalog.symbols.size >= 220)
        assertEquals(SymbolCatalog.symbols.size, SymbolCatalog.symbols.map { it.id }.toSet().size)
        assertEquals("Kardinal şamandıra", SymbolCatalog.filter("cardinal", SymbolCategory.ALL).single().title)
        assertTrue(SymbolCatalog.filter("cable", SymbolCategory.ALL).any { it.title == "Denizaltı kablosu" })
        assertTrue(SymbolCatalog.filter("S/M", SymbolCategory.ALL).any { it.title == "Kum üstü çamur kısaltması" })
        assertEquals("Ezberlenecek.pdf / Chart Symbols and Abbreviations", SymbolCatalog.find("pdf-pilot-transfer").referenceFamily)
    }

    @Test
    fun studySheetCardsAreGroupedIntoSheetSections() {
        val sheet = SymbolCatalog.studySheetSymbols

        assertEquals(65, sheet.size)
        assertEquals(sheet.size, SymbolCatalog.symbols.count { it.deck != null })
        assertEquals(StudyDeck.sheetOrder, SymbolCatalog.decks().map { it.first })
        assertEquals(sheet.size, SymbolCatalog.decks().sumOf { it.second.size })
        assertEquals(14, SymbolCatalog.deck(StudyDeck.SEABED_ABBREVIATIONS).size)
        assertEquals(28, SymbolCatalog.deck(StudyDeck.SYMBOL_MEANINGS).size)
        assertEquals(16, SymbolCatalog.deck(StudyDeck.ADMIRALTY_DANGERS).size)
        assertEquals(7, SymbolCatalog.deck(StudyDeck.ADMIRALTY_LIMITS).size)
    }

    @Test
    fun studySheetCardsCarryHandWrittenRecognitionAndTips() {
        val generatedRecognition = "için kullanılan temel çizgi"
        val generatedTip = "Önce kategoriyi"

        SymbolCatalog.studySheetSymbols.forEach { symbol ->
            assertFalse(
                "${symbol.id} still uses the generated recognition text",
                symbol.recognition.contains(generatedRecognition)
            )
            assertFalse(
                "${symbol.id} still uses the generated memory tip",
                symbol.memoryTip.startsWith(generatedTip)
            )
            assertEquals(
                "Ezberlenecek.pdf / Chart Symbols and Abbreviations",
                symbol.referenceFamily
            )
        }
    }

    @Test
    fun deckFilterNarrowsSearchToOneSheetSection() {
        val seabed = SymbolCatalog.filter("", SymbolCategory.ALL, StudyDeck.SEABED_ABBREVIATIONS)

        assertEquals(14, seabed.size)
        assertTrue(seabed.all { it.deck == StudyDeck.SEABED_ABBREVIATIONS })
        assertTrue(seabed.any { it.chartNotation == "S/M" })

        // Without a deck the same query still reaches the whole catalogue.
        assertTrue(SymbolCatalog.filter("", SymbolCategory.ALL).size > seabed.size)
    }
}
