package com.mrhakan.chartsymbols

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mrhakan.chartsymbols.ui.ChartSymbolsApp
import com.mrhakan.chartsymbols.ui.theme.ChartSymbolsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChartSymbolsTheme {
                ChartSymbolsApp()
            }
        }
    }
}
