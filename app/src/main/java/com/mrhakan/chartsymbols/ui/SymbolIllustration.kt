package com.mrhakan.chartsymbols.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.mrhakan.chartsymbols.data.ChartSymbol
import com.mrhakan.chartsymbols.data.SymbolAccent
import com.mrhakan.chartsymbols.data.SymbolIcon
import kotlin.math.min

fun symbolColor(accent: SymbolAccent): Color = when (accent) {
    SymbolAccent.TEAL -> Color(0xFF0E7490)
    SymbolAccent.AMBER -> Color(0xFFD97706)
    SymbolAccent.BLUE -> Color(0xFF2563EB)
    SymbolAccent.CORAL -> Color(0xFFE76F51)
    SymbolAccent.VIOLET -> Color(0xFF7653A6)
    SymbolAccent.GREEN -> Color(0xFF2F855A)
}

@Composable
fun SymbolIllustration(
    symbol: ChartSymbol,
    modifier: Modifier = Modifier
) {
    val color = symbolColor(symbol.accent)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(22.dp))
            .background(color.copy(alpha = 0.11f))
            .semantics { contentDescription = "${symbol.title}, ${symbol.englishName}" }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawSymbol(symbol.icon, color)
        }
    }
}

private fun DrawScope.drawSymbol(icon: SymbolIcon, color: Color) {
    val canvasSide = min(size.width, size.height)
    val scale = canvasSide / 100f
    val offsetX = (size.width - canvasSide) / 2f
    val offsetY = (size.height - canvasSide) / 2f

    fun point(x: Float, y: Float) = androidx.compose.ui.geometry.Offset(
        offsetX + x * scale,
        offsetY + y * scale
    )

    fun line(x1: Float, y1: Float, x2: Float, y2: Float, width: Float = 4f) {
        drawLine(
            color = color,
            start = point(x1, y1),
            end = point(x2, y2),
            strokeWidth = width * scale,
            cap = StrokeCap.Round
        )
    }

    when (icon) {
        SymbolIcon.LIGHT -> {
            line(50f, 24f, 50f, 72f, 5f)
            line(35f, 74f, 65f, 74f, 5f)
            line(40f, 82f, 60f, 82f, 5f)
            drawPath(
                path = Path().apply {
                    moveTo(point(38f, 25f).x, point(38f, 25f).y)
                    lineTo(point(62f, 25f).x, point(62f, 25f).y)
                    lineTo(point(50f, 17f).x, point(50f, 17f).y)
                    close()
                },
                color = color,
                style = Fill
            )
            line(50f, 13f, 50f, 7f, 3f)
            line(36f, 14f, 32f, 9f, 3f)
            line(64f, 14f, 68f, 9f, 3f)
            line(28f, 23f, 21f, 21f, 3f)
            line(72f, 23f, 79f, 21f, 3f)
        }

        SymbolIcon.BUOY -> {
            line(50f, 18f, 50f, 29f, 4f)
            drawPath(
                path = Path().apply {
                    moveTo(point(38f, 28f).x, point(38f, 28f).y)
                    lineTo(point(62f, 28f).x, point(62f, 28f).y)
                    lineTo(point(58f, 39f).x, point(58f, 39f).y)
                    lineTo(point(58f, 66f).x, point(58f, 66f).y)
                    lineTo(point(50f, 78f).x, point(50f, 78f).y)
                    lineTo(point(42f, 66f).x, point(42f, 66f).y)
                    lineTo(point(42f, 39f).x, point(42f, 39f).y)
                    close()
                },
                color = color,
                style = Fill
            )
            line(42f, 47f, 58f, 47f, 3f)
            line(33f, 84f, 67f, 84f, 4f)
            line(22f, 88f, 78f, 88f, 2f)
        }

        SymbolIcon.WRECK -> {
            line(24f, 67f, 76f, 34f, 5f)
            line(28f, 37f, 72f, 68f, 5f)
            line(38f, 44f, 50f, 25f, 4f)
            line(58f, 57f, 70f, 75f, 4f)
            line(34f, 73f, 66f, 73f, 3f)
            line(18f, 81f, 82f, 81f, 2f)
        }

        SymbolIcon.ANCHORAGE -> {
            line(50f, 20f, 50f, 72f, 5f)
            line(32f, 43f, 68f, 43f, 5f)
            line(50f, 72f, 35f, 83f, 5f)
            line(50f, 72f, 65f, 83f, 5f)
            drawArc(
                color = color,
                startAngle = 200f,
                sweepAngle = 140f,
                useCenter = false,
                topLeft = point(20f, 26f),
                size = androidx.compose.ui.geometry.Size(60f * scale, 60f * scale),
                style = Stroke(width = 5f * scale, cap = StrokeCap.Round)
            )
            line(42f, 19f, 58f, 19f, 5f)
        }

        SymbolIcon.CONTOUR -> {
            drawOval(
                color = color,
                topLeft = point(17f, 20f),
                size = androidx.compose.ui.geometry.Size(66f * scale, 60f * scale),
                style = Stroke(width = 4f * scale)
            )
            drawOval(
                color = color,
                topLeft = point(27f, 30f),
                size = androidx.compose.ui.geometry.Size(46f * scale, 40f * scale),
                style = Stroke(width = 4f * scale)
            )
            drawOval(
                color = color,
                topLeft = point(37f, 40f),
                size = androidx.compose.ui.geometry.Size(26f * scale, 20f * scale),
                style = Stroke(width = 4f * scale)
            )
            line(23f, 86f, 77f, 86f, 3f)
        }

        SymbolIcon.ROCK -> {
            drawPath(
                path = Path().apply {
                    moveTo(point(26f, 72f).x, point(26f, 72f).y)
                    lineTo(point(35f, 48f).x, point(35f, 48f).y)
                    lineTo(point(48f, 60f).x, point(48f, 60f).y)
                    lineTo(point(62f, 34f).x, point(62f, 34f).y)
                    lineTo(point(76f, 71f).x, point(76f, 71f).y)
                    close()
                },
                color = color,
                style = Fill
            )
            line(21f, 81f, 37f, 81f, 3f)
            line(43f, 84f, 58f, 84f, 3f)
            line(64f, 81f, 80f, 81f, 3f)
            line(27f, 89f, 73f, 89f, 2f)
        }

        SymbolIcon.RESTRICTED -> {
            drawCircle(
                color = color,
                radius = 31f * scale,
                center = point(50f, 50f),
                style = Stroke(width = 5f * scale)
            )
            line(29f, 71f, 71f, 29f, 5f)
            line(37f, 72f, 72f, 37f, 2f)
            line(29f, 50f, 71f, 50f, 2f)
        }

        SymbolIcon.SOUNDING -> {
            drawCircle(color = color, radius = 6f * scale, center = point(50f, 50f))
            line(35f, 35f, 65f, 35f, 4f)
            line(35f, 65f, 65f, 65f, 4f)
            line(26f, 83f, 74f, 83f, 3f)
        }
    }
}
