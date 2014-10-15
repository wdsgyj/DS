package com.clark.ds.list;

/**
 * Created by clark on 2014/10/15.
 */
public interface IList<T> {

    IList<T> addHead(T t);

    IList<T> addTail(T t);

    T removeHead();

    T removeTail();

    boolean contains(T t);

    boolean isEmpty();

    int size();

    IList<T> clear();

    int indexOf(T t);

    int lastIndexOf(T t);

    IList<T> sublist(int from, int to);

    IListIterator<T> listIterator();

    IListIterator<T> reverseListIterator();

}
