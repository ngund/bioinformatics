package ru.ngundobin.bio.dnarepl.util;

import java.util.*;

public class LargeArray<E> implements Collection<E> {

    private static final int STANDARD_BATCH_SIZE = Integer.MAX_VALUE / 8;

    private final int batchSize;
    private final ArrayList<ArrayList<E>> data;
    private ArrayList<E> currentBatchForAdding;
    private long size;

    public LargeArray() {
        batchSize = STANDARD_BATCH_SIZE;
        data = new ArrayList<>();
        size = 0;
    }

    public LargeArray(int batchSize) {
        if (batchSize <= 0) throw new IllegalArgumentException("'batchSize' <= 0");
        this.batchSize = batchSize;
        this.data = new ArrayList<>();
        size = 0;
    }

    private ArrayList<E> getBatch(long elementIndex) {
        checkIndex(elementIndex);
        int batchIndex = (int) (elementIndex / batchSize);
        return data.get(batchIndex);
    }

    private void checkIndex(long index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("'index'");
    }

    private E get(long index) {
        List<E> batch = getBatch(index);
        int correctedIndex = (int) (index % batchSize);
        return batch.get(correctedIndex);
    }

    @Override
    public int size() {
        if (size >= Integer.MAX_VALUE) {
            return (int) (Integer.MIN_VALUE + size - Integer.MAX_VALUE);
        }
        return (int) size;
    }

    public long longSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new LargeListIterator(0);
    }

    public ListIterator<E> listIterator() {
        return new LargeListIterator(0);
    }

    public ListIterator<E> listIterator(long index) {
        return new LargeListIterator(index);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        if (e == null) throw new NullPointerException("'e'");
        if (currentBatchForAdding == null) {
            currentBatchForAdding = new ArrayList<>();
            data.add(currentBatchForAdding);
        }
        currentBatchForAdding.add(e);
        size++;
        if (currentBatchForAdding.size() == batchSize) {
            currentBatchForAdding.trimToSize();
            currentBatchForAdding = null;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) throw new NullPointerException("'c'");
        if (c.contains(null)) throw new NullPointerException("'c' contains null");

        for (E element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public LargeArray<E> subSequence(int startIndex, int endIndex) {
        checkIndex(endIndex);
        Iterator<E> iterator = new LargeListIterator(startIndex);
        LargeArray<E> result = new LargeArray<>(batchSize);
        long position = startIndex;
        while (iterator.hasNext() && position <= endIndex) {
            result.add(iterator.next());
            position++;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LargeArray that = (LargeArray) o;

        return size == that.size && data.equals(that.data);
    }

    @Override
    public int hashCode() {
        int result = data.hashCode();
        result = 31 * result + (int) (size ^ (size >>> 32));
        return result;
    }

    class LargeListIterator implements ListIterator<E> {

        private long cursor;

        LargeListIterator(long startIndex) {
            if (startIndex < 0 || startIndex > size) throw new IndexOutOfBoundsException("'index'");
            this.cursor = startIndex;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            return get(cursor++);
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            return get(--cursor);
        }

        @Override
        public int nextIndex() {
            return (int) cursor;
        }

        @Override
        public int previousIndex() {
            return (int) (cursor - 1);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
