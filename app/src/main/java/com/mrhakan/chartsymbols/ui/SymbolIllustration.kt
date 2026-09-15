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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.mrhakan.chartsymbols.data.ChartSymbol
import com.mrhakan.chartsymbols.data.SymbolIcon
import kotlin.math.min

/**
 * Chart-paper palette used by the original vector redraws below.
 *
 * These are deliberately drawn in code instead of shipping a scan or copied
 * page from an IHO/UKHO publication. The shapes follow the visual grammar of
 * INT 1 / Chart 1: black construction lines, magenta aids and restrictions,
 * and blue bathymetric information.
 */
private val ChartPaper = Color(0xFFFFFCF2)
private val ChartInk = Color(0xFF18252D)
private val ChartMagenta = Color(0xFFB0004B)
private val ChartBlue = Color(0xFF236B83)
private val ChartRule = Color(0xFFB6C9C9)

@Composable
fun SymbolIllustration(
    symbol: ChartSymbol,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(22.dp))
            .background(ChartPaper)
            .semantics {
                contentDescription = "${symbol.title}, ${symbol.englishName}, Chart 1 tarzı sembol"
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawChartPaper()
            drawSymbol(symbol.icon)
        }
    }
}

private fun DrawScope.drawChartPaper() {
    val side = min(size.width, size.height)
    val margin = side * 0.06f
    drawRect(
        color = ChartRule.copy(alpha = 0.58f),
        topLeft = androidx.compose.ui.geometry.Offset(margin, margin),
        size = androidx.compose.ui.geometry.Size(size.width - margin * 2f, size.height - margin * 2f),
        style = Stroke(width = side * 0.006f)
    )
}

private fun DrawScope.drawSymbol(icon: SymbolIcon) {
    val canvasSide = min(size.width, size.height)
    val scale = canvasSide / 100f
    val offsetX = (size.width - canvasSide) / 2f
    val offsetY = (size.height - canvasSide) / 2f

    fun point(x: Float, y: Float) = androidx.compose.ui.geometry.Offset(
        offsetX + x * scale,
        offsetY + y * scale
    )

    fun line(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        color: Color = ChartInk,
        width: Float = 3f,
        cap: StrokeCap = StrokeCap.Square
    ) {
        drawLine(
            color = color,
            start = point(x1, y1),
            end = point(x2, y2),
            strokeWidth = width * scale,
            cap = cap
        )
    }

    fun outlinePath(path: Path, color: Color = ChartInk, width: Float = 3f) {
        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = width * scale,
                cap = StrokeCap.Square,
                join = StrokeJoin.Round
            )
        )
    }

    fun filledPath(path: Path, color: Color) {
        drawPath(path = path, color = color, style = Fill)
    }

    fun wave(y: Float, color: Color = ChartBlue) {
        val path = Path().apply {
            moveTo(point(14f, y).x, point(14f, y).y)
            cubicTo(
                point(23f, y - 3f).x,
                point(23f, y - 3f).y,
                point(28f, y + 3f).x,
                point(28f, y + 3f).y,
                point(37f, y).x,
                point(37f, y).y
            )
            cubicTo(
                point(46f, y - 3f).x,
                point(46f, y - 3f).y,
                point(51f, y + 3f).x,
                point(51f, y + 3f).y,
                point(60f, y).x,
                point(60f, y).y
            )
            cubicTo(
                point(69f, y - 3f).x,
                point(69f, y - 3f).y,
                point(74f, y + 3f).x,
                point(74f, y + 3f).y,
                point(83f, y).x,
                point(83f, y).y
            )
        }
        outlinePath(path, color, 1.8f)
    }

    when (icon) {
        SymbolIcon.LIGHT -> drawLight(::point, ::line, ::outlinePath, ::filledPath)
        SymbolIcon.BUOY -> drawBuoy(::point, ::line, ::outlinePath, ::filledPath, ::wave)
        SymbolIcon.WRECK -> drawWreck(::point, ::line, ::outlinePath, ::filledPath, ::wave)
        SymbolIcon.ANCHORAGE -> drawAnchorage(::point, ::line, ::outlinePath, ::filledPath)
        SymbolIcon.CONTOUR -> drawContour(::point, ::line, ::outlinePath)
        SymbolIcon.ROCK -> drawRock(::point, ::line, ::outlinePath)
        SymbolIcon.RESTRICTED -> drawRestricted(::point, ::line, ::outlinePath)
        SymbolIcon.SOUNDING -> drawSounding(::point, ::line)
    }
}

private typealias Point = (Float, Float) -> androidx.compose.ui.geometry.Offset
private typealias Line = (Float, Float, Float, Float, Color, Float, StrokeCap) -> Unit
private typealias PathDrawer = (Path, Color, Float) -> Unit
private typealias FillDrawer = (Path, Color) -> Unit
private typealias WaveDrawer = (Float, Color) -> Unit

private fun DrawScope.drawLight(
    point: Point,
    line: Line,
    outlinePath: PathDrawer,
    filledPath: FillDrawer
) {
    val tower = Path().apply {
        moveTo(point(43f, 42f).x, point(43f, 42f).y)
        lineTo(point(57f, 42f).x, point(57f, 42f).y)
        lineTo(point(61f, 78f).x, point(61f, 78f).y)
        lineTo(point(39f, 78f).x, point(39f, 78f).y)
        close()
    }
    filledPath(tower, ChartInk.copy(alpha = 0.10f))
    outlinePath(tower, ChartInk, 2.8f)
    line(39f, 53f, 61f, 53f, ChartInk, 2.2f, StrokeCap.Square)
    line(40f, 64f, 60f, 64f, ChartInk, 2.2f, StrokeCap.Square)
    line(34f, 80f, 66f, 80f, ChartInk, 3f, StrokeCap.Square)
    line(39f, 84f, 61f, 84f, ChartInk, 2f, StrokeCap.Square)

    drawCircleAt(point(50f, 38f), 4.5f, ChartMagenta)
    line(50f, 31f, 50f, 20f, ChartMagenta, 2.4f, StrokeCap.Square)
    line(42f, 33f, 34f, 26f, ChartMagenta, 2.4f, StrokeCap.Square)
    line(58f, 33f, 66f, 26f, ChartMagenta, 2.4f, StrokeCap.Square)
    line(39f, 38f, 28f, 38f, ChartMagenta, 2.4f, StrokeCap.Square)
    line(61f, 38f, 72f, 38f, ChartMagenta, 2.4f, StrokeCap.Square)
}

private fun DrawScope.drawBuoy(
    point: Point,
    line: Line,
    outlinePath: PathDrawer,
    filledPath: FillDrawer,
    wave: WaveDrawer
) {
    wave(79f, ChartBlue)
    wave(87f, ChartBlue)

    line(50f, 22f, 50f, 39f, ChartInk, 2.8f, StrokeCap.Square)
    val topmark = Path().apply {
        moveTo(point(43f, 31f).x, point(43f, 31f).y)
        lineTo(point(50f, 20f).x, point(50f, 20f).y)
        lineTo(point(57f, 31f).x, point(57f, 31f).y)
        close()
    }
    filledPath(topmark, ChartInk)

    val body = Path().apply {
        moveTo(point(39f, 39f).x, point(39f, 39f).y)
        lineTo(point(61f, 39f).x, point(61f, 39f).y)
        lineTo(point(58f, 55f).x, point(58f, 55f).y)
        lineTo(point(56f, 68f).x, point(56f, 68f).y)
        cubicTo(
            point(54f, 73f).x,
            point(54f, 73f).y,
            point(46f, 73f).x,
            point(46f, 73f).y,
            point(44f, 68f).x,
            point(44f, 68f).y
        )
        lineTo(point(42f, 55f).x, point(42f, 55f).y)
        close()
    }
    filledPath(body, ChartMagenta.copy(alpha = 0.88f))
    outlinePath(body, ChartInk, 2.8f)
    line(41f, 51f, 59f, 51f, ChartPaper, 2.6f, StrokeCap.Square)
    line(44f, 64f, 56f, 64f, ChartPaper, 2.2f, StrokeCap.Square)
    line(50f, 73f, 50f, 82f, ChartInk, 2.2f, StrokeCap.Square)
    line(45f, 84f, 55f, 84f, ChartInk, 2.2f, StrokeCap.Square)
}

private fun DrawScope.drawWreck(
    point: Point,
    line: Line,
    outlinePath: PathDrawer,
    filledPath: FillDrawer,
    wave: WaveDrawer
) {
    wave(79f, ChartBlue.copy(alpha = 0.80f))
    val hull = Path().apply {
        moveTo(point(27f, 59f).x, point(27f, 59f).y)
        lineTo(point(39f, 46f).x, point(39f, 46f).y)
        lineTo(point(67f, 49f).x, point(67f, 49f).y)
        lineTo(point(77f, 61f).x, point(77f, 61f).y)
        lineTo(point(65f, 68f).x, point(65f, 68f).y)
        lineTo(point(34f, 68f).x, point(34f, 68f).y)
        close()
    }
    filledPath(hull, ChartInk.copy(alpha = 0.12f))
    outlinePath(hull, ChartInk, 2.8f)
    line(42f, 47f, 49f, 31f, ChartInk, 2.8f, StrokeCap.Square)
    line(49f, 31f, 58f, 48f, ChartInk, 2.8f, StrokeCap.Square)
    line(49f, 31f, 49f, 23f, ChartMagenta, 2.3f, StrokeCap.Square)
    line(43f, 41f, 56f, 41f, ChartMagenta, 2f, StrokeCap.Square)
    line(34f, 56f, 69f, 56f, ChartMagenta, 2f, StrokeCap.Square)
    line(36f, 65f, 29f, 73f, ChartInk, 2.2f, StrokeCap.Square)
    line(64f, 65f, 71f, 73f, ChartInk, 2.2f, StrokeCap.Square)
}

private fun DrawScope.drawAnchorage(
    point: Point,
    line: Line,
    outlinePath: PathDrawer,
    filledPath: FillDrawer
) {
    val boundary = Path().apply {
        moveTo(point(22f, 27f).x, point(22f, 27f).y)
        cubicTo(
            point(35f, 18f).x,
            point(35f, 18f).y,
            point(67f, 18f).x,
            point(67f, 18f).y,
            point(79f, 29f).x,
            point(79f, 29f).y
        )
        cubicTo(
            point(88f, 43f).x,
            point(88f, 43f).y,
            point(82f, 71f).x,
            point(82f, 71f).y,
            point(70f, 78f).x,
            point(70f, 78f).y
        )
        cubicTo(
            point(54f, 87f).x,
            point(54f, 87f).y,
            point(29f, 82f).x,
            point(29f, 82f).y,
            point(21f, 67f).x,
            point(21f, 67f).y
        )
        close()
    }
    outlinePath(boundary, ChartMagenta, 2.4f)
    drawHatching(point, 25f, 23f, 76f, 70f, ChartMagenta)

    line(50f, 35f, 50f, 69f, ChartInk, 3.4f, StrokeCap.Square)
    line(39f, 46f, 61f, 46f, ChartInk, 3.2f, StrokeCap.Square)
    drawCircleAt(point(50f, 31f), 4f, ChartInk, outlineOnly = true)
    line(50f, 69f, 37f, 79f, ChartInk, 3.4f, StrokeCap.Square)
    line(50f, 69f, 63f, 79f, ChartInk, 3.4f, StrokeCap.Square)
}

private fun DrawScope.drawContour(
    point: Point,
    line: Line,
    outlinePath: PathDrawer
) {
    val upper = Path().apply {
        moveTo(point(14f, 36f).x, point(14f, 36f).y)
        cubicTo(
            point(27f, 24f).x,
            point(27f, 24f).y,
            point(42f, 47f).x,
            point(42f, 47f).y,
            point(55f, 35f).x,
            point(55f, 35f).y
        )
        cubicTo(
            point(67f, 24f).x,
            point(67f, 24f).y,
            point(75f, 28f).x,
            point(75f, 28f).y,
            point(86f, 23f).x,
            point(86f, 23f).y
        )
    }
    outlinePath(upper, ChartBlue, 2.4f)

    val lower = Path().apply {
        moveTo(point(14f, 72f).x, point(14f, 72f).y)
        cubicTo(
            point(29f, 60f).x,
            point(29f, 60f).y,
            point(38f, 80f).x,
            point(38f, 80f).y,
            point(52f, 68f).x,
            point(52f, 68f).y
        )
        cubicTo(
            point(64f, 57f).x,
            point(64f, 57f).y,
            point(75f, 73f).x,
            point(75f, 73f).y,
            point(86f, 63f).x,
            point(86f, 63f).y
        )
    }
    outlinePath(lower, ChartBlue, 2.4f)

    line(43f, 34f, 57f, 34f, ChartPaper, 7f, StrokeCap.Square)
    line(44f, 34f, 56f, 34f, ChartInk, 1.8f, StrokeCap.Square)
    drawDigit(point, line, 4, 24f, 27f, ChartInk)
    drawDigit(point, line, 5, 33f, 27f, ChartInk)
}

private fun DrawScope.drawRock(
    point: Point,
    line: Line,
    outlinePath: PathDrawer
) {
    drawCircleAt(point(50f, 52f), 5f, ChartMagenta)
    line(50f, 24f, 50f, 80f, ChartInk, 2.5f, StrokeCap.Square)
    line(22f, 52f, 78f, 52f, ChartInk, 2.5f, StrokeCap.Square)
    line(31f, 33f, 69f, 71f, ChartInk, 2.5f, StrokeCap.Square)
    line(69f, 33f, 31f, 71f, ChartInk, 2.5f, StrokeCap.Square)
    line(37f, 83f, 63f, 83f, ChartBlue, 1.9f, StrokeCap.Square)
    line(29f, 88f, 71f, 88f, ChartBlue, 1.9f, StrokeCap.Square)
}

private fun DrawScope.drawRestricted(
    point: Point,
    line: Line,
    outlinePath: PathDrawer
) {
    val boundary = Path().apply {
        moveTo(point(22f, 27f).x, point(22f, 27f).y)
        lineTo(point(78f, 27f).x, point(78f, 27f).y)
        lineTo(point(78f, 73f).x, point(78f, 73f).y)
        lineTo(point(22f, 73f).x, point(22f, 73f).y)
        close()
    }
    outlinePath(boundary, ChartMagenta, 2.6f)
    drawHatching(point, 25f, 30f, 75f, 70f, ChartMagenta)
    line(31f, 39f, 69f, 61f, ChartMagenta, 3f, StrokeCap.Square)
    line(69f, 39f, 31f, 61f, ChartMagenta, 3f, StrokeCap.Square)
    line(39f, 79f, 61f, 79f, ChartInk, 2f, StrokeCap.Square)
}

private fun DrawScope.drawSounding(
    point: Point,
    line: Line
) {
    drawDigit(point, line, 2, 31f, 31f, ChartInk, width = 2.8f)
    drawDigit(point, line, 3, 55f, 31f, ChartInk, width = 2.8f)
    drawCircleAt(point(50f, 75f), 2.4f, ChartMagenta)
    line(36f, 81f, 64f, 81f, ChartBlue, 1.8f, StrokeCap.Square)
}

private fun DrawScope.drawHatching(
    point: Point,
    left: Float,
    top: Float,
    right: Float,
    bottom: Float,
    color: Color
) {
    var start = left - (bottom - top)
    while (start < right) {
        val x1 = start.coerceAtLeast(left)
        val y1 = top + (x1 - start)
        val x2 = (start + (bottom - top)).coerceAtMost(right)
        val y2 = bottom - (start + (bottom - top) - x2)
        drawLine(
            color = color.copy(alpha = 0.58f),
            start = point(x1, y1),
            end = point(x2, y2),
            strokeWidth = 1.25f * min(size.width, size.height) / 100f,
            cap = StrokeCap.Square
        )
        start += 9f
    }
}

private fun drawDigit(
    point: Point,
    line: Line,
    digit: Int,
    left: Float,
    top: Float,
    color: Color,
    width: Float = 2.2f
) {
    val segments = when (digit) {
        0 -> setOf(0, 1, 2, 3, 4, 5)
        1 -> setOf(1, 2)
        2 -> setOf(0, 1, 6, 4, 3)
        3 -> setOf(0, 1, 6, 2, 3)
        4 -> setOf(5, 6, 1, 2)
        5 -> setOf(0, 5, 6, 2, 3)
        6 -> setOf(0, 5, 6, 4, 2, 3)
        7 -> setOf(0, 1, 2)
        8 -> setOf(0, 1, 2, 3, 4, 5, 6)
        9 -> setOf(0, 1, 2, 3, 5, 6)
        else -> emptySet()
    }
    val right = left + 13f
    val middle = top + 10f
    val bottom = top + 20f
    val segmentsById = listOf(
        floatArrayOf(left + 2f, top, right - 2f, top),
        floatArrayOf(right, top + 2f, right, middle - 1f),
        floatArrayOf(right, middle + 1f, right, bottom - 2f),
        floatArrayOf(left + 2f, bottom, right - 2f, bottom),
        floatArrayOf(left, middle + 1f, left, bottom - 2f),
        floatArrayOf(left, top + 2f, left, middle - 1f),
        floatArrayOf(left + 2f, middle, right - 2f, middle)
    )
    segments.forEach { id ->
        val segment = segmentsById[id]
        line(segment[0], segment[1], segment[2], segment[3], color, width, StrokeCap.Square)
    }
}

private fun DrawScope.drawCircleAt(
    center: androidx.compose.ui.geometry.Offset,
    radius: Float,
    color: Color,
    outlineOnly: Boolean = false
) {
    val scale = min(size.width, size.height) / 100f
    drawCircle(
        color = color,
        radius = radius * scale,
        center = center,
        style = if (outlineOnly) Stroke(width = 2.4f * scale) else Fill
    )
}
