package NonDivisibleSubset

import java.io.*
import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val solutions = solveAll(scanner)

    solutions.forEach { println(it) }
}

fun solveAll(scan: Scanner): List<Int> {
    val (n, k) = scan.nextLine().split(" ").map { Integer.parseInt(it) }
    val s = scan.nextLine().split(" ").map { Integer.parseInt(it) }

    val result = solve(n, k, s)
    return listOf(result)
}

fun solve(n: Int, k: Int, s: List<Int>): Int {
//    println("n:$n, k:$k, s:$s")

    if (n == 1)
        return 1
    else if (n == 2)
        return if ((s[0] + s[1]) % k == 0) 1 else 2

    val sumMultiple = Array(n, { BooleanArray(n) })
    for (i in 0 until n) {
        for (j in 0 until n) {
            sumMultiple[i][j] = (i != j) && (s[i] + s[j]) % k == 0
        }
    }

    val count = IntArray(n)
    var max = 0
    var maxI = 0
    var currN = n
    do {
        for (i in 0 until n) {
            count[i] = sumMultiple[i].count { it }
            if (count[i] > max) {
                max = count[i]
                maxI = i
            }
        }
    } while (max != 0)

    if (max == 0) {
        return n
    } else {
        val sub = s.filterIndexed { index, _ -> index != maxI }
        return solve(n - 1, k, sub)
    }


}

fun solveNaive(n: Int, k: Int, s: List<Int>): Int {

    //    println("n:$n, k:$k, s:$s")
    if (n == 1)
        return 1
    else if (n == 2)
        return if ((s[0] + s[1]) % k == 0) 1 else 2

    for (i in 0 until s.size) {
        val other: Int = s.indexOfFirst { s[i] != it && (s[i] + it) % k == 0 }
        if (other >= 0) {
//            println("removing ${s[i]} or ${s[other]}")
            return Math.max(solve(n - 1, k, s.filterIndexed { index, _ -> index != i }),
                    n - 1)
        }
    }
    return n
}