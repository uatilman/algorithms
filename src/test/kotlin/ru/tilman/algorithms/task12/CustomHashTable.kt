package ru.tilman.algorithms.task12

private const val DEFAULT_INITIAL_CAPACITY = 1 shl 4
private const val DEFAULT_LOAD_FACTOR = 0.75f
private const val MAX_CAPACITY = 1 shl 30


class CustomHashTable<K, V : Any>(
    private var capacity: Int = DEFAULT_INITIAL_CAPACITY,
    private val loadFactor: Float = DEFAULT_LOAD_FACTOR,
) {

    private var size = 0
    private var table = arrayOfNulls<Node<K, V>>(capacity)

    operator fun set(key: K, value: V): V = put(key, value)

    operator fun get(key: K): V? {
        val start = getNode(key) ?: return null
        if (start.key == key) return start.value
        if (start.next == null) return null
        val iterator = start.iterator()
        while (iterator.hasNext()) {
            val current = iterator.next()
            if (current.key == key) return current.value
        }
        return null
    }

    fun remove(key: K): V? {
        val i = countTableIndex(hash(key))
        val start = table[i] ?: return null

        //Первый элемент требуемый. Наиболее частый вариант при хорошем распределении
        if (start.key == key) {
            size--
            table[i] = start.next
            return start.value
        }

        if (start.next == null) return null
        val iterator = start.iterator()
        var previous = iterator.next() // первый уже рассмотрен, сразу переключаем на него итератор

        while (iterator.hasNext()) {
            val current = iterator.next()
            if (current.key == key) {
                previous.next = current.next
                size--
                return current.value
            } else {
                previous = current
            }
        }
        return null
    }

    private fun put(key: K, value: V, isDebug: Boolean = false): V {
        val hash = hash(key)
        if (isDebug) println("hash of key $key = $hash")
        if (size + 1 > capacity * loadFactor) resize()
        val i = countTableIndex(hash, isDebug)
        if (table[i] == null) table[i] = Node(hash, key, value, null)
        else table[i]?.add(hash, key, value)
        size++
        return value.apply { if (isDebug) println("put $key = $value, result:\n${this@CustomHashTable}") }
    }


    private fun getNode(key: K): Node<K, V>? {
        return table[countTableIndex(hash(key))]
    }

    private fun countTableIndex(hash: Int, isDebug: Boolean = false): Int {
        if (isDebug) println("index $hash%$capacity = ${hash % capacity}")
        return hash % capacity
    }

    private fun resize() {
        println("=========RESIZE START==========")
        println("resize capacity from $capacity to ${capacity shl 1}. capacity * loadFactor = ${capacity * loadFactor}, size = $size")
        println("old table:\n$this")
        size = 0
        val newCapacity = capacity shl 1
        capacity = if (newCapacity > MAX_CAPACITY) MAX_CAPACITY else newCapacity

        val oldTable = table
        table = arrayOfNulls(capacity)
        oldTable.forEach {
            if (it != null) {
                val iterator = it.iterator()
                while (iterator.hasNext()) {
                    val next = iterator.next()
                    next.key?.let { key -> next.value?.let { value -> put(key, value) } }
                }
            }
        }
        println("new table:\n$this")
        println("==========RESIZE END===========")
    }

    private fun hash(key: K): Int {
//        var h: Int
//        return if (key == null) 0 else key.hashCode().also { h = it } xor (h ushr 16) из  java.util.HashMap, судя по всему компромиссный вариант при использовании деревьев
        return (key.hashCode() and 0x7FFFFFFF) //из java.util.Hashtable
    }

    override fun toString(): String {
        val builder = StringBuilder("capacity = $capacity. capacity * loadFactor = ${capacity * loadFactor}, size = $size\n")
        table.forEachIndexed { index, it ->
            builder.append("$index: " + "${it?.toString()}\n")
        }
        return builder.toString()
    }

    inner class Node<K, V : Any>(
        private val hash: Int = 0,
        val key: K? = null,
        var value: V? = null,
        var next: Node<K, V>? = null,
    ) : Iterable<Node<K, V>> {

        fun add(hash: Int, key: K, value: V) {
            var current: Node<K, V> = this
            val iterator = iterator()
            while (iterator.hasNext()) {
                current = iterator.next()
                if (current.key == key) { //перетираем value
                    current.value = value
                    return
                }
            }
            current.next = Node(hash, key, value, null)
        }

        override fun iterator(): Iterator<Node<K, V>> = NodeIterator(this)

        inner class NodeIterator(private var current: Node<K, V>) : Iterator<Node<K, V>> {

            private var lastReturn: Node<K, V>? = null

            override fun hasNext() = current.next != null || lastReturn == null

            override fun next(): Node<K, V> =
                if (lastReturn == null) {
                    lastReturn = current
                    current
                } else {
                    current.next?.let {
                        current = it
                        current
                    } ?: throw NoSuchElementException("No next element")
                }
        }

        override fun toString(): String {
            val builder = StringBuilder()
            val iterator = iterator()
            while (iterator.hasNext()) {
                val el = iterator.next()
                builder.append("->${el.key}=${el.value}")
            }
            return builder.toString()
        }

    }
}
