package org.remotecontrol4j.server.runtime;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.remotecontrol4j.server.meta.Task;

/**
 * 任务路由器 <br>
 * 
 * @author and4walker
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class TaskRouter
{
	
	/**
	 * 执行任务入口点 ：智能分配任务数
	 * 
	 * @param clazz
	 * @param taskCount
	 * @param threadCount
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static <P extends Object> void run(Class<? extends Task> clazz,int taskCount,int threadCount,P... params) throws Exception{
		if(1 < threadCount){
		  runMulti(clazz,taskCount,threadCount,params);
		}else{
			runSingle(clazz,taskCount,params);
		}
	}	
	
	private static <P extends Object> void runSingle(Class<? extends Task> clazz,int taskCount,P... params) throws Exception{
		List<Task> taskList = getTaskList(clazz,taskCount,params);
		for(Task task : taskList){
			 task.execute();
		}
	}
	
	private static <P extends Object> void runMulti(Class<? extends Task> clazz,int taskCount,int threadCount,P... params) throws Exception {		
		List<Task> taskList = getTaskList(clazz,taskCount,params);
		List<Task>[] taskListPerThread = TaskRouter.distribute(taskList, threadCount);
//		ExecutorService pool = Executors.newFixedThreadPool(taskCount);
//		for (int i = 0; i < taskListPerThread.length; i++) {
//				Callable<Result> workThread = new TaskWorker(i, taskListPerThread[i]);
//				Future<Result> future = pool.submit(workThread);
//				ConcurrentHashMap<Integer,Result> taskResultMap = future.get().getResultMap();
//				for(Integer key : taskResultMap.keySet()){
//					result.getResultMap().put(key, taskResultMap.get(key));
//				}
//		}
//		pool.shutdown();
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < taskListPerThread.length; i++) {
			TaskWorker workThread = new TaskWorker(i,taskListPerThread[i]);
			pool.execute(workThread);
		}
		pool.shutdown();
	}
	
	/**
	 * 通过反射获取其构造方法并构建任务列表
	 * @param clazz
	 * @param taskCount
	 * @return
	 * @throws Exception
	 */
	private static <P extends Object> List<Task> getTaskList(Class<? extends Task> clazz,int taskCount,P... params) throws Exception{
		Constructor<Task> constructor =  (Constructor<Task>) clazz.getConstructors()[0];
		List<Task> taskList = new ArrayList<Task>();
		for (int i = 0; i < taskCount; i++) {
			taskList.add(constructor.newInstance(i,params));
		}
		return taskList;
	}
	
	/**
	 * 把 List 中的任务平均分配给每个线程，剩于的依次附加给前面的线程 
	 * 返回的数组有多少个元素 (List) 就表明将启动多少个工作线程
	 * 
	 * @param taskList
	 * @param threadCount
	 * @return 
	 */
	private static List<Task>[] distribute(List<Task> taskList, int threadCount) {
		int minTaskCount = taskList.size() / threadCount;
		int remainTaskCount = taskList.size() % threadCount;
		int actualThreadCount = minTaskCount > 0 ? threadCount : remainTaskCount;
		List<Task>[] taskListPerThread = new List[actualThreadCount];
		int taskIndex = 0;
		int remainIndces = remainTaskCount;
		for (int i = 0; i < taskListPerThread.length; i++) {
			taskListPerThread[i] = new ArrayList<Task>();
			if (minTaskCount > 0) {
				for (int j = taskIndex; j < minTaskCount + taskIndex; j++) {
					taskListPerThread[i].add(taskList.get(j));
				}
				taskIndex += minTaskCount;
			}
			if (remainIndces > 0) {
				taskListPerThread[i].add(taskList.get(taskIndex++));
				remainIndces--;
			}
		}
		return taskListPerThread;
	}
}
