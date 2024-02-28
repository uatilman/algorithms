package ru.tilman.algorithms.task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.math.pow

/**
 * 01. +1 байт. Реализовать итеративный O(N) алгоритм возведения числа в степень.
 * */
fun iterationPow(base: Int, exp: Int): Int {
    require(exp >= 0) { "Отрицательная степень" }
    if (exp == 0) return 1
    if (exp == 1) return base
    var result = base
    var expCounter = exp
    while (expCounter != 1) {
        result *= base
        expCounter--
    }
    return result
}

/**
 * 11. +1 байт. Реализовать алгоритм возведения в степень через домножение O(N/2+LogN) = O(N).
 * */
fun recursivePow(base: Int, exp: Int): Int {
    require(exp >= 0) { "Отрицательная степень" }
    if (exp == 0) return 1
    if (exp == 1) return base
    return if (exp % 2 == 0) {
        val result = recursivePow(base, exp / 2)
        result * result
    } else {
        val result = recursivePow(base, exp - 1)
        result * base
    }
}

/**
 *  12. +1 байт. Реализовать алгоритм возведения в степень через двоичное разложение показателя степени O(2LogN) = O(LogN).
 * */
fun binaryPow(base: Double, exp: Long): Double {
    require(exp >= 0) { "Отрицательная степень" }
    if (exp == 0L) return 1.0
    if (exp == 1L) return base
    var _exp = exp
    var result = 1.0
    var counter = base
    while (_exp != 0L) {
        if (_exp and 1 == 1L) result *= counter
        _exp = _exp shr 1
        if (_exp != 0L) counter *= counter

    }
    return result
}

/**
 * Возведение в степень большого числа
 * */
fun binaryBigPow(base: BigInteger, exp: BigInteger): BigInteger {
    require(exp >= BigInteger.ZERO) { "Отрицательная степень" }
    if (exp == BigInteger.ZERO) return BigInteger.ONE
    if (exp == BigInteger.ONE) return base
    var _exp = exp
    var result = BigInteger.ONE
    var counter = base
    while (_exp != BigInteger.ZERO) {
        if (_exp and BigInteger.ONE == BigInteger.ONE) result *= counter
        _exp = _exp shr 1
        if (_exp != BigInteger.ZERO) counter *= counter

    }
    return result
}

/**
 * Возведение в степень матрицы
 * */
fun binaryMatrixPow(base: Matrix, exp: BigInteger): Matrix {
    require(exp >=BigInteger.ZERO) { "Отрицательная степень" }
    if (exp == BigInteger.ZERO) return Matrix(arrayOf(arrayOf(BigInteger.ZERO, BigInteger.ZERO), arrayOf(BigInteger.ZERO, BigInteger.ZERO)))
    if (exp == BigInteger.ONE) return base
    var _exp = exp - BigInteger.ONE
    var result = base
    var counter = base
    while (_exp != BigInteger.ZERO) {
        if (_exp and BigInteger.ONE == BigInteger.ONE) result *= counter
        _exp = _exp shr 1
        if (_exp != BigInteger.ZERO) counter *= counter
    }
    return result
}

class Pow {
    @Test
    fun run() {
        assertEquals(2.0.pow(3.0), recursivePow(2, 3) + 0.0)
        assertEquals(3.0.pow(9.0), recursivePow(3, 9) + 0.0)


        assertEquals(2.0.pow(3.0), iterationPow(2, 3) + 0.0)
        assertEquals(3.0.pow(9.0), iterationPow(3, 9) + 0.0)

        assertEquals(2.0.pow(3.0), binaryPow(2.0, 3))
        assertEquals(3.0.pow(9.0), binaryPow(3.0, 9))
    }
// тестирование для матрицы простых чисел
//    @Test
//    fun binaryMatrixPowTest() {
//        val base = Matrix(arrayOf(arrayOf(1, 1), arrayOf(1, 0)))
//        val expected = Matrix(arrayOf(arrayOf(2, 1), arrayOf(1, 1)))
//        assertEquals(expected, binaryMatrixPow(base, 2))
//        assertEquals(base, binaryMatrixPow(base, 1))
//        assertEquals(Matrix(arrayOf(arrayOf(0, 0), arrayOf(0, 0))), binaryMatrixPow(base, 0))
//    }
}

