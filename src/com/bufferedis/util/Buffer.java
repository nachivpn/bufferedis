/**
Contributors: Nachi
*/
package com.bufferedis.util;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
	
	private List <String> buffer;
	private int bufferPointer;
	private long bufferLimit;
	
	public Buffer() {
		buffer = new ArrayList<String>();
		bufferPointer = 0;
		bufferLimit = 10000;
	}

	public void add(String arg){
		buffer.add(arg);
		bufferPointer++;
	}
	
	public String[] flush(){
		String [] bufferArray = (String[]) buffer.toArray(new String[buffer.size()]);
		try{
			bufferPointer = 0;
			buffer.clear();
		}catch(Exception e){
			e.printStackTrace();
		}
		return bufferArray;
	}
	
	public boolean overflow(){
		return (bufferPointer >= getBufferLimit());
	}

	/**
	 * @return the bufferLimit
	 */
	public long getBufferLimit() {
		return bufferLimit;
	}

	/**
	 * @param bufferLimit the bufferLimit to set
	 */
	public void setBufferLimit(long bufferLimit) {
		this.bufferLimit = bufferLimit;
	}
}
