# Unit Converter (Extendable)

A lightweight unit converter for converting values between different units across dimensions like distance and
temperature. You can extend by adding more dimensions and converters.

### Features

- Convert between common units (e.g., kilometers to miles, Celsius to Fahrenheit).
- Smart unit matching (matches "km", "Km", "Kilometers", "KiloMeters", etc.).
- Customizable output formatting.
- Easy to extend with new units and dimensions.

### Example Usage

```kotlin
val converter = DistanceConverter()
val result = converter.convert(5.0, "km", "miles")
val formatted = converter.convertToString(5.0, "km", "miles") { value, symbol ->
    "${"%.2f".format(value)} $symbol"
}
println("Unformatted result: $result")
println("Formatted result: $formatted")

// Prints:
// Unformatted result: 3.107520198881293
// Formatted result: 3.11 miles
```

### Adding new Dimensions

You can extend the `DimensionConverter` class to add new dimensions. Here's an example:

```kotlin
class TimeConverter : DimensionConverter("Seconds") {

    override val converters = mapOf(
        "Seconds" to SimpleUnitConverter(
            name = "Seconds",
            symbol = "s",
            convert = { it },
            toBaseUnit = { it }
        ),
        "Minutes" to SimpleUnitConverter(
            name = "Minutes",
            symbol = "min",
            convert = { it / 60 },
            toBaseUnit = { it * 60 }
        ),
        "Hours" to SimpleUnitConverter(
            name = "Hours",
            symbol = "h",
            convert = { it / 3600 },
            toBaseUnit = { it * 3600 }
        ),
        "Days" to SimpleUnitConverter(
            name = "Days",
            symbol = "d",
            convert = { it / 86400 },
            toBaseUnit = { it * 86400 }
        )
    )

}
```

