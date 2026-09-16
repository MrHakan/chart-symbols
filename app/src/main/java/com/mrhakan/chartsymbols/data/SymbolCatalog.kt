package com.mrhakan.chartsymbols.data

/**
 * Offline INT 1 / Chart 1 learning catalogue.
 *
 * The catalogue is intentionally data-driven: adding a symbol does not
 * require changing the navigation, search or quiz screens. Visuals are
 * original vector redraws selected by [SymbolIcon], while the names and
 * explanations are kept separate so the catalogue can grow safely.
 */
private const val PDF_REFERENCE = "Ezberlenecek.pdf / Chart Symbols and Abbreviations"

object SymbolCatalog {
    val symbols: List<ChartSymbol> = listOf(
        // Navigation aids and the original starter cards.
        c(
            id = "light",
            title = "Deniz feneri",
            englishName = "Light / Lighthouse",
            category = SymbolCategory.NAVIGATION,
            icon = SymbolIcon.LIGHT,
            summary = "Sabit ışık tesisini ve ışık karakteristiğini tanı.",
            meaning = "Kıyıdaki veya açık denizdeki sabit ışık tesisinin bulunduğu yeri gösterir. Renk, periyot ve menzil bilgisi haritadaki ışık notuyla birlikte değerlendirilir.",
            recognition = "Kule/gövde, magenta ışık halesi ve ışın çizgileri; ışık karakteristiği harita notuyla birlikte okunur.",
            memoryTip = "Magenta ışık halesi = gece görülen seyir yardımcısı.",
            aliases = listOf("lighthouse", "light", "fener", "ışık")
        ),
        c(
            id = "buoy-lateral",
            title = "Lateral şamandıra",
            englishName = "Lateral mark / Buoy",
            category = SymbolCategory.NAVIGATION,
            icon = SymbolIcon.BUOY_LATERAL,
            summary = "Kanal kenarını şekil, renk ve üst işaretle oku.",
            meaning = "Seyredilebilir kanalın kenarlarını gösteren yüzer veya sabit seyir yardımcısıdır. Bölgesel lateral sistem, renk ve numaralandırma birlikte kontrol edilir.",
            recognition = "Su çizgisi, magenta gövde, konik üst işaret ve renk bandı.",
            memoryTip = "Lateral markada tarafı tek başına değil, bölgesel sistemle birlikte düşün.",
            aliases = listOf("buoy", "lateral mark", "şamandıra", "kanal işareti")
        ),
        c(
            id = "wreck",
            title = "Batık",
            englishName = "Wreck",
            category = SymbolCategory.HAZARDS,
            icon = SymbolIcon.WRECK,
            summary = "Seyir için tehlike oluşturabilecek batık işaretini ayırt et.",
            meaning = "Deniz tabanında bulunan ve emniyetli geçişi etkileyebilecek batık veya su altında kalan engeli ifade eder.",
            recognition = "Gövde/mast çizgileri ve batığın konumunu gösteren magenta vurgu; en küçük derinlik bilgisi ayrıca verilebilir.",
            memoryTip = "Gövde + mast çizgileri = su altında kalan bir gemi/enkazı ara.",
            aliases = listOf("wreck", "batık", "enkaz")
        ),
        c(
            id = "anchorage",
            title = "Demirleme alanı",
            englishName = "Anchorage",
            category = SymbolCategory.AREAS,
            icon = SymbolIcon.ANCHORAGE,
            summary = "Demirleme için ayrılmış alanı ve sınırını oku.",
            meaning = "Gemilerin demirleyebileceği ayrılmış bölgeyi gösterir. Derinlik, zemin, trafik ve yerel talimatlar ayrıca kontrol edilmelidir.",
            recognition = "Magenta sınır/tarama içinde merkezî çapa işareti.",
            memoryTip = "Çapa gördüğünde alanın sınırlarını ve kısıtlarını da ara.",
            aliases = listOf("anchorage", "demirleme", "demir yeri", "çapa")
        ),
        c(
            id = "contour",
            title = "Derinlik eğrisi",
            englishName = "Depth contour",
            category = SymbolCategory.DEPTH,
            icon = SymbolIcon.CONTOUR,
            summary = "Aynı derinliğe sahip noktaları birleştiren çizgiyi oku.",
            meaning = "Aynı derinliğe sahip noktaları birleştirir ve sığlaşma veya derinleşmenin genel şeklini anlamaya yardım eder.",
            recognition = "Mavi, kıvrımlı izobat çizgisi ve çizgi üzerindeki derinlik etiketi.",
            memoryTip = "İzobatlar sıklaşıyorsa derinlik kısa mesafede hızlı değişiyor olabilir.",
            aliases = listOf("depth contour", "isobath", "izobat", "derinlik eğrisi")
        ),
        c(
            id = "rock",
            title = "Kayalık",
            englishName = "Rock",
            category = SymbolCategory.HAZARDS,
            icon = SymbolIcon.ROCK,
            summary = "Su altında, su seviyesinde veya su üstündeki kayalık tehlikeyi fark et.",
            meaning = "Seyir emniyetini etkileyebilecek kayalık bir oluşumu gösterir. Kayalığın suya göre durumu ve verilen derinlik bilgisi birlikte incelenmelidir.",
            recognition = "Konumda kesişen yıldız/çarpı biçimli kaya işareti ve eşlik eden derinlik bilgisi.",
            memoryTip = "Çarpı/yıldız işareti = kaya tehlikesi; derinlik notunu kontrol et.",
            aliases = listOf("rock", "kaya", "kayalık", "underwater rock")
        ),
        c(
            id = "restricted",
            title = "Kısıtlı alan",
            englishName = "Restricted area",
            category = SymbolCategory.AREAS,
            icon = SymbolIcon.RESTRICTED,
            summary = "Seyir veya demirlemenin sınırlanabileceği alanı tanı.",
            meaning = "Seyir, demirleme veya başka bir faaliyetin sınırlandırılabileceği özel alanı ifade eder. Harita notları ve güncel yerel duyurular kontrol edilmelidir.",
            recognition = "Magenta alan sınırı, çapraz tarama ve merkezî uyarı işareti.",
            memoryTip = "Çapraz tarama = alan açıklamasını oku; otomatik geçiş yapma.",
            aliases = listOf("restricted", "restriction", "kısıtlı alan", "yasak alan")
        ),
        c(
            id = "sounding",
            title = "Derinlik noktası",
            englishName = "Sounding",
            category = SymbolCategory.DEPTH,
            icon = SymbolIcon.SOUNDING,
            summary = "Tek bir noktada ölçülmüş su derinliğini oku.",
            meaning = "Belirli bir noktadaki ölçülmüş su derinliğini gösterir. Sayı, birim ve düşey datum ile birlikte değerlendirilmelidir.",
            recognition = "Tek bir konuma ait sayısal derinlik ve küçük konum noktası.",
            memoryTip = "Sayıyı tek başına okuma: birim + datum + draft birlikte düşünülür.",
            aliases = listOf("sounding", "spot depth", "iskandil", "derinlik")
        ),

        // Exact study terms and symbol families collected from Ezberlenecek.pdf.
        p("pdf-sand", "Kum tabanı kısaltması", "Sand (S)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "S", meaning = "S, harita üzerindeki deniz tabanının kum olduğunu belirtir.", aliases = listOf("S", "sand", "kum")),
        p("pdf-clay", "Kil / balçık tabanı kısaltması", "Clay (Cy; Cl)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "Cy; Cl", meaning = "Cy veya Cl, deniz tabanındaki kil ya da balçık malzemesini belirtir.", aliases = listOf("Cy", "Cl", "clay", "kil", "balçık")),
        p("pdf-mud", "Çamur tabanı kısaltması", "Mud (M)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "M", meaning = "M, deniz tabanının çamur olduğunu belirtir.", aliases = listOf("M", "mud", "çamur")),
        p("pdf-silt", "Alüvyon tabanı kısaltması", "Silt (Si)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "Si", meaning = "Si, ince taneli alüvyon veya silt tabanını belirtir.", aliases = listOf("Si", "silt", "alüvyon")),
        p("pdf-stones", "Taşlık taban kısaltması", "Stones (St)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "St", meaning = "St, deniz tabanının taşlık olduğunu belirtir.", aliases = listOf("St", "stones", "taşlık")),
        p("pdf-gravel", "İnce çakıl tabanı kısaltması", "Gravel (G)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "G", meaning = "G, deniz tabanındaki ince çakıl malzemesini belirtir.", aliases = listOf("G", "gravel", "ince çakıl")),
        p("pdf-pebbles", "Orta çakıl tabanı kısaltması", "Pebbles (P)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "P", meaning = "P, deniz tabanındaki orta boy çakılları belirtir.", aliases = listOf("P", "pebbles", "orta çakıl")),
        p("pdf-cobbles", "Büyük çakıl tabanı kısaltması", "Cobbles (Cb)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "Cb", meaning = "Cb, deniz tabanındaki büyük çakıl veya taş parçalarını belirtir.", aliases = listOf("Cb", "cobbles", "büyük çakıl")),
        p("pdf-rocky", "Kaya tabanı kısaltması", "Rock / Rocky (Rk)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "Rk", meaning = "Rk, deniz tabanının kaya veya kayalık olduğunu belirtir.", aliases = listOf("Rk", "rock", "rocky", "kaya", "kayalık")),
        p("pdf-coral", "Mercan tabanı kısaltması", "Coral (Co)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "Co", meaning = "Co, deniz tabanındaki mercan oluşumunu belirtir.", aliases = listOf("Co", "coral", "mercan")),
        p("pdf-shells", "Kabuklu taban kısaltması", "Shells (Sh)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "Sh", meaning = "Sh, deniz tabanında kabuk veya kavkı birikimi bulunduğunu belirtir.", aliases = listOf("Sh", "shells", "kabuk")),
        p("pdf-sand-over-mud", "Kum üstü çamur kısaltması", "Sand over Mud (S/M)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "S/M", meaning = "S/M, iki tabakalı zemini; üstte kum, altta çamur bulunduğunu belirtir.", aliases = listOf("S/M", "sand over mud", "iki kat")),
        p("pdf-weed", "Otluk / yosun kısaltması", "Weed / Kelp (Wd)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "Wd", meaning = "Wd, deniz tabanında otluk veya kelp/yosun bulunduğunu belirtir.", aliases = listOf("Wd", "weed", "kelp", "otluk", "yosun")),
        p("pdf-mussels", "Midye tabanı kısaltması", "Mussels (Ms)", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, "Ms", meaning = "Ms, deniz tabanında midye yatakları bulunduğunu belirtir.", aliases = listOf("Ms", "mussels", "midye")),

        p("pdf-danger-rock-unknown", "Derinliği belirsiz tehlikeli sualtı kayası", "Dangerous underwater rock of uncertain depth", SymbolCategory.HAZARDS, SymbolIcon.DANGEROUS_ROCK_UNKNOWN, meaning = "Derinliği kesin olarak bilinmeyen, yüzey seyrine tehlike oluşturduğu kabul edilen sualtı kayasıdır.", aliases = listOf("dangerous underwater rock", "uncertain depth", "belirsiz derinlik")),
        p("pdf-danger-rock-known", "Derinliği bilinen tehlikeli sualtı kayası", "Dangerous underwater rock of certain depth", SymbolCategory.HAZARDS, SymbolIcon.DANGEROUS_ROCK_KNOWN, meaning = "Derinliği ölçülmüş, ancak yüzey seyrine tehlike oluşturabilecek sualtı kayasıdır.", aliases = listOf("dangerous underwater rock", "certain depth", "bilinen derinlik")),
        p("pdf-underwater-rock", "Sualtı kayası", "Underwater rock", SymbolCategory.HAZARDS, SymbolIcon.UNDERWATER_ROCK, meaning = "Su seviyesinin altında kalan kaya oluşumunu belirtir; derinlik notu emniyet değerlendirmesine dahil edilir.", aliases = listOf("underwater rock", "sualtı kayası")),
        p("pdf-land-above-sea", "Deniz seviyesinin üstündeki kara parçası", "Piece of land above sea level", SymbolCategory.TOPOGRAPHY, SymbolIcon.LAND_ABOVE_SEA, meaning = "Kara veya küçük ada parçasının deniz seviyesinin üzerinde bulunduğunu belirtir.", aliases = listOf("land above sea level", "island", "kara parçası")),
        p("pdf-depth-contours", "Derinlik konturları", "Depth contours", SymbolCategory.DEPTH, SymbolIcon.CONTOUR, meaning = "Eşit derinlikteki noktaları birleştiren kontur/izobat çizgileridir.", aliases = listOf("depth contours", "isobath", "izobat")),
        p("pdf-corals", "Mercan sembolü", "Corals", SymbolCategory.HYDROGRAPHY, SymbolIcon.CORAL, meaning = "Haritada mercan oluşumlarını veya mercanlı deniz tabanını belirtir.", aliases = listOf("corals", "coral symbol", "mercan")),
        p("pdf-anchoring-area", "Demirleme alanı", "Anchoring area", SymbolCategory.AREAS, SymbolIcon.ANCHORAGE, meaning = "Gemilerin demirlemesi için ayrılmış alanı belirtir.", aliases = listOf("anchoring area", "anchorage", "demirleme")),
        p("pdf-prohibited-anchoring-area", "Demirlemenin yasak olduğu alan", "Prohibited anchoring area", SymbolCategory.AREAS, SymbolIcon.NO_ANCHOR, meaning = "Bu alanda demirleme yapılmaması gerektiğini belirtir.", aliases = listOf("prohibited anchoring", "anchorage prohibited", "demir yasak")),

        // Page 2 symbols from the supplied study sheet.
        p("pdf-wreck-hull-datum", "Datum seviyesinde gövdesi görünen batık", "Wreck showing hull or superstructure at chart datum", SymbolCategory.HAZARDS, SymbolIcon.WRECK_DATUM_HULL, "Wk", meaning = "Batığın gövdesi veya üst yapısının harita datumu seviyesinde görüldüğünü belirtir.", aliases = listOf("wreck hull", "chart datum", "Wk")),
        p("pdf-wreck-mast-datum", "Datum üzerinde direği görünen batık", "Wreck showing mast or masts above chart datum only", SymbolCategory.HAZARDS, SymbolIcon.WRECK_DATUM_MAST, "Mast", meaning = "Batığın yalnızca direk veya direklerinin harita datumu üzerinde kaldığını belirtir.", aliases = listOf("wreck mast", "mast above chart datum", "direk")),
        p("pdf-sunken-wreck-safe", "Yüzey seyrine tehlikeli olmayan batık", "Sunken wreck, not dangerous to surface navigation", SymbolCategory.HAZARDS, SymbolIcon.WRECK_SUNKEN_SAFE, "Wk", meaning = "Tamamen batmış ve yüzey seyrine tehlike oluşturmadığı kabul edilen batıktır.", aliases = listOf("sunken wreck", "safe wreck", "güvenli batık")),
        p("pdf-wreck-mast-sea-level", "Deniz seviyesinin üstünde direği görünen batık", "Wreck showing mast above sea level", SymbolCategory.HAZARDS, SymbolIcon.WRECK_MAST_SEA_LEVEL, "Mast", meaning = "Batığın direğinin deniz seviyesinin üzerine çıktığını belirtir.", aliases = listOf("mast above sea level", "batık direği")),
        p("pdf-wreck-least-depth-safe", "Emniyetli açıklığı olan batık", "Wreck, least depth unknown but safe clearance considered", SymbolCategory.HAZARDS, SymbolIcon.WRECK_SAFE_CLEARANCE, "Wk", meaning = "En küçük derinliği bilinmeyen, fakat gösterilen derinliğe kadar emniyetli açıklığı olduğu kabul edilen batıktır.", aliases = listOf("safe clearance wreck", "least depth unknown")),
        p("pdf-prohibited-area", "Yasak saha", "Prohibited area", SymbolCategory.AREAS, SymbolIcon.PROHIBITED_AREA, meaning = "Seyir veya belirli bir faaliyetin yasaklandığı alanı belirtir.", aliases = listOf("prohibited area", "yasak saha")),
        p("pdf-underwater-cable", "Sualtı kablosu", "Underwater cable / submarine cable", SymbolCategory.HYDROGRAPHY, SymbolIcon.CABLE, meaning = "Deniz tabanından geçen sualtı haberleşme veya enerji kablosunu belirtir.", aliases = listOf("underwater cable", "submarine cable", "kablo")),
        p("pdf-underwater-pipeline", "Sualtı boru hattı", "Underwater pipeline", SymbolCategory.HYDROGRAPHY, SymbolIcon.PIPELINE, meaning = "Deniz tabanından geçen boru hattını belirtir.", aliases = listOf("underwater pipeline", "pipeline", "boru hattı")),
        p("pdf-prohibited-fishing-area", "Balıkçılığın yasak olduğu alan", "Prohibited fishing area", SymbolCategory.AREAS, SymbolIcon.PROHIBITED_FISHING, meaning = "Balıkçılık faaliyetinin yasaklandığı alanı belirtir.", aliases = listOf("prohibited fishing", "fishing prohibited", "balıkçılık yasak")),
        p("pdf-mooring-buoys", "Bağlama şamandıraları", "Buoys for mooring", SymbolCategory.NAVIGATION, SymbolIcon.MOORING_BUOY, meaning = "Gemilerin bağlanması için kullanılan bağlama şamandıralarını belirtir.", aliases = listOf("mooring buoy", "bağlama şamandırası")),
        p("pdf-unused-pipeline", "Kullanım dışı sualtı boru hattı", "Underwater pipeline not in use", SymbolCategory.HYDROGRAPHY, SymbolIcon.UNUSED_PIPELINE, meaning = "Haritada gösterilen ancak artık kullanımda olmayan sualtı boru hattını belirtir.", aliases = listOf("pipeline not in use", "unused pipeline", "kullanım dışı boru")),
        p("pdf-danger-line", "Genel tehlike hattı", "Danger line in general", SymbolCategory.HAZARDS, SymbolIcon.DANGER_LINE, meaning = "Bir tehlikenin çevresini veya tehlikeli alan sınırını genel olarak belirtir.", aliases = listOf("danger line", "tehlike hattı")),
        p("pdf-obstruction-unknown", "Derinliği bilinmeyen engel", "Obstruction, depth unknown", SymbolCategory.HAZARDS, SymbolIcon.OBSTRUCTION, "Obstn", meaning = "Deniz içindeki engelin bulunduğunu, ancak derinliğinin bilinmediğini belirtir.", aliases = listOf("obstruction depth unknown", "obstn", "engel")),
        p("pdf-swept-wire-drag", "Tel taraması veya dalgıçla taranmış alan", "Swept by wire drag or diver", SymbolCategory.HAZARDS, SymbolIcon.SWEPT_DRAG, "3", meaning = "Tel taraması veya dalgıç kontrolüyle belirli bir derinliğe kadar taranmış alanı belirtir.", aliases = listOf("wire drag", "diver swept", "tarama")),
        p("pdf-lighted-offshore-platform", "Işıklı açık deniz platformu", "Lighted offshore platform", SymbolCategory.NAVIGATION, SymbolIcon.OFFSHORE_PLATFORM, meaning = "Işıklandırılmış açık deniz petrol, gaz veya çalışma platformunu belirtir.", aliases = listOf("lighted offshore platform", "platform")),
        p("pdf-lightship-big-light", "Büyük ışıklı fener gemisi / şamandıra", "Lightship or buoy having a big light", SymbolCategory.NAVIGATION, SymbolIcon.LIGHTSHIP, meaning = "Büyük bir fener taşıyan lightship veya şamandırayı belirtir.", aliases = listOf("lightship", "big light", "fener gemisi")),
        p("pdf-minaret", "Minare", "Minaret, single or twin", SymbolCategory.TOPOGRAPHY, SymbolIcon.MINARET, "Minaret", meaning = "Tek veya çift minareyi, kıyıdaki belirgin kara işareti olarak belirtir.", aliases = listOf("minaret", "single minaret", "twin minaret", "minare")),
        p("pdf-direction-of-buoyage", "Şamandıra sisteminin yönü", "Direction of buoyage", SymbolCategory.NAVIGATION, SymbolIcon.BUOYAGE_DIRECTION, meaning = "Lateral şamandıra sisteminde seyir yönünü veya buoyage yönünü belirtir.", aliases = listOf("direction of buoyage", "buoyage direction", "şamandıra yönü")),
        p("pdf-underwater-rock-known-depth", "Derinliği bilinen sualtı kayası", "Underwater rock, depth known", SymbolCategory.HAZARDS, SymbolIcon.ROCK_KNOWN_DEPTH, "67 Rk", meaning = "Sualtı kayasının derinliğinin bilindiğini; örnekte 67 ve Rk gösterimini belirtir.", aliases = listOf("underwater rock depth known", "67 Rk", "bilinen kaya derinliği")),
        p("pdf-pilot-transfer", "Kılavuz kaptan transfer yeri", "Location of pilot transfer", SymbolCategory.SERVICES, SymbolIcon.PILOT_TRANSFER, meaning = "Kılavuz kaptanın gemiye alındığı veya gemiden transfer edildiği yeri belirtir.", aliases = listOf("pilot transfer", "pilot boarding", "kılavuz transferi")),

        // The supplied page 3 also contains an Admiralty-style danger and limits table.
        p("pdf-rock-not-cover", "Örtmeyen kaya", "Rock which does not cover", SymbolCategory.HAZARDS, SymbolIcon.ROCK_NOT_COVER, meaning = "Gelgitte tamamen su altında kalmayan ve belirtilen yükseklikle birlikte gösterilen kayadır.", aliases = listOf("rock does not cover", "örtmeyen kaya")),
        p("pdf-rock-covers-uncovers", "Örten ve açığa çıkan kaya", "Rock which covers and uncovers", SymbolCategory.HAZARDS, SymbolIcon.ROCK_COVERS_UNCOVERS, meaning = "Gelgit seviyesine göre bazen su altında kalan, bazen açığa çıkan kayadır.", aliases = listOf("covers and uncovers", "rock awash", "gelgit kayası")),
        p("pdf-rock-awash", "Harita datumu seviyesinde kaya", "Rock awash at chart datum", SymbolCategory.HAZARDS, SymbolIcon.ROCK_AWASH, meaning = "Harita datumu seviyesinde suyla aynı hizada kalan kayayı belirtir.", aliases = listOf("rock awash", "chart datum rock")),
        p("pdf-rock-dangerous-unknown", "Derinliği bilinmeyen tehlikeli kaya", "Rock dangerous to navigation, depth unknown", SymbolCategory.HAZARDS, SymbolIcon.ROCK_DANGEROUS_UNKNOWN, meaning = "Derinliği bilinmeyen, ancak yüzey seyrine tehlikeli kabul edilen kayadır.", aliases = listOf("dangerous rock unknown", "tehlikeli bilinmeyen kaya")),
        p("pdf-rock-not-dangerous", "Yüzey seyrine tehlikeli olmayan kaya", "Underwater rock not dangerous to surface navigation", SymbolCategory.HAZARDS, SymbolIcon.ROCK_NOT_DANGEROUS, "35 R", meaning = "Verilen derinlik ve R notuyla yüzey seyrine tehlikeli kabul edilmeyen kayayı belirtir.", aliases = listOf("rock not dangerous", "safe underwater rock", "35 R")),
        p("pdf-wreck-swept", "Tel taramasıyla derinliği bulunan batık", "Wreck swept by wire to depth shown", SymbolCategory.HAZARDS, SymbolIcon.WRECK_SWEPT, "Wk", meaning = "Tel taramasıyla gösterilen derinliğe kadar kontrol edilmiş batığı belirtir.", aliases = listOf("wreck swept by wire", "wire swept wreck")),
        p("pdf-wreck-sounded", "İskandille derinliği bulunan batık", "Wreck depth obtained by sounding, not wire sweep", SymbolCategory.HAZARDS, SymbolIcon.WRECK_SOUNDED, "Wk", meaning = "Derinliği iskandille ölçülmüş, ancak tel taramasıyla doğrulanmamış batığı belirtir.", aliases = listOf("wreck sounding", "sounded wreck", "iskandil batık")),
        p("pdf-wreck-safe-clearance", "Derinliği bilinmeyen emniyetli batık", "Wreck of unknown exact depth with safe clearance", SymbolCategory.HAZARDS, SymbolIcon.WRECK_SAFE_CLEARANCE, "Wk", meaning = "Kesin derinliği bilinmeyen, fakat gösterilen derinlikte emniyetli açıklığı olduğu kabul edilen batıktır.", aliases = listOf("safe clearance", "wreck unknown depth")),
        p("pdf-foul", "Foul alan", "Foul ground / remains of wreck", SymbolCategory.HAZARDS, SymbolIcon.FOUL, "Foul", meaning = "Batık kalıntısı veya başka bir foul alanı; yüzey seyrine artık doğrudan tehlikeli olmasa da trol ve demirleme için kaçınılması gereken bölgedir.", aliases = listOf("foul ground", "foul area", "batık kalıntısı")),
        p("pdf-obstruction-known", "Derinliği bilinen engel", "Obstruction, depth known", SymbolCategory.HAZARDS, SymbolIcon.OBSTRUCTION_KNOWN, "Obstn", meaning = "Engelin derinliğinin ölçülmüş olduğunu belirtir.", aliases = listOf("obstruction depth known", "known obstruction")),
        p("pdf-obstruction-swept", "Tel taramasıyla kontrol edilmiş engel", "Obstruction swept by wire to depth shown", SymbolCategory.HAZARDS, SymbolIcon.OBSTRUCTION_SWEPT, "Obstn", meaning = "Engelin tel taramasıyla gösterilen derinliğe kadar kontrol edildiğini belirtir.", aliases = listOf("swept obstruction", "wire swept obstruction")),
        p("pdf-overfalls", "Overfall / gelgit yarışı", "Overfalls, tide rips and races", SymbolCategory.HYDROGRAPHY, SymbolIcon.TIDE_RIP, meaning = "Gelgit akıntısının deniz yüzeyinde overfall, rip veya race oluşturduğu bölgeyi belirtir.", aliases = listOf("overfalls", "tide rip", "tide race", "gelgit yarışı")),
        p("pdf-eddies", "Girdaplar", "Eddies", SymbolCategory.HYDROGRAPHY, SymbolIcon.EDDIES, meaning = "Girdap veya dönen akıntı oluşumlarının bulunduğu bölgeyi belirtir.", aliases = listOf("eddies", "girdap")),
        p("pdf-oil-gas-platform", "Petrol/gaz platformu ve emniyet sahası", "Oil or gas production platform with or without safety zone", SymbolCategory.AREAS, SymbolIcon.PLATFORM_ZONE, meaning = "Petrol veya gaz üretim platformunu ve varsa etrafındaki emniyet bölgesini belirtir.", aliases = listOf("oil platform", "gas platform", "safety zone")),
        p("pdf-breakers", "Kırılan dalgalar", "Breakers", SymbolCategory.HYDROGRAPHY, SymbolIcon.BREAKERS, meaning = "Sığlık, resif veya akıntı nedeniyle dalgaların kırıldığı bölgeyi belirtir.", aliases = listOf("breakers", "breaking waves", "kırılan dalga")),
        p("pdf-marine-farm", "Deniz çiftliği", "Marine farm, large scale chart", SymbolCategory.AREAS, SymbolIcon.MARINE_FARM, meaning = "Büyük ölçekli haritada gösterilen deniz çiftliği veya yetiştiricilik alanını belirtir.", aliases = listOf("marine farm", "fish farm", "deniz çiftliği")),
        p("pdf-leading-line", "Transit hattı", "Leading line", SymbolCategory.ROUTES, SymbolIcon.LEADING_LINE, meaning = "İki fener veya işaretin aynı kerterizde tutulmasıyla takip edilen transit hattıdır.", aliases = listOf("leading line", "transit line", "transit")),
        p("pdf-traffic-separation", "Trafik ayırım düzeni", "Traffic separation scheme", SymbolCategory.ROUTES, SymbolIcon.TRAFFIC_SEPARATION, meaning = "Tek yönlü trafik şeritlerini ve aralarındaki ayırım bölgelerini belirtir.", aliases = listOf("traffic separation scheme", "TSS", "trafik ayırım")),
        p("pdf-power-cable", "Enerji amaçlı sualtı kablosu", "Submarine cable, power", SymbolCategory.HYDROGRAPHY, SymbolIcon.POWER_CABLE, meaning = "Enerji iletiminde kullanılan sualtı elektrik kablosunu belirtir.", aliases = listOf("power cable", "submarine power cable", "enerji kablosu")),
        p("pdf-national-fishing-zone", "Ulusal balıkçılık bölgesi sınırı", "Limits of national fishing zones", SymbolCategory.AREAS, SymbolIcon.FISHING_ZONE, meaning = "Ulusal balıkçılık bölgesinin sınırlarını belirtir.", aliases = listOf("national fishing zone", "fishing zone limits", "balıkçılık bölgesi")),
        p("pdf-anchorage-general", "Genel demirleme alanı", "Anchorage area in general", SymbolCategory.AREAS, SymbolIcon.ANCHORAGE, meaning = "Genel demirleme alanını; özel tip, numara veya gemi sınıfı kısıtları ayrıca belirtilmiş olabilir.", aliases = listOf("general anchorage", "anchorage area")),
        p("pdf-anchorage-prohibited", "Demirleme yasak alanı", "Anchorage prohibited", SymbolCategory.AREAS, SymbolIcon.NO_ANCHOR, meaning = "Demirleme yapılmasının yasak olduğunu belirten alan işaretidir.", aliases = listOf("anchorage prohibited", "no anchoring")),
        p("pdf-fishing-prohibited", "Balıkçılık yasak alanı", "Fishing prohibited", SymbolCategory.AREAS, SymbolIcon.PROHIBITED_FISHING, meaning = "Balıkçılık yapılmasının yasak olduğunu belirten alan işaretidir.", aliases = listOf("fishing prohibited", "no fishing")),

        // A. General chart information and marginalia.
        c("chart-title", "Harita başlığı", "Chart title", SymbolCategory.GENERAL, SymbolIcon.NOTE, aliases = listOf("title", "başlık")),
        c("north-arrow", "Kuzey oku", "North arrow", SymbolCategory.GENERAL, SymbolIcon.COMPASS, aliases = listOf("north", "true north", "kuzey")),
        c("compass-rose", "Pusula gülü", "Compass rose", SymbolCategory.GENERAL, SymbolIcon.COMPASS, aliases = listOf("compass", "pusula")),
        c("scale-bar", "Ölçek çubuğu", "Linear scale", SymbolCategory.GENERAL, SymbolIcon.SCALE, aliases = listOf("scale", "ölçek")),
        c("chart-datum", "Harita datumu", "Chart datum", SymbolCategory.GENERAL, SymbolIcon.NOTE, aliases = listOf("datum", "chart datum")),
        c("vertical-datum", "Derinlik datumu", "Vertical datum", SymbolCategory.GENERAL, SymbolIcon.NOTE, aliases = listOf("vertical datum", "düşey datum")),
        c("magnetic-variation", "Manyetik varyasyon", "Magnetic variation", SymbolCategory.GENERAL, SymbolIcon.COMPASS, aliases = listOf("variation", "magnetic", "manyetik")),
        c("chart-note", "Harita notu", "Chart note", SymbolCategory.GENERAL, SymbolIcon.NOTE, aliases = listOf("note", "not")),
        c("chart-limit", "Harita sınırı", "Chart limit", SymbolCategory.GENERAL, SymbolIcon.NOTE, aliases = listOf("border", "limit", "sınır")),
        c("not-to-scale", "Ölçeksiz işaret", "Not to scale", SymbolCategory.GENERAL, SymbolIcon.SCALE, aliases = listOf("NTS", "ölçeksiz")),

        // B. Topography and conspicuous land features.
        c("coastline", "Kıyı çizgisi", "Coastline", SymbolCategory.TOPOGRAPHY, SymbolIcon.COASTLINE, aliases = listOf("shoreline", "coast", "kıyı")),
        c("cliff", "Falez / dik kıyı", "Cliff", SymbolCategory.TOPOGRAPHY, SymbolIcon.CLIFF, aliases = listOf("cliff", "falez", "dik kıyı")),
        c("beach", "Kumsal", "Beach", SymbolCategory.TOPOGRAPHY, SymbolIcon.LAND, aliases = listOf("beach", "sahil", "kumsal")),
        c("sand-dunes", "Kum tepeleri", "Sand dunes", SymbolCategory.TOPOGRAPHY, SymbolIcon.LAND, aliases = listOf("dune", "kum")),
        c("marsh", "Bataklık / sazlık", "Marsh", SymbolCategory.TOPOGRAPHY, SymbolIcon.VEGETATION, aliases = listOf("marsh", "swamp", "bataklık")),
        c("mangrove", "Mangrov", "Mangrove", SymbolCategory.TOPOGRAPHY, SymbolIcon.VEGETATION, aliases = listOf("mangrove")),
        c("woods", "Ormanlık alan", "Woods", SymbolCategory.TOPOGRAPHY, SymbolIcon.VEGETATION, aliases = listOf("forest", "woods", "orman")),
        c("orchard", "Meyvelik", "Orchard", SymbolCategory.TOPOGRAPHY, SymbolIcon.VEGETATION, aliases = listOf("orchard", "meyvelik")),
        c("building", "Bina", "Building", SymbolCategory.TOPOGRAPHY, SymbolIcon.BUILDING, aliases = listOf("building", "bina")),
        c("church", "Kilise", "Church", SymbolCategory.TOPOGRAPHY, SymbolIcon.LANDMARK, aliases = listOf("church", "kilise")),
        c("tower", "Kule", "Tower", SymbolCategory.TOPOGRAPHY, SymbolIcon.LANDMARK, aliases = listOf("tower", "kule")),
        c("chimney", "Baca", "Chimney", SymbolCategory.TOPOGRAPHY, SymbolIcon.LANDMARK, aliases = listOf("chimney", "baca")),
        c("windmill", "Yel değirmeni", "Windmill", SymbolCategory.TOPOGRAPHY, SymbolIcon.LANDMARK, aliases = listOf("windmill", "yel değirmeni")),
        c("water-tower", "Su kulesi", "Water tower", SymbolCategory.TOPOGRAPHY, SymbolIcon.LANDMARK, aliases = listOf("water tower", "su kulesi")),
        c("monument", "Anıt", "Monument", SymbolCategory.TOPOGRAPHY, SymbolIcon.LANDMARK, aliases = listOf("monument", "anıt")),
        c("ruins", "Harabe", "Ruins", SymbolCategory.TOPOGRAPHY, SymbolIcon.LANDMARK, aliases = listOf("ruins", "harabe")),
        c("fort", "Hisar / kale", "Fort", SymbolCategory.TOPOGRAPHY, SymbolIcon.LANDMARK, aliases = listOf("fort", "castle", "hisar", "kale")),
        c("cemetery", "Mezarlık", "Cemetery", SymbolCategory.TOPOGRAPHY, SymbolIcon.LAND, aliases = listOf("cemetery", "mezarlık")),
        c("road", "Karayolu", "Road", SymbolCategory.TOPOGRAPHY, SymbolIcon.ROAD, aliases = listOf("road", "karayolu")),
        c("railway", "Demiryolu", "Railway", SymbolCategory.TOPOGRAPHY, SymbolIcon.ROAD, aliases = listOf("railway", "rail", "demiryolu")),
        c("bridge", "Köprü", "Bridge", SymbolCategory.TOPOGRAPHY, SymbolIcon.BRIDGE, aliases = listOf("bridge", "köprü")),
        c("airport", "Havaalanı", "Airport / airfield", SymbolCategory.TOPOGRAPHY, SymbolIcon.AIRFIELD, aliases = listOf("airport", "airfield", "havaalanı")),

        // C. Hydrography, seabed and natural water features.
        c("intertidal", "Gelgit arası bölge", "Intertidal area", SymbolCategory.HYDROGRAPHY, SymbolIcon.DEPTH_AREA, aliases = listOf("intertidal", "foreshore", "gelgit")),
        c("drying-area", "Kurumaya kalan alan", "Drying area", SymbolCategory.HYDROGRAPHY, SymbolIcon.DRYING, aliases = listOf("drying area", "kuruma")),
        c("dredged-area", "Taranmış alan", "Dredged area", SymbolCategory.HYDROGRAPHY, SymbolIcon.DEPTH_AREA, aliases = listOf("dredged", "tarama")),
        c("seabed-sand", "Kum tabanı", "Sand", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, aliases = listOf("sand", "kum tabanı")),
        c("seabed-mud", "Çamur tabanı", "Mud", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, aliases = listOf("mud", "çamur")),
        c("seabed-clay", "Kil tabanı", "Clay", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, aliases = listOf("clay", "kil")),
        c("seabed-gravel", "Çakıl tabanı", "Gravel", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, aliases = listOf("gravel", "çakıl")),
        c("seabed-rock", "Kaya tabanı", "Rock seabed", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, aliases = listOf("rock seabed", "kaya tabanı")),
        c("seabed-coral", "Mercan tabanı", "Coral", SymbolCategory.HYDROGRAPHY, SymbolIcon.SEABED, aliases = listOf("coral", "mercan")),
        c("kelp", "Deniz yosunu", "Kelp / weed", SymbolCategory.HYDROGRAPHY, SymbolIcon.VEGETATION, aliases = listOf("kelp", "weed", "yosun")),
        c("breakers", "Kırılan dalgalar", "Breakers", SymbolCategory.HYDROGRAPHY, SymbolIcon.CURRENT, aliases = listOf("breakers", "sörf", "dalga")),
        c("rapids", "Çağlayan akıntı", "Rapids", SymbolCategory.HYDROGRAPHY, SymbolIcon.CURRENT, aliases = listOf("rapids", "rapid", "çağlayan")),
        c("waterfall", "Şelale", "Waterfall", SymbolCategory.HYDROGRAPHY, SymbolIcon.CURRENT, aliases = listOf("waterfall", "şelale")),
        c("current-arrow", "Akıntı oku", "Current arrow", SymbolCategory.HYDROGRAPHY, SymbolIcon.CURRENT, aliases = listOf("current", "current arrow", "akıntı")),
        c("tidal-stream", "Gelgit akıntısı", "Tidal stream", SymbolCategory.HYDROGRAPHY, SymbolIcon.CURRENT, aliases = listOf("tidal stream", "gelgit akıntısı")),
        c("tide-race", "Gelgit yarışı", "Tide race", SymbolCategory.HYDROGRAPHY, SymbolIcon.CURRENT, aliases = listOf("tide race", "race", "gelgit yarışı")),
        c("submarine-cable", "Denizaltı kablosu", "Submarine cable", SymbolCategory.HYDROGRAPHY, SymbolIcon.CABLE, aliases = listOf("cable", "submarine cable", "denizaltı kablosu")),
        c("submarine-pipeline", "Denizaltı boru hattı", "Submarine pipeline", SymbolCategory.HYDROGRAPHY, SymbolIcon.PIPELINE, aliases = listOf("pipeline", "boru hattı")),
        c("spoil-ground", "Döküntü sahası", "Spoil ground", SymbolCategory.HYDROGRAPHY, SymbolIcon.DUMPING, aliases = listOf("spoil ground", "döküntü")),
        c("fish-haven", "Balık barınağı", "Fish haven", SymbolCategory.HYDROGRAPHY, SymbolIcon.FARM, aliases = listOf("fish haven", "balık barınağı")),

        // Depth information and bottom notation.
        c("depth-area", "Derinlik alanı", "Depth area", SymbolCategory.DEPTH, SymbolIcon.DEPTH_AREA, aliases = listOf("depth area", "derinlik alanı")),
        c("drying-height", "Kuruma yüksekliği", "Drying height", SymbolCategory.DEPTH, SymbolIcon.DRYING, aliases = listOf("drying height", "kuruma yüksekliği")),
        c("depth-unknown", "Derinliği bilinmeyen alan", "Unsurveyed / unknown depth", SymbolCategory.DEPTH, SymbolIcon.DEPTH_AREA, aliases = listOf("unknown depth", "unsurveyed", "bilinmeyen derinlik")),
        c("least-depth", "En küçük derinlik", "Least depth", SymbolCategory.DEPTH, SymbolIcon.SOUNDING, aliases = listOf("least depth", "minimum depth", "en küçük derinlik")),
        c("depth-unit", "Derinlik birimi", "Depth unit", SymbolCategory.DEPTH, SymbolIcon.NOTE, aliases = listOf("unit", "metre", "fathom", "derinlik birimi")),

        // D. Lights, beacons, buoys and electronic aids to navigation.
        c("light-beacon", "Işıklı fener", "Lighted beacon", SymbolCategory.NAVIGATION, SymbolIcon.LIGHT_BEACON, aliases = listOf("beacon light", "ışıklı fener")),
        c("sector-light", "Sektör ışığı", "Sector light", SymbolCategory.NAVIGATION, SymbolIcon.SECTOR_LIGHT, aliases = listOf("sector", "sector light", "sektör")),
        c("leading-line", "Transit hattı", "Leading line", SymbolCategory.NAVIGATION, SymbolIcon.LEADING_LINE, aliases = listOf("leading line", "transit", "seyir hattı")),
        c("leading-beacon", "Transit fenerleri", "Leading beacons", SymbolCategory.NAVIGATION, SymbolIcon.LEADING_LINE, aliases = listOf("leading beacon", "transit feneri")),
        c("beacon", "Fener / işaret", "Beacon", SymbolCategory.NAVIGATION, SymbolIcon.BEACON, aliases = listOf("beacon", "fener")),
        c("daymark", "Gündüz işareti", "Daymark", SymbolCategory.NAVIGATION, SymbolIcon.BEACON, aliases = listOf("daymark", "gündüz işareti")),
        c("cardinal-buoy", "Kardinal şamandıra", "Cardinal mark / Buoy", SymbolCategory.NAVIGATION, SymbolIcon.BUOY_CARDINAL, aliases = listOf("cardinal", "cardinal buoy", "kardinal")),
        c("isolated-danger-buoy", "İzole tehlike şamandırası", "Isolated danger mark", SymbolCategory.NAVIGATION, SymbolIcon.BUOY_ISOLATED_DANGER, aliases = listOf("isolated danger", "izole tehlike")),
        c("safe-water-buoy", "Emniyetli su şamandırası", "Safe water mark", SymbolCategory.NAVIGATION, SymbolIcon.BUOY_SAFE_WATER, aliases = listOf("safe water", "safe water buoy", "emniyetli su")),
        c("special-mark-buoy", "Özel mark şamandırası", "Special mark", SymbolCategory.NAVIGATION, SymbolIcon.BUOY_SPECIAL, aliases = listOf("special mark", "özel mark")),
        c("mooring-buoy", "Bağlama şamandırası", "Mooring buoy", SymbolCategory.NAVIGATION, SymbolIcon.BUOY_SPECIAL, aliases = listOf("mooring buoy", "bağlama şamandırası")),
        c("light-float", "Işıklı şamandıra / lightship", "Light float / Light vessel", SymbolCategory.NAVIGATION, SymbolIcon.LIGHT_FLOAT, aliases = listOf("light float", "lightship", "ışıklı şamandıra")),
        c("fog-signal", "Sis işareti", "Fog signal", SymbolCategory.NAVIGATION, SymbolIcon.SERVICE, aliases = listOf("fog signal", "sis düdüğü", "sis işareti")),
        c("racon", "Racon", "Radar beacon / RACON", SymbolCategory.NAVIGATION, SymbolIcon.RADAR_AID, aliases = listOf("racon", "radar beacon")),
        c("radar-conspicuous", "Radar için belirgin nesne", "Radar conspicuous", SymbolCategory.NAVIGATION, SymbolIcon.RADAR_AID, aliases = listOf("radar conspicuous", "radar")),
        c("ais-aton", "AIS seyir yardımcısı", "AIS aid to navigation", SymbolCategory.NAVIGATION, SymbolIcon.RADAR_AID, aliases = listOf("AIS AtoN", "AIS", "electronic aid")),
        c("virtual-aton", "Sanal seyir yardımcısı", "Virtual aid to navigation", SymbolCategory.NAVIGATION, SymbolIcon.RADAR_AID, aliases = listOf("virtual AtoN", "virtual aid", "sanal")),
        c("coast-light", "Kıyı ışığı", "Coast light", SymbolCategory.NAVIGATION, SymbolIcon.LIGHT, aliases = listOf("coast light", "kıyı ışığı")),
        c("directional-light", "Yönlü ışık", "Directional light", SymbolCategory.NAVIGATION, SymbolIcon.SECTOR_LIGHT, aliases = listOf("directional light", "yönlü ışık")),
        c("prominent-light", "Belirgin ışık", "Prominent light", SymbolCategory.NAVIGATION, SymbolIcon.LIGHT, aliases = listOf("prominent light", "belirgin ışık")),
        c("signal-station", "Sinyal istasyonu", "Signal station", SymbolCategory.NAVIGATION, SymbolIcon.SERVICE, aliases = listOf("signal station", "sinyal istasyonu")),

        // E. Dangers and obstructions.
        c("dangerous-wreck", "Tehlikeli batık", "Wreck with danger", SymbolCategory.HAZARDS, SymbolIcon.WRECK, aliases = listOf("dangerous wreck", "tehlikeli batık")),
        c("rock-awash", "Su seviyesindeki kaya", "Rock awash", SymbolCategory.HAZARDS, SymbolIcon.ROCK, aliases = listOf("rock awash", "su seviyesinde kaya")),
        c("underwater-rock", "Su altı kayası", "Underwater rock", SymbolCategory.HAZARDS, SymbolIcon.ROCK, aliases = listOf("underwater rock", "submerged rock", "su altı kayası")),
        c("foul-ground", "Temizlenmemiş tehlikeli zemin", "Foul ground", SymbolCategory.HAZARDS, SymbolIcon.FOUL_GROUND, aliases = listOf("foul ground", "foul", "tehlikeli zemin")),
        c("obstruction", "Sualtı engeli", "Obstruction", SymbolCategory.HAZARDS, SymbolIcon.OBSTRUCTION, aliases = listOf("obstruction", "engel")),
        c("submerged-obstruction", "Batık engel", "Submerged obstruction", SymbolCategory.HAZARDS, SymbolIcon.OBSTRUCTION, aliases = listOf("submerged obstruction", "batık engel")),
        c("mine", "Mayın sahası / mayın", "Mine", SymbolCategory.HAZARDS, SymbolIcon.MINE, aliases = listOf("mine", "mayın")),
        c("pipeline-danger", "Boru hattı tehlikesi", "Pipeline danger", SymbolCategory.HAZARDS, SymbolIcon.PIPELINE, aliases = listOf("pipeline danger", "boru hattı tehlikesi")),
        c("cable-danger", "Kablo tehlikesi", "Cable danger", SymbolCategory.HAZARDS, SymbolIcon.CABLE, aliases = listOf("cable danger", "kablo tehlikesi")),
        c("dangerous-area", "Tehlike alanı", "Danger area", SymbolCategory.HAZARDS, SymbolIcon.RESTRICTED, aliases = listOf("danger area", "tehlike alanı")),

        // F. Areas, prohibitions and regulated zones.
        c("prohibited-anchorage", "Demirlemenin yasak olduğu alan", "Anchoring prohibited", SymbolCategory.AREAS, SymbolIcon.NO_ANCHOR, aliases = listOf("anchoring prohibited", "prohibited anchorage", "demir yasak")),
        c("no-anchor", "Demirleme yasak işareti", "No anchoring", SymbolCategory.AREAS, SymbolIcon.NO_ANCHOR, aliases = listOf("no anchoring", "no anchor", "demirleme yasak")),
        c("no-fishing", "Balıkçılık yasak alanı", "Fishing prohibited", SymbolCategory.AREAS, SymbolIcon.RESTRICTED, aliases = listOf("no fishing", "fishing prohibited", "balıkçılık yasak")),
        c("no-landing", "Karaya çıkma yasak alanı", "Landing prohibited", SymbolCategory.AREAS, SymbolIcon.RESTRICTED, aliases = listOf("no landing", "karaya çıkma yasak")),
        c("military-area", "Askerî uygulama alanı", "Military practice area", SymbolCategory.AREAS, SymbolIcon.RESTRICTED, aliases = listOf("military", "exercise area", "askerî alan")),
        c("dumping-ground", "Döküm alanı", "Dumping ground", SymbolCategory.AREAS, SymbolIcon.DUMPING, aliases = listOf("dumping ground", "dumping", "döküm alanı")),
        c("marine-farm", "Deniz çiftliği", "Marine farm", SymbolCategory.AREAS, SymbolIcon.FARM, aliases = listOf("marine farm", "deniz çiftliği")),
        c("fish-farm", "Balık çiftliği", "Fish farm", SymbolCategory.AREAS, SymbolIcon.FARM, aliases = listOf("fish farm", "balık çiftliği")),
        c("nature-reserve", "Deniz koruma alanı", "Nature reserve", SymbolCategory.AREAS, SymbolIcon.RESTRICTED, aliases = listOf("nature reserve", "marine protected area", "koruma alanı")),
        c("diving-area", "Dalış alanı", "Diving area", SymbolCategory.AREAS, SymbolIcon.RESTRICTED, aliases = listOf("diving area", "dalış alanı")),
        c("cable-area", "Kablo alanı", "Cable area", SymbolCategory.AREAS, SymbolIcon.CABLE, aliases = listOf("cable area", "kablo alanı")),
        c("pipeline-area", "Boru hattı alanı", "Pipeline area", SymbolCategory.AREAS, SymbolIcon.PIPELINE, aliases = listOf("pipeline area", "boru hattı alanı")),
        c("construction-area", "İnşaat / çalışma alanı", "Works in progress", SymbolCategory.AREAS, SymbolIcon.RESTRICTED, aliases = listOf("works", "construction", "inşaat")),
        c("offshore-platform", "Açık deniz platformu", "Offshore installation", SymbolCategory.AREAS, SymbolIcon.RESTRICTED, aliases = listOf("offshore platform", "platform")),
        c("aquaculture", "Akuakültür alanı", "Aquaculture", SymbolCategory.AREAS, SymbolIcon.FARM, aliases = listOf("aquaculture", "akuakültür")),
        c("recreation-area", "Rekreasyon alanı", "Recreation area", SymbolCategory.AREAS, SymbolIcon.RESTRICTED, aliases = listOf("recreation", "rekreasyon")),
        c("seaplane-area", "Deniz uçağı alanı", "Seaplane area", SymbolCategory.AREAS, SymbolIcon.RESTRICTED, aliases = listOf("seaplane", "deniz uçağı")),
        c("quarantine-area", "Karantina alanı", "Quarantine anchorage", SymbolCategory.AREAS, SymbolIcon.ANCHORAGE, aliases = listOf("quarantine", "karantina")),

        // M. Tracks, routes and traffic management.
        c("traffic-separation-scheme", "Trafik ayırım düzeni", "Traffic separation scheme", SymbolCategory.ROUTES, SymbolIcon.TRAFFIC_SEPARATION, aliases = listOf("TSS", "traffic separation", "trafik ayırım")),
        c("separation-zone", "Ayırım bölgesi", "Separation zone", SymbolCategory.ROUTES, SymbolIcon.TRAFFIC_SEPARATION, aliases = listOf("separation zone", "ayırım bölgesi")),
        c("inshore-traffic-zone", "Kıyı içi trafik bölgesi", "Inshore traffic zone", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("inshore traffic", "kıyı içi trafik")),
        c("deep-water-route", "Derin su rotası", "Deep water route", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("deep water route", "derin su rotası")),
        c("recommended-track", "Tavsiye edilen rota", "Recommended track", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("recommended track", "tavsiye rota")),
        c("fairway", "Seyir kanalı", "Fairway", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("fairway", "channel", "seyir kanalı")),
        c("ferry-route", "Feribot rotası", "Ferry route", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("ferry", "ferry route", "feribot")),
        c("one-way-route", "Tek yönlü rota", "One-way route", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("one way", "tek yön")),
        c("two-way-route", "İki yönlü rota", "Two-way route", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("two way", "iki yön")),
        c("roundabout", "Trafik dönüş alanı", "Roundabout", SymbolCategory.ROUTES, SymbolIcon.TRAFFIC_SEPARATION, aliases = listOf("roundabout", "dönüş alanı")),
        c("crossing-route", "Kesişen rota", "Crossing route", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("crossing", "kesişme")),
        c("joining-route", "Birleşen rota", "Joining route", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("joining", "birleşme")),
        c("precautionary-area", "Tedbir alanı", "Precautionary area", SymbolCategory.ROUTES, SymbolIcon.RESTRICTED, aliases = listOf("precautionary", "tedbir alanı")),
        c("archipelagic-route", "Takımada deniz yolu", "Archipelagic sea lane", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("archipelagic", "takımada")),
        c("routeing-measure", "Rota düzenleme tedbiri", "Routeing measure", SymbolCategory.ROUTES, SymbolIcon.ROUTE, aliases = listOf("routeing", "route measure", "rota düzenleme")),

        // Ports, harbour infrastructure and shore facilities.
        c("harbour", "Liman / barınak", "Harbour", SymbolCategory.PORTS, SymbolIcon.PORT, aliases = listOf("harbor", "harbour", "liman")),
        c("port-limit", "Liman sınırı", "Port limit", SymbolCategory.PORTS, SymbolIcon.PORT, aliases = listOf("port limit", "liman sınırı")),
        c("quay", "Rıhtım", "Quay", SymbolCategory.PORTS, SymbolIcon.QUAY, aliases = listOf("quay", "rıhtım")),
        c("pier", "İskele", "Pier", SymbolCategory.PORTS, SymbolIcon.QUAY, aliases = listOf("pier", "iskele")),
        c("jetty", "Dalgakıran iskelesi", "Jetty", SymbolCategory.PORTS, SymbolIcon.QUAY, aliases = listOf("jetty", "jetty", "iskele")),
        c("berth", "Gemi yanaşma yeri", "Berth", SymbolCategory.PORTS, SymbolIcon.QUAY, aliases = listOf("berth", "yanaşma yeri")),
        c("marina", "Marina", "Marina", SymbolCategory.PORTS, SymbolIcon.MARINA, aliases = listOf("marina", "yat limanı")),
        c("dry-dock", "Kuru havuz", "Dry dock", SymbolCategory.PORTS, SymbolIcon.LOCK, aliases = listOf("dry dock", "kuru havuz")),
        c("slipway", "Kızak", "Slipway", SymbolCategory.PORTS, SymbolIcon.LOCK, aliases = listOf("slipway", "kızak")),
        c("lock", "Deniz kilidi", "Lock", SymbolCategory.PORTS, SymbolIcon.LOCK, aliases = listOf("lock", "kilit")),
        c("dock-gate", "Havuz kapısı", "Dock gate", SymbolCategory.PORTS, SymbolIcon.LOCK, aliases = listOf("dock gate", "havuz kapısı")),
        c("crane", "Vinç", "Crane", SymbolCategory.PORTS, SymbolIcon.CRANE, aliases = listOf("crane", "vinç")),
        c("shipyard", "Tersane", "Shipyard", SymbolCategory.PORTS, SymbolIcon.PORT, aliases = listOf("shipyard", "tersane")),
        c("bunkering", "Yakıt ikmal yeri", "Bunkering place", SymbolCategory.PORTS, SymbolIcon.SERVICE, aliases = listOf("bunkering", "fuel", "yakıt")),
        c("mooring", "Bağlama yeri", "Mooring", SymbolCategory.PORTS, SymbolIcon.QUAY, aliases = listOf("mooring", "bağlama")),
        c("turning-basin", "Dönüş havuzu", "Turning basin", SymbolCategory.PORTS, SymbolIcon.PORT, aliases = listOf("turning basin", "dönüş havuzu")),
        c("breakwater", "Dalgakıran", "Breakwater", SymbolCategory.PORTS, SymbolIcon.PORT, aliases = listOf("breakwater", "dalgakıran")),
        c("terminal", "Terminal", "Terminal", SymbolCategory.PORTS, SymbolIcon.PORT, aliases = listOf("terminal")),
        c("boat-ramp", "Tekne rampası", "Boat ramp", SymbolCategory.PORTS, SymbolIcon.QUAY, aliases = listOf("boat ramp", "rampa")),

        // Services and reporting facilities.
        c("pilot-station", "Kılavuz kaptan istasyonu", "Pilot station", SymbolCategory.SERVICES, SymbolIcon.PILOT, aliases = listOf("pilot station", "pilotaj")),
        c("pilot-boarding-place", "Kılavuz kaptan alma yeri", "Pilot boarding place", SymbolCategory.SERVICES, SymbolIcon.PILOT, aliases = listOf("pilot boarding", "pilot alma")),
        c("rescue-station", "Kurtarma istasyonu", "Rescue station", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("rescue station", "kurtarma")),
        c("coast-guard", "Sahil güvenlik", "Coast guard station", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("coast guard", "sahil güvenlik")),
        c("customs", "Gümrük", "Customs", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("customs", "gümrük")),
        c("port-control", "Liman kontrolü", "Port control", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("port control", "liman kontrol")),
        c("vts", "VTS merkezi", "Vessel traffic service", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("VTS", "vessel traffic", "gemi trafik hizmeti")),
        c("radio-station", "Deniz telsiz istasyonu", "Marine radio station", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("radio station", "telsiz")),
        c("storm-signal", "Fırtına sinyal istasyonu", "Storm signal station", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("storm signal", "fırtına")),
        c("fog-signal-station", "Sis sinyal istasyonu", "Fog signal station", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("fog signal station", "sis istasyonu")),
        c("lifeboat-station", "Cankurtarma istasyonu", "Lifeboat station", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("lifeboat station", "cankurtarma")),
        c("ferry-terminal", "Feribot terminali", "Ferry terminal", SymbolCategory.SERVICES, SymbolIcon.PORT, aliases = listOf("ferry terminal", "feribot terminali")),
        c("anchorage-control", "Demirleme kontrol noktası", "Anchorage control", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("anchorage control", "demirleme kontrol")),
        c("reporting-point", "Raporlama noktası", "Reporting point", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("reporting point", "raporlama")),
        c("restricted-service", "Özel hizmet alanı", "Special service area", SymbolCategory.SERVICES, SymbolIcon.SERVICE, aliases = listOf("special service", "özel hizmet"))
    )

    fun find(id: String): ChartSymbol = symbols.firstOrNull { it.id == id } ?: symbols.first()

    fun filter(query: String, category: SymbolCategory): List<ChartSymbol> {
        val normalizedQuery = query.trim().lowercase()
        return symbols.filter { symbol ->
            val matchesCategory = category == SymbolCategory.ALL || symbol.category == category
            val searchableText = listOf(
                symbol.id,
                symbol.title,
                symbol.englishName,
                symbol.summary,
                symbol.meaning,
                symbol.recognition,
                symbol.chartNotation.orEmpty(),
                symbol.aliases.joinToString(" ")
            )
            val matchesQuery = normalizedQuery.isBlank() || searchableText.any {
                it.lowercase().contains(normalizedQuery)
            }
            matchesCategory && matchesQuery
        }
    }

    private fun p(
        id: String,
        title: String,
        englishName: String,
        category: SymbolCategory,
        icon: SymbolIcon,
        notation: String? = null,
        summary: String? = null,
        meaning: String? = null,
        recognition: String? = null,
        memoryTip: String? = null,
        aliases: List<String> = emptyList()
    ): ChartSymbol = c(
        id = id,
        title = title,
        englishName = englishName,
        category = category,
        icon = icon,
        summary = summary,
        meaning = meaning,
        recognition = recognition,
        memoryTip = memoryTip,
        aliases = aliases,
        chartNotation = notation,
        referenceFamily = PDF_REFERENCE
    )

    private fun c(
        id: String,
        title: String,
        englishName: String,
        category: SymbolCategory,
        icon: SymbolIcon,
        summary: String? = null,
        meaning: String? = null,
        recognition: String? = null,
        memoryTip: String? = null,
        aliases: List<String> = emptyList(),
        chartNotation: String? = null,
        referenceFamily: String = "IHO INT 1 / Chart No. 1"
    ): ChartSymbol = ChartSymbol(
        id = id,
        title = title,
        englishName = englishName,
        category = category,
        icon = icon,
        accent = accentFor(category),
        summary = summary ?: "$title işaretini temel kartografik biçimiyle tanı.",
        meaning = meaning ?: "$title, deniz haritasında ilgili fiziksel özellik veya operasyonel bilgiyi gösterir. Sembol; renk, çizgi, alan deseni ve eşlik eden yazıyla birlikte okunmalıdır.",
        recognition = recognition ?: "${englishName} için kullanılan temel çizgi, nokta, alan deseni veya kısa etiketi ara.",
        memoryTip = memoryTip ?: "Önce kategoriyi, sonra şekli ve son olarak renk/etiketi kontrol et.",
        aliases = aliases,
        chartNotation = chartNotation,
        referenceFamily = referenceFamily
    )

    private fun accentFor(category: SymbolCategory): SymbolAccent = when (category) {
        SymbolCategory.GENERAL -> SymbolAccent.VIOLET
        SymbolCategory.TOPOGRAPHY -> SymbolAccent.GREEN
        SymbolCategory.HYDROGRAPHY, SymbolCategory.DEPTH -> SymbolAccent.BLUE
        SymbolCategory.NAVIGATION -> SymbolAccent.AMBER
        SymbolCategory.HAZARDS -> SymbolAccent.CORAL
        SymbolCategory.AREAS, SymbolCategory.ROUTES -> SymbolAccent.VIOLET
        SymbolCategory.PORTS, SymbolCategory.SERVICES -> SymbolAccent.TEAL
        else -> SymbolAccent.TEAL
    }
}
