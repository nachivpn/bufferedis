package com.bufferedis.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @Autor: vicboma1
 */
public class SetTest {

    private Set setBufferedis;

    @Before
    public void setUp() throws Exception {
        setBufferedis = new Set("localhost", 6379, "");
    }

    @After
    public void tearDown() throws Exception {
        setBufferedis = null;
    }

    @Test
    public void execute() throws Exception {
        for(int i= 0; i < 100000; i++){
            final String aux = String.valueOf(i);
            setBufferedis.add(aux,aux);
        }

        final CompletableFuture<String> resAsync = setBufferedis.exec();
        final String res = resAsync.get();

        Assert.assertEquals("OK",res);
    }

}