package com.clark.ds.list;

/**
 * Created by clark on 2014/10/16.
 */
public interface IListIterator<T> {
    boolean hasNext();

    boolean hasPrev();

    T next();

    T prev();

    boolean isHead();

    boolean isTail();
}
