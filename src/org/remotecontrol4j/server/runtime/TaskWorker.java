package org.remotecontrol4j.server.runtime;

import java.util.List;

import org.remotecontrol4j.server.meta.Task;

/**
 * 任务运行器
 * 
 * @author and4walker
 * @param <P>
 * @param <T>
 *
 */
@SuppressWarnings("rawtypes")
public class TaskWorker<P> extends Thread
{
	private List<Task> taskList;
	private int threadId;


	public TaskWorker(int threadId,List<Task> taskList){
		this.threadId = threadId;
		this.taskList = taskList;
	}

//	@Override
//	public Result call() throws Exception {
//		for(Task task : taskList){
//			this.result.getResultMap().put(task.getTaskId(), task.execute());
//		}
//		return result;
//	}
	

	@Override
	public void run() {
		for(Task task : taskList){
			try {
				task.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

}
