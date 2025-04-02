package w14_converter

import java.text.DecimalFormat

fun main() {

    data class SimpleUnit(
        val name: String,
        val convert: (baseUnit: Double) -> Double,
        val toBaseUnit: (value: Double) -> Double,
        val toString: (Double) -> String = { "$it $name" }
    )

    abstract class DimensionConverter(
        protected val baseUnitName: String
    ) {
        protected abstract val converters: List<SimpleUnit>

        abstract fun convert(fromValue: Double, fromUnit: String, toUnit: String): Double

        abstract fun unitsList(): List<String>
    }

    class DistanceConverter : DimensionConverter("m") {

        override val converters = listOf(
            SimpleUnit(name = "nano", convert = { it * 1_000_000_000 }, toBaseUnit = { it / 1_000_000_000 }),
            SimpleUnit(name = "micro", convert = { it * 1_000_000 }, toBaseUnit = { it / 1_000_000 }),
            SimpleUnit(name = "mm", convert = { it * 1_000 }, toBaseUnit = { it / 1_000 }),
            SimpleUnit(name = "cm", convert = { it * 100 }, toBaseUnit = { it / 100 }),
            SimpleUnit(name = baseUnitName, convert = { it }, toBaseUnit = { it }),
            SimpleUnit(name = "km", convert = { it / 1000 }, toBaseUnit = { it * 1000 }),
            SimpleUnit(name = "miles", convert = { it / 1609 }, toBaseUnit = { it * 1609 }),
            SimpleUnit(name = "yards", convert = { it * 1.09361 }, toBaseUnit = { it / 1.09361 }),
            SimpleUnit(name = "ft", convert = { it * 3.28084 }, toBaseUnit = { it / 3.28084 }),
            SimpleUnit(name = "in", convert = { it * 39.3701 }, toBaseUnit = { it / 39.3701 }),
            SimpleUnit(name = "knot", convert = { it / 1852 }, toBaseUnit = { it * 1852 }),
        )

        override fun convert(fromValue: Double, fromUnit: String, toUnit: String): Double {

            if (fromUnit == toUnit) return fromValue //

            val baseUnitVal = converters.find { it.name == fromUnit }?.toBaseUnit?.invoke(fromValue)
                ?: throw RuntimeException("Unable to parse the unit \"$fromUnit\".")

            val converter = converters.find { it.name == toUnit }
                ?: throw RuntimeException("Unable to find the unit \"$toUnit\".")

            return converter.convert(baseUnitVal)
        }

        override fun unitsList() = converters.map { it.name }

    }

    val formatter = DecimalFormat("#.#########")

    val distanceConverter = DistanceConverter()

    val fromValue = 455.0
    val fromUnit = "yards"

    distanceConverter.unitsList().forEach {

        val converted = distanceConverter.convert(fromValue, fromUnit, it)

        println("$fromValue $fromUnit is ${formatter.format(converted)} $it")

    }

}