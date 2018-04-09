package magicSquareForming

import java.util.*
import kotlin.math.abs

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    print(solveAll(scan))
}

fun solveAll(scan: Scanner): List<Int> {
    val size = 3
    val matrix = array2dOfInt(size, size)

    for (r in 0 until size) {
        for (c in 0 until size) {
            matrix[r][c] = scan.nextInt()
        }
    }


    return listOf(solve(matrix))
}

fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray> = Array(sizeOuter) { IntArray(sizeInner) }

val magicForw: IntArray = intArrayOf(4, 9, 2, 7, 6, 1, 8, 3)
val magicBack: IntArray = intArrayOf(2, 9, 4, 3, 8, 1, 6, 7)


fun solve(matrix: Array<IntArray>): Int {

    val diffs = IntArray(8)// intArrayOf(0, 0, 0, 0)

    //forward
    for (d in 0 until diffs.size) {
        val magic = if (d < 4) magicForw else magicBack

        val start = (6 * d) % magic.size
        var i = 0
        diffs[d] += abs(magic[(start + i++) % magic.size] - matrix[0][0])
        diffs[d] += abs(magic[(start + i++) % magic.size] - matrix[0][1])
        diffs[d] += abs(magic[(start + i++) % magic.size] - matrix[0][2])
        diffs[d] += abs(magic[(start + i++) % magic.size] - matrix[1][2])
        diffs[d] += abs(magic[(start + i++) % magic.size] - matrix[2][2])
        diffs[d] += abs(magic[(start + i++) % magic.size] - matrix[2][1])
        diffs[d] += abs(magic[(start + i++) % magic.size] - matrix[2][0])
        diffs[d] += abs(magic[(start + i) % magic.size] - matrix[1][0])
        diffs[d] += abs(5 - matrix[1][1])
    }

    return diffs.min()!!
}


