package com.clark.ds.list;

/**
 * Created by clark on 2014/10/15.
 */
public class List<T> implements IList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public List() {
    }

    private void addSingleElement(T t) {
        head = tail = new Node<T>();
        head.value = t;
        size++;
    }

    @Override
    public IList<T> addHead(T t) {
        if (size == 0) {
            addSingleElement(t);
        } else {
            head.prev = new Node<T>();
            head.prev.next = head;
            head.prev.value = t;
            head = head.prev;
            size++;
        }
        return this;
    }

    @Override
    public IList<T> addTail(T t) {
        if (size == 0) {
            addSingleElement(t);
        } else {
            tail.next = new Node<T>();
            tail.next.prev = tail;
            tail.next.value = t;
            tail = tail.next;
            size++;
        }
        return this;
    }

    private T removeSingleElement() {
        final Node<T> e = head;
        clear();
        return e.value;
    }

    @Override
    public T removeHead() {
        if (size > 1) {
            final Node<T> e = head;
            head = head.next;
            head.prev = null;
            size--;
            return e.value;
        } else if (size == 0) {
            return null;
        } else {
            return removeSingleElement();
        }
    }

    @Override
    public T removeTail() {
        if (size > 1) {
            final Node<T> e = tail;
            tail = tail.prev;
            tail.next = null;
            size--;
            return e.value;
        } else if (size == 0) {
            return null;
        } else {
            return removeSingleElement();
        }
    }

    @Override
    public boolean contains(T t) {
        if (size == 0) {
            return false;
        }

        for (Node<T> fromHead = head, fromTail = tail; ;
             fromHead = fromHead.next, fromTail = fromTail.prev) {
            if (fromHead.value == t || fromTail.value == t) {
                return true;
            }

            if (fromHead == fromTail) {
                break;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public IList<T> clear() {
        head = tail = null;
        size = 0;
        return this;
    }

    @Override
    public int indexOf(T t) {
        if (size > 0) {
            int index = 0;
            for (Node<T> node = head; node != null; node = node.next, index++) {
                if (t == node.value) {
                    return index;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(T t) {
        if (size > 0) {
            int index = size - 1;
            for (Node<T> node = tail; node != null; node = node.prev, index--) {
                if (t == node.value) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public IList<T> sublist(int from, int to) {
        if (from < 0 || to >= size || from > to) {
            throw new IndexOutOfBoundsException();
        }

        int index = 0;
        Node<T> node = head;
        for (; index != from; index++) {
            node = node.next;
        }

        List<T> anotherList = new List<T>();
        for (; index != to; index++, node = node.next) {
            anotherList.addHead(node.value);
        }

        return anotherList;
    }

    @Override
    public IListIterator<T> listIterator() {
        final Node<T> tmp = new Node<T>();
        tmp.next = head;
        return new ListIteratorImpl(tmp);
    }

    @Override
    public IListIterator<T> reverseListIterator() {
        final Node<T> tmp = new Node<T>();
        tmp.prev = tail;
        return new ListIteratorImpl(tmp);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("List [");
        final IListIterator<T> iterator = listIterator();
        while (iterator.hasNext()) {
            if (buf.length() > 6) {
                buf.append(", ");
            }
            buf.append(iterator.next());
        }
        buf.append("]");
        return buf.toString();
    }

    private class ListIteratorImpl implements IListIterator<T> {
        private Node<T> element;

        private ListIteratorImpl(Node<T> element) {
            this.element = element;
        }

        @Override
        public boolean hasNext() {
            return element.next != null;
        }

        @Override
        public boolean hasPrev() {
            return element.prev != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }

            element = element.next;
            return element.value;
        }

        @Override
        public T prev() {
            if (!hasPrev()) {
                throw new IllegalStateException();
            }

            element = element.prev;
            return element.value;
        }

        @Override
        public boolean isHead() {
            return element.next == head;
        }

        @Override
        public boolean isTail() {
            return element.prev == tail;
        }
    }

    private static class Node<T> {
        public Node<T> next;
        public Node<T> prev;
        public T value;
    }
}
