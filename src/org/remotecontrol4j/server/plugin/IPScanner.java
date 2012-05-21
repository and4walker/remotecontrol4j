package org.remotecontrol4j.server.plugin;

import org.remotecontrol4j.server.meta.Host;
import org.remotecontrol4j.server.meta.Result;
import org.remotecontrol4j.server.meta.Task;

/**
 * IP扫描器 <br>
 * 
 * @author and4walker
 * @param <T>
 *
 */
public class IPScanner<P> extends Task<P>
{	

	public IPScanner(int taskId, P... params)
	{
		super(taskId, params);
		this.taskId = taskId;
		this.params = params;
	}

	@Override
	public void execute() throws Exception {	
		Host host = (Host)this.params[0];
		if(host == null){
			return;
		}
		String ip = host.getPreIp() + (this.taskId+1);
		Result result = new Result();
		Host newHost = Ping.getHost(ip);
		result.setValue(newHost);			
		Result.resultMap.put(this.taskId, result);
	}

}
