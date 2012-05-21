package org.remotecontrol4j.server.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.remotecontrol4j.server.meta.NIC;

/**
 * 获取本机信息
 * 
 * @author and4walker
 * 
 */
public class SysInfo
{

	/**
	 * 获取本机网卡信息
	 * @return
	 * @throws UnknownHostException
	 */
	public static List<NIC> getNIC() {
		
		NIC nic = null;
		List<NIC> result = new ArrayList<NIC>();
		try {
			Enumeration<?> em = (Enumeration<?>) NetworkInterface
					.getNetworkInterfaces();
			while (em.hasMoreElements()) {
				String mac = "";
				nic = new NIC();
				NetworkInterface ni = (NetworkInterface) em.nextElement();
				if(ni.getName().indexOf("eth") == -1){
					continue;
				}				
				nic.setName(ni.getDisplayName().split(" - ")[0]);//过滤掉乱码
				nic.setEncap(ni.getName());
				byte[] b = ni.getHardwareAddress();
				if (b == null) {
					continue;
				}
				for (int i = 0; i < b.length; i++) {
					mac += StringUtil.byteToHex(b[i]);
					if(i != b.length-1){
						mac += "-";
					}
				}
				nic.setMac(mac);
				Enumeration<?> e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					InetAddress ia = (InetAddress) e2.nextElement();
					String ip = ia.getHostAddress();
					if(StringUtil.isIPV4(ip)){
						nic.setIp(ip);
					}
				}
				result.add(nic);
			}
		} catch (SocketException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
