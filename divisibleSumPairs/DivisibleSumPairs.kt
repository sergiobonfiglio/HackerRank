package divisibleSumPairs

import java.io.*
import java.util.*

fun main(args: Array<String>) {
//    val scan = Scanner(System.`in`)
//    val (n,k) = scan.nextLine().split(" ").map{Integer.parseInt(it)}
//    val arr = scan.nextLine().split(" ").map{Integer.parseInt(it)}

    val sol = solve(6, 3, listOf(1, 3, 2, 6, 1, 2))
    print(sol)


    val scan = Scanner(System.`in`)
    val n = Integer.parseInt(scan.nextLine())
    val arr = scan.nextLine().split(" ").map { Integer.parseInt(it) }

    print(arr.groupingBy { it }.eachCount().minWith(compareByDescending<Map.Entry<Int, Int>> { it.value }.thenBy { it.key }))


}

fun solve(n: Int, k: Int, array: List<Int>): Int {
    return (0 until array.size).sumBy { i ->
        (i + 1 until array.size)
                .count { (array[i] + array[it]) % k == 0 }
    }


}

