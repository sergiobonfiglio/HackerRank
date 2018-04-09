package MaximumGcdAndSum

import org.junit.jupiter.api.Test
import org.testng.Assert
import java.io.File
import java.nio.file.Paths
import java.util.*


class Tests {

    fun testGenericInput(testCase: String) {
        val outputFileName = testCase.replace("input", "output")

        val dir = File(Paths.get("").toAbsolutePath().toString()
                + Unit::class.java.`package`.name.replace('.', File.separatorChar).substring("kotlin".length)
                + "/HackerRank/${this::class.java.`package`.name}/res")
        val inFile = File("$dir/$testCase")
        val outFile = File("$dir/$outputFileName")

        val solutions = outFile.readLines()

        val scanner = Scanner(inFile)//.useDelimiter("\n")

        val solveAll = magicSquareForming.solveAll(scanner)

        println(solveAll)
        solveAll.forEachIndexed { index, it -> Assert.assertEquals("" + it, solutions[index]) }
    }

    @Test
    fun testAllInput() {
        val dir = File(Paths.get("").toAbsolutePath().toString()
                + Unit::class.java.`package`.name.replace('.', File.separatorChar).substring("kotlin".length)
                + "/HackerRank/${this::class.java.`package`.name}/res")
        dir.walkTopDown()
                .filter { it.isFile && it.extension == "txt" && it.name.startsWith("input") }
                .forEach { testGenericInput(it.name) }
    }




}