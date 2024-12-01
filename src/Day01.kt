import kotlin.math.abs

fun getNumbersLists(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
    val leftNumbers: MutableList<Int> = ArrayList<Int>()
    val rightNumbers: MutableList<Int> = ArrayList<Int>()

    input.forEach { line ->
        val nums = line.split(" ").map {it.trim()}.filter {it.isNotBlank()}.map {it.toInt()}
        leftNumbers.add(nums[0])
        rightNumbers.add(nums[1])
    }

    return Pair(leftNumbers, rightNumbers)
}

fun main() {
    fun part1(input: List<String>): Int {
        val numbers = getNumbersLists(input)
        val leftNumbers = numbers.first
        val rightNumbers = numbers.second

        leftNumbers.sort()
        rightNumbers.sort()

        var sumOfDifferences: Int = 0

        for (i in leftNumbers.indices) {
            sumOfDifferences += abs(leftNumbers[i] - rightNumbers[i])
        }

        return sumOfDifferences
    }

    fun part2(input: List<String>): Int {
        val appearanceCountMap: MutableMap<Int, Int> = mutableMapOf()
        var similarityScore: Int = 0

        val numbers = getNumbersLists(input)
        val leftNumbers = numbers.first
        val rightNumbers = numbers.second

        rightNumbers.forEach { number ->
            if (number in appearanceCountMap.keys) {
                appearanceCountMap[number] = appearanceCountMap[number]!! + 1
            }
            else {
                appearanceCountMap[number] = 1
            }
        }

        leftNumbers.forEach { number ->
            if (number in appearanceCountMap.keys) {
                similarityScore += number * appearanceCountMap[number]!!
            }
        }

        return similarityScore
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
