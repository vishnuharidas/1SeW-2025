package w02_squid_game_reward_distribution

fun main() {

    fun format(number: Number) = "%,d".format(number)

    val participantsCount = 456
    val share = 100_000_000L // Share per participant

    println("Total Participants: $participantsCount")
    println("Per elimination adds: ${format(share)} won.")
    println("Total prize money: ${format(participantsCount * share)} won.")
    println()

    repeat(participantsCount) { i ->

        val prizeMoney = i * share
        val distribution = prizeMoney / (participantsCount - i)
        println("Eliminated $i: Prize: ${format(prizeMoney)} won. Quit to get: ${format(distribution)} won per head.")

    }

    println("Last standing player gets an additional ${format(share)}, making it a total of: ${format(participantsCount * share)} won.")

}