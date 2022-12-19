fun main() {
  fun solve(buffer: String, numUnique: Int): Int {
    for (i in numUnique..buffer.length) {
      if (buffer.substring(i - numUnique, i).toSet().size == numUnique) {
        return i
      }
    }
    return -1
  }

  fun part1(input: List<String>): Int {
    return solve(input.single(), 4)
  }

  fun part2(input: List<String>): Int {
    return solve(input.single(), 14)
  }

  val input = readInput("in")
  part1(input).println()
  part2(input).println()
}