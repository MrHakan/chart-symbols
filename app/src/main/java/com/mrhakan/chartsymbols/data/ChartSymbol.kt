package com.mrhakan.chartsymbols.data

enum class SymbolCategory(val label: String) {
    ALL("Tümü"),
    GENERAL("Genel"),
    TOPOGRAPHY("Topografya"),
    HYDROGRAPHY("Hidrografi"),
    DEPTH("Derinlik"),
    NAVIGATION("Seyir yardımcıları"),
    HAZARDS("Tehlikeler"),
    AREAS("Alanlar"),
    ROUTES("Rotalar"),
    PORTS("Liman"),
    SERVICES("Servisler")
}

enum class SymbolIcon {
    LIGHT,
    LIGHT_BEACON,
    SECTOR_LIGHT,
    LEADING_LINE,
    BEACON,
    BUOY_LATERAL,
    BUOY_CARDINAL,
    BUOY_ISOLATED_DANGER,
    BUOY_SAFE_WATER,
    BUOY_SPECIAL,
    LIGHT_FLOAT,
    RADAR_AID,
    WRECK,
    WRECK_DATUM_HULL,
    WRECK_DATUM_MAST,
    WRECK_SUNKEN_SAFE,
    WRECK_MAST_SEA_LEVEL,
    WRECK_SAFE_CLEARANCE,
    WRECK_SWEPT,
    WRECK_SOUNDED,
    ROCK,
    DANGEROUS_ROCK_UNKNOWN,
    DANGEROUS_ROCK_KNOWN,
    UNDERWATER_ROCK,
    LAND_ABOVE_SEA,
    ROCK_KNOWN_DEPTH,
    ROCK_NOT_COVER,
    ROCK_COVERS_UNCOVERS,
    ROCK_AWASH,
    ROCK_DANGEROUS_UNKNOWN,
    ROCK_NOT_DANGEROUS,
    CORAL,
    OBSTRUCTION,
    OBSTRUCTION_KNOWN,
    OBSTRUCTION_SWEPT,
    FOUL_GROUND,
    FOUL,
    MINE,
    SOUNDING,
    CONTOUR,
    DEPTH_AREA,
    DRYING,
    SEABED,
    CURRENT,
    CABLE,
    PIPELINE,
    POWER_CABLE,
    UNUSED_PIPELINE,
    DANGER_LINE,
    SWEPT_DRAG,
    COASTLINE,
    CLIFF,
    LAND,
    VEGETATION,
    BUILDING,
    LANDMARK,
    ROAD,
    BRIDGE,
    AIRFIELD,
    ANCHORAGE,
    RESTRICTED,
    NO_ANCHOR,
    PROHIBITED_AREA,
    PROHIBITED_FISHING,
    MOORING_BUOY,
    OFFSHORE_PLATFORM,
    LIGHTSHIP,
    MINARET,
    BUOYAGE_DIRECTION,
    PILOT_TRANSFER,
    TIDE_RIP,
    EDDIES,
    PLATFORM_ZONE,
    BREAKERS,
    MARINE_FARM,
    FISHING_ZONE,
    FARM,
    DUMPING,
    ROUTE,
    TRAFFIC_SEPARATION,
    PORT,
    QUAY,
    MARINA,
    LOCK,
    CRANE,
    PILOT,
    SERVICE,
    COMPASS,
    SCALE,
    NOTE
}

/**
 * Sections of the supplied `Ezberlenecek.pdf` study sheet.
 *
 * The sheet is the user's memorisation list, so the cards it produces are
 * grouped exactly the way the sheet presents them. Catalogue cards that do
 * not come from the sheet leave [ChartSymbol.deck] null.
 */
enum class StudyDeck(val label: String, val description: String) {
    SEABED_ABBREVIATIONS(
        "Deniz tabanı kısaltmaları",
        "Çalışma sayfasının \"Seabed Terms Abbreviations on Charts\" tablosu: iskandil değerlerinin yanında okunan zemin kısaltmaları."
    ),
    SYMBOL_MEANINGS(
        "Sembol ve anlamları",
        "Çalışma sayfasının \"Chart Symbols and Meanings\" tablosu: kaya, batık, kablo, boru hattı ve alan sembolleri."
    ),
    ADMIRALTY_DANGERS(
        "Admiralty – Tehlikeler",
        "Çalışma sayfasının Admiralty Chart Symbols eki, DANGERS sütunu: kaya, batık, engel ve akıntı tehlikeleri."
    ),
    ADMIRALTY_LIMITS(
        "Admiralty – Sınırlar",
        "Çalışma sayfasının Admiralty Chart Symbols eki, LIMITS sütunu: transit hattı, trafik ayırımı, kablo ve alan sınırları."
    );

    companion object {
        /** Sheet order, used for deck listings and the study screen. */
        val sheetOrder: List<StudyDeck> = listOf(
            SEABED_ABBREVIATIONS,
            SYMBOL_MEANINGS,
            ADMIRALTY_DANGERS,
            ADMIRALTY_LIMITS
        )
    }
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
    val aliases: List<String> = emptyList(),
    val chartNotation: String? = null,
    val referenceFamily: String = "IHO INT 1 / Chart No. 1",
    /** Non-null when the card is transcribed from the `Ezberlenecek.pdf` study sheet. */
    val deck: StudyDeck? = null
)
