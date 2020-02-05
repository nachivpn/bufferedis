/**
Contributors: Nachi
*/
package com.bufferedis.util;

import java.util.concurrent.CompletableFuture;

public abstract class Command<T> {

	private Buffer<T> buffer = new Buffer<T>();
	private String key = null;
	private String host = null;
	private Integer port = null;
	private String pass = null;

	protected abstract <T> CompletableFuture<T> customFlush();

	public Command(String host, Integer port, String pass, String key){
		this.setHost(host);
		this.setPort(port);
		this.setPass(pass);
		this.setKey(key);
	}
	
	public Command(String host, Integer port, String pass){
		this.setHost(host);
		this.setPort(port);
		this.setPass(pass);
	}

	public <T> CompletableFuture<T> exec(){
		return customFlush();
	}
	
	public Boolean add(String field){
		if(getBuffer().overflow())
			customFlush();

		return getBuffer().add(field);
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the buffer
	 */
	public Buffer getBuffer() {
		return buffer;
	}

	/**
	 * @param buffer the buffer to set
	 */
	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}
	
}
