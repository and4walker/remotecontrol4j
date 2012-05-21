package org.remotecontrol4j.server.runtime;

import java.util.EnumMap;

import org.remotecontrol4j.server.plugin.Arp;
import org.remotecontrol4j.server.plugin.Nbtstat;
import org.remotecontrol4j.server.plugin.Ping;
import org.remotecontrol4j.server.plugin.Restart;
import org.remotecontrol4j.server.plugin.Shutdown;

/**
 * 初始化常量池<br>
 * 
 * @author and4walker
 *
 */
public class InitPool {
	
	public static EnumMap<Executor,String> CMD_MAP = new EnumMap<Executor,String>(Executor.class);
	
	/**
	 * 启动本程式时自动加载初始化操作 <br>
	 */
	static{
		OS.initCurrentOS();
		cache();
	}
	
	/**
	 * 进程初始化操作  <br>
	 * 将本类型OS的命令全部加载到内存中 <br>
	 */
	private static void cache(){		
		CMD_MAP.put(Executor.shutdown_key, new Shutdown().load());
		CMD_MAP.put(Executor.restart_key, new Restart().load());
		CMD_MAP.put(Executor.nbtstat_key, new Nbtstat().load());
		CMD_MAP.put(Executor.arp_key, new Arp().load());
		CMD_MAP.put(Executor.ping_key, new Ping().load());
	}
	
}

