import kotlin.math.max

fun main() {
  class Forest(val grid: List<String>) {
    val n = grid.size
    val m = grid[0].length

    fun inRange(i: Int, j: Int): Boolean =
      (i in 0 until n) && (j in 0 until m)

    tailrec fun visible(i: Int, j: Int, di: Int, dj: Int, height: Int): Boolean {
      val ni = i + di
      val nj = j + dj
      if (!inRange(ni, nj)) return true
      if (grid[ni][nj].digitToInt() >= height) return false
      return visible(ni, nj, di, dj, height)
    }

    fun countVisible(i: Int, j: Int, di: Int, dj: Int, height: Int): Int {
      val ni = i + di
      val nj = j + dj
      if (!inRange(ni, nj)) return 0
      if (grid[ni][nj].digitToInt() >= height) return 1
      return 1 + countVisible(ni, nj, di, dj, height)
    }
  }

  fun part1(input: List<String>): Int {
    val forest = Forest(input)
    var ans = 0
    for (i in 0 until forest.n) {
      for (j in 0 until forest.m) {
        val height = input[i][j].digitToInt()
        if (forest.visible(i, j, 0, -1, height)
          || forest.visible(i, j, 0, 1, height)
          || forest.visible(i, j, -1, 0, height)
          || forest.visible(i, j, 1, 0, height)
        ) {
          ++ans
        }
      }
    }
    return ans
  }

  fun part2(input: List<String>): Int {
    val forest = Forest(input)
    var ans = 0
    for (i in 0 until forest.n) {
      for (j in 0 until forest.m) {
        val height = input[i][j].digitToInt()
        ans = max(ans, (
          forest.countVisible(i, j, 0, -1, height)
            * forest.countVisible(i, j, 0, 1, height)
            * forest.countVisible(i, j, -1, 0, height)
            * forest.countVisible(i, j, 1, 0, height)
          ))
      }
    }
    return ans
  }

  val input = readInput("in")
  part1(input).println()
  part2(input).println()
}