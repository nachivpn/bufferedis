/**
Contributors: Nachi
*/
package com.bufferedis.async;

import redis.clients.jedis.Jedis;

public class AsyncDel implements Runnable{

	private Jedis redis = null;
	private String[] values;
	
	public AsyncDel(String host, Integer port, String pass,
			String[] values) {
		this.values = values;
		this.redis = new Jedis(host,port,0);
		this.redis.auth(pass);
	}

	@Override
	public void run() {
		redis.del(values);
		redis.disconnect();
	}

}
