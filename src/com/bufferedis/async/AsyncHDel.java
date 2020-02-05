/**
Contributors: Nachi
*/
package com.bufferedis.async;

import redis.clients.jedis.Jedis;
import java.util.function.Supplier;

public class AsyncHDel implements Supplier<Long> {

	private Jedis redis;
	private String hash;
	private String[] fields;
	
	public AsyncHDel(String host, Integer port, String pass, String hash, String[] fields) {
		this.hash = hash;
		this.fields = fields;
		this.redis = new Jedis(host, port, 0);
		if(!"".equals(pass))
			this.redis.auth(pass);
	}

	@Override
	public Long get() {
		final Long res = redis.hdel(hash, fields);
		redis.disconnect();
		return res;
	}

}
