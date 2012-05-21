package org.remotecontrol4j.server.util;

public class TestStringUtil
{

	public static void main(String[] args){
		String str = StringUtil.isNullOrBlank("")?"is null":"is not null";
		System.out.println(StringUtil.isIPV4(""));
	}
}
