package org.remotecontrol4j.server.meta;

/**
 * 远程计算机实体类
 * @author and4walker
 *
 */
public class Host
{

  /** mac地址 **/
  private String mac;
  /** 内网广播域或者公网IP地址 **/
  private String ip;
  /** 端口号 **/
  private int port;
  /** 计算机名称 **/
  private String name;
  /** 计算机组名称 **/
  private String groupName;
  /** 是否在线 **/
  private boolean isOnline;
  /** ip前缀 **/
  private String preIp;
  /** 是否为windows **/
  private boolean isWindows; 

	/**
   * 获取16进制的MAC地址
   * 
   * @return
   */
  public byte[] getHexMac()
  {
  	byte[] bytes = new byte[6];
    String[] hex = mac.trim().split("(\\:|\\-)");
    if (hex.length != 6) {
        throw new IllegalArgumentException("Invalid MAC address.");
    }
    try {
        for (int i = 0; i < 6; i++) {
            bytes[i] = (byte) Integer.parseInt(hex[i], 16);
        }
    }catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid hex digit in MAC address.");
    }
    return bytes;
  }

  
  public boolean isWindows() {
		return isWindows;
	}


	public void setWindows(boolean isWindows) {
		this.isWindows = isWindows;
	}


	public String getMac()
  {
    return mac;
  }

  public void setMac(String mac)
  {
    this.mac = mac;
  }

  public String getIp()
  {
    return ip;
  }

  public void setIp(String ip)
  {
    this.ip = ip;
  }

  public int getPort()
  {
    return port;
  }

  public void setPort(int port)
  {
    this.port = port;
  }

  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public String getPreIp() {
		return preIp;
	}

	public void setPreIp(String preIp) {
		this.preIp = preIp;
	}
}
