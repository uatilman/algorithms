package ru.tilman.algorithms.task4.model

import ru.tilman.algorithms.task4.IArray

class ArrayListWrapped<T> : IArray<T> {

    private val data = ArrayList<T>()


    override fun size(): Int {
        return data.size
    }

    override fun add(item: T) {
        data.add(item)
    }

    override fun get(index: Int): T {
       return data[index]
    }

    override fun add(item: T, index: Int) {
        data.add(index, item)
    }

    override fun remove(index: Int): T {
        return data.removeAt(index)
    }

}