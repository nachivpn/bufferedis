/**
Contributors: Nachi
*/
package com.bufferedis.core;

import com.bufferedis.async.AsyncDel;
import com.bufferedis.util.Command;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Del extends Command<Long>{

	public Del(String host, Integer port, String pass){
		super(host, port, pass);
	}

	public CompletableFuture<Long> customFlush(){
		return CompletableFuture.supplyAsync(
				new AsyncDel(
						getHost(),
						getPort(),
						getPass(),
						getBuffer().flush()
				),
				Executors.newCachedThreadPool()
		);
	}
}
