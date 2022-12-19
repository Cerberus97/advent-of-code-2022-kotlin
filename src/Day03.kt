fun main() {
  fun part1(input: List<String>): Int {
    return input.sumOf { items ->
      val sz = items.length
      val firstHalf = items.substring(0, sz / 2).toSet()
      val secondHalf = items.substring(sz / 2, sz).toSet()
      val common = firstHalf.intersect(secondHalf).single()
      if (common.isLowerCase()) (common - 'a' + 1) else (common - 'A' + 27)
    }
  }

  fun part2(input: List<String>): Int {
    return input.chunked(3).sumOf { itemGroup ->
      val itemSets = itemGroup.map { items -> items.toSet() }
      val common = itemSets[0].intersect(itemSets[1]).intersect(itemSets[2]).single()
      if (common.isLowerCase()) (common - 'a' + 1) else (common - 'A' + 27)
    }
  }

  val input = readInput("in")
  part1(input).println()
  part2(input).println()
}