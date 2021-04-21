package sample.logic;

import java.util.*;

public class MyList<T> extends AbstractCollection<T> {
    private static final Object[] NULL = {};
    private Object[] elements;
    private int size;

    public MyList() {
        this.elements = new Object[16];
    }
    public MyList(int cap) {
        if (cap < 0) {
            throw new IllegalArgumentException("Illegal capacity");
        } else if (cap == 0) {
            this.elements = NULL;
        } else {
            this.elements = new Object[cap];
        }
    }

    public boolean add(T e) {
        final int s = size;
        Object[] es = elements;
        if (s == es.length)
            elements = es = Arrays.copyOf(es, s < 64 ? ((s + 1) << 1) : (s + (s >>> 1)));
        es[s] = e;
        size = s + 1;
        return true;
    }
    public int indexOf(Object o) {
        int s = size;
        int i = 0;
        Object[] fs = elements;
        if (o == null) {
            while (i < s) {
                if (null == fs[i])
                    return i;
                i++;
            }
        } else {
            while (i < s) {
                if (o.equals(fs[i]))
                    return i;
                i++;
            }
        }
        return ~i;
    }
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i < 0)
            return false;
        System.arraycopy(elements, i + 1, elements, i, size - i);
        size--;
        return true;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (i < size)
                    return (T) elements[i++];
                throw new NoSuchElementException();
            }
        };
    }
}