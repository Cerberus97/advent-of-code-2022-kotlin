fun main() {
  fun parseInputStacks(input: List<String>): List<MutableList<Char>> {
    val numStacks = (input[0].length + 1) / 4
    val stacks = List(numStacks) { mutableListOf<Char>() }
    input.forEach { row ->
      if (row.contains('[')) {
        row.forEachIndexed { idx, crate ->
          if (crate.isUpperCase()) {
            val stackIdx = ((idx - 1) / 4)
            stacks[stackIdx] += crate
          }
        }
      }
    }
    stacks.forEach { it.reverse() }
    return stacks
  }

  fun part1(input: List<String>): String {
    val stacks = parseInputStacks(input)
    input.forEach { row ->
      if (row.contains("move")) {
        val tokens = row.split(' ')
        val cnt = tokens[1].toInt()
        val from = tokens[3].toInt() - 1
        val to = tokens[5].toInt() - 1
        repeat(cnt) {
          stacks[to] += stacks[from].removeLast()
        }
      }
    }
    return stacks.joinToString("") { stack -> stack.last().toString() }
  }

  fun part2(input: List<String>): String {
    val stacks = parseInputStacks(input)
    input.forEach { row ->
      if (row.contains("move")) {
        val tokens = row.split(' ')
        val cnt = tokens[1].toInt()
        val from = tokens[3].toInt() - 1
        val to = tokens[5].toInt() - 1
        val toMove = mutableListOf<Char>()
        repeat(cnt) { toMove += stacks[from].removeLast() }
        stacks[to] += toMove.reversed()
      }
    }
    return stacks.joinToString("") { stack -> stack.last().toString() }
  }

  val input = readInput("in")
  part1(input).println()
  part2(input).println()
}