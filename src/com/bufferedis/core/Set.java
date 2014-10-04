/**
Contributors: Nachi
*/
package com.bufferedis.core;

import com.bufferedis.async.AsyncSet;
import com.bufferedis.util.Command;

public class Set extends Command{

	public Set(String host, Integer port, String pass){
		super(host, port, pass);
	}

	public void customFlush(){
		String[] result = getBuffer().flush();
		AsyncSet runner = new AsyncSet(getHost(), getPort(), getPass(), result);
		new Thread(runner).start();
	}
	
	public void add(String key, String value){
		if(getBuffer().pairedOverflow())
			customFlush();
		getBuffer().add(key);
		getBuffer().add(value);
	}

}
