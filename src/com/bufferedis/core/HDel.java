/**
Contributors: Nachi
 */
package com.bufferedis.core;

import com.bufferedis.async.AsyncHDel;
import com.bufferedis.util.Command;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class HDel extends Command {

	public HDel(String host, Integer port, String pass, String key){
		super(host, port, pass, key);
	}

	public CompletableFuture<Long> customFlush(){
		return CompletableFuture.supplyAsync(
				new AsyncHDel(
						getHost(),
						getPort(),
						getPass(),
						getKey(),
						getBuffer().flush()
				),
				Executors.newCachedThreadPool()
		);
	}
	
}
