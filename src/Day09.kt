import kotlin.math.abs
import kotlin.system.exitProcess

fun main() {
  data class Point(val x: Int, val y: Int)

  fun Point.move(dir: Char): Point = when (dir) {
    'R' -> Point(x + 1, y)
    'L' -> Point(x - 1, y)
    'D' -> Point(x, y + 1)
    'U' -> Point(x, y - 1)
    else -> exitProcess(-1)
  }

  fun findTailPosition(headPosition: Point, tailPosition: Point): Point {
    if (abs(headPosition.x - tailPosition.x) <= 1 && abs(headPosition.y - tailPosition.y) <= 1) return tailPosition
    return Point(
      listOf(tailPosition.x, tailPosition.x - 1, tailPosition.x + 1).minBy { x -> abs(x - headPosition.x) },
      listOf(tailPosition.y, tailPosition.y - 1, tailPosition.y + 1).minBy { y -> abs(y - headPosition.y) },
    )
  }

  fun part1(input: List<String>): Int {
    var headPosition = Point(0, 0)
    var tailPosition = Point(0, 0)
    val visited = mutableSetOf(tailPosition)
    input.forEach { row ->
      val dir = row[0]
      val cnt = row.substringAfter(' ').toInt()
      repeat(cnt) {
        headPosition = headPosition.move(dir)
        tailPosition = findTailPosition(headPosition, tailPosition)
        visited += tailPosition
      }
    }
    return visited.size
  }

  fun part2(input: List<String>): Int {
    val ropeLength = 10
    val rope = MutableList(ropeLength) { Point(0, 0) }
    val visited = mutableSetOf(rope.last())
    input.forEach { row ->
      val dir = row[0]
      val cnt = row.substringAfter(' ').toInt()
      repeat(cnt) {
        rope[0] = rope[0].move(dir)
        for (i in 1 until ropeLength) {
          rope[i] = findTailPosition(rope[i - 1], rope[i])
        }
        visited += rope.last()
      }
    }
    return visited.size
  }

  val input = readInput("in")
  part1(input).println()
  part2(input).println()
}