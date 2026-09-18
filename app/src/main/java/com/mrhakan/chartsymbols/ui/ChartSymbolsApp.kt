package com.mrhakan.chartsymbols.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrhakan.chartsymbols.data.ChartSymbol
import com.mrhakan.chartsymbols.data.QuizBuilder
import com.mrhakan.chartsymbols.data.StudyDeck
import com.mrhakan.chartsymbols.data.SymbolCatalog
import com.mrhakan.chartsymbols.data.SymbolCategory

private const val HOME = "home"
private const val EXPLORE = "explore"
private const val STUDY = "study"
private const val QUIZ = "quiz"

@Composable
fun ChartSymbolsApp() {
    var tab by rememberSaveable { mutableStateOf(HOME) }
    var detailSymbolId by rememberSaveable { mutableStateOf<String?>(null) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedCategoryName by rememberSaveable { mutableStateOf(SymbolCategory.ALL.name) }
    var learnedIds by remember { mutableStateOf(emptySet<String>()) }
    var bookmarkedIds by remember { mutableStateOf(emptySet<String>()) }
    var quizDeckName by rememberSaveable { mutableStateOf<String?>(null) }

    val quizDeck = quizDeckName?.let { StudyDeck.valueOf(it) }

    val selectedCategory = SymbolCategory.valueOf(selectedCategoryName)
    val visibleSymbols = SymbolCatalog.filter(searchQuery, selectedCategory)
    val homeSymbols = visibleSymbols.take(10)

    if (detailSymbolId != null) {
        SymbolDetailScreen(
            symbol = SymbolCatalog.find(detailSymbolId!!),
            isBookmarked = detailSymbolId?.let { it in bookmarkedIds } == true,
            onBack = { detailSymbolId = null },
            onToggleBookmark = {
                detailSymbolId?.let { id ->
                    bookmarkedIds = if (id in bookmarkedIds) bookmarkedIds - id else bookmarkedIds + id
                }
            },
            onStartQuiz = {
                detailSymbolId = null
                tab = QUIZ
            }
        )
        return
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            AppBottomBar(
                selectedTab = tab,
                onTabSelected = { tab = it }
            )
        }
    ) { innerPadding ->
        when (tab) {
            HOME -> HomeScreen(
                modifier = Modifier.padding(innerPadding),
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                selectedCategory = selectedCategory,
                onCategoryChange = { selectedCategoryName = it.name },
                visibleSymbols = homeSymbols,
                learnedCount = learnedIds.size,
                onOpenSymbol = { symbol ->
                    learnedIds = learnedIds + symbol.id
                    detailSymbolId = symbol.id
                },
                onOpenExplore = { tab = EXPLORE },
                onOpenQuiz = { tab = QUIZ }
            )

            EXPLORE -> ExploreScreen(
                modifier = Modifier.padding(innerPadding),
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                selectedCategory = selectedCategory,
                onCategoryChange = { selectedCategoryName = it.name },
                visibleSymbols = visibleSymbols,
                onOpenSymbol = { symbol ->
                    learnedIds = learnedIds + symbol.id
                    detailSymbolId = symbol.id
                }
            )

            STUDY -> StudyScreen(
                modifier = Modifier.padding(innerPadding),
                onOpenSymbol = { symbol ->
                    learnedIds = learnedIds + symbol.id
                    detailSymbolId = symbol.id
                },
                onQuizDeck = { deck ->
                    quizDeckName = deck.name
                    tab = QUIZ
                }
            )

            QUIZ -> QuizScreen(
                modifier = Modifier.padding(innerPadding),
                deck = quizDeck,
                onDeckChange = { quizDeckName = it?.name },
                onOpenSymbol = { symbol ->
                    learnedIds = learnedIds + symbol.id
                    detailSymbolId = symbol.id
                }
            )
        }
    }
}

@Composable
private fun HomeScreen(
    modifier: Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedCategory: SymbolCategory,
    onCategoryChange: (SymbolCategory) -> Unit,
    visibleSymbols: List<ChartSymbol>,
    learnedCount: Int,
    onOpenSymbol: (ChartSymbol) -> Unit,
    onOpenExplore: () -> Unit,
    onOpenQuiz: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { HomeHeader() }
        item {
            HeroCard(
                learnedCount = learnedCount,
                totalCount = SymbolCatalog.symbols.size,
                onOpenQuiz = onOpenQuiz
            )
        }
        item {
            SearchField(
                value = searchQuery,
                onValueChange = onSearchQueryChange
            )
        }
        item {
            CategoryRow(
                selected = selectedCategory,
                onSelected = onCategoryChange
            )
        }
        item {
            SectionHeader(
                title = "Chart 1 sembol kataloğu",
                subtitle = "Gör, oku, hatırla",
                action = "Tümünü gör",
                onAction = onOpenExplore
            )
        }
        if (visibleSymbols.isEmpty()) {
            item { EmptyState() }
        } else {
            items(visibleSymbols.chunked(2)) { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    row.forEach { symbol ->
                        SymbolCard(
                            symbol = symbol,
                            modifier = Modifier.weight(1f),
                            onClick = { onOpenSymbol(symbol) }
                        )
                    }
                    if (row.size == 1) Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        item {
            Text(
                text = "IHO INT 1 / Chart No. 1 ve Ezberlenecek.pdf çalışma kartlarından özgün vektör yeniden çizimleri — seyir için güncel resmî haritayı ve gemi prosedürlerini kontrol et.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
private fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Chart Symbols",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Deniz haritasını görerek öğren",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Icon(
                imageVector = Icons.Outlined.MenuBook,
                contentDescription = "Kütüphane",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(11.dp)
            )
        }
    }
}

@Composable
private fun HeroCard(
    learnedCount: Int,
    totalCount: Int,
    onOpenQuiz: () -> Unit
) {
    val progress = (learnedCount.toFloat() / totalCount.coerceAtLeast(1)).coerceIn(0f, 1f)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFF0E7490), Color(0xFF0B3B50))
                    )
                )
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Güverteye hazır mısın?",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Sembolleri hızlıca tanı, sonra kısa quiz ile kendini dene.",
                            color = Color.White.copy(alpha = 0.78f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD166),
                        modifier = Modifier.size(34.dp)
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "$learnedCount / $totalCount kart keşfedildi",
                            color = Color.White.copy(alpha = 0.85f),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "${(progress * 100).toInt()}%",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(50)),
                        color = Color(0xFFFFD166),
                        trackColor = Color.White.copy(alpha = 0.20f)
                    )
                }
                Button(
                    onClick = onOpenQuiz,
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF0B3B50)
                    )
                ) {
                    Icon(Icons.Outlined.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(7.dp))
                    Text("Görsel quiz başlat", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun SearchField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(17.dp),
        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Ara") },
        trailingIcon = if (value.isNotEmpty()) {
            { IconButton(onClick = { onValueChange("") }) { Icon(Icons.Outlined.Close, contentDescription = "Temizle") } }
        } else null,
        placeholder = { Text("Sembol, İngilizce adı, kısaltma veya anlam ara") }
    )
}

@Composable
private fun CategoryRow(
    selected: SymbolCategory,
    onSelected: (SymbolCategory) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(SymbolCategory.entries) { category ->
            FilterChip(
                selected = selected == category,
                onClick = { onSelected(category) },
                label = { Text(category.label) }
            )
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    subtitle: String,
    action: String,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        TextButton(onClick = onAction) {
            Text(action)
            Icon(Icons.Outlined.ChevronRight, contentDescription = null, modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
private fun SymbolCard(
    symbol: ChartSymbol,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SymbolIllustration(
                symbol = symbol,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                    text = symbol.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = symbol.englishName,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun ExploreScreen(
    modifier: Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedCategory: SymbolCategory,
    onCategoryChange: (SymbolCategory) -> Unit,
    visibleSymbols: List<ChartSymbol>,
    onOpenSymbol: (ChartSymbol) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text("Sembol kütüphanesi", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                Text("Kategorilere göre filtrele ve bir sembolün ayrıntılarını aç.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        item { SearchField(searchQuery, onSearchQueryChange) }
        item { CategoryRow(selectedCategory, onCategoryChange) }
        if (visibleSymbols.isEmpty()) {
            item { EmptyState() }
        } else {
            items(visibleSymbols) { symbol ->
                LibraryRow(symbol = symbol, onClick = { onOpenSymbol(symbol) })
            }
        }
    }
}

@Composable
private fun LibraryRow(symbol: ChartSymbol, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            SymbolIllustration(symbol, Modifier.size(82.dp))
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(symbol.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(symbol.englishName, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                Text(symbol.summary, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
            Icon(Icons.Outlined.ChevronRight, contentDescription = "Aç", tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SymbolDetailScreen(
    symbol: ChartSymbol,
    isBookmarked: Boolean,
    onBack: () -> Unit,
    onToggleBookmark: () -> Unit,
    onStartQuiz: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sembol detayı") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, contentDescription = "Geri") }
                },
                actions = {
                    IconButton(onClick = onToggleBookmark) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = if (isBookmarked) "Kaydı kaldır" else "Kaydet"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SymbolIllustration(
                    symbol = symbol,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(symbol.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)
                    Text(symbol.englishName, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(symbol.category.label, modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp), style = MaterialTheme.typography.labelMedium)
                        }
                        symbol.deck?.let { deck ->
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(50)
                            ) {
                                Text(
                                    text = "Ezberlenecek · ${deck.label}",
                                    modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }
                    symbol.chartNotation?.let { notation ->
                        Text(
                            text = "Harita gösterimi: $notation",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            item { InfoBlock(title = "Ne anlatır?", icon = Icons.Outlined.Explore, body = symbol.meaning) }
            item { InfoBlock(title = "Nasıl tanınır?", icon = Icons.Outlined.Search, body = symbol.recognition) }
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Icon(Icons.Outlined.Lightbulb, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("Akılda tut", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Text(symbol.memoryTip, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                    }
                }
            }
            item {
                Text(
                    text = "Kaynak ailesi: ${symbol.referenceFamily}. Görsel, resmî yayından kopya değildir; öğrenme amaçlı vektör yeniden çizimidir ve seyir için kullanılamaz.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            item {
                Button(onClick = onStartQuiz, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Outlined.Quiz, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Bu sembolle quiz yap", fontWeight = FontWeight.Bold)
                }
            }
            item { Spacer(Modifier.height(12.dp)) }
        }
    }
}

@Composable
private fun InfoBlock(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    body: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
        Text(body, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 24.sp)
    }
}

@Composable
private fun QuizScreen(
    modifier: Modifier,
    deck: StudyDeck?,
    onDeckChange: (StudyDeck?) -> Unit,
    onOpenSymbol: (ChartSymbol) -> Unit
) {
    // Bumped on every restart so a new round reshuffles instead of replaying
    // the same questions in the same order.
    var round by rememberSaveable { mutableStateOf(0) }
    var questionIndex by rememberSaveable { mutableStateOf(0) }
    var selectedOption by rememberSaveable { mutableStateOf<String?>(null) }
    var score by rememberSaveable { mutableStateOf(0) }
    var finished by rememberSaveable { mutableStateOf(false) }

    val questions = remember(deck, round) {
        QuizBuilder.build(if (deck == null) SymbolCatalog.symbols else SymbolCatalog.deck(deck))
    }
    if (questions.isEmpty()) {
        EmptyState()
        return
    }
    val current = questions[questionIndex.coerceIn(0, questions.lastIndex)]
    val options = current.options

    val restart = {
        questionIndex = 0
        selectedOption = null
        score = 0
        finished = false
        round += 1
    }

    if (finished) {
        QuizResult(
            score = score,
            total = questions.size,
            modifier = modifier,
            onRestart = restart,
            onOpenSymbol = { onOpenSymbol(current.symbol) }
        )
        return
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Görsel quiz", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                    Text(
                        text = deck?.label ?: "Tüm katalog",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(50)) {
                    Text("${questionIndex + 1} / ${questions.size}", modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        }
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    FilterChip(
                        selected = deck == null,
                        onClick = {
                            onDeckChange(null)
                            restart()
                        },
                        label = { Text("Tüm katalog") }
                    )
                }
                items(StudyDeck.sheetOrder) { option ->
                    FilterChip(
                        selected = deck == option,
                        onClick = {
                            onDeckChange(option)
                            restart()
                        },
                        label = { Text(option.label) }
                    )
                }
            }
        }
        item {
            LinearProgressIndicator(
                progress = (questionIndex + 1).toFloat() / questions.size,
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(50))
            )
        }
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(26.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    SymbolIllustration(current.symbol, Modifier.fillMaxWidth().height(230.dp))
                    Text("Bu sembol neyi anlatıyor?", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
        items(options) { option ->
            val isSelected = selectedOption == option
            val isCorrect = option == current.answer
            val background = when {
                selectedOption == null -> MaterialTheme.colorScheme.surface
                isCorrect -> Color(0xFFDDF5E4)
                isSelected -> Color(0xFFFFE3DD)
                else -> MaterialTheme.colorScheme.surface
            }
            val borderColor = when {
                selectedOption == null -> MaterialTheme.colorScheme.outlineVariant
                isCorrect -> Color(0xFF2F855A)
                isSelected -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.outlineVariant
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, borderColor, RoundedCornerShape(17.dp))
                    .clip(RoundedCornerShape(17.dp))
                    .clickable(enabled = selectedOption == null) { selectedOption = option },
                color = background,
                shape = RoundedCornerShape(17.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(option, modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
                    if (selectedOption != null && isCorrect) Icon(Icons.Outlined.CheckCircle, contentDescription = "Doğru", tint = Color(0xFF2F855A))
                    if (selectedOption != null && isSelected && !isCorrect) Icon(Icons.Outlined.Close, contentDescription = "Yanlış", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
        item {
            if (selectedOption != null) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = if (selectedOption == current.answer) {
                            "Doğru! ${current.symbol.memoryTip}"
                        } else {
                            "Doğru cevap: ${current.answer} — ${current.symbol.memoryTip}"
                        },
                        color = if (selectedOption == current.answer) Color(0xFF2F855A) else MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Button(
                        onClick = {
                            if (selectedOption == current.answer) score += 1
                            if (questionIndex == questions.lastIndex) {
                                finished = true
                            } else {
                                questionIndex += 1
                                selectedOption = null
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (questionIndex == questions.lastIndex) "Sonucu gör" else "Sonraki soru")
                        Spacer(Modifier.width(8.dp))
                        Icon(Icons.Outlined.ArrowForward, contentDescription = null)
                    }
                }
            } else {
                Text("Bir cevap seçerek devam et.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun QuizResult(
    score: Int,
    total: Int,
    modifier: Modifier,
    onRestart: () -> Unit,
    onOpenSymbol: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(30.dp)) {
            Icon(Icons.Outlined.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(24.dp).size(54.dp))
        }
        Spacer(Modifier.height(20.dp))
        Text("İyi iş, vardiya tamamlandı!", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.height(8.dp))
        Text("$total soruda $score doğru cevap", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(10.dp))
        Text("Kartları tekrar ederek sembolleri daha hızlı tanıyabilirsin.", textAlign = androidx.compose.ui.text.style.TextAlign.Center, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onRestart, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Outlined.Refresh, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Quiz’i yeniden başlat")
        }
        TextButton(onClick = onOpenSymbol) {
            Icon(Icons.Outlined.MenuBook, contentDescription = null)
            Spacer(Modifier.width(6.dp))
            Text("Bir kartı tekrar aç")
        }
    }
}

/**
 * The `Ezberlenecek.pdf` study sheet, shown section by section in sheet order
 * so the deck can be revised the same way the sheet is laid out.
 */
@Composable
private fun StudyScreen(
    modifier: Modifier,
    onOpenSymbol: (ChartSymbol) -> Unit,
    onQuizDeck: (StudyDeck) -> Unit
) {
    val decks = remember { SymbolCatalog.decks() }
    var openDeckName by rememberSaveable { mutableStateOf(StudyDeck.sheetOrder.first().name) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Ezberlenecek", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                Text(
                    text = "Çalışma sayfasındaki ${SymbolCatalog.studySheetSymbols.size} kart, sayfadaki sırasıyla.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        decks.forEach { (deck, cards) ->
            item(key = deck.name) {
                val expanded = openDeckName == deck.name
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { openDeckName = if (expanded) "" else deck.name },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                                Text(deck.label, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                Text(
                                    text = "${cards.size} kart",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Icon(
                                imageVector = if (expanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                                contentDescription = if (expanded) "Daralt" else "Genişlet"
                            )
                        }

                        if (expanded) {
                            Text(
                                text = deck.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            cards.forEach { symbol ->
                                Divider(color = MaterialTheme.colorScheme.outlineVariant)
                                LibraryRow(symbol = symbol, onClick = { onOpenSymbol(symbol) })
                            }
                            Button(onClick = { onQuizDeck(deck) }, modifier = Modifier.fillMaxWidth()) {
                                Icon(Icons.Outlined.Quiz, contentDescription = null)
                                Spacer(Modifier.width(8.dp))
                                Text("Bu desteyi çöz", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Kaynak: kullanıcı tarafından sağlanan Ezberlenecek.pdf çalışma sayfası. Görseller öğrenme amaçlı özgün vektör yeniden çizimlerdir; resmî yayın kopyası değildir ve seyir için kullanılamaz.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        item { Spacer(Modifier.height(12.dp)) }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(Icons.Outlined.Search, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(36.dp))
        Text("Eşleşen sembol bulunamadı", fontWeight = FontWeight.Bold)
        Text("Arama kelimesini veya filtreyi değiştir.", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun AppBottomBar(selectedTab: String, onTabSelected: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedTab == HOME,
            onClick = { onTabSelected(HOME) },
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Ana sayfa") },
            label = { Text("Ana sayfa") }
        )
        NavigationBarItem(
            selected = selectedTab == EXPLORE,
            onClick = { onTabSelected(EXPLORE) },
            icon = { Icon(Icons.Outlined.Explore, contentDescription = "Keşfet") },
            label = { Text("Keşfet") }
        )
        NavigationBarItem(
            selected = selectedTab == STUDY,
            onClick = { onTabSelected(STUDY) },
            icon = { Icon(Icons.Outlined.MenuBook, contentDescription = "Ezberle") },
            label = { Text("Ezberle") }
        )
        NavigationBarItem(
            selected = selectedTab == QUIZ,
            onClick = { onTabSelected(QUIZ) },
            icon = { Icon(Icons.Outlined.Quiz, contentDescription = "Quiz") },
            label = { Text("Quiz") }
        )
    }
}
