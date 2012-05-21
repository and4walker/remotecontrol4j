package org.remotecontrol4j.server.plugin;

import java.util.ArrayList;
import java.util.List;

import org.remotecontrol4j.server.runtime.Container;
import org.remotecontrol4j.server.runtime.Executor;
import org.remotecontrol4j.server.runtime.InitPool;
import org.remotecontrol4j.server.runtime.Launcher;
import org.remotecontrol4j.server.runtime.OS;
import org.remotecontrol4j.server.util.StringUtil;

/**
 * ARP工具 <br>
 * 
 * @author and4walker
 *
 */
public class Arp implements Container
{

	public String load() {
		String cmd = null;
		switch(OS.TYPE){
		case WINDOWS:
			cmd = Executor.win_arp.getPrototype();
			break;
		case UNIX:
			cmd = Executor.unix_arp.getPrototype();
			break;
		}
		return cmd;
	}
	
	/**
	 * ARP Cache中是否为空
	 * @param msg
	 * @return
	 */
	public static boolean isNotNull(String msg){
		String info = msg.split(" ")[0];
		if("No".equals(info)){
			return false;
		}
		return true;
	}

	/**
	 * 根据IP地址获取MAC
	 * @param ip
	 * @return
	 */
	public static String getMAC(String ip){
		String mac = null;
		try {
			String msg = Launcher.run(InitPool.CMD_MAP.get(Executor.arp_key)+ip);
			String[] rowInfo = msg.split(StringUtil.ROW_SPLIT);
			List<String> strList = new ArrayList<String>();
			for(String info : rowInfo){
				String[] row = info.split(StringUtil.BLANK);
				if(row.length == 4){
					continue;
				}
				for(String str : row){
					if(StringUtil.isNullOrBlank(str)){
						continue;
					}
					strList.add(str);
				}
			}
			for(int i=0;i<strList.size();i++){
				if(ip.equals(strList.get(i))){
					mac = strList.get(i+1);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mac;
	}

}
