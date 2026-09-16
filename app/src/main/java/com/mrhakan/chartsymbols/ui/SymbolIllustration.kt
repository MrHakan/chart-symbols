package com.mrhakan.chartsymbols.ui

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.mrhakan.chartsymbols.data.ChartSymbol
import com.mrhakan.chartsymbols.data.SymbolIcon
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private val ChartPaper = Color(0xFFFFFCF2)
private val ChartInk = Color(0xFF18252D)
private val ChartMagenta = Color(0xFFB0004B)
private val ChartBlue = Color(0xFF236B83)
private val ChartGreen = Color(0xFF3D7657)
private val ChartYellow = Color(0xFFE3AA32)
private val ChartRule = Color(0xFFB6C9C9)

/**
 * Chart-paper styled original vector redraws.
 *
 * The app does not ship scanned IHO/UKHO pages or copied symbol artwork.
 * These drawings use the same black/magenta/blue cartographic grammar while
 * keeping the repository distributable as an educational application.
 */
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
                contentDescription = "${symbol.title}, ${symbol.englishName}, INT 1 tarzı sembol"
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            ChartPainter(this).draw(symbol.icon, symbol.chartNotation)
        }
    }
}

private class ChartPainter(private val scope: DrawScope) {
    private val side = min(scope.size.width, scope.size.height)
    private val scale = side / 100f
    private val offsetX = (scope.size.width - side) / 2f
    private val offsetY = (scope.size.height - side) / 2f

    private fun point(x: Float, y: Float): Offset = Offset(
        offsetX + x * scale,
        offsetY + y * scale
    )

    private fun line(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        color: Color = ChartInk,
        width: Float = 2.8f,
        cap: StrokeCap = StrokeCap.Square
    ) {
        scope.drawLine(color, point(x1, y1), point(x2, y2), width * scale, cap)
    }

    private fun circle(x: Float, y: Float, radius: Float, color: Color = ChartInk, outline: Boolean = false) {
        scope.drawCircle(
            color = color,
            radius = radius * scale,
            center = point(x, y),
            style = if (outline) Stroke(width = 2.4f * scale) else Fill
        )
    }

    private fun rect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        color: Color = ChartInk,
        width: Float = 2.5f,
        fill: Boolean = false
    ) {
        scope.drawRect(
            color = color,
            topLeft = point(left, top),
            size = Size((right - left) * scale, (bottom - top) * scale),
            style = if (fill) Fill else Stroke(width = width * scale)
        )
    }

    private fun path(
        builder: Path.() -> Unit,
        color: Color = ChartInk,
        width: Float = 2.6f,
        fill: Boolean = false
    ) {
        val result = Path().apply(builder)
        scope.drawPath(
            path = result,
            color = color,
            style = if (fill) Fill else Stroke(width = width * scale, cap = StrokeCap.Square, join = StrokeJoin.Round)
        )
    }

    private fun oval(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        color: Color = ChartInk,
        outline: Boolean = true,
        width: Float = 2.2f
    ) {
        scope.drawOval(
            color = color,
            topLeft = point(left, top),
            size = Size((right - left) * scale, (bottom - top) * scale),
            style = if (outline) Stroke(width = width * scale) else Fill
        )
    }

    private fun dottedOval(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        color: Color = ChartInk
    ) {
        val centerX = (left + right) / 2f
        val centerY = (top + bottom) / 2f
        val radiusX = (right - left) / 2f
        val radiusY = (bottom - top) / 2f
        repeat(24) { index ->
            val angle = index * 2f * PI / 24f
            circle(
                centerX + cos(angle).toFloat() * radiusX,
                centerY + sin(angle).toFloat() * radiusY,
                1.0f,
                color
            )
        }
    }

    private fun chartText(
        value: String,
        x: Float = 50f,
        y: Float = 54f,
        size: Float = 14f,
        color: Color = ChartInk
    ) {
        scope.drawIntoCanvas { canvas ->
            val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                this.color = color.toArgb()
                textSize = size * scale
                textAlign = Paint.Align.CENTER
                typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            }
            canvas.nativeCanvas.drawText(value, point(x, y).x, point(x, y).y, paint)
        }
    }

    private fun hatch(left: Float, top: Float, right: Float, bottom: Float, color: Color = ChartMagenta) {
        var start = left - (bottom - top)
        while (start < right) {
            val x1 = start.coerceAtLeast(left)
            val y1 = top + (x1 - start)
            val x2 = (start + bottom - top).coerceAtMost(right)
            val y2 = bottom - (start + bottom - top - x2)
            line(x1, y1, x2, y2, color.copy(alpha = 0.58f), 1.2f)
            start += 9f
        }
    }

    private fun wave(y: Float, color: Color = ChartBlue) {
        path({
            moveTo(point(12f, y).x, point(12f, y).y)
            cubicTo(point(20f, y - 3f).x, point(20f, y - 3f).y, point(26f, y + 3f).x, point(26f, y + 3f).y, point(34f, y).x, point(34f, y).y)
            cubicTo(point(42f, y - 3f).x, point(42f, y - 3f).y, point(48f, y + 3f).x, point(48f, y + 3f).y, point(56f, y).x, point(56f, y).y)
            cubicTo(point(64f, y - 3f).x, point(64f, y - 3f).y, point(70f, y + 3f).x, point(70f, y + 3f).y, point(78f, y).x, point(78f, y).y)
        }, color, 1.7f)
    }

    fun draw(icon: SymbolIcon, notation: String?) {
        drawPaperFrame()
        when (icon) {
            SymbolIcon.LIGHT -> drawLight()
            SymbolIcon.LIGHT_BEACON, SymbolIcon.BEACON -> drawBeacon()
            SymbolIcon.SECTOR_LIGHT -> drawSectorLight()
            SymbolIcon.LEADING_LINE -> drawLeadingLine()
            SymbolIcon.BUOY_LATERAL -> drawBuoy(BuoyVariant.LATERAL)
            SymbolIcon.BUOY_CARDINAL -> drawBuoy(BuoyVariant.CARDINAL)
            SymbolIcon.BUOY_ISOLATED_DANGER -> drawBuoy(BuoyVariant.ISOLATED_DANGER)
            SymbolIcon.BUOY_SAFE_WATER -> drawBuoy(BuoyVariant.SAFE_WATER)
            SymbolIcon.BUOY_SPECIAL -> drawBuoy(BuoyVariant.SPECIAL)
            SymbolIcon.LIGHT_FLOAT -> drawLightFloat()
            SymbolIcon.RADAR_AID -> drawRadarAid()
            SymbolIcon.WRECK -> drawWreck()
            SymbolIcon.WRECK_DATUM_HULL -> drawWreckVariant(WreckVariant.DATUM_HULL, notation)
            SymbolIcon.WRECK_DATUM_MAST -> drawWreckVariant(WreckVariant.DATUM_MAST, notation)
            SymbolIcon.WRECK_SUNKEN_SAFE -> drawWreckVariant(WreckVariant.SUNKEN_SAFE, notation)
            SymbolIcon.WRECK_MAST_SEA_LEVEL -> drawWreckVariant(WreckVariant.MAST_SEA_LEVEL, notation)
            SymbolIcon.WRECK_SAFE_CLEARANCE -> drawWreckVariant(WreckVariant.SAFE_CLEARANCE, notation)
            SymbolIcon.WRECK_SWEPT -> drawWreckVariant(WreckVariant.SWEPT, notation)
            SymbolIcon.WRECK_SOUNDED -> drawWreckVariant(WreckVariant.SOUNDED, notation)
            SymbolIcon.ROCK -> drawRock()
            SymbolIcon.DANGEROUS_ROCK_UNKNOWN -> drawDangerousRock(knownDepth = false)
            SymbolIcon.DANGEROUS_ROCK_KNOWN -> drawDangerousRock(knownDepth = true)
            SymbolIcon.UNDERWATER_ROCK -> drawUnderwaterRock()
            SymbolIcon.LAND_ABOVE_SEA -> drawLandAboveSea()
            SymbolIcon.ROCK_KNOWN_DEPTH -> drawRockVariant(RockVariant.KNOWN_DEPTH, notation)
            SymbolIcon.ROCK_NOT_COVER -> drawRockVariant(RockVariant.NOT_COVER, notation)
            SymbolIcon.ROCK_COVERS_UNCOVERS -> drawRockVariant(RockVariant.COVERS_UNCOVERS, notation)
            SymbolIcon.ROCK_AWASH -> drawRockVariant(RockVariant.AWASH, notation)
            SymbolIcon.ROCK_DANGEROUS_UNKNOWN -> drawRockVariant(RockVariant.DANGEROUS_UNKNOWN, notation)
            SymbolIcon.ROCK_NOT_DANGEROUS -> drawRockVariant(RockVariant.NOT_DANGEROUS, notation)
            SymbolIcon.CORAL -> drawCoral()
            SymbolIcon.OBSTRUCTION -> drawObstruction()
            SymbolIcon.OBSTRUCTION_KNOWN -> drawObstructionVariant(knownDepth = true, swept = false, notation = notation)
            SymbolIcon.OBSTRUCTION_SWEPT -> drawObstructionVariant(knownDepth = true, swept = true, notation = notation)
            SymbolIcon.FOUL_GROUND -> drawFoulGround()
            SymbolIcon.FOUL -> drawFoul()
            SymbolIcon.MINE -> drawMine()
            SymbolIcon.SOUNDING -> drawSounding()
            SymbolIcon.CONTOUR -> drawContour()
            SymbolIcon.DEPTH_AREA -> drawDepthArea()
            SymbolIcon.DRYING -> drawDrying()
            SymbolIcon.SEABED -> drawSeabed(notation)
            SymbolIcon.CURRENT -> drawCurrent()
            SymbolIcon.CABLE -> drawCable()
            SymbolIcon.PIPELINE -> drawPipeline()
            SymbolIcon.POWER_CABLE -> drawPowerCable()
            SymbolIcon.UNUSED_PIPELINE -> drawUnusedPipeline()
            SymbolIcon.DANGER_LINE -> drawDangerLine()
            SymbolIcon.SWEPT_DRAG -> drawSweptDrag(notation)
            SymbolIcon.COASTLINE -> drawCoastline()
            SymbolIcon.CLIFF -> drawCliff()
            SymbolIcon.LAND -> drawLand()
            SymbolIcon.VEGETATION -> drawVegetation()
            SymbolIcon.BUILDING -> drawBuilding()
            SymbolIcon.LANDMARK -> drawLandmark()
            SymbolIcon.ROAD -> drawRoad()
            SymbolIcon.BRIDGE -> drawBridge()
            SymbolIcon.AIRFIELD -> drawAirfield()
            SymbolIcon.ANCHORAGE -> drawAnchorage()
            SymbolIcon.RESTRICTED -> drawRestricted()
            SymbolIcon.NO_ANCHOR -> drawNoAnchor()
            SymbolIcon.PROHIBITED_AREA -> drawProhibitedArea()
            SymbolIcon.PROHIBITED_FISHING -> drawFishingProhibited()
            SymbolIcon.MOORING_BUOY -> drawMooringBuoy()
            SymbolIcon.OFFSHORE_PLATFORM -> drawOffshorePlatform()
            SymbolIcon.LIGHTSHIP -> drawLightship()
            SymbolIcon.MINARET -> drawMinaret()
            SymbolIcon.BUOYAGE_DIRECTION -> drawBuoyageDirection()
            SymbolIcon.PILOT_TRANSFER -> drawPilotTransfer()
            SymbolIcon.TIDE_RIP -> drawTideRip()
            SymbolIcon.EDDIES -> drawEddies()
            SymbolIcon.PLATFORM_ZONE -> drawPlatformZone()
            SymbolIcon.BREAKERS -> drawBreakers()
            SymbolIcon.MARINE_FARM -> drawMarineFarm()
            SymbolIcon.FISHING_ZONE -> drawFishingZone()
            SymbolIcon.FARM -> drawFarm()
            SymbolIcon.DUMPING -> drawDumping()
            SymbolIcon.ROUTE -> drawRoute()
            SymbolIcon.TRAFFIC_SEPARATION -> drawTrafficSeparation()
            SymbolIcon.PORT -> drawPort()
            SymbolIcon.QUAY -> drawQuay()
            SymbolIcon.MARINA -> drawMarina()
            SymbolIcon.LOCK -> drawLock()
            SymbolIcon.CRANE -> drawCrane()
            SymbolIcon.PILOT -> drawPilot()
            SymbolIcon.SERVICE -> drawService()
            SymbolIcon.COMPASS -> drawCompass()
            SymbolIcon.SCALE -> drawScale()
            SymbolIcon.NOTE -> drawNote()
        }
    }

    private fun drawPaperFrame() {
        val margin = side * 0.06f
        scope.drawRect(
            color = ChartRule.copy(alpha = 0.58f),
            topLeft = Offset(margin, margin),
            size = Size(scope.size.width - margin * 2f, scope.size.height - margin * 2f),
            style = Stroke(width = side * 0.006f)
        )
    }

    private fun drawLight() {
        path({
            moveTo(point(43f, 42f).x, point(43f, 42f).y)
            lineTo(point(57f, 42f).x, point(57f, 42f).y)
            lineTo(point(61f, 78f).x, point(61f, 78f).y)
            lineTo(point(39f, 78f).x, point(39f, 78f).y)
            close()
        }, ChartInk, 2.8f)
        line(39f, 53f, 61f, 53f)
        line(40f, 64f, 60f, 64f)
        line(34f, 80f, 66f, 80f, width = 3.2f)
        circle(50f, 38f, 4.5f, ChartMagenta)
        line(50f, 31f, 50f, 20f, ChartMagenta, 2.4f)
        line(42f, 33f, 34f, 26f, ChartMagenta, 2.4f)
        line(58f, 33f, 66f, 26f, ChartMagenta, 2.4f)
        line(39f, 38f, 28f, 38f, ChartMagenta, 2.4f)
        line(61f, 38f, 72f, 38f, ChartMagenta, 2.4f)
    }

    private fun drawBeacon() {
        line(50f, 25f, 50f, 75f, width = 3.6f)
        path({
            moveTo(point(42f, 76f).x, point(42f, 76f).y)
            lineTo(point(58f, 76f).x, point(58f, 76f).y)
            lineTo(point(55f, 39f).x, point(55f, 39f).y)
            lineTo(point(45f, 39f).x, point(45f, 39f).y)
            close()
        }, ChartInk, 2.6f)
        line(40f, 39f, 60f, 39f, ChartMagenta, 2.5f)
        line(41f, 78f, 59f, 78f, width = 3f)
    }

    private fun drawSectorLight() {
        circle(50f, 57f, 4f, ChartMagenta)
        line(50f, 57f, 28f, 29f, ChartMagenta, 2.4f)
        line(50f, 57f, 74f, 32f, ChartMagenta, 2.4f)
        path({
            moveTo(point(30f, 31f).x, point(30f, 31f).y)
            lineTo(point(70f, 31f).x, point(70f, 31f).y)
            lineTo(point(50f, 74f).x, point(50f, 74f).y)
            close()
        }, ChartYellow.copy(alpha = 0.48f), 1.5f, fill = true)
        line(25f, 78f, 75f, 78f, width = 3f)
    }

    private fun drawLeadingLine() {
        line(31f, 78f, 50f, 31f, ChartMagenta, 2.5f)
        line(69f, 78f, 50f, 31f, ChartMagenta, 2.5f)
        drawBeaconAt(50f, 28f, 6f)
        drawBeaconAt(31f, 78f, 5f)
        drawBeaconAt(69f, 78f, 5f)
    }

    private fun drawBeaconAt(x: Float, y: Float, height: Float) {
        line(x, y, x, y + height * 4f, width = 2.4f)
        line(x - 4f, y + height * 4f, x + 4f, y + height * 4f, width = 2f)
    }

    private enum class BuoyVariant { LATERAL, CARDINAL, ISOLATED_DANGER, SAFE_WATER, SPECIAL }

    private fun drawBuoy(variant: BuoyVariant) {
        wave(80f)
        wave(88f)
        line(50f, 20f, 50f, 39f, width = 2.6f)
        when (variant) {
            BuoyVariant.LATERAL -> {
                path({
                    moveTo(point(39f, 39f).x, point(39f, 39f).y)
                    lineTo(point(61f, 39f).x, point(61f, 39f).y)
                    lineTo(point(57f, 69f).x, point(57f, 69f).y)
                    cubicTo(point(55f, 74f).x, point(55f, 74f).y, point(45f, 74f).x, point(45f, 74f).y, point(43f, 69f).x, point(43f, 69f).y)
                    close()
                }, ChartMagenta, 2.8f, fill = true)
                line(41f, 53f, 59f, 53f, ChartPaper, 2.6f)
                path({
                    moveTo(point(43f, 31f).x, point(43f, 31f).y)
                    lineTo(point(50f, 20f).x, point(50f, 20f).y)
                    lineTo(point(57f, 31f).x, point(57f, 31f).y)
                    close()
                }, ChartInk, 2f, fill = true)
            }
            BuoyVariant.CARDINAL -> {
                path({
                    moveTo(point(38f, 40f).x, point(38f, 40f).y)
                    lineTo(point(62f, 40f).x, point(62f, 40f).y)
                    lineTo(point(58f, 69f).x, point(58f, 69f).y)
                    lineTo(point(42f, 69f).x, point(42f, 69f).y)
                    close()
                }, ChartInk, 2.6f)
                line(40f, 52f, 60f, 52f, ChartYellow, 3f)
                line(40f, 62f, 60f, 62f, ChartYellow, 3f)
                line(46f, 30f, 50f, 20f, width = 2f)
                line(54f, 30f, 50f, 20f, width = 2f)
            }
            BuoyVariant.ISOLATED_DANGER -> {
                circle(50f, 55f, 15f, ChartInk, outline = true)
                line(39f, 48f, 61f, 48f, ChartMagenta, 3f)
                line(39f, 61f, 61f, 61f, ChartMagenta, 3f)
                circle(46f, 25f, 4f, ChartInk)
                circle(54f, 25f, 4f, ChartInk)
            }
            BuoyVariant.SAFE_WATER -> {
                circle(50f, 55f, 15f, ChartMagenta, outline = true)
                line(50f, 40f, 50f, 70f, ChartMagenta, 3f)
                line(42f, 55f, 58f, 55f, ChartMagenta, 3f)
                circle(50f, 26f, 5f, ChartInk, outline = true)
            }
            BuoyVariant.SPECIAL -> {
                circle(50f, 55f, 14f, ChartYellow, outline = true)
                line(50f, 40f, 50f, 70f, ChartYellow, 3f)
                line(40f, 55f, 60f, 55f, ChartYellow, 3f)
                line(44f, 30f, 56f, 30f, ChartInk, 2.4f)
                line(50f, 24f, 50f, 36f, ChartInk, 2.4f)
            }
        }
        line(44f, 73f, 56f, 73f, width = 2.4f)
    }

    private fun drawLightFloat() {
        wave(78f)
        wave(87f)
        circle(50f, 53f, 14f, ChartMagenta, outline = true)
        line(50f, 39f, 50f, 23f, width = 2.6f)
        line(43f, 23f, 57f, 23f, ChartMagenta, 2.5f)
        circle(50f, 19f, 3.5f, ChartMagenta)
    }

    private fun drawRadarAid() {
        circle(50f, 52f, 18f, ChartMagenta, outline = true)
        line(50f, 30f, 50f, 74f, ChartMagenta, 2.4f)
        line(28f, 52f, 72f, 52f, ChartMagenta, 2.4f)
        line(36f, 36f, 64f, 68f, ChartInk, 2f)
        line(64f, 36f, 36f, 68f, ChartInk, 2f)
        line(50f, 52f, 82f, 25f, ChartInk, 2f)
    }

    private fun drawWreck() {
        wave(81f, ChartBlue.copy(alpha = 0.8f))
        path({
            moveTo(point(27f, 60f).x, point(27f, 60f).y)
            lineTo(point(39f, 46f).x, point(39f, 46f).y)
            lineTo(point(67f, 49f).x, point(67f, 49f).y)
            lineTo(point(77f, 61f).x, point(77f, 61f).y)
            lineTo(point(65f, 68f).x, point(65f, 68f).y)
            lineTo(point(34f, 68f).x, point(34f, 68f).y)
            close()
        }, ChartInk, 2.8f)
        line(42f, 47f, 49f, 31f, width = 2.8f)
        line(49f, 31f, 58f, 48f, width = 2.8f)
        line(49f, 31f, 49f, 23f, ChartMagenta, 2.3f)
        line(34f, 56f, 69f, 56f, ChartMagenta, 2f)
    }

    private enum class WreckVariant {
        DATUM_HULL,
        DATUM_MAST,
        SUNKEN_SAFE,
        MAST_SEA_LEVEL,
        SAFE_CLEARANCE,
        SWEPT,
        SOUNDED
    }

    private fun drawWreckVariant(variant: WreckVariant, notation: String?) {
        when (variant) {
            WreckVariant.DATUM_HULL -> {
                path({
                    moveTo(point(25f, 60f).x, point(25f, 60f).y)
                    lineTo(point(37f, 48f).x, point(37f, 48f).y)
                    lineTo(point(66f, 49f).x, point(66f, 49f).y)
                    lineTo(point(77f, 60f).x, point(77f, 60f).y)
                    lineTo(point(65f, 68f).x, point(65f, 68f).y)
                    lineTo(point(34f, 68f).x, point(34f, 68f).y)
                    close()
                }, ChartInk, 2.8f, fill = true)
                line(32f, 57f, 68f, 57f, ChartMagenta, 2f)
            }
            WreckVariant.DATUM_MAST -> {
                dottedOval(25f, 36f, 75f, 67f)
                line(37f, 53f, 63f, 53f, width = 2.8f)
                line(44f, 53f, 44f, 36f, width = 2.6f)
                line(56f, 53f, 56f, 36f, width = 2.6f)
            }
            WreckVariant.SUNKEN_SAFE -> {
                line(29f, 54f, 71f, 54f, width = 3f)
                line(39f, 45f, 39f, 63f, width = 2.8f)
                line(50f, 45f, 50f, 63f, width = 2.8f)
                line(61f, 45f, 61f, 63f, width = 2.8f)
            }
            WreckVariant.MAST_SEA_LEVEL -> {
                dottedOval(20f, 36f, 80f, 68f)
                line(34f, 52f, 66f, 52f, width = 2.8f)
                line(50f, 52f, 50f, 22f, width = 3f)
                line(42f, 37f, 58f, 37f, width = 2.4f)
            }
            WreckVariant.SAFE_CLEARANCE -> {
                oval(25f, 35f, 75f, 69f, ChartInk, outline = true, width = 1.8f)
                chartText(notation ?: "Wk", 50f, 54f, 13f)
                line(42f, 76f, 58f, 76f, ChartBlue, 2f)
            }
            WreckVariant.SWEPT -> {
                line(28f, 70f, 72f, 70f, width = 2.8f)
                line(34f, 70f, 34f, 58f, width = 2.4f)
                line(66f, 70f, 66f, 58f, width = 2.4f)
                dottedOval(33f, 35f, 67f, 61f)
                chartText(notation ?: "Wk", 50f, 53f, 12f)
            }
            WreckVariant.SOUNDED -> {
                oval(27f, 36f, 73f, 68f, ChartInk, outline = true, width = 1.8f)
                line(39f, 52f, 61f, 52f, width = 2.8f)
                line(50f, 42f, 50f, 62f, width = 2.4f)
                chartText(notation ?: "Wk", 50f, 79f, 11f)
            }
        }
        if (variant != WreckVariant.SAFE_CLEARANCE && variant != WreckVariant.SWEPT && variant != WreckVariant.SOUNDED) {
            chartText(notation.orEmpty(), 50f, 82f, 10f, ChartMagenta)
        }
    }

    private fun drawRock() {
        circle(50f, 52f, 5f, ChartMagenta)
        line(50f, 24f, 50f, 80f)
        line(22f, 52f, 78f, 52f)
        line(31f, 33f, 69f, 71f)
        line(69f, 33f, 31f, 71f)
        wave(84f, ChartBlue)
    }

    private fun drawDangerousRock(knownDepth: Boolean) {
        line(50f, 35f, 50f, 69f, width = 3f)
        line(34f, 52f, 66f, 52f, width = 3f)
        if (knownDepth) {
            oval(29f, 31f, 71f, 73f, ChartInk, outline = true, width = 1.8f)
            chartText("4.1", 50f, 84f, 11f, ChartBlue)
        } else {
            dottedOval(27f, 30f, 73f, 74f)
        }
    }

    private fun drawUnderwaterRock() {
        line(50f, 35f, 50f, 69f, width = 2.8f)
        line(34f, 52f, 66f, 52f, width = 2.8f)
        dottedOval(35f, 38f, 65f, 66f)
    }

    private fun drawLandAboveSea() {
        path({
            moveTo(point(25f, 61f).x, point(25f, 61f).y)
            cubicTo(point(28f, 46f).x, point(28f, 46f).y, point(37f, 38f).x, point(37f, 38f).y, point(48f, 43f).x, point(48f, 43f).y)
            cubicTo(point(59f, 33f).x, point(59f, 33f).y, point(72f, 43f).x, point(72f, 43f).y, point(75f, 59f).x, point(75f, 59f).y)
            cubicTo(point(63f, 70f).x, point(63f, 70f).y, point(38f, 72f).x, point(38f, 72f).y, point(25f, 61f).x, point(25f, 61f).y)
            close()
        }, ChartGreen.copy(alpha = 0.2f), 2.4f, fill = true)
        line(22f, 75f, 78f, 75f, ChartBlue, 2f)
        circle(40f, 52f, 2.2f, ChartGreen)
        circle(60f, 48f, 2.2f, ChartGreen)
    }

    private enum class RockVariant {
        KNOWN_DEPTH,
        NOT_COVER,
        COVERS_UNCOVERS,
        AWASH,
        DANGEROUS_UNKNOWN,
        NOT_DANGEROUS
    }

    private fun drawRockVariant(variant: RockVariant, notation: String?) {
        when (variant) {
            RockVariant.KNOWN_DEPTH -> {
                line(50f, 33f, 50f, 69f, width = 2.8f)
                line(34f, 51f, 66f, 51f, width = 2.8f)
                oval(28f, 31f, 72f, 72f, ChartInk, outline = true, width = 1.8f)
                chartText(notation ?: "Rk", 50f, 84f, 11f, ChartBlue)
            }
            RockVariant.NOT_COVER -> {
                path({
                    moveTo(point(28f, 67f).x, point(28f, 67f).y)
                    lineTo(point(36f, 48f).x, point(36f, 48f).y)
                    lineTo(point(47f, 56f).x, point(47f, 56f).y)
                    lineTo(point(60f, 39f).x, point(60f, 39f).y)
                    lineTo(point(73f, 67f).x, point(73f, 67f).y)
                    close()
                }, ChartYellow.copy(alpha = 0.55f), 2.4f, fill = true)
                line(25f, 72f, 75f, 72f, ChartBlue, 2f)
            }
            RockVariant.COVERS_UNCOVERS -> {
                path({
                    moveTo(point(28f, 68f).x, point(28f, 68f).y)
                    lineTo(point(38f, 45f).x, point(38f, 45f).y)
                    lineTo(point(51f, 61f).x, point(51f, 61f).y)
                    lineTo(point(63f, 43f).x, point(63f, 43f).y)
                    lineTo(point(73f, 68f).x, point(73f, 68f).y)
                    close()
                }, ChartYellow.copy(alpha = 0.55f), 2.4f, fill = true)
                wave(73f)
            }
            RockVariant.AWASH -> {
                line(50f, 31f, 50f, 69f, width = 2.8f)
                line(35f, 50f, 65f, 50f, width = 2.8f)
                line(24f, 53f, 76f, 53f, ChartBlue, 2f)
            }
            RockVariant.DANGEROUS_UNKNOWN -> drawDangerousRock(knownDepth = false)
            RockVariant.NOT_DANGEROUS -> {
                line(50f, 37f, 50f, 67f, width = 2.4f)
                line(37f, 52f, 63f, 52f, width = 2.4f)
                chartText(notation ?: "R", 50f, 82f, 12f, ChartBlue)
            }
        }
        if (variant == RockVariant.KNOWN_DEPTH || variant == RockVariant.DANGEROUS_UNKNOWN) {
            chartText(notation.orEmpty(), 50f, 87f, 10f, ChartMagenta)
        }
    }

    private fun drawCoral() {
        path({
            moveTo(point(24f, 68f).x, point(24f, 68f).y)
            cubicTo(point(24f, 54f).x, point(24f, 54f).y, point(35f, 51f).x, point(35f, 51f).y, point(38f, 40f).x, point(38f, 40f).y)
            cubicTo(point(44f, 50f).x, point(44f, 50f).y, point(48f, 45f).x, point(48f, 45f).y, point(51f, 34f).x, point(51f, 34f).y)
            cubicTo(point(57f, 45f).x, point(57f, 45f).y, point(63f, 50f).x, point(63f, 50f).y, point(67f, 40f).x, point(67f, 40f).y)
            cubicTo(point(69f, 54f).x, point(69f, 54f).y, point(77f, 55f).x, point(77f, 55f).y, point(76f, 68f).x, point(76f, 68f).y)
            close()
        }, ChartYellow.copy(alpha = 0.5f), 2.4f, fill = true)
        chartText("Co", 50f, 82f, 12f, ChartBlue)
    }

    private fun drawObstruction() {
        rect(34f, 37f, 66f, 67f, ChartMagenta, 2.5f)
        line(34f, 37f, 66f, 67f, ChartInk, 3f)
        line(66f, 37f, 34f, 67f, ChartInk, 3f)
        circle(50f, 52f, 4f, ChartMagenta)
        line(31f, 76f, 69f, 76f, ChartBlue, 2f)
    }

    private fun drawObstructionVariant(knownDepth: Boolean, swept: Boolean, notation: String?) {
        if (swept) {
            line(28f, 70f, 72f, 70f, width = 2.6f)
            line(35f, 70f, 35f, 57f, width = 2.2f)
            line(65f, 70f, 65f, 57f, width = 2.2f)
            dottedOval(33f, 35f, 67f, 61f)
        } else {
            oval(27f, 35f, 73f, 69f, ChartBlue.copy(alpha = 0.65f), outline = false)
            dottedOval(25f, 33f, 75f, 71f, ChartInk)
            line(39f, 52f, 61f, 52f, ChartMagenta, 2.6f)
            line(50f, 42f, 50f, 62f, ChartMagenta, 2.6f)
        }
        chartText(notation ?: "Obstn", 50f, 83f, 10f, if (knownDepth) ChartBlue else ChartMagenta)
    }

    private fun drawFoulGround() {
        circle(31f, 44f, 3f, ChartMagenta)
        circle(47f, 35f, 3f, ChartMagenta)
        circle(64f, 44f, 3f, ChartMagenta)
        circle(38f, 61f, 3f, ChartMagenta)
        circle(59f, 65f, 3f, ChartMagenta)
        line(24f, 79f, 76f, 79f, ChartInk, 2f)
        line(31f, 84f, 69f, 84f, ChartBlue, 2f)
    }

    private fun drawFoul() {
        rect(23f, 31f, 77f, 70f, ChartInk, 1.6f)
        for (x in 31..69 step 13) {
            line(x.toFloat(), 34f, (x + 6).toFloat(), 40f, ChartInk, 1.6f)
            line(x.toFloat(), 61f, (x + 6).toFloat(), 67f, ChartInk, 1.6f)
        }
        chartText("Foul", 50f, 53f, 12f, ChartMagenta)
    }

    private fun drawMine() {
        circle(50f, 51f, 13f, ChartMagenta, outline = true)
        repeat(8) { index ->
            val angle = index * 45f
            val radians = Math.toRadians(angle.toDouble())
            val x = 50f + (kotlin.math.cos(radians) * 24f).toFloat()
            val y = 51f + (kotlin.math.sin(radians) * 24f).toFloat()
            line(50f, 51f, x, y, ChartInk, 1.7f)
        }
        circle(50f, 51f, 3f, ChartMagenta)
    }

    private fun drawSounding() {
        drawDigit(2, 31f, 31f, ChartInk, 2.8f)
        drawDigit(3, 55f, 31f, ChartInk, 2.8f)
        circle(50f, 75f, 2.4f, ChartMagenta)
        line(36f, 81f, 64f, 81f, ChartBlue, 1.8f)
    }

    private fun drawContour() {
        path({
            moveTo(point(13f, 35f).x, point(13f, 35f).y)
            cubicTo(point(27f, 23f).x, point(27f, 23f).y, point(42f, 47f).x, point(42f, 47f).y, point(55f, 35f).x, point(55f, 35f).y)
            cubicTo(point(67f, 24f).x, point(67f, 24f).y, point(75f, 28f).x, point(75f, 28f).y, point(87f, 23f).x, point(87f, 23f).y)
        }, ChartBlue, 2.4f)
        path({
            moveTo(point(13f, 72f).x, point(13f, 72f).y)
            cubicTo(point(29f, 60f).x, point(29f, 60f).y, point(38f, 80f).x, point(38f, 80f).y, point(52f, 68f).x, point(52f, 68f).y)
            cubicTo(point(64f, 57f).x, point(64f, 57f).y, point(75f, 73f).x, point(75f, 73f).y, point(87f, 63f).x, point(87f, 63f).y)
        }, ChartBlue, 2.4f)
        line(42f, 34f, 58f, 34f, ChartPaper, 7f)
        drawDigit(4, 24f, 27f, ChartInk)
        drawDigit(5, 33f, 27f, ChartInk)
    }

    private fun drawDepthArea() {
        rect(18f, 26f, 82f, 74f, ChartBlue, 2f)
        wave(36f, ChartBlue)
        wave(49f, ChartBlue)
        wave(62f, ChartBlue)
        line(27f, 82f, 73f, 82f, ChartBlue, 2f)
    }

    private fun drawDrying() {
        rect(20f, 28f, 80f, 72f, ChartBlue, 2f)
        hatch(22f, 30f, 78f, 70f, ChartBlue)
        line(31f, 80f, 69f, 80f, ChartBlue, 2f)
    }

    private fun drawSeabed(notation: String?) {
        if (!notation.isNullOrBlank()) {
            chartText(notation, 50f, 55f, if (notation.length > 4) 11f else 22f, ChartInk)
            line(24f, 73f, 76f, 73f, ChartBlue, 2f)
            return
        }
        repeat(5) { row ->
            repeat(4) { column ->
                val x = 28f + column * 14f + if (row % 2 == 0) 3f else 0f
                val y = 30f + row * 9f
                circle(x, y, 1.7f, ChartBlue)
            }
        }
        line(25f, 77f, 75f, 77f, ChartBlue, 2f)
    }

    private fun drawCurrent() {
        wave(76f, ChartBlue)
        line(27f, 62f, 73f, 36f, ChartBlue, 3f)
        path({
            moveTo(point(73f, 36f).x, point(73f, 36f).y)
            lineTo(point(61f, 38f).x, point(61f, 38f).y)
            lineTo(point(68f, 47f).x, point(68f, 47f).y)
            close()
        }, ChartBlue, 2f, fill = true)
    }

    private fun drawTideRip() {
        for (y in listOf(34f, 49f, 64f)) wave(y, ChartBlue)
        line(27f, 79f, 73f, 79f, ChartBlue, 2f)
        chartText("RIP", 50f, 23f, 10f, ChartMagenta)
    }

    private fun drawEddies() {
        for (x in listOf(31f, 50f, 69f)) {
            path({
                moveTo(point(x + 8f, 45f).x, point(x + 8f, 45f).y)
                cubicTo(point(x - 5f, 34f).x, point(x - 5f, 34f).y, point(x - 8f, 58f).x, point(x - 8f, 58f).y, point(x + 4f, 58f).x, point(x + 4f, 58f).y)
                cubicTo(point(x + 13f, 58f).x, point(x + 13f, 58f).y, point(x + 12f, 43f).x, point(x + 12f, 43f).y, point(x + 2f, 43f).x, point(x + 2f, 43f).y)
            }, ChartBlue, 2f)
        }
        line(28f, 78f, 72f, 78f, ChartBlue, 2f)
    }

    private fun drawPlatformZone() {
        drawOffshorePlatform()
        dottedOval(20f, 23f, 80f, 80f, ChartMagenta)
    }

    private fun drawBreakers() {
        for (y in listOf(34f, 49f, 64f)) {
            path({
                moveTo(point(20f, y).x, point(20f, y).y)
                cubicTo(point(28f, y - 6f).x, point(28f, y - 6f).y, point(34f, y + 6f).x, point(34f, y + 6f).y, point(42f, y).x, point(42f, y).y)
                cubicTo(point(50f, y - 6f).x, point(50f, y - 6f).y, point(56f, y + 6f).x, point(56f, y + 6f).y, point(64f, y).x, point(64f, y).y)
                cubicTo(point(72f, y - 6f).x, point(72f, y - 6f).y, point(78f, y + 6f).x, point(78f, y + 6f).y, point(84f, y).x, point(84f, y).y)
            }, ChartBlue, 2.2f)
        }
    }

    private fun drawMarineFarm() {
        rect(23f, 30f, 77f, 70f, ChartGreen, 2f)
        for (x in 30..70 step 13) line(x.toFloat(), 32f, x.toFloat(), 68f, ChartGreen, 1.6f)
        for (y in 38..62 step 12) line(25f, y.toFloat(), 75f, y.toFloat(), ChartGreen, 1.6f)
        for (x in listOf(36f, 50f, 64f)) circle(x, 50f, 4f, ChartMagenta, outline = true)
        chartText("Farm", 50f, 83f, 10f, ChartGreen)
    }

    private fun drawFishingZone() {
        rect(22f, 30f, 78f, 70f, ChartMagenta, 2f)
        for (x in 28..72 step 12) {
            line(x.toFloat(), 30f, (x + 5).toFloat(), 35f, ChartMagenta, 1.5f)
            line(x.toFloat(), 65f, (x + 5).toFloat(), 70f, ChartMagenta, 1.5f)
        }
        path({
            moveTo(point(37f, 51f).x, point(37f, 51f).y)
            cubicTo(point(44f, 43f).x, point(44f, 43f).y, point(56f, 43f).x, point(56f, 43f).y, point(63f, 51f).x, point(63f, 51f).y)
            cubicTo(point(56f, 59f).x, point(56f, 59f).y, point(44f, 59f).x, point(44f, 59f).y, point(37f, 51f).x, point(37f, 51f).y)
        }, ChartMagenta, 2f)
        line(50f, 43f, 50f, 59f, ChartMagenta, 1.8f)
    }

    private fun drawFishingProhibited() {
        rect(22f, 30f, 78f, 70f, ChartMagenta, 2f)
        for (x in 28..72 step 12) {
            line(x.toFloat(), 30f, (x + 5).toFloat(), 35f, ChartMagenta, 1.5f)
            line(x.toFloat(), 65f, (x + 5).toFloat(), 70f, ChartMagenta, 1.5f)
        }
        path({
            moveTo(point(37f, 51f).x, point(37f, 51f).y)
            cubicTo(point(44f, 43f).x, point(44f, 43f).y, point(56f, 43f).x, point(56f, 43f).y, point(63f, 51f).x, point(63f, 51f).y)
            cubicTo(point(56f, 59f).x, point(56f, 59f).y, point(44f, 59f).x, point(44f, 59f).y, point(37f, 51f).x, point(37f, 51f).y)
        }, ChartMagenta, 2f)
        line(50f, 43f, 50f, 59f, ChartMagenta, 1.8f)
        line(31f, 36f, 69f, 66f, ChartMagenta, 3f)
    }

    private fun drawCable() {
        path({
            moveTo(point(16f, 63f).x, point(16f, 63f).y)
            cubicTo(point(30f, 43f).x, point(30f, 43f).y, point(42f, 78f).x, point(42f, 78f).y, point(55f, 58f).x, point(55f, 58f).y)
            cubicTo(point(66f, 41f).x, point(66f, 41f).y, point(75f, 56f).x, point(75f, 56f).y, point(84f, 37f).x, point(84f, 37f).y)
        }, ChartMagenta, 2.4f)
        for (x in 24..76 step 12) line(x.toFloat(), 48f, x + 4f, 44f, ChartInk, 1.6f)
    }

    private fun drawPipeline() {
        line(16f, 59f, 84f, 42f, ChartMagenta, 4f)
        for (x in 23..77 step 12) circle(x.toFloat(), 55f - (x - 16f) * 0.25f, 2.2f, ChartPaper)
        line(20f, 74f, 80f, 74f, ChartBlue, 2f)
    }

    private fun drawPowerCable() {
        path({
            moveTo(point(16f, 60f).x, point(16f, 60f).y)
            cubicTo(point(30f, 42f).x, point(30f, 42f).y, point(42f, 76f).x, point(42f, 76f).y, point(55f, 57f).x, point(55f, 57f).y)
            cubicTo(point(66f, 41f).x, point(66f, 41f).y, point(75f, 55f).x, point(75f, 55f).y, point(84f, 38f).x, point(84f, 38f).y)
        }, ChartMagenta, 2.6f)
        for (x in 24..76 step 13) {
            line(x.toFloat(), 48f, (x + 4).toFloat(), 44f, ChartInk, 1.6f)
            circle(x.toFloat(), 48f, 1.4f, ChartYellow)
        }
        chartText("Power", 50f, 82f, 9f, ChartBlue)
    }

    private fun drawUnusedPipeline() {
        for (start in listOf(16f, 42f, 68f)) {
            path({
                moveTo(point(start, 56f).x, point(start, 56f).y)
                cubicTo(point(start + 5f, 49f).x, point(start + 5f, 49f).y, point(start + 9f, 63f).x, point(start + 9f, 63f).y, point(start + 15f, 56f).x, point(start + 15f, 56f).y)
            }, ChartMagenta, 2.6f)
        }
        line(28f, 76f, 72f, 76f, ChartBlue, 1.8f)
        chartText("Not in use", 50f, 86f, 8f, ChartMagenta)
    }

    private fun drawDangerLine() {
        dottedOval(22f, 31f, 78f, 71f, ChartInk)
        line(39f, 45f, 61f, 57f, ChartMagenta, 2.6f)
        line(61f, 45f, 39f, 57f, ChartMagenta, 2.6f)
        circle(50f, 51f, 3f, ChartMagenta)
    }

    private fun drawSweptDrag(notation: String?) {
        dottedOval(28f, 30f, 72f, 69f)
        line(34f, 70f, 34f, 78f, width = 2f)
        line(66f, 70f, 66f, 78f, width = 2f)
        line(34f, 78f, 66f, 78f, width = 2f)
        chartText(notation ?: "3", 50f, 52f, 16f, ChartBlue)
    }

    private fun drawCoastline() {
        path({
            moveTo(point(15f, 30f).x, point(15f, 30f).y)
            lineTo(point(42f, 35f).x, point(42f, 35f).y)
            cubicTo(point(52f, 37f).x, point(52f, 37f).y, point(57f, 47f).x, point(57f, 47f).y, point(67f, 49f).x, point(67f, 49f).y)
            cubicTo(point(74f, 51f).x, point(74f, 51f).y, point(80f, 58f).x, point(80f, 58f).y, point(86f, 66f).x, point(86f, 66f).y)
        }, ChartInk, 3f)
        scope.drawRect(
            color = ChartGreen.copy(alpha = 0.18f),
            topLeft = point(15f, 30f),
            size = Size(71f * scale, 38f * scale),
            style = Fill
        )
        wave(78f)
        wave(87f)
    }

    private fun drawCliff() {
        line(20f, 32f, 80f, 32f, ChartInk, 3f)
        for (x in 24..76 step 8) {
            line(x.toFloat(), 32f, (x - 4).toFloat(), 47f, ChartInk, 2f)
            line((x - 4).toFloat(), 47f, (x + 2).toFloat(), 59f, ChartInk, 2f)
        }
        wave(76f)
    }

    private fun drawLand() {
        path({
            moveTo(point(22f, 33f).x, point(22f, 33f).y)
            lineTo(point(78f, 33f).x, point(78f, 33f).y)
            lineTo(point(72f, 70f).x, point(72f, 70f).y)
            lineTo(point(27f, 70f).x, point(27f, 70f).y)
            close()
        }, ChartGreen.copy(alpha = 0.16f), 2f, fill = true)
        hatch(28f, 38f, 72f, 66f, ChartGreen)
    }

    private fun drawVegetation() {
        for (x in listOf(31f, 50f, 69f)) {
            line(x, 68f, x, 43f, ChartGreen, 2.2f)
            circle(x, 39f, 7f, ChartGreen, outline = true)
            circle(x - 5f, 47f, 5f, ChartGreen, outline = true)
            circle(x + 5f, 47f, 5f, ChartGreen, outline = true)
        }
        line(23f, 75f, 77f, 75f, ChartGreen, 2f)
    }

    private fun drawBuilding() {
        rect(30f, 42f, 70f, 72f, ChartInk, 2.5f)
        path({
            moveTo(point(26f, 42f).x, point(26f, 42f).y)
            lineTo(point(50f, 26f).x, point(50f, 26f).y)
            lineTo(point(74f, 42f).x, point(74f, 42f).y)
            close()
        }, ChartInk, 2.5f)
        rect(45f, 57f, 55f, 72f, ChartMagenta, 2f)
        circle(39f, 52f, 3f, ChartBlue, outline = true)
        circle(61f, 52f, 3f, ChartBlue, outline = true)
    }

    private fun drawLandmark() {
        line(50f, 23f, 50f, 76f, ChartInk, 3f)
        path({
            moveTo(point(40f, 76f).x, point(40f, 76f).y)
            lineTo(point(60f, 76f).x, point(60f, 76f).y)
            lineTo(point(57f, 38f).x, point(57f, 38f).y)
            lineTo(point(43f, 38f).x, point(43f, 38f).y)
            close()
        }, ChartInk, 2.5f)
        line(43f, 38f, 57f, 38f, ChartMagenta, 2.5f)
        line(43f, 30f, 57f, 30f, ChartInk, 2f)
    }

    private fun drawRoad() {
        line(20f, 72f, 43f, 57f, ChartInk, 5f)
        line(43f, 57f, 80f, 32f, ChartInk, 5f)
        line(20f, 72f, 43f, 57f, ChartPaper, 1.5f)
        line(43f, 57f, 80f, 32f, ChartPaper, 1.5f)
        for (x in 30..72 step 14) line(x.toFloat(), 63f - (x - 30f) * 0.55f, (x + 5).toFloat(), 60f - (x - 30f) * 0.55f, ChartInk, 1.2f)
    }

    private fun drawBridge() {
        line(23f, 65f, 77f, 65f, ChartInk, 3f)
        line(30f, 65f, 30f, 82f, ChartInk, 3f)
        line(70f, 65f, 70f, 82f, ChartInk, 3f)
        path({
            moveTo(point(29f, 65f).x, point(29f, 65f).y)
            cubicTo(point(36f, 34f).x, point(36f, 34f).y, point(64f, 34f).x, point(64f, 34f).y, point(71f, 65f).x, point(71f, 65f).y)
        }, ChartMagenta, 2.4f)
        wave(87f)
    }

    private fun drawAirfield() {
        rect(20f, 33f, 80f, 68f, ChartInk, 2.2f)
        line(27f, 50f, 73f, 50f, ChartInk, 4f)
        line(36f, 42f, 36f, 58f, ChartInk, 2f)
        line(64f, 42f, 64f, 58f, ChartInk, 2f)
        line(50f, 40f, 50f, 60f, ChartPaper, 1.5f)
        line(30f, 78f, 70f, 78f, ChartGreen, 2f)
    }

    private fun drawAnchorage() {
        path({
            moveTo(point(22f, 28f).x, point(22f, 28f).y)
            cubicTo(point(35f, 18f).x, point(35f, 18f).y, point(67f, 18f).x, point(67f, 18f).y, point(79f, 29f).x, point(79f, 29f).y)
            cubicTo(point(88f, 43f).x, point(88f, 43f).y, point(82f, 71f).x, point(82f, 71f).y, point(70f, 78f).x, point(70f, 78f).y)
            cubicTo(point(54f, 87f).x, point(54f, 87f).y, point(29f, 82f).x, point(29f, 82f).y, point(21f, 67f).x, point(21f, 67f).y)
            close()
        }, ChartMagenta, 2.4f)
        hatch(25f, 23f, 76f, 70f)
        drawAnchor()
    }

    private fun drawAnchor() {
        line(50f, 35f, 50f, 69f, ChartInk, 3.4f)
        line(39f, 46f, 61f, 46f, ChartInk, 3.2f)
        circle(50f, 31f, 4f, ChartInk, outline = true)
        line(50f, 69f, 37f, 79f, ChartInk, 3.4f)
        line(50f, 69f, 63f, 79f, ChartInk, 3.4f)
    }

    private fun drawRestricted() {
        rect(22f, 27f, 78f, 73f, ChartMagenta, 2.6f)
        hatch(25f, 30f, 75f, 70f)
        line(31f, 39f, 69f, 61f, ChartMagenta, 3f)
        line(69f, 39f, 31f, 61f, ChartMagenta, 3f)
        line(39f, 79f, 61f, 79f, ChartInk, 2f)
    }

    private fun drawNoAnchor() {
        drawAnchor()
        line(27f, 28f, 73f, 75f, ChartMagenta, 4f)
    }

    private fun drawProhibitedArea() {
        rect(22f, 29f, 78f, 72f, ChartMagenta, 2f)
        for (x in 27..73 step 12) {
            line(x.toFloat(), 31f, (x + 6).toFloat(), 37f, ChartMagenta, 1.5f)
            line(x.toFloat(), 64f, (x + 6).toFloat(), 70f, ChartMagenta, 1.5f)
        }
        line(36f, 42f, 64f, 60f, ChartMagenta, 3f)
        line(64f, 42f, 36f, 60f, ChartMagenta, 3f)
        chartText("PROHIBITED", 50f, 83f, 8f, ChartMagenta)
    }

    private fun drawMooringBuoy() {
        wave(79f)
        wave(88f)
        line(50f, 24f, 50f, 40f, width = 2.2f)
        circle(50f, 48f, 10f, ChartInk, outline = true)
        line(43f, 55f, 57f, 55f, ChartMagenta, 2.6f)
        line(43f, 61f, 57f, 61f, ChartMagenta, 2.6f)
        line(50f, 58f, 50f, 73f, ChartInk, 2.2f)
        line(41f, 73f, 59f, 73f, ChartInk, 2.4f)
    }

    private fun drawOffshorePlatform() {
        rect(30f, 46f, 70f, 72f, ChartInk, 2.4f)
        line(38f, 46f, 38f, 31f, ChartInk, 2.2f)
        line(62f, 46f, 62f, 31f, ChartInk, 2.2f)
        line(32f, 39f, 68f, 39f, ChartInk, 2.4f)
        line(38f, 31f, 62f, 31f, ChartInk, 2.2f)
        circle(50f, 23f, 4f, ChartMagenta)
        line(50f, 27f, 50f, 31f, ChartMagenta, 2f)
        line(26f, 77f, 74f, 77f, ChartBlue, 2f)
    }

    private fun drawLightship() {
        wave(79f)
        wave(88f)
        path({
            moveTo(point(31f, 57f).x, point(31f, 57f).y)
            lineTo(point(39f, 43f).x, point(39f, 43f).y)
            lineTo(point(61f, 43f).x, point(61f, 43f).y)
            lineTo(point(69f, 57f).x, point(69f, 57f).y)
            close()
        }, ChartInk, 2.6f)
        circle(50f, 56f, 4f, ChartMagenta, outline = true)
        line(50f, 43f, 50f, 27f, ChartInk, 2.4f)
        circle(50f, 23f, 4f, ChartMagenta)
        line(43f, 65f, 57f, 65f, ChartMagenta, 2f)
    }

    private fun drawMinaret() {
        line(50f, 71f, 50f, 39f, ChartInk, 2.8f)
        circle(50f, 52f, 9f, ChartPaper, outline = true)
        circle(50f, 52f, 3f, ChartInk)
        line(50f, 39f, 42f, 27f, ChartInk, 2.4f)
        line(50f, 39f, 58f, 27f, ChartInk, 2.4f)
        circle(42f, 25f, 3f, ChartMagenta)
        circle(58f, 25f, 3f, ChartMagenta)
        line(41f, 75f, 59f, 75f, ChartInk, 2.4f)
    }

    private fun drawBuoyageDirection() {
        line(50f, 76f, 50f, 27f, ChartMagenta, 3f)
        path({
            moveTo(point(50f, 22f).x, point(50f, 22f).y)
            lineTo(point(42f, 34f).x, point(42f, 34f).y)
            lineTo(point(58f, 34f).x, point(58f, 34f).y)
            close()
        }, ChartMagenta, 2f, fill = true)
        path({
            moveTo(point(50f, 81f).x, point(50f, 81f).y)
            lineTo(point(42f, 69f).x, point(42f, 69f).y)
            lineTo(point(58f, 69f).x, point(58f, 69f).y)
            close()
        }, ChartBlue, 2f, fill = true)
    }

    private fun drawPilotTransfer() {
        oval(28f, 29f, 72f, 73f, ChartInk, outline = true, width = 2f)
        path({
            moveTo(point(50f, 35f).x, point(50f, 35f).y)
            lineTo(point(62f, 51f).x, point(62f, 51f).y)
            lineTo(point(50f, 67f).x, point(50f, 67f).y)
            lineTo(point(38f, 51f).x, point(38f, 51f).y)
            close()
        }, ChartInk, 2f, fill = true)
        circle(50f, 51f, 4f, ChartPaper)
    }

    private fun drawFarm() {
        rect(22f, 28f, 78f, 72f, ChartGreen, 2f)
        for (x in 28..72 step 11) line(x.toFloat(), 30f, x.toFloat(), 70f, ChartGreen, 1.6f)
        for (y in 36..66 step 10) line(24f, y.toFloat(), 76f, y.toFloat(), ChartGreen, 1.6f)
        circle(50f, 50f, 5f, ChartMagenta, outline = true)
    }

    private fun drawDumping() {
        path({
            moveTo(point(24f, 35f).x, point(24f, 35f).y)
            lineTo(point(76f, 35f).x, point(76f, 35f).y)
            lineTo(point(68f, 70f).x, point(68f, 70f).y)
            lineTo(point(31f, 70f).x, point(31f, 70f).y)
            close()
        }, ChartMagenta, 2.5f)
        for (x in listOf(34f, 44f, 55f, 65f)) circle(x, 51f + (x % 3f) * 3f, 2f, ChartMagenta)
        line(32f, 80f, 68f, 80f, ChartBlue, 2f)
    }

    private fun drawRoute() {
        line(22f, 70f, 78f, 30f, ChartMagenta, 3f)
        for (x in 31..67 step 14) line(x.toFloat(), 63f - (x - 22f) * 0.72f, (x + 7).toFloat(), 58f - (x - 22f) * 0.72f, ChartMagenta, 1.4f)
        path({
            moveTo(point(78f, 30f).x, point(78f, 30f).y)
            lineTo(point(67f, 31f).x, point(67f, 31f).y)
            lineTo(point(74f, 41f).x, point(74f, 41f).y)
            close()
        }, ChartMagenta, 2f, fill = true)
    }

    private fun drawTrafficSeparation() {
        line(18f, 70f, 80f, 29f, ChartMagenta, 2.8f)
        line(20f, 81f, 82f, 40f, ChartMagenta, 2.8f)
        hatch(34f, 52f, 60f, 67f, ChartMagenta)
        line(37f, 58f, 54f, 47f, ChartInk, 2f)
        line(53f, 48f, 47f, 48f, ChartInk, 2f)
        line(53f, 48f, 49f, 53f, ChartInk, 2f)
    }

    private fun drawPort() {
        wave(78f)
        wave(87f)
        line(21f, 37f, 21f, 71f, ChartInk, 4f)
        line(21f, 71f, 79f, 71f, ChartInk, 4f)
        for (x in 31..71 step 13) line(x.toFloat(), 61f, x.toFloat(), 71f, ChartMagenta, 2f)
        rect(33f, 34f, 66f, 48f, ChartInk, 2.2f)
        line(40f, 41f, 59f, 41f, ChartMagenta, 2f)
    }

    private fun drawQuay() {
        line(23f, 29f, 23f, 75f, ChartInk, 4f)
        line(23f, 75f, 78f, 75f, ChartInk, 4f)
        for (y in 36..68 step 9) line(23f, y.toFloat(), 30f, y.toFloat(), ChartMagenta, 2f)
        for (x in 38..70 step 12) line(x.toFloat(), 68f, x.toFloat(), 75f, ChartBlue, 2f)
    }

    private fun drawMarina() {
        wave(78f)
        wave(87f)
        line(25f, 31f, 25f, 71f, ChartInk, 3f)
        line(25f, 71f, 77f, 71f, ChartInk, 3f)
        for (x in 34..68 step 12) {
            line(x.toFloat(), 46f, x.toFloat(), 71f, ChartBlue, 1.8f)
            path({
                moveTo(point((x - 5).toFloat(), 50f).x, point((x - 5).toFloat(), 50f).y)
                lineTo(point(x.toFloat(), 42f).x, point(x.toFloat(), 42f).y)
                lineTo(point((x + 5).toFloat(), 50f).x, point((x + 5).toFloat(), 50f).y)
            }, ChartMagenta, 1.8f)
        }
    }

    private fun drawLock() {
        rect(25f, 29f, 75f, 73f, ChartInk, 2.8f)
        line(25f, 45f, 75f, 45f, ChartMagenta, 2.4f)
        line(25f, 58f, 75f, 58f, ChartMagenta, 2.4f)
        line(39f, 30f, 39f, 72f, ChartBlue, 2f)
        line(61f, 30f, 61f, 72f, ChartBlue, 2f)
        wave(82f)
    }

    private fun drawCrane() {
        line(32f, 77f, 32f, 37f, ChartInk, 3f)
        line(32f, 37f, 70f, 37f, ChartInk, 3f)
        line(70f, 37f, 70f, 63f, ChartInk, 2f)
        line(32f, 48f, 70f, 37f, ChartMagenta, 2.5f)
        line(70f, 63f, 70f, 73f, ChartMagenta, 2f)
        circle(70f, 75f, 3f, ChartMagenta)
    }

    private fun drawPilot() {
        circle(50f, 51f, 18f, ChartMagenta, outline = true)
        line(50f, 33f, 50f, 69f, ChartMagenta, 2.5f)
        line(38f, 42f, 62f, 42f, ChartMagenta, 2.5f)
        line(38f, 60f, 62f, 60f, ChartMagenta, 2.5f)
        line(35f, 78f, 65f, 78f, ChartInk, 2f)
    }

    private fun drawService() {
        line(50f, 24f, 50f, 76f, ChartInk, 2.8f)
        path({
            moveTo(point(50f, 28f).x, point(50f, 28f).y)
            lineTo(point(73f, 35f).x, point(73f, 35f).y)
            lineTo(point(50f, 43f).x, point(50f, 43f).y)
            close()
        }, ChartMagenta, 2f, fill = true)
        line(38f, 76f, 62f, 76f, ChartInk, 3f)
        line(41f, 82f, 59f, 82f, ChartBlue, 2f)
    }

    private fun drawCompass() {
        circle(50f, 51f, 28f, ChartInk, outline = true)
        path({
            moveTo(point(50f, 20f).x, point(50f, 20f).y)
            lineTo(point(55f, 51f).x, point(55f, 51f).y)
            lineTo(point(50f, 82f).x, point(50f, 82f).y)
            lineTo(point(45f, 51f).x, point(45f, 51f).y)
            close()
        }, ChartMagenta, 2f, fill = true)
        line(20f, 51f, 80f, 51f, ChartInk, 1.8f)
        line(50f, 21f, 50f, 81f, ChartInk, 1.8f)
        circle(50f, 51f, 3f, ChartInk)
    }

    private fun drawScale() {
        line(23f, 68f, 77f, 68f, ChartInk, 3f)
        line(23f, 61f, 23f, 75f, ChartInk, 2f)
        line(41f, 61f, 41f, 75f, ChartInk, 2f)
        line(59f, 61f, 59f, 75f, ChartInk, 2f)
        line(77f, 61f, 77f, 75f, ChartInk, 2f)
        rect(23f, 61f, 41f, 68f, ChartInk, fill = true)
        rect(59f, 61f, 77f, 68f, ChartInk, fill = true)
        line(33f, 37f, 67f, 37f, ChartBlue, 2f)
        line(33f, 43f, 67f, 43f, ChartBlue, 2f)
    }

    private fun drawNote() {
        rect(25f, 28f, 75f, 72f, ChartInk, 2.4f)
        line(34f, 40f, 66f, 40f, ChartMagenta, 2.4f)
        line(34f, 50f, 66f, 50f, ChartInk, 2f)
        line(34f, 59f, 58f, 59f, ChartInk, 2f)
        line(34f, 68f, 63f, 68f, ChartInk, 2f)
        circle(50f, 40f, 3f, ChartMagenta)
    }

    private fun drawDigit(digit: Int, left: Float, top: Float, color: Color, width: Float = 2.2f) {
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
        val lines = listOf(
            floatArrayOf(left + 2f, top, right - 2f, top),
            floatArrayOf(right, top + 2f, right, middle - 1f),
            floatArrayOf(right, middle + 1f, right, bottom - 2f),
            floatArrayOf(left + 2f, bottom, right - 2f, bottom),
            floatArrayOf(left, middle + 1f, left, bottom - 2f),
            floatArrayOf(left, top + 2f, left, middle - 1f),
            floatArrayOf(left + 2f, middle, right - 2f, middle)
        )
        segments.forEach { id ->
            val segment = lines[id]
            line(segment[0], segment[1], segment[2], segment[3], color, width)
        }
    }
}
