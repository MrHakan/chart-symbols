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
        assertEquals("Batık", SymbolCatalog.filter("wreck", SymbolCategory.ALL).single().title)
        assertEquals("Deniz feneri", SymbolCatalog.filter("fener", SymbolCategory.ALL).single().title)
    }

    @Test
    fun expandedCatalogueHasUniqueIdsAndAliasSearch() {
        assertTrue(SymbolCatalog.symbols.size >= 220)
        assertEquals(SymbolCatalog.symbols.size, SymbolCatalog.symbols.map { it.id }.toSet().size)
        assertEquals("Kardinal şamandıra", SymbolCatalog.filter("cardinal", SymbolCategory.ALL).single().title)
        assertEquals("Denizaltı kablosu", SymbolCatalog.filter("cable", SymbolCategory.ALL).single().title)
        assertEquals("Kum tabanı kısaltması", SymbolCatalog.filter("S/M", SymbolCategory.ALL).single().title)
        assertEquals("Ezberlenecek.pdf / Chart Symbols and Abbreviations", SymbolCatalog.find("pdf-pilot-transfer").referenceFamily)
    }
}
