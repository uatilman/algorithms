package ru.tilman.algorithms.task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.Math.sqrt
import java.math.BigInteger
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.exists

/**
 * +0.5 байт. Реализовать рекурсивный O(2^N) алгоритм поиска чисел Фибоначчи.
 * @param n - порядковый номер числа
 * @result число Фибоначчи с порядковым номером [n]
 * сложность O(n^2)
 * */
fun calcFibonacciRecursive(n: BigInteger): BigInteger {
    require(n >= BigInteger.ZERO) { "Отрицательный порядковый номер" }
    if (n == BigInteger.ZERO) return BigInteger.ZERO
    if (n == BigInteger.ONE) return BigInteger.ONE

    return calcFibonacciRecursive(n - BigInteger.ONE) + calcFibonacciRecursive(n - BigInteger("2"))
}

/**
 * +0.5 байт. Реализовать итеративный O(N) алгоритмы поиска чисел Фибоначчи.
 * @param n - порядковый номер числа
 * @result число Фибоначчи с порядковым номером [n]
 * сложность O(n)
 * */
fun calcFibonacciIterate(n: BigInteger): BigInteger {
    require(n >= BigInteger.ZERO) { "Отрицательный порядковый номер" }
    if (n == BigInteger.ZERO) return BigInteger.ZERO
    if (n == BigInteger.ONE) return BigInteger.ONE

    var fibCurrent = BigInteger.ZERO
    var fibNext = BigInteger.ONE
    var result = BigInteger.ZERO
    var i = BigInteger.ONE
    while (i < n) {
        result = fibCurrent + fibNext
        fibCurrent = fibNext
        fibNext = result
        i++
    }

    return result
}

/**
 * 13. +1 байт. Реализовать алгоритм поиска чисел Фибоначчи по формуле золотого сечения.
 * @param n - порядковый номер числа
 * @result число Фибоначчи с порядковым номером [n]
 * сложность O(LogN).
 * */
fun calcFibonacciBineGold(n: Long): Long {
    require(n >= 0L) { "Отрицательный порядковый номер" }
    if (n == 0L) return 0L
    if (n == 1L) return 1L

    val fiveSqrt = sqrt(5.0)
    val fi = (1.0 + fiveSqrt) / 2.0
    return (binaryPow(fi, n) / fiveSqrt + 0.5).toLong()
}


/**
 * +1 байт. Написать класс умножения матриц [Matrix],
 *  реализовать алгоритм возведения матрицы в степень через двоичное разложение показателя степени,
 * реализовать алгоритм поиска чисел Фибоначчи O(LogN) через умножение матриц, используя созданный класс.
 * @param n - порядковый номер числа
 * @result число Фибоначчи с порядковым номером [n]
 * сложность O(LogN).
 * */
fun calcFibonacciMatrix(n: BigInteger): BigInteger {
    require(n >= BigInteger.ZERO) { "Отрицательный порядковый номер" }
    if (n == BigInteger.ZERO) return BigInteger.ZERO
    if (n == BigInteger.ONE) return BigInteger.ONE

    val base = Matrix(arrayOf(arrayOf(BigInteger.ONE, BigInteger.ONE), arrayOf(BigInteger.ONE, BigInteger.ZERO)))
    val result = binaryMatrixPow(base, n - BigInteger.ONE)

    return result[0, 0]
}

class Fibonacci {
    @Test
    fun run() {
        val path = "src/test/resources/4.Fibo/"
        var counter = 0
        while (true) {
            val inFile = Path("${path}test.${counter}.in")
            val outFile = Path("${path}test.${counter}.out")
            if (!inFile.exists() || !outFile.exists()) return
            val start = System.currentTimeMillis()
            if (counter >= 6) {
                val inData = BigInteger(String(Files.readAllBytes(inFile)).trim())
                val expectedData = BigInteger(String(Files.readAllBytes(outFile)).trim())
                assertEquals(expectedData, calcFibonacciMatrix(inData))
            } else {
                val inData = BigInteger(String(Files.readAllBytes(inFile)).trim())
                val expectedData = BigInteger(String(Files.readAllBytes(outFile)).trim())

                assertEquals(expectedData, calcFibonacciRecursive(inData))
                assertEquals(expectedData, calcFibonacciIterate(inData))
                assertEquals(expectedData.toLong(), calcFibonacciBineGold(inData.toLong()))
                assertEquals(expectedData, calcFibonacciMatrix(inData))

            }
            println("test ${path}test.${counter} finished success at ${System.currentTimeMillis() - start} ms}")
            counter++
        }
    }
}