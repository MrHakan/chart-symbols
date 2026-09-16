package com.mrhakan.chartsymbols.data

import org.junit.Assert.assertEquals
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
}
