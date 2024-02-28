package ru.tilman.algorithms.task4.model;

import ru.tilman.algorithms.task4.IArray;

public class SingleArray<T> implements IArray<T> {

    private Object[] array;

    public SingleArray() {
        array = new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        resize();
        array[size() - 1] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > size()) throw new IllegalArgumentException("Invalid index " + index);

        Object[] newArray = new Object[size() + 1];
        if (newArray.length != 1) {
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index, newArray, index + 1, size() - index);
        }
        newArray[index] = item;
        array = newArray;
    }


    @Override
    public T remove(int index) {
        if (index < 0 || index >= size() || size() == 0)
            throw new IllegalArgumentException("Invalid index " + index);
        @SuppressWarnings("unchecked") T removedElement = (T) array[index];
        Object[] newArray = new Object[size() - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size() - index - 1);

        array = newArray;
        return removedElement;
    }


    private void resize() {
        Object[] newArray = new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, size());
//        for (int j = 0; j < size(); j ++)
//            newArray[j] = array[j];
        array = newArray;
    }
}
