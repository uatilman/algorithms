package ru.tilman.algorithms.task6

import java.security.SecureRandom


fun setRandom(size: Int): Array<Int> {
    val random = SecureRandom()
    return Array(size) {
        random.nextInt(10)
    }
}

fun setRandom(size: Int, bound: Int): Array<Int> {
    val random = SecureRandom()
    return Array(size) {
        random.nextInt(1,bound)
    }
}

fun <T : Comparable<T>> Array<T>.binarySearchRecursion(searched: T, low: Int, high: Int, compare: CompareCounter): Int {
    if (high <= low)
        return if (searched >= get(low)) low + 1 else low
    val middleIndex = (low + high) / 2
    compare.inc()

    return if (searched > get(middleIndex)) binarySearchRecursion(searched, middleIndex + 1, high, compare)
    else binarySearchRecursion(searched, low, middleIndex - 1, compare)

}


fun <T : Comparable<T>> Array<T>.binarySearch(searched: T, from: Int, to: Int, compare: CompareCounter): Int {
    var low = from
    var high = to

    while (low <= high) {
        compare.inc()
        val mid = (low + high) / 2
        val midValue = get(mid)
        if (searched > midValue ) {
            low = mid + 1
        } else  {
            high = mid - 1
        }
    }
    return if (searched >= get(low)) low + 1 else low
}
