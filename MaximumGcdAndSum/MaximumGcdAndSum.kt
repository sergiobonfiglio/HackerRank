package MaximumGcdAndSum

import java.math.BigInteger
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)


    print(magicSquareForming.solveAll(scan))
}

fun solveAll(scan: Scanner): List<Int> {
    scan.nextLine()
    val a = scan.nextLine().split(" ").map { Integer.parseInt(it) }
    val b = scan.nextLine().split(" ").map { Integer.parseInt(it) }

    return listOf(solve(a, b))
}

fun solve2(aIn: List<Int>, bIn: List<Int>): Int {
//fun solve(a: List<Int>, b: List<Int>): Int {


    val a = aIn.sortedDescending()
    val b = bIn.sortedDescending()


    var maxGcd = 0
    var sum = 0
    for (ai in a) {

        if (ai > maxGcd) {
            var subGcd = 0
            var bIndex = 0
            while (subGcd < 2 && bIndex < b.size && b[bIndex] > maxGcd) {
                subGcd = gcd(ai, b[bIndex++], maxGcd)
            }
            if (subGcd > maxGcd) {
                maxGcd = subGcd
                sum = ai + b[bIndex - 1]
            }
        }

    }
    print("gcdCount: ${gcdCount}")
    return sum
}


fun solve(a: List<Int>, b: List<Int>): Int {

//    val a = aIn.sortedDescending()
//    val b = bIn.sortedDescending()

//    print("sorted")


    var maxGcd = 0
    var sum = 0
    for (ai in a) {

        if (ai > maxGcd) {
            for (bi in b) {
                if (bi > maxGcd) {
                    val curGcd = gcd(ai, bi, maxGcd)
                    if (curGcd > maxGcd) {
                        maxGcd = curGcd
                        sum = ai + bi
                    } else if (curGcd == maxGcd && ai + bi > sum) {
                        sum = ai + bi
                    }
                }
            }
        }
    }
    println("gcdCount: ${gcdCount}")
    return sum
}

/*
* fullcap:  57s     --10.559.103.681
* nocap:    11m6s   --69.066.447.803
* noif:     1m16s   --10.559.103.681
* 2 if:     56s     --7.597.387.192
* sort:     10m39s  --65.682.997.505
* */
var gcdCount: Long = 0

val memo = HashMap<Set<Int>, Int>()


fun gcd2(a: Int, b: Int, cap: Int): Int {
    gcdCount++
    return BigInteger.valueOf(a.toLong()).gcd(BigInteger.valueOf(b.toLong())).toInt()
}

tailrec fun gcd(a: Int, b: Int, cap: Int): Int {
    gcdCount++
    //println("a:$a, b:$b")
    return if (b == 0) a else if (b < cap || a < cap) -1 else gcd(b, a % b, cap)

}

