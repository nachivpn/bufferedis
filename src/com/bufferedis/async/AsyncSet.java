/**
Contributors: Nachi
*/
package com.bufferedis.async;

import redis.clients.jedis.Jedis;

public class AsyncSet implements Runnable{

	private Jedis redis = null;
	private String[] args;
	
	public AsyncSet(String host, Integer port, String pass,
			String[] args) {
		this.args = args;
		this.redis = new Jedis(host,port,0);
		this.redis.auth(pass);
	}

	@Override
	public void run() {
		redis.mset(args);
		redis.disconnect();
	}

}
