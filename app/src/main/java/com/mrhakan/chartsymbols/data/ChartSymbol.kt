package com.mrhakan.chartsymbols.data

enum class SymbolCategory(val label: String) {
    ALL("Tümü"),
    NAVIGATION("Seyir"),
    HAZARDS("Tehlike"),
    DEPTH("Derinlik"),
    AREAS("Alan")
}

enum class SymbolIcon {
    LIGHT,
    BUOY,
    WRECK,
    ANCHORAGE,
    CONTOUR,
    ROCK,
    RESTRICTED,
    SOUNDING
}

enum class SymbolAccent {
    TEAL,
    AMBER,
    BLUE,
    CORAL,
    VIOLET,
    GREEN
}

data class ChartSymbol(
    val id: String,
    val title: String,
    val englishName: String,
    val category: SymbolCategory,
    val icon: SymbolIcon,
    val accent: SymbolAccent,
    val summary: String,
    val meaning: String,
    val recognition: String,
    val memoryTip: String,
    val referenceFamily: String = "INT 1 ailesi"
)
