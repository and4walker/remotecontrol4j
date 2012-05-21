package org.remotecontrol4j.server.meta;


/**
 * 任务基类
 * 
 * @author and4walker
 * @param <P>
 *
 */
public abstract class Task<P>
{
	public static final int STATUS_READY = 0;
	public static final int STATUS_RUNNING = 1;
	public static final int STATUS_FINISHED = 2;
	
	protected int status;
	protected int taskId;
	protected P[] params;

	public Task(int taskId,P... params)
	{
		this.status = STATUS_READY;
		this.taskId = taskId;
		this.params = params;
	}
	
	public abstract void execute() throws Exception;

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTaskId() {
		return taskId;
	}

	public int getStatus() {
		return status;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public P[] getParams() {
		return params;
	}

	public void setParams(P[] params) {
		this.params = params;
	}

}
