package ru.tilman.algorithms.task10

import java.lang.Integer.max


class Node(
    var key: Int,
    var left: Node? = null,
    var right: Node? = null,
) {

    constructor(node: Node) : this(node.key, node.left, node.right)

    var height: Int = 0
    var balanceFactor: Int = 0


    fun updateHeightAndBalanceFactor() {
        height = max(left?.height ?: 0, right?.height ?: 0) + 1
        balanceFactor = (left?.height ?: 0) - (right?.height ?: 0)
    }

    override fun toString(): String {
        return "$key (l: ${left?.key}, r: ${right?.key}, h: ${height}, b: ${balanceFactor})"
    }
}

