/**
Contributors: Nachi
*/
package com.bufferedis.core;

import com.bufferedis.async.AsyncSet;
import com.bufferedis.util.Command;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Set extends Command {

	public Set(String host, Integer port, String pass){
		super(host, port, pass);
	}

	public CompletableFuture<String> customFlush(){
		return CompletableFuture.supplyAsync(
				new AsyncSet(
						getHost(),
						getPort(),
						getPass(),
						getBuffer().flush()
				),
                Executors.newCachedThreadPool()
		);
	}
	
	public Boolean add(String key, String value){
		if(getBuffer().pairedOverflow())
			customFlush();
		return getBuffer().add(key) && getBuffer().add(value);
	}

}
