package com.mrhakan.chartsymbols.data

object SymbolCatalog {
    val symbols = listOf(
        ChartSymbol(
            id = "light",
            title = "Deniz feneri",
            englishName = "Light / Lighthouse",
            category = SymbolCategory.NAVIGATION,
            icon = SymbolIcon.LIGHT,
            accent = SymbolAccent.AMBER,
            summary = "Sabit bir ışık tesisini ve ışık karakteristiğini okumaya giriş.",
            meaning = "Kıyıdaki veya açık denizdeki sabit bir ışık tesisinin bulunduğu yeri gösterir. Işık rengi, periyodu ve menzili harita üzerindeki ilgili ışık bilgisiyle birlikte değerlendirilir.",
            recognition = "Kule veya ışık gövdesi, üst bölümdeki ışın çizgileri ve çoğunlukla belirgin sarı/amber vurgu.",
            memoryTip = "Işık ışınları = geceleri tanınabilir bir seyir yardımcısı."
        ),
        ChartSymbol(
            id = "buoy",
            title = "Şamandıra",
            englishName = "Buoy",
            category = SymbolCategory.NAVIGATION,
            icon = SymbolIcon.BUOY,
            accent = SymbolAccent.TEAL,
            summary = "Yüzer seyir yardımcısını şekli, rengi ve işaretiyle tanı.",
            meaning = "Kanalı, tehlikeyi veya özel bir alanı işaretleyen yüzer seyir yardımcısıdır. Şekil, renk, tepe işareti ve ışık bilgisi birlikte okunmalıdır.",
            recognition = "Su çizgisi, yüzer gövde, üst işaret ve isteğe bağlı ışık noktası.",
            memoryTip = "Şamandıra tek bir ipucu değildir; şekil + renk + ışık birlikte düşünülür."
        ),
        ChartSymbol(
            id = "wreck",
            title = "Batık",
            englishName = "Wreck",
            category = SymbolCategory.HAZARDS,
            icon = SymbolIcon.WRECK,
            accent = SymbolAccent.CORAL,
            summary = "Seyir için tehlike oluşturabilecek batık işaretini ayırt et.",
            meaning = "Deniz tabanında bulunan ve geminin emniyetli geçişini etkileyebilecek batık veya su altında kalan engeli ifade eder.",
            recognition = "Çarpışan gövde çizgileri veya üstten görünüşlü enkaz şekli; çevresinde derinlik ve tehlike notları bulunabilir.",
            memoryTip = "Düzensiz kesişen çizgiler = su altında kalan bir gövdeyi düşün."
        ),
        ChartSymbol(
            id = "anchorage",
            title = "Demirleme alanı",
            englishName = "Anchorage",
            category = SymbolCategory.AREAS,
            icon = SymbolIcon.ANCHORAGE,
            accent = SymbolAccent.BLUE,
            summary = "Demirleme için ayrılmış alanı ve kapsamını okumaya giriş.",
            meaning = "Gemilerin demirleyebileceği ayrılmış bölgeyi gösterir. Derinlik, zemin, trafik ve yerel talimatlar ayrıca kontrol edilmelidir.",
            recognition = "Alan vurgusu içinde çapa işareti veya çapa fikrini veren merkezî sembol.",
            memoryTip = "Çapa gördüğünde sadece yeri değil, sınırları ve kısıtları da ara."
        ),
        ChartSymbol(
            id = "contour",
            title = "Derinlik eğrisi",
            englishName = "Depth contour",
            category = SymbolCategory.DEPTH,
            icon = SymbolIcon.CONTOUR,
            accent = SymbolAccent.VIOLET,
            summary = "Aynı derinliğe sahip noktaları birleştiren çizgiyi oku.",
            meaning = "Aynı derinliğe sahip noktaları birleştirir ve sığlaşma veya derinleşmenin genel şeklini anlamaya yardım eder.",
            recognition = "Birbirini takip eden kapalı veya kıvrımlı çizgiler; sayı değeri varsa birim ve datum ile okunur.",
            memoryTip = "Çizgiler sıklaşıyorsa derinlik kısa mesafede değişiyor olabilir."
        ),
        ChartSymbol(
            id = "rock",
            title = "Kayalık",
            englishName = "Rock",
            category = SymbolCategory.HAZARDS,
            icon = SymbolIcon.ROCK,
            accent = SymbolAccent.CORAL,
            summary = "Su altında, su seviyesinde veya su üstündeki kayalık tehlikeyi fark et.",
            meaning = "Seyir emniyetini etkileyebilecek kayalık bir oluşumu gösterir. Kayalığın suya göre durumu ve verilen derinlik bilgisi birlikte incelenmelidir.",
            recognition = "Sivri kaya gövdesi ve altındaki dalga çizgileri; gerçek sınıflandırma harita kaydına göre değişebilir.",
            memoryTip = "Dalga çizgileri ve sert köşe = dibi/üstü tehlike olabilecek kaya."
        ),
        ChartSymbol(
            id = "restricted",
            title = "Kısıtlı alan",
            englishName = "Restricted area",
            category = SymbolCategory.AREAS,
            icon = SymbolIcon.RESTRICTED,
            accent = SymbolAccent.VIOLET,
            summary = "Seyir veya demirlemenin sınırlanabileceği alanı tanı.",
            meaning = "Seyir, demirleme veya başka bir faaliyetin sınırlandırılabileceği özel alanı ifade eder. Harita notları ve güncel yerel duyurular mutlaka kontrol edilmelidir.",
            recognition = "Belirgin alan sınırı ve çapraz tarama/uyarı deseni.",
            memoryTip = "Çapraz tarama = dur, alan açıklamasını oku, otomatik geçiş yapma."
        ),
        ChartSymbol(
            id = "sounding",
            title = "Derinlik noktası",
            englishName = "Sounding",
            category = SymbolCategory.DEPTH,
            icon = SymbolIcon.SOUNDING,
            accent = SymbolAccent.GREEN,
            summary = "Tek bir noktada ölçülmüş su derinliğini okumayı öğren.",
            meaning = "Belirli bir noktadaki ölçülmüş su derinliğini gösterir. Sayı, haritanın kullandığı birim ve düşey datum ile birlikte değerlendirilmelidir.",
            recognition = "Küçük bir nokta veya sembol yanında yer alan sayısal derinlik bilgisi.",
            memoryTip = "Sayıyı tek başına okuma: birim + datum + geminin draftı birlikte düşünülür."
        )
    )

    fun find(id: String): ChartSymbol = symbols.firstOrNull { it.id == id } ?: symbols.first()

    fun filter(query: String, category: SymbolCategory): List<ChartSymbol> {
        val normalizedQuery = query.trim().lowercase()
        return symbols.filter { symbol ->
            val matchesCategory = category == SymbolCategory.ALL || symbol.category == category
            val matchesQuery = normalizedQuery.isBlank() || listOf(
                symbol.title,
                symbol.englishName,
                symbol.summary,
                symbol.meaning
            ).any { it.lowercase().contains(normalizedQuery) }
            matchesCategory && matchesQuery
        }
    }
}
