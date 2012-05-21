package org.remotecontrol4j.server.meta;

import org.remotecontrol4j.server.meta.Host;

public class TestHost
{
	
	public static void main(String[] args){
		Host host = new Host();
		host.setIp("");
		host.setMac("60:EB:69:D7:50:52");

		System.out.println(host.getHexMac());
	}

}
