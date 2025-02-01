package w05_byte_to_store_8_flags

// Sample use case
private enum class Privilege {
    READ_EMAIL,
    WRITE_EMAIL,
    READ_USERS,
    WRITE_USERS,
    READ_PROJECTS,
    WRITE_PROJECTS,
    MANAGE_RESOURCES,
    MANAGE_SERVICES,
}

fun main() {

    class FlagByte<T>(
        private val flags: List<T>
    ) {
        private var byte: UByte = 0b0000_0000u

        init {
            require(flags.size == 8) { "There should be only 8 flags in the list. It has ${flags.size} flags." }
        }

        fun getFlag(flag: T): Boolean {

            val pos = flags.indexOf(flag)

            require(pos in 0..7) { "The flag $flag is not found in the original list of flags." }

            return byte and (0b0000_0001 shl pos).toUByte() > 0u

        }

        fun setFlag(flag: T, value: Boolean) {

            val pos = flags.indexOf(flag)

            if (pos == -1) return

            byte = if (value) {
                byte or (0b0000_0001u shl pos).toUByte()
            } else {
                byte and (0b0000_0001u shl pos).inv().toUByte()
            }
        }

        fun debugPrint() {
            println("Debug: ${byte.toString(2).padStart(8, '0')}")
        }

    }

    // Example 1: a few flags represented by numbers
    val flagByte = FlagByte(
        listOf(
            1, 2, 3, 4, 5, 6, 7, 8
        )
    )

    with(flagByte) {

        debugPrint()

        println("Setting all flags")
        setFlag(1, true); debugPrint()
        setFlag(2, true); debugPrint()
        setFlag(3, true); debugPrint()
        setFlag(4, true); debugPrint()
        setFlag(5, true); debugPrint()
        setFlag(6, true); debugPrint()
        setFlag(7, true); debugPrint()
        setFlag(8, true); debugPrint()

        println("Unsetting alternative flags")
        setFlag(1, true); debugPrint()
        setFlag(2, false); debugPrint()
        setFlag(3, true); debugPrint()
        setFlag(4, false); debugPrint()
        setFlag(5, true); debugPrint()
        setFlag(6, false); debugPrint()
        setFlag(7, true); debugPrint()
        setFlag(8, false); debugPrint()

        println("Inverting flags")
        setFlag(1, false)
        setFlag(2, true)
        setFlag(3, false)
        setFlag(4, true)
        setFlag(5, false)
        setFlag(6, true)
        setFlag(7, false)
        setFlag(8, true); debugPrint()

        println("Unsetting all flags")
        setFlag(1, false)
        setFlag(2, false)
        setFlag(3, false)
        setFlag(4, false)
        setFlag(5, false)
        setFlag(6, false)
        setFlag(7, false)
        setFlag(8, false); debugPrint()

    }

    // Example 2: storing user privileges
    val privileges = FlagByte(
        listOf(
            Privilege.READ_EMAIL,
            Privilege.WRITE_EMAIL,
            Privilege.READ_USERS,
            Privilege.WRITE_USERS,
            Privilege.READ_PROJECTS,
            Privilege.WRITE_PROJECTS,
            Privilege.MANAGE_RESOURCES,
            Privilege.MANAGE_SERVICES,
        )
    )

    with(privileges) {

        println("Init: Privileges flag")
        debugPrint()

        setFlag(Privilege.READ_EMAIL, true)
        println("Can user read email? ${getFlag(Privilege.READ_EMAIL)}")

        setFlag(Privilege.WRITE_USERS, true)
        println("Can user write users? ${getFlag(Privilege.WRITE_USERS)}")

        setFlag(Privilege.READ_EMAIL, false)
        println("Can user read email? ${getFlag(Privilege.READ_EMAIL)}")

        println("Can user write users? ${getFlag(Privilege.WRITE_USERS)}")

    }
}