package NonDivisibleSubset

import org.junit.jupiter.api.Test
import org.testng.Assert
import java.io.File
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList

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

        val solveAll = solveAll(scanner)

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

    @Test
    fun fuzzTest() {

        testCase(1, 1, generateArray(1))
        //testCase(Math.pow(10.0, 5.0).toInt(), 1, generateArray(Math.pow(10.0, 5.0).toInt()))

        var time = 0L
        val iterations = 1000
        for (i in 1..iterations) {
            val n = (Math.random() * (Math.pow(10.0, 5.0) + 1) + 1).toInt()
            val k = (Math.random() * (100 + 1) + 1).toInt()
            //println("[$i] n, k, s: ($n, $k, ...)")
            val s = generateArray(n)

            val start = System.nanoTime()
            testCase(n, k, s)
            time += System.nanoTime() - start
        }
        println("avg: ${time / iterations} ns")
    }

    private fun generateArray(n: Int): List<Int> {
        val s = ArrayList<Int>(n)
        for (j in 0 until n) {
            val el = (Math.random() * (Math.pow(10.0, 9.0) + 1) + 1).toInt()
            s.add(el)
        }
        return s
    }

    private fun testCase(n: Int, k: Int, s: List<Int>) {
        println("solve($n, $k, _)...")
        var result: Int? = null
        val time: Long
        val start = System.nanoTime()
        var err: Exception? = null
        try {
            result = solve(n, k, s)
        } catch (e: Exception) {
            err = e
        } finally {
            time = System.nanoTime() - start
        }


        if (err != null) {
            println("[$time ns] solve($n, $k, $s) = $result")
            throw err
        } else {
            println("[$time ns] solve($n, $k, _) = $result")
        }
    }

}