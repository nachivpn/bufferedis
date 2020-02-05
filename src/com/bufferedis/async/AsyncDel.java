/**
Contributors: Nachi
*/
package com.bufferedis.async;

import redis.clients.jedis.Jedis;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class AsyncDel implements Supplier<Long> {

	private Jedis redis;
	private String[] values;
	
	public AsyncDel(String host, Integer port, String pass, String[] values) {
		this.values = values;
		this.redis = new Jedis(host,port,0);
		if(!"".equals(pass))
			this.redis.auth(pass);
	}

	@Override
	public Long get() {
		final Long res = redis.del(values);
		redis.disconnect();
		return res;
	}

}
