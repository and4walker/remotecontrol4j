package org.remotecontrol4j.server.plugin;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.remotecontrol4j.server.meta.Host;
import org.remotecontrol4j.server.runtime.Container;
import org.remotecontrol4j.server.runtime.Executor;
import org.remotecontrol4j.server.runtime.InitPool;
import org.remotecontrol4j.server.runtime.Launcher;
import org.remotecontrol4j.server.util.StringUtil;


/**
 * 
 * 
 * @author and4walker
 *
 */
public class Ping implements Container{

	private static final char TIME_OUT = 3000;
	
	/**
	 * 利用InetAddress的isReachable方法实现 <br>
	 * 非ICMP的ping工具 <br>
	 * @param ip
	 * @return
	 */
	public synchronized static boolean send(String ip) {
		try {
			InetAddress address = InetAddress.getByName(ip);
      return address.isReachable(TIME_OUT);
	  } catch (UnknownHostException e) {
	      e.printStackTrace();
	  } catch (IOException e) {
	      e.printStackTrace();
	  } 
		return false;
	}

	@Override
	public String load() {
		return Executor.ping.getPrototype();
	}
  
	/**
	 * OS自带的ping工具，可以用来 <br>
	 * 获取是否在线、主机名、组名等 <br>
	 * @param ip
	 * @return
	 */
	public static Host getHost(String ip){
		Host host = new Host();
		host.setIp(ip);
		try {
			String msg = Launcher.run(InitPool.CMD_MAP.get(Executor.ping_key)+ip);
			String[] rowMsg = msg.split(StringUtil.ROW_SPLIT);
			if(!StringUtil.isNull(rowMsg) && rowMsg.length >= 2){
				String[] rowStr = rowMsg[0].split(StringUtil.BLANK);
				if(!StringUtil.isNull(rowStr) && rowStr.length >= 2 && !StringUtil.isIPV4(rowStr[1]) && rowStr[1].split("\\.").length==3){//exist
					host.setName(rowStr[1].split("\\.")[0]);
					host.setGroupName(rowStr[1].split("\\.")[1]);
				}
				if(!rowMsg[1].contains("Request timed out.")){//online
					host.setOnline(true);
					Host nbt = Nbtstat.getHost(ip);
					host.setName(StringUtil.isNullOrBlank(host.getName())?nbt.getName():host.getName());
					host.setGroupName(StringUtil.isNullOrBlank(host.getGroupName())?nbt.getGroupName():host.getGroupName());
					host.setMac(StringUtil.isNullOrBlank(nbt.getMac())?Arp.getMAC(ip):nbt.getMac());
					if(StringUtil.isNullOrBlank(host.getName()) && StringUtil.isNullOrBlank(host.getGroupName())){
						host.setWindows(false);
					}else{
						host.setWindows(true);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return host;
	}
	
}
