package ru.tilman.algorithms.task10

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertTrue

class BinarySearchTreeTest {
    @Test
    fun add() {
        val max = 1000
        val random = Random()
        val bst = BinarySearchTree()
        repeat(10) {
            bst.add(random.nextInt(max))
        }
        bst.doWork { node -> println(node.key.toString()) }
    }

    @Test
    fun search() {
        val bst = BinarySearchTree()
        val testData = arrayOf(661, 584, 745, 281, 834, 225, 540, 929, 858, 52)
        testData.forEach { bst.add(it) }

        bst.doWork { node -> println(node.key.toString()) }
        assertTrue (bst.search(661))
        assertFalse (bst.search(555))

    }
}