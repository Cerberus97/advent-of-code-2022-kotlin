import kotlin.system.exitProcess

fun main() {
  fun getP2Score(p1_char: Char, p2_char: Char): Int {
    val p1 = (p1_char - 'A')
    val p2 = (p2_char - 'X')
    return (p2 + 1) + when ((p2 - p1 + 3) % 3) {
      0 -> 3 // Draw
      1 -> 6 // Win
      2 -> 0 // Lose
      else -> exitProcess(-1)
    }
  }

  fun part1(input: List<String>): Int {
    return input.sumOf { getP2Score(it[0], it[2]) }
  }

  fun part2(input: List<String>): Int {
    return input.sumOf {
      val p1 = (it[0] - 'A')
      val p2 = (p1 + when(it[2]) {
        'X' -> 2
        'Y' -> 0
        'Z' -> 1
        else -> exitProcess(-1)
      }) % 3
      getP2Score(it[0], ('X' + p2))
    }
  }

  val input = readInput("in")
  part1(input).println()
  part2(input).println()
}