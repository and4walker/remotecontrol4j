package org.remotecontrol4j.server.runtime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.remotecontrol4j.server.meta.Host;
import org.remotecontrol4j.server.meta.NIC;
import org.remotecontrol4j.server.meta.Result;
import org.remotecontrol4j.server.plugin.IPScanner;
import org.remotecontrol4j.server.util.StringUtil;
import org.remotecontrol4j.server.util.SysInfo;

public class TestTaskRouter
{
	
	public static String getMac() throws Exception{
		Launcher.run("ping 192.168.0.1");
		String msg = Launcher.run("arp -a  192.168.0.1");
		String mac = msg.split(" ")[32];
		return mac;
	}

	public static void main(String[] args) throws Exception{ 
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(new Date()));
				

		Host host = new Host();
		host.setIp("192.168.3.176");
		String preIP = StringUtil.getPreIPV4(host.getIp());
		host.setPreIp(preIP);
		TaskRouter.run(IPScanner.class, 255, 200, host);
		
		while(Result.resultMap.size() != 255){
			Thread.sleep(1000);
		}
		
		for(Integer key : Result.resultMap.keySet()){
			Object value = Result.resultMap.get(key).getValue();
			if(null == value){
				continue;
			}
			Host result = (Host)value; 
			if(StringUtil.isNullOrBlank(result.getName()) && StringUtil.isNullOrBlank(result.getGroupName()) 
					&& StringUtil.isNullOrBlank(result.getMac()) ){
				continue;
			}
			System.out.println(result.getIp()+" iswindows="+result.isWindows()+" isonline="+result.isOnline()+" name:"+result.getName()+" groupname="+result.getGroupName()+" mac="+result.getMac() );
		}
		System.out.println(format.format(new Date()));
		
		
	}
	
}
