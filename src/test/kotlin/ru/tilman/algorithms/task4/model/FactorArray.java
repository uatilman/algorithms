package ru.tilman.algorithms.task4.model;

import ru.tilman.algorithms.task4.IArray;

public class FactorArray<T> implements IArray<T> {

    private Object[] array;
    private final int factor;
    private int size;

    public FactorArray(int factor, int initLength) {
        this.factor = factor;
        array = new Object[initLength];
        size = 0;
    }

    public FactorArray() {
        this(50, 10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > size()) throw new IllegalArgumentException("Invalid index " + index);
        if (size() == 0) {
            array[0] = item;
        } else if (size() == array.length) {
            Object[] newArray = new Object[array.length + array.length * factor / 100];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index, newArray, index + 1, size() - index);
            newArray[index] = item;
            array = newArray;
        } else {
            @SuppressWarnings("unchecked") T tmp = (T) array[index];
            System.arraycopy(array, 0, array, 0, index);
            System.arraycopy(array, index, array, index + 1, size() - index);
            array[index] = item;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size() || size() == 0)
            throw new IllegalArgumentException("Invalid index " + index);
        @SuppressWarnings("unchecked") T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size() - index - 1);
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    private void resize() {
        Object[] newArray = new Object[array.length + array.length * factor / 100];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
