package BetweenTwoSets

import java.util.*

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)

    val solutions = solveAll(scanner)

    solutions.forEach { println(it) }
}

fun solveAll(scanner: Scanner): List<Int> {

    /*val n = scanner.nextInt()
    val m = scanner.nextInt()*/
    scanner.nextLine()
    val setA = scanner.nextLine().split(" ").map { Integer.parseInt(it) }
    val setB = scanner.nextLine().split(" ").map { Integer.parseInt(it) }



    val result = setA between setB

    return listOf(result)
}

infix fun List<Int>.between(other: List<Int>): Int {

    val otherMin = other.min()!!
    val thisMax = this.max()!!

    return (thisMax..otherMin step thisMax)
            .count { x -> all { x % it == 0 } && other.all { it % x == 0 } }
}