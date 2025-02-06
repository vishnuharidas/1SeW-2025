package w06_version_number_comparison

import kotlin.math.absoluteValue

// Compare SemVer format version numbers: MAJOR.MINOR.PATCH
fun main() {

    data class Version(
        val major: Int,
        val minor: Int,
        val patch: Int
    ) : Comparable<Version> {

        override fun compareTo(other: Version): Int {

            val majorDiff = this.major - other.major
            val minorDiff = this.minor - other.minor
            val patchDiff = this.patch - other.patch

            if (majorDiff == 0 && minorDiff == 0 && patchDiff == 0) return 0

            return if (majorDiff == 0) {

                if (minorDiff == 0) {
                    patchDiff / patchDiff.absoluteValue // To return 1 or -1
                } else {
                    minorDiff / minorDiff.absoluteValue // To return 1 or -1
                }

            } else {
                majorDiff / majorDiff.absoluteValue // To return 1 or -1
            }

        }

        override fun toString(): String {
            return "$major.$minor.$patch"
        }

    }

    fun String.toVersion(): Version {
        val (major, minor, patch) = split(".").map { it.toInt() }
        return Version(major, minor, patch)
    }

    // Simple comparison
    val v1 = "1.2.3".toVersion()
    val v2 = "3.2.1".toVersion()

    println(v1.compareTo(v2))

    // Try sorting a list
    val versions = listOf(
        "3.12.7",
        "1.0.0",
        "2.5.9",
        "10.3.4",
        "0.9.8",
        "7.6.2",
        "4.15.0",
        "6.0.3",
        "8.2.1",
        "5.11.5",
        "9.0.0",
        "2.1.0",
        "11.9.4",
        "3.0.1",
        "6.8.0",
        "12.7.6",
        "2.5.9",
        "1.10.3",
        "4.2.2",
        "7.0.0",
        "5.3.9",
        "12.7.12",
        "16.15.20",
        "8.4.4",
        "17.17.17",
        "20.9.11",
        "19.4.6",
        "5.14.20",
        "13.12.19",
        "20.14.13",
        "13.10.13",
        "14.5.9",
        "15.3.1",
        "3.1.3",
        "1.5.10",
        "16.8.0",
        "2.9.0",
        "15.12.0",
        "18.17.2",
        "0.5.10",
        "17.0.3",
        "3.19.15",
        "15.6.16",
        "8.11.5",
        "17.17.4",
        "2.4.18",
        "2.2.18",
        "9.16.19",
        "15.10.20",
        "4.1.15",
        "9.10.11",
        "6.13.13",
        "1.6.6",
        "5.14.3",
        "17.8.6",
        "6.10.10",
        "9.18.3",
        "12.6.6",
        "10.18.10",
        "19.5.4",
        "11.13.5",
        "1.14.19",
        "3.12.1",
        "0.3.1",
        "19.9.4",
        "5.10.18",
        "18.17.2",
        "9.2.10",
        "1.7.1",
        "0.20.16",
        "16.9.12",
        "0.15.19",
        "20.11.9",
        "14.15.6",
        "16.19.5",
        "15.7.13",
        "11.3.7",
        "8.19.6",
        "1.2.19",
        "11.12.17",
        "10.5.4",
        "1.6.4",
        "4.10.4",
        "15.0.18",
        "13.8.17",
        "13.3.13",
        "10.14.20",
        "7.5.11",
        "16.3.9",
        "4.2.20",
        "18.18.3",
        "3.7.7",
        "0.4.10",
        "11.10.0",
        "5.0.7",
        "0.0.0",
        "0.0.1",
        "0.0.2",
        "0.1.0",
        "0.1.4",
        "0.1.1",
        "1.0.0",
        "6.4.0",
        "14.15.19",
        "2.9.13",
        "13.2.5",
        "1.6.11",
        "3.12.6",
        "19.18.2",
        "11.3.18",
        "19.0.0",
        "2.13.19",
        "10.20.1",
        "2.11.1",
        "1.6.6",
        "2.0.11",
        "5.1.1",
        "1.14.20",
        "11.13.4",
        "9.17.11",
        "14.19.9",
        "9.7.16",
        "15.2.3",
        "18.12.11",
        "14.7.14",
        "7.12.4",
        "3.8.19",
        "8.10.9",
    ).map { it.toVersion() }

    println("Sorted versions: ${versions.sorted().joinToString("\n")}")
}