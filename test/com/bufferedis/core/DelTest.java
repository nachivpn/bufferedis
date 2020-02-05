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
public class DelTest {

    private Del delBufferedis;

    @Before
    public void setUp() throws Exception {
        delBufferedis = new Del("localhost", 6379, "");
    }

    @After
    public void tearDown() throws Exception {
        delBufferedis = null;
    }

    @Test
    public void execute() throws Exception {
        for (int i = 0; i < 100000; i++) {
            final String aux = String.valueOf(i);
            delBufferedis.add(aux);
        }

        final CompletableFuture<Long> resAsync = delBufferedis.exec();
        final Long res = resAsync.get();

        Assert.assertTrue(res >= 0);
    }

}