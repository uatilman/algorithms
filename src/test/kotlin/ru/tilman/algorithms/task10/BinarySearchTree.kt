package ru.tilman.algorithms.task10

import java.util.function.Consumer
import kotlin.math.abs

class BinarySearchTree(
    private var root: Node? = null,
) {

    fun add(key: Int) {
        root = add(key, root)
    }

    private fun add(key: Int, node: Node?): Node {
        if (node == null) return Node(key)

        if (key < node.key) {
            node.left = add(key, node.left)
        } else if (key > node.key) {
            node.right = add(key, node.right)
        } else {
            // TODO: 17.03.2024 реализовать список хранения одинаковых элементов при более сложных структурах
            // в данном примере ничего не делаем или для реализации int без объекта можем хранить счетчик элементов в узле
        }

        node.updateHeightAndBalanceFactor()
        return node
    }

    // FIXME: 18.03.2024 не работает отладить

    fun reBalance(node: Node? = root) {
        if (node == null) return
        reBalance(node.left)

        if (abs(node.balanceFactor) > 1) {
            val tmp = Node(node)
            if (node.balanceFactor > 0) {
                node.key = node.left!!.key
                node.right = tmp
                tmp.updateHeightAndBalanceFactor()
                node.updateHeightAndBalanceFactor()
            } else {
                node.key = node.right!!.key
                node.left = tmp
                tmp.updateHeightAndBalanceFactor()
                node.updateHeightAndBalanceFactor()
            }
        }

        reBalance(node.right)
    }

    fun doWork(node: Node? = root, consumer: Consumer<Node>) {
        if (node == null) return
        doWork(node.left, consumer)
        consumer.accept(node)
        doWork(node.right, consumer)
    }

    fun search(key: Int): Boolean {
        var result: Node? = null
        doWork() {
            if (it.key == key) {
                result = it
                return@doWork
            }
        }
        return result != null
    }
}