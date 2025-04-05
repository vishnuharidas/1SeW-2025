package w14_converter

import java.text.DecimalFormat

fun main() {

    data class SimpleUnitConverter(
        val name: String,
        val symbol: String = name,
        val convert: (baseUnit: Double) -> Double,
        val toBaseUnit: (value: Double) -> Double, // Every measurement has to be converted to a base unit for easy conversion to other units.
        val toString: (value: Double, unit: String) -> String = { value, unit -> "$value $unit" }
    )

    abstract class DimensionConverter(
        protected val baseUnitName: String
    ) {
        protected abstract val converters: Map<String, SimpleUnitConverter>

        // Just to search all possible combinations of the input - "km", "Km", "Kilometers", "KiloMeters", etc.
        // Building 3 maps of STRING to CONVERTER
        private val converters1 by lazy {
            converters.map { it.key.uppercase() to it.value }.associate { it.first to it.second }
        }
        private val converters2 by lazy { converters.values.associateBy { it.name.uppercase() } }
        private val converters3 by lazy { converters.values.associateBy { it.symbol.uppercase() } }

        fun convert(fromValue: Double, fromUnit: String, toUnit: String): Double {

            if (fromUnit == toUnit) return fromValue

            val (baseUnitVal, converter) = find(fromValue, fromUnit, toUnit)

            return converter.convert(baseUnitVal)
        }

        fun convertToString(
            fromValue: Double,
            fromUnit: String,
            toUnit: String,
            formatter: ((value: Double, symbol: String) -> String)? = null
        ): String {

            val (baseUnitVal, converter) = find(fromValue, fromUnit, toUnit)

            val converted = converter.convert(baseUnitVal)

            return formatter?.invoke(converted, converter.symbol)
                ?: converter.toString(converted, converter.symbol)

        }

        private fun find(fromValue: Double, fromUnit: String, toUnit: String): Pair<Double, SimpleUnitConverter> {

            // Search in all possible way.
            val converterFrom: SimpleUnitConverter = converters1[fromUnit.uppercase()]
                ?: converters2[fromUnit.uppercase()]
                ?: converters3[fromUnit.uppercase()]
                ?: throw IllegalArgumentException("Unable to parse the unit \"$fromUnit\".")

            val converterTo: SimpleUnitConverter = converters1[toUnit.uppercase()]
                ?: converters2[toUnit.uppercase()]
                ?: converters3[toUnit.uppercase()]
                ?: throw IllegalArgumentException("Unable to find the unit \"$toUnit\".")

            val baseUnitVal = converterFrom.toBaseUnit(fromValue)

            return baseUnitVal to converterTo
        }

        fun unitsList() = converters.keys

    }

    class DistanceConverter : DimensionConverter("Meters") {

        override val converters = mapOf(
            baseUnitName to SimpleUnitConverter(
                name = baseUnitName,
                symbol = "m",
                convert = { it },
                toBaseUnit = { it }),
            "Nanometers" to SimpleUnitConverter(
                name = "Nanometers",
                symbol = "nm",
                convert = { it * 1_000_000_000 },
                toBaseUnit = { it / 1_000_000_000 }),
            "Micrometers" to SimpleUnitConverter(
                name = "Micrometers",
                symbol = "micro",
                convert = { it * 1_000_000 },
                toBaseUnit = { it / 1_000_000 }),
            "Millimeters" to SimpleUnitConverter(
                name = "Millimeters",
                symbol = "mm",
                convert = { it * 1_000 },
                toBaseUnit = { it / 1_000 }),
            "Centimeters" to SimpleUnitConverter(
                name = "Centimeters",
                symbol = "cm",
                convert = { it * 100 },
                toBaseUnit = { it / 100 }),
            "Kilometers" to SimpleUnitConverter(
                name = "Kilometers",
                "km",
                convert = { it / 1000 },
                toBaseUnit = { it * 1000 }),
            "Miles" to SimpleUnitConverter(
                name = "Miles",
                symbol = "miles",
                convert = { it / 1609 },
                toBaseUnit = { it * 1609 }),
            "Yards" to SimpleUnitConverter(
                name = "Yards",
                symbol = "yards",
                convert = { it * 1.09361 },
                toBaseUnit = { it / 1.09361 }),
            "Feet" to SimpleUnitConverter(
                name = "Feet",
                symbol = "ft",
                convert = { it * 3.28084 },
                toBaseUnit = { it / 3.28084 }),
            "Inches" to SimpleUnitConverter(
                name = "Inches",
                symbol = "in",
                convert = { it * 39.3701 },
                toBaseUnit = { it / 39.3701 }),
            "Knots" to SimpleUnitConverter(
                name = "Knots",
                symbol = "knot",
                convert = { it / 1852 },
                toBaseUnit = { it * 1852 }),
        )

    }

    class TemperatureConverter : DimensionConverter("Celsius") {
        override val converters = mapOf(
            baseUnitName to SimpleUnitConverter(
                name = baseUnitName,
                symbol = "°C",
                convert = { it },
                toBaseUnit = { it }
            ),
            "Fahrenheit" to SimpleUnitConverter(
                name = "Fahrenheit",
                symbol = "°F",
                convert = { it * 9 / 5 + 32 },
                toBaseUnit = { (it - 32) * 5 / 9 }
            ),
            "Kelvin" to SimpleUnitConverter(
                name = "Kelvin",
                symbol = "K",
                convert = { it + 273.15 },
                toBaseUnit = { it - 273.15 }
            )
        )
    }

    // Distance Conversion
    val formatter = DecimalFormat("#.###")

    val distanceConverter = DistanceConverter()

    val fromValue = 455.0
    val fromUnit = "Yards"

    distanceConverter.unitsList().forEach {

        val converted = distanceConverter.convert(fromValue, fromUnit, it)
        val convertedStr = distanceConverter.convertToString(fromValue, fromUnit, it) { value, symbol ->
            "${formatter.format(value)} ${symbol.lowercase()}"
        }

        println("$fromValue $fromUnit is $converted >> $convertedStr")

    }

    // Temperature
    println("Temperature Conversion - ")
    val formatterTemp = DecimalFormat("#.###")

    val temperatureConverter = TemperatureConverter()

    val fromValueTemp = 100.0
    val fromUnitTemp = "Celsius"

    temperatureConverter.unitsList().forEach {

        val converted = temperatureConverter.convert(fromValueTemp, fromUnitTemp, it)
        val convertedStr = temperatureConverter.convertToString(fromValueTemp, fromUnitTemp, it) { value, symbol ->

            val newSymbol = when {
                symbol.contains("C") -> "Degree Celsius"
                symbol.contains("F") -> "Degree Fahrenheit"
                symbol.contains("K") -> "Kelvin"
                else -> symbol
            }
            "${formatterTemp.format(value)} $newSymbol"
        }

        println("$fromValueTemp $fromUnitTemp is $converted >> $convertedStr")

    }

    val fh = temperatureConverter.convertToString(37.0, "Celsius", "Fahrenheit")
    val cel = temperatureConverter.convertToString(37.0, "Celsius", "Celsius")
    val kel = temperatureConverter.convertToString(37.0, "Celsius", "Kelvin")
    println("Average human body temperature is $cel, which is: $fh or $kel")


}