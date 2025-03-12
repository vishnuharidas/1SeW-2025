package w11_password_generator

fun main() {

    fun generatePassword(
        length: Int = 16,
        digits: Int = 3,
        specialChars: Int = 3,
        includeUppercase: Boolean = true,
        includeLowercase: Boolean = true,
    ): String {

        require(length > digits + specialChars) { "Error: Length ($length) must be greater than the count of digits ($digits) and special characters ($specialChars)." }
        require(includeUppercase || includeLowercase) { "Error: Both `includeUppercase` and `includeLowercase` cannot be false at the same time." }

        val uc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toList()
        val lc = "abcdefghijklmnopqrstuvwxyz".toList()
        val dg = "01234567890".toList()
        val sp = "~!@#$%^&*()_+-={}[]:\";'<>?,/".toList()

        val chars = mutableListOf<Char>().apply {

            // Pick digits
            repeat(digits) {
                add(dg.random())
            }

            // Pick special characters
            repeat(specialChars) {
                add(sp.random())
            }

            // Pick at least one Uppercase
            if (includeUppercase && size < length) {
                add(uc.random())
            }

            // Pick at least one Lowercase
            if (includeLowercase && size < length) {
                add(lc.random())
            }

            // Pick remaining from a pool of mixed character set.
            val remaining = length - size
            val pool = when {
                !includeUppercase -> lc
                !includeLowercase -> uc
                else -> uc + lc
            }
            repeat(remaining) {
                add(pool.random())
            }

        }

        return chars
            .shuffled()
            .joinToString("")

    }

    println("Passwords: (default)")
    repeat(10) {
        println(generatePassword())
    }

    println()
    println("Passwords: (length = 20, specialChars = 5, digits = 5)")
    repeat(10) {
        println(generatePassword(length = 20, specialChars = 5, digits = 5))
    }

    println()
    println("Passwords: (length = 20, specialChars = 5, digits = 5, includeUppercase = false)")
    repeat(10) {
        println(generatePassword(length = 20, specialChars = 5, digits = 5, includeUppercase = false))
    }

    println()
    println("Passwords: (length = 20, specialChars = 5, digits = 5, includeLowercase = false)")
    repeat(10) {
        println(generatePassword(length = 20, specialChars = 5, digits = 5, includeLowercase = false))
    }

    println()
    println("Passwords: (length = 10, specialChars = 1, digits = 1)")
    repeat(10) {
        println(generatePassword(length = 10, specialChars = 8, digits = 1))
    }

    println()
    println("Passwords: (length = 64, specialChars = 10, digits = 10, includeUppercase = false)")
    repeat(10) {
        println(generatePassword(length = 64, specialChars = 10, digits = 10, includeUppercase = false))
    }

}