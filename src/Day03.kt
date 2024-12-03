fun main() {

    fun multiplyStatement(statement: String): Long {
        val parts = statement.split(",")
        val num1 = (parts[0].split("("))[1].toLong()
        val num2 = parts[1].split(")")[0].toLong()

        return num1 * num2
    }

    fun part1(input: List<String>): Long {
        val regex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
        var sum: Long = 0

        for ( line in input ) {
            var correctStatement = regex.find(line)
            while (correctStatement != null) {
                val statement = correctStatement.value

                sum += multiplyStatement(statement)

                correctStatement = correctStatement.next()
            }
        }
        return sum
    }

    fun isEnabled(lastDo: MatchResult?, lastDont: MatchResult?): Boolean {
        return (lastDont == null) || (lastDo != null && (lastDo.range.first > lastDont.range.first))
    }

    fun part2(input: List<String>): Long {
        val regex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
        val regexDo = "do\\(\\)".toRegex()
        val regexDont = "don't\\(\\)".toRegex()
        var sum: Long = 0

        val line = input.joinToString("")

        var correctStatement = regex.find(line)
        while (correctStatement != null) {
            val statement = correctStatement!!.value
            val index = correctStatement!!.range.first

            val substringBefore = line.substring(0, index)
            var lastDo = regexDo.find(substringBefore)
            if (lastDo != null) {
                while (lastDo!!.next() != null)
                    lastDo = lastDo.next()
            }

            var lastDont = regexDont.find(substringBefore)
            if (lastDont != null) {
                while (lastDont!!.next() != null)
                    lastDont = lastDont.next()
            }

            if (isEnabled(lastDo, lastDont)) {
                sum += multiplyStatement(statement)
            }

            correctStatement = correctStatement!!.next()
        }

        return sum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161L)
    check(part2(testInput) == 48L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}