package Equal

import org.junit.jupiter.api.Test
import org.testng.Assert
import java.io.File
import java.nio.file.Paths
import java.util.*

class EqualTests {


    fun testGenericInput(testCase: String) {
        val outputFileName = testCase.replace("input", "output")

        val dir = File(Paths.get("").toAbsolutePath().toString()
                + Unit::class.java.`package`.name.replace('.', File.separatorChar).substring("kotlin".length)
                + "/HackerRank/Equal/res")
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
                + "/HackerRank/Equal/res")
        dir.walkTopDown()
                .filter { it.isFile && it.extension == "txt" && it.name.startsWith("input") }
                .forEach { testGenericInput(it.name) }
    }


    @Test
    fun testSolve() {

        Assert.assertEquals(solve(listOf(1)), 0)
        Assert.assertEquals(solve(listOf(1, 1)), 0)

        Assert.assertEquals(solve(listOf(1, 1, 4)), 2)

    }

    @Test
    fun testSolve2() {
        Assert.assertEquals(solve(listOf(1, 4, 4)), 3)
    }

    @Test
    fun testSolve3() {
        Assert.assertEquals(
                solve(listOf(512, 125, 928, 381, 890, 90, 512, 789, 469, 473, 908, 990, 195, 763, 102, 643, 458, 366, 684, 857, 126, 534, 974, 875, 459, 892, 686, 373, 127, 297, 576, 991, 774, 856, 372, 664, 946, 237, 806, 767, 62, 714, 758, 258, 477, 860, 253, 287, 579, 289, 496)), 5104)
    }

    @Test
    fun testSolve4() {
        Assert.assertEquals(
                solve(listOf(582, 942, 451, 552, 655, 554, 908, 809, 639, 290, 228, 373, 330, 240, 531, 106, 349, 44, 456, 462, 172, 751, 499, 469, 6, 314, 6, 386, 199, 415, 873, 781, 358, 324, 685, 13, 230, 945, 174, 869, 587, 402, 243, 918, 994, 126, 24, 344, 522, 832, 158, 694, 584, 9, 515, 942, 323, 874, 329, 874, 289, 202, 656, 999, 526, 693, 364, 757, 991, 538, 626, 578, 293, 221, 496, 287, 347, 873, 983, 870, 57, 141, 916, 993, 150, 784, 936, 826, 658, 265, 700, 299, 467, 708, 299, 345, 402, 15, 102, 393, 554, 81, 323, 847, 302, 820, 486, 2, 45, 470, 224, 102, 611, 492, 96, 114, 276, 384, 940, 286, 649, 640, 586, 468, 701, 237, 813, 455, 252, 268, 848, 158, 349, 523)), 12793)
    }

    @Test
    fun testSolve5() {
        Assert.assertEquals(solve(listOf(1, 5,5,5)), 4)
        Assert.assertEquals(solve(listOf(1,1,5,5,5)), 5)
        Assert.assertEquals(solve(listOf(1,4,5,5)), 6)
        "".split(" ").joinToString(" ")
    }

}
