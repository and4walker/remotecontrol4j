package org.remotecontrol4j.server.runtime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.remotecontrol4j.server.meta.Host;
import org.remotecontrol4j.server.plugin.Ping;
import org.remotecontrol4j.server.util.StringUtil;

public class TestPing
{
	public static ConcurrentHashMap hashmap = new ConcurrentHashMap();

	
	public static void main(String[] args) throws InterruptedException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(new Date()));
		
		String preIP = StringUtil.getPreIPV4("192.168.1.5");
		System.out.println(preIP);
		for(int i=1;i<125;i++){
			Put put1=new Put(i,preIP+i);
			put1.start();
		}
		
		System.out.println(hashmap.size());
		
		
		System.out.println(format.format(new Date()));
	}
	
	
	
}


class Put extends Thread{
	private int id;
	private String ip;
	Put(int id,String ip){
		this.id = id;
		this.ip = ip;
	}
	
	public void run(){
		Host host = Ping.getHost(ip);
			TestPing.hashmap.put(id, ip);
		if(host == null){
			return;
		}
		System.out.println("ip="+host.getIp()+" online="+host.isOnline()+" name="+host.getName()+" groupName="+host.getGroupName());
			
	}
}