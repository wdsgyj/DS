package com.clark.ds.list;

import org.junit.Test;

/**
 * Created by clark on 2014/10/16.
 */
public class ListTest {

    @Test
    public void testConstructor() throws Throwable {
        IList<String> list = new List<String>();
        for (int i = 0; i < 100; i++) {
            list.addTail(String.valueOf(i));
        }
        System.out.println(list);
    }

}
