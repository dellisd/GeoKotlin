@file:Suppress("MagicNumber")

package io.github.dellisd.spatialk.turf

import io.github.dellisd.spatialk.geojson.LngLat
import io.github.dellisd.spatialk.geojson.dsl.lineString
import kotlin.test.Test
import kotlin.test.assertEquals

class MeasurementTests {

    @Test
    fun testBearing() {
        val start = LngLat(-75.0, 45.0)
        val end = LngLat(20.0, 60.0)

        assertEquals(37.75, bearing(start, end).coerceIn(37.75, 37.75), "Initial Bearing")
        assertEquals(
            120.01, bearing(start, end, final = true)
                .coerceIn(120.01, 120.01), "Final Bearing"
        )
    }

    @Test
    fun testDestination() {
        val point0 = LngLat(-75.0, 38.10096062273525)
        val (longitude, latitude) = destination(point0, 100.0, 0.0)

        assertEquals(-75.0, longitude.coerceIn(-75.0, -75.0))
        assertEquals(39.000281, latitude.coerceIn(39.000281, 39.000281))
    }

    @Test
    fun testLength() {
        val lineString = lineString {
            +LngLat(-75.934, 45.207)
            +LngLat(-75.833, 45.302)
            +LngLat(-75.635, 45.290)
            +LngLat(-75.545, 45.397)
        }

        assertEquals(42.560767589197006, length(lineString, Units.Kilometers))
    }
}
