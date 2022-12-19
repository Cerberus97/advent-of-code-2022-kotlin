import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var ans = 0
        var cur = 0
        input.forEach { cal ->
            if (cal == "") {
                cur = 0
            } else {
                cur += cal.toInt()
                ans = max(ans, cur)
            }
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        val calorieList = mutableListOf<Int>()
        var cur = 0
        input.forEach { cal ->
            if (cal == "") {
                calorieList += cur
                cur = 0
            } else {
                cur += cal.toInt()
            }
        }
        calorieList += cur
        return calorieList.sortedDescending().subList(0, 3).sum()
    }

    val input = readInput("in")
    part1(input).println()
    part2(input).println()
}
