package org.remotecontrol4j.server.meta;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 执行结果集 <br>
 * 
 * @author and4walker
 *
 */
public class Result
{
	private String ok;
	
	private String error;
	
	private Object value;
	
	public static  ConcurrentHashMap<Integer,Result> resultMap = new ConcurrentHashMap<Integer,Result>();
	
	public Result(){
	}
	
	public ConcurrentHashMap<Integer, Result> getResultMap() {
		return resultMap;
	}

	public void setResultMap(ConcurrentHashMap<Integer, Result> resultMap) {
		this.resultMap = resultMap;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
