package org.remotecontrol4j.server.meta;

/**
 * 网卡 <br>
 * 
 * @author and4walker
 *
 */
public class NIC
{
	/**Windows下为网卡名称,Unix下为encap**/
	private String name;
	/**mac地址**/
	private String mac;
	/**网卡对应ip**/
	private String ip;
	/**连接概括**/
	private String encap;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getEncap() {
		return encap;
	}
	public void setEncap(String encap) {
		this.encap = encap;
	}
}
