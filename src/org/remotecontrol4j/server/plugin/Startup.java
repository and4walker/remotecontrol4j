package org.remotecontrol4j.server.plugin;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.remotecontrol4j.server.meta.Host;
import org.remotecontrol4j.server.util.StringUtil;


/**
 * 远程开机
 * 
 * 硬件要求: <br>
 * 1.电源符合ATX 2.01标准,且+5V的备用电流必须在600mA以上 <br> 
 * 2.主板跟网卡都必须支持WOL(Wakeup On LAN)技术 <br> 
 * 3.开启BIOS中的Wakeup On Lan功能 <br> 
 * 
 * 实现步骤: <br>
 * 1.向目标服务器发送特殊数据包 <br> 
 * 2.目标服务器的网卡只要接收到这些数据包,便将其唤醒 <br> 
 * 
 */
public class Startup
{

  /**默认远程唤醒的广播地址**/
  private static final String DEFAULT_BROADCAST_ADDRESS = "255.255.255.255";
  
  /**默认远程唤醒端口号，要求防火墙没有限制**/
  private static final int DEFAULT_PORT = 9;
  
  /**
   * 构建特殊数据包(以太网信息包),
   * 总共102个字节
   */
  private DatagramPacket createPacket(byte[] destMac, String ip, int port)
      throws UnknownHostException
  {
    InetAddress destHost = InetAddress.getByName(ip);
    // 1.包含6个字节的FF
    byte[] magicBytes = new byte[102];
    for (int i = 0; i < 6; i++) {
      magicBytes[i] = (byte) 0xFF;
    }
    // 2.重复16遍目标计算机的MAC地址
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < destMac.length; j++) {
        magicBytes[6 + destMac.length * i + j] = destMac[j];
      }
    }
    // 创建数据报
    DatagramPacket dp = new DatagramPacket(magicBytes, magicBytes.length,
        destHost, port);
    return dp;
  }

  /**
   * 向目标服务器发送唤醒数据包
   */
  public boolean send(Host host)
  {
    DatagramSocket ds = null;
    try {
      byte[] hexMac = host.getHexMac();
      String ip = host.getIp();
      int port = host.getPort();
      if (StringUtil.isNullOrBlank(ip)) {
        ip = DEFAULT_BROADCAST_ADDRESS;
      }
      if (0 == port) {
        port = DEFAULT_PORT;
      }
      DatagramPacket dp = createPacket(hexMac, ip, port);
      if (null != dp) {
        ds = new DatagramSocket();
        ds.send(dp);
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (ds != null) {
        ds.close();
      }
    }
    return false;
  }

}
