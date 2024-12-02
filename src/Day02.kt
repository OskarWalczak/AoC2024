fun main() {

    fun isReportSafe(levels: List<Int>): Boolean {
        if (levels[0] == levels[1]) {
            return false
        }
        val allowedChangeRange = if (levels[0] < levels[1]) {(-1 downTo -3)} else {(1 .. 3)}
        var isReportSafe = true

        for (i in 0 .. levels.size-2) {
            if (levels[i] - levels[i+1] !in allowedChangeRange){
                isReportSafe = false
                break
            }
        }
        return isReportSafe
    }

    fun part1(input: List<String>): Int {
        var numberOfSafeReports = 0
        for (line in input) {
            val levels: List<Int> = line.split(" ").map { it.toInt() }
            val isReportSafe = isReportSafe(levels)
            if (isReportSafe) {
                numberOfSafeReports++
            }
        }

        return numberOfSafeReports
    }

    fun part2(input: List<String>): Int {
        var numberOfSafeReports = 0
        for (line in input) {
            val levels: List<Int> = line.split(" ").map { it.toInt() }

            var isReportSafe = isReportSafe(levels)

            if (!isReportSafe) {
                for (i in levels.indices) {
                    val newLevels = levels.filterIndexed { index, _ -> index != i }
                    if (isReportSafe(newLevels)) {
                        numberOfSafeReports++
                        break
                    }
                }
            }

            if (isReportSafe) {
                numberOfSafeReports++
            }
        }

        return numberOfSafeReports
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}