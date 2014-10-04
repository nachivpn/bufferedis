/**
Contributors: Nachi
 */
package com.bufferedis.util;

import java.util.ArrayList;
import java.util.List;

public class Buffer {

	/* buffer list */
	private List <String> buffer;
	/* points to the index of the next available block */
	private int bufferPointer;
	/* max buffer size */
	private long bufferLimit;

	public Buffer() {
		buffer = new ArrayList<String>();
		bufferPointer = 0;
		bufferLimit = 100000;
	}

	public void add(String arg){
		buffer.add(arg);
		bufferPointer++;
	}

	public long getSize(){
		return buffer.size();
	}

	public String[] flush(){
		String [] bufferArray = (String[]) buffer.toArray(new String[buffer.size()]);
		bufferPointer = 0;
		buffer.clear();
		return bufferArray;
	}

	public boolean overflow(){
		return (bufferPointer >= getBufferLimit());
	}
	
	public boolean pairedOverflow(){
		return (bufferPointer+1 >= getBufferLimit());
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
