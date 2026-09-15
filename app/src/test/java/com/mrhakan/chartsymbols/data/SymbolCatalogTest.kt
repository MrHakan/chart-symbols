package com.mrhakan.chartsymbols.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SymbolCatalogTest {
    @Test
    fun categoryFilterReturnsOnlyMatchingSymbols() {
        val result = SymbolCatalog.filter("", SymbolCategory.DEPTH)

        assertEquals(2, result.size)
        assertTrue(result.all { it.category == SymbolCategory.DEPTH })
    }

    @Test
    fun searchMatchesTurkishAndEnglishNames() {
        assertEquals("Batık", SymbolCatalog.filter("wreck", SymbolCategory.ALL).single().title)
        assertEquals("Deniz feneri", SymbolCatalog.filter("fener", SymbolCategory.ALL).single().title)
    }
}
