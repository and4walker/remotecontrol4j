package org.remotecontrol4j.server.runtime;

/**
 * 可执行内部命令集
 * 
 * @author and4walker
 *
 */
public enum Executor
{
	shutdown_key,
	restart_key,
	arp_key,
	nbtstat_key,
	ping_key,
	
	ping("ping -n 1 -a "),
	
	win_shutdown("shutdown "),
	win_restart("shutdown "),
	win_arp("arp -a "),
	win_nbtstat("nbtstat -a "),
	
	unix_shutdown("shutdown "),
	unix_restart("shutdown "),
	unix_arp("arp "),
	unix_nbtstat("nbtstat -a ");
	
	/** 命令原型 **/
  private String prototype;
	
  Executor(){
  	
  }
  
	Executor(String prototype){
		this.prototype = prototype;
	}
	
	public String getPrototype() {
		return prototype;
	}

	public void setPrototype(String prototype) {
		this.prototype = prototype;
	}
	
}
