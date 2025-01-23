package w04_same_gmail_address

/**
 * Email Variation Generator: This code for generating dot variations in email local parts
 * and combining them with Gmail-style "+suffix" aliases was generated using ChatGPT.
 */


fun main() {

    // Function to generate all possible dot variations for the local part
    fun generateDotVariations(localPart: String): List<String> {
        val result = mutableListOf<String>()
        val n = localPart.length

        // Use a bitmask to insert dots at different positions
        for (mask in 0 until (1 shl (n - 1))) {
            val sb = StringBuilder()
            for (i in 0 until n) {
                sb.append(localPart[i])
                if (i < n - 1 && (mask and (1 shl i)) != 0) {
                    sb.append(".")
                }
            }
            result.add(sb.toString())
        }
        return result
    }

    fun generateGmailVariations(baseEmail: String): List<String> {
        val localPart = baseEmail.substringBefore("@")
        val domain = baseEmail.substringAfter("@")

        // Generate all dot variations
        val dotVariations = generateDotVariations(localPart)

        // Generate variations with +suffix
        val plusSuffixes = listOf("test", "filter", "spam", "promo", "123") // Add more as needed
        val plusVariations = dotVariations.flatMap { variation ->
            plusSuffixes.map { suffix -> "$variation+$suffix@$domain" }
        }

        // Combine all variations (dot variations + plus variations)
        return dotVariations.map { "$it@$domain" } + plusVariations
    }


    val baseEmail = "example@gmail.com"
    val variations = generateGmailVariations(baseEmail)

    // Print all variations (for testing purposes) in `listOf(..)` syntax.
    println("variations = listOf(")
    variations.forEach { println("\"$it\",") }
    println(")")

}
