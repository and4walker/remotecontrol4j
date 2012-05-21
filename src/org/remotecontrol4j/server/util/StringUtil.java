package org.remotecontrol4j.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * 
 * @author and4walker
 * 
 */
public class StringUtil
{
	/** 分号 **/	
	public static final String ROW_SPLIT = ";";
	
	/** 空格 **/
	public static final String BLANK = " ";
	
	/**
	 * 判断字符串是否为空或空格
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrBlank(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断数组是否为空或空格
	 * @param str
	 * @return
	 */
	public static boolean isNull(String... str) {
		if (str == null || str.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为IPV4的地址
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isIPV4(String str) {
		Pattern p = Pattern
				.compile("^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
						+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 根据IP获取该IP所属整个网段的IP列表
	 * 
	 * @param ip
	 * @return
	 */
	public static List<String> getAllIPV4(String ip) {
		if (!isIPV4(ip)) {
			return null;
		}
		List<String> allIPs = new ArrayList<String>();
		String ss = getPreIPV4(ip);
		for (int i = 1; i <= 255; i++) {
			allIPs.add(ss + i);
		}
		return allIPs;
	}

	/**
	 * 获取ipv4地址的前缀地址
	 * @param ip
	 * @return
	 */
	public static String getPreIPV4(String ip){
		return ip.substring(0, ip.lastIndexOf(".") + 1);
	}
	
	/**
	 * 字节码转换为十六进制
	 * @param ib
	 * @return
	 */
	public static String byteToHex(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		return new String(ob);
	}

}
