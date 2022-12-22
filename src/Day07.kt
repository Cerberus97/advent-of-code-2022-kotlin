fun main() {
  class Node(val name: String, val parent: Node? = null) {
    val children = mutableMapOf<String, Node>()
    val files = mutableMapOf<String, Int>()
    var size = 0

    fun getChild(childName: String): Node {
      if (!children.containsKey(childName)) children[childName] = Node(childName, this)
      return children[childName]!!
    }
  }

  fun Node.sumAllDirSizes(maxDirSize: Int = Int.MAX_VALUE): Int {
    val childAnsSum = children.values.sumOf { it.sumAllDirSizes(maxDirSize) }
    size = files.values.sum() + children.values.sumOf { it.size }
    return childAnsSum + if (size <= maxDirSize) size else 0
  }

  fun Node.smallestSizeLargerThan(requiredSize: Int): Int {
    return (children.values.map { it.smallestSizeLargerThan(requiredSize) } + (if (size >= requiredSize) size else Int.MAX_VALUE)).min()
  }

  fun createInputTree(input: List<String>): Node {
    val root = Node("/")
    var cur = root
    input.forEach { row ->
      val tokens = row.split(' ')
      if (tokens[0] == "$") {
        if (tokens[1] == "cd") {
          cur = when (tokens[2]) {
            "/" -> root
            ".." -> cur.parent!!
            else -> cur.getChild(tokens[2])
          }
        }
      } else if (tokens[0] != "dir") {
        cur.files[tokens[1]] = tokens[0].toInt()
      }
    }
    return root
  }

  fun part1(root: Node): Int {
    return root.sumAllDirSizes(100000)
  }

  fun part2(root: Node): Int {
    val requiredSpace = 30000000 - 70000000 + root.size
    return root.smallestSizeLargerThan(requiredSpace)
  }

  val input = readInput("in")
  val root = createInputTree(input)
  part1(root).println()
  part2(root).println()
}