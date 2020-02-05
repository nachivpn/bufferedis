package com.bufferedis.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;

/**
 * @Autor: vicboma1
 */
public class HDelTest {

    private HDel hDelBufferedis;

    @Before
    public void setUp() throws Exception {
        hDelBufferedis = new HDel("localhost", 6379, "","key");
    }

    @After
    public void tearDown() throws Exception {
        hDelBufferedis = null;
    }

    @Test
    public void execute() throws Exception {
        for(int i= 0; i < 100000; i++){
            final String aux = String.valueOf(i);
            hDelBufferedis.add( i%2 ==0 ? aux : "key");
        }

        final CompletableFuture<Long> resAsync = hDelBufferedis.exec();
        final Long res = resAsync.get();

        Assert.assertTrue(res >= 0);
    }

}