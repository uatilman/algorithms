package ru.tilman.algorithms.task17

class Trie {

    private val power = 128
    private val root = Node()

    fun insert(word: String) {
        var node = root
        for (letter in word) {
            node = node.nextOrCreate(letter)
        }
        node.isEnd = true
    }

    fun search(word: String) = go(word)?.isEnd == true


    fun startsWith(prefix: String) = go(prefix) != null

    private fun go(word: String): Node? {
        var node: Node? = root
        for (letter in word) {
            if (node?.hasNext(letter) == true) node = node.next(letter)
            else return null
        }
        return node
    }


    inner class Node() {
        private val children: Array<Node?> = Array(power) { null }
        var isEnd = false

        fun hasNext(letter: Char) = children[letter.code] != null

        fun next(letter: Char): Node? = children[letter.code]

        fun nextOrCreate(letter: Char): Node =
            if (hasNext(letter)) {
                children[letter.code]!!
            } else {
                children[letter.code] = Node()
                children[letter.code]!!
            }
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */