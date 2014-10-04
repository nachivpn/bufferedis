/**
Contributors: Nachi
 */
package com.bufferedis.core;

import com.bufferedis.async.AsyncHDel;
import com.bufferedis.util.Command;

public class HDel extends Command{

	public HDel(String host, Integer port, String pass, String key){
		super(host, port, pass, key);
	}

	public void customFlush(){
		String[] result = getBuffer().flush();
		AsyncHDel runner = new AsyncHDel(getHost(), getPort(), getPass(), getKey(), result);
		new Thread(runner).start();
	}
	
}
