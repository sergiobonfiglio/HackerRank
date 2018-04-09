package Equal

import java.util.*
import kotlin.collections.ArrayList

object Logger {
    fun debug(str: String) {
        if (isLocal) {
            print(str)
        }
    }
}

val isLocal = true

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val solutions = solveAll(scanner)

    solutions.forEach { println(it) }
}

fun solveAll(scanner: Scanner): List<Int> {

    val t = scanner.nextInt()

    val results = ArrayList<Int>(t)

    for (i in 1..t) {

        /*val n = */scanner.nextInt() //not used

        scanner.nextLine()//discard

        val choc = scanner.nextLine().split(" ").map { Integer.parseInt(it) }

        val solved = solve(choc)

        results.add(solved)

    }

    return results
}


fun solve(chocolates: List<Int>, indentation: String = ""): Int {

    val sorted = chocolates.sorted()
    if (indentation.isEmpty()) Logger.debug("\n$indentation${sorted.size};$sorted\n")
    val allEquals = sorted.all { it == sorted[0] }

    Logger.debug("$indentation$sorted -> ")

    if (allEquals) {
        return 0
    } else {
        val min: Int = sorted[0]
        val min2: Int = sorted.find { it != min }!!

        var (numOfOp, sumOfOp, currentMax) = getOperations(sorted, min, min2, "$indentation")

        val nextStep = sorted.map {
            if (it != min && it != min2) it + sumOfOp
            else currentMax
        }
        Logger.debug("$indentation $nextStep\n")

        val sol = numOfOp + solve(nextStep, "$indentation  ")
        return sol
    }

}

private fun step(subArr: List<Int>, chosenOp: Int, targetIndex: Int): List<Int> {
    if (targetIndex >= subArr.size) throw RuntimeException()
    return subArr.mapIndexed { index, it -> if (index != targetIndex) it + chosenOp else it }
}

private fun getOperations(chocolates: List<Int>, min: Int, min2: Int, indentation: String): Triple<Int, Int, Int> {
    var numOfOp = 0
    var sumOfOp = 0
    var subArr = chocolates.filter { it == min || it == min2 }
    var currentMin = min
    var currentMin2 = min2

    var iter = 0
    while (currentMin != currentMin2) {
        iter++
        val firstMin2 = subArr.indexOfFirst { it == currentMin2 }
        val currentDiff = currentMin2 - currentMin
        val chosenOp =
                if (currentDiff == 1) {
                    1
                } else if (currentDiff == 2) {
                    2
                } else if (currentDiff == 3 || currentDiff == 4) {
                    val sol2 = solve(step(subArr, 2, firstMin2), "$indentation ")
                    var sol5: Int? = null

                    if (sol2 > 1) {
                        sol5 = solve(step(subArr, 5, firstMin2), "$indentation ")
                    }

                    if (sol5 == null || sol2 <= sol5) {
                        2
                    } else {
                        5
                    }
                } else if (currentDiff == 5) {

                    5
                } else {
                    currentDiff / 5 * 5
                }
        Logger.debug("+$chosenOp")

        subArr = step(subArr, chosenOp, firstMin2)

        currentMin = subArr.min()!!
        currentMin2 = subArr.filter { it != currentMin }.min() ?: currentMin

        if (chosenOp <= 5) {
            numOfOp++
        } else {
            numOfOp += chosenOp / 5
        }
        sumOfOp += chosenOp

    }
    return Triple(numOfOp, sumOfOp, currentMin2)
}
