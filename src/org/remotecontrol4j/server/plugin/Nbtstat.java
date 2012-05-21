package org.remotecontrol4j.server.plugin;

import java.util.ArrayList;
import java.util.List;

import org.remotecontrol4j.server.meta.Host;
import org.remotecontrol4j.server.runtime.Container;
import org.remotecontrol4j.server.runtime.Executor;
import org.remotecontrol4j.server.runtime.InitPool;
import org.remotecontrol4j.server.runtime.Launcher;
import org.remotecontrol4j.server.util.StringUtil;

public class Nbtstat implements Container
{

	public String load() {
		return Executor.win_nbtstat.getPrototype();
	}

	/**
	 * 根据行信息，过滤出计算机名称、组名称、MAC地址
	 * @param info
	 * @return
	 */
	public static Host getHost(String ip){
		Host host = new Host();
		try {
			String msg = Launcher.run(InitPool.CMD_MAP.get(Executor.nbtstat_key)+ip);
			String[] rowInfo = msg.split(StringUtil.ROW_SPLIT);
			List<String> strList = new ArrayList<String>();
			for(String row : rowInfo){
				if(!row.contains("<00>") && !row.contains("MAC")){
					continue;
				}
				for(String str : row.split(StringUtil.BLANK)){
					if(StringUtil.isNullOrBlank(str)){
						continue;
					}
					strList.add(str);
				}
			}						
			for(int i=0;i<strList.size();i++){
				if(strList.get(i).trim().contains("<00>")){
					if("UNIQUE".equalsIgnoreCase(strList.get(i+1).trim())){
						host.setName(StringUtil.isNullOrBlank(strList.get(i).replace("<00>", "")) ? 
								strList.get(i-1) : strList.get(i).replace("<00>", ""));
						continue;
					}else if("GROUP".equalsIgnoreCase(strList.get(i+1).trim())){
						host.setGroupName(strList.get(i-1));
						continue;
					}				
				}
				if("MAC".equalsIgnoreCase(strList.get(i).trim())){
					host.setMac(strList.get(i+3));
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return host;
	}

}
