/**
Contributors: Nachi
*/
package com.bufferedis.async;

import redis.clients.jedis.Jedis;

public class AsyncHDel implements Runnable {

	private Jedis redis = null;
	private String hash;
	private String[] fields;
	
	public AsyncHDel(String host, Integer port, String pass,
			String hash, String[] fields) {
		this.hash = hash;
		this.fields = fields;
		this.redis = new Jedis(host, port, 0);
		this.redis.auth(pass);
	}

	@Override
	public void run() {
		redis.hdel(hash, fields);
		redis.disconnect();
	}

}
