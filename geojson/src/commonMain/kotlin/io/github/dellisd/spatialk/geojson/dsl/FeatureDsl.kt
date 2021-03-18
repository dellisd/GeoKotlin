@file:JvmName("-FeatureDslKt")

package io.github.dellisd.spatialk.geojson.dsl

import io.github.dellisd.spatialk.geojson.BoundingBox
import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.Geometry
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlin.jvm.JvmName

@GeoJsonDsl
class FeatureDsl(
    var geometry: Geometry? = null,
    var bbox: BoundingBox? = null,
    var id: String? = null,
    private val properties: MutableMap<String, JsonElement> = mutableMapOf()
) {
    fun create() = Feature(geometry, id = id, bbox = bbox, properties = properties)

    inner class PropertiesDsl {
        infix fun String.to(string: String?) {
            properties[this] = JsonPrimitive(string)
        }

        infix fun String.to(number: Number) {
            properties[this] = JsonPrimitive(number)
        }

        infix fun String.to(boolean: Boolean) {
            properties[this] = JsonPrimitive(boolean)
        }

        infix fun String.to(json: JsonElement) {
            properties[this] = json
        }
    }

    fun properties(block: PropertiesDsl.() -> Unit) {
        PropertiesDsl().apply(block)
    }
}

inline fun feature(block: FeatureDsl.() -> Unit) = FeatureDsl()
    .apply(block).create()
