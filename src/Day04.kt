fun main() {
  fun parseRange(range: String): IntRange {
    val lo = range.substringBefore('-').toInt()
    val hi = range.substringAfter('-').toInt()
    return IntRange(lo, hi)
  }

  fun IntRange.contains(other: IntRange): Boolean = contains(other.first) && contains(other.last)

  fun IntRange.overlaps(other: IntRange): Boolean = contains(other.first) || contains(other.last)

  fun part1(input: List<String>): Int {
    return input.count { ranges ->
      val firstRange = parseRange(ranges.substringBefore(','))
      val secondRange = parseRange(ranges.substringAfter(','))
      firstRange.contains(secondRange) || secondRange.contains(firstRange)
    }
  }

  fun part2(input: List<String>): Int {
    return input.count { ranges ->
      val firstRange = parseRange(ranges.substringBefore(','))
      val secondRange = parseRange(ranges.substringAfter(','))
      firstRange.overlaps(secondRange) || secondRange.overlaps(firstRange)
    }
  }

  val input = readInput("in")
  part1(input).println()
  part2(input).println()
}