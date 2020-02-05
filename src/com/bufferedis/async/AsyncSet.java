/**
Contributors: Nachi
*/
package com.bufferedis.async;

import redis.clients.jedis.Jedis;
import java.util.function.Supplier;

public class AsyncSet implements Supplier<String> {

	private Jedis redis;
	private String[] args;
	
	public AsyncSet(String host, Integer port, String pass,  String[] args) {
		this.args = args;
		this.redis = new Jedis(host,port,0);
		if(!"".equals(pass))
			this.redis.auth(pass);
	}

	@Override
	public String get() {
		final String res = redis.mset(args);
		redis.disconnect();
		return res;
	}

}
