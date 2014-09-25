/**
Contributors: Nachi
 */
package com.bufferedis.core;

import com.bufferedis.async.AsyncHDel;
import com.bufferedis.util.Buffer;

public class HDel{

	private Buffer buffer = new Buffer();
	private String hashName = null;
	private String host;
	private Integer port;
	private String pass;
	
	public HDel(String host, Integer port, String pass, String hash){
		this.host = host;
		this.port = port;
		this.pass = pass;
		this.hashName = hash;
	}

	public void exec(){
		customFlush();
	}
	
	private void customFlush(){
		String[] result = buffer.flush();
		AsyncHDel runner = new AsyncHDel(host, port, pass, hashName, result);
		new Thread(runner).start();
	}
	
	public void add(String field){
		if(buffer.overflow())
			customFlush();
		buffer.add(field);
	}

}
