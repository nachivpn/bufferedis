/**
Contributors: Nachi
*/
package com.bufferedis.core;

import com.bufferedis.async.AsyncDel;
import com.bufferedis.util.Command;

public class Del extends Command{

	public Del(String host, Integer port, String pass){
		super(host, port, pass);
	}

	public void customFlush(){
		String[] result = getBuffer().flush();
		AsyncDel runner = new AsyncDel(getHost(), getPort(), getPass(), result);
		new Thread(runner).start();
	}
}
