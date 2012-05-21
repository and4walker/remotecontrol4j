package org.remotecontrol4j.server.plugin;

import java.util.concurrent.ExecutionException;

import org.remotecontrol4j.server.meta.Host;
import org.remotecontrol4j.server.meta.Result;
import org.remotecontrol4j.server.runtime.Container;
import org.remotecontrol4j.server.runtime.Executor;
import org.remotecontrol4j.server.runtime.Launcher;
import org.remotecontrol4j.server.runtime.OS;

/**
 * 远程关机 <br>
 * 
 * 目前还没有太好的实现，调用系统命令实现 <br>
 * 
 * @author and4walker
 *
 */
public class Shutdown implements Container
{

	public String load() {	
		String cmd = "";
		switch(OS.TYPE){
			case WINDOWS:
				cmd = Executor.win_shutdown.getPrototype();
				break;
			case UNIX:
				cmd = Executor.unix_shutdown.getPrototype();
				break;
		}
		return cmd;
	}


	
}
