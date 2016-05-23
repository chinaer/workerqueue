package com.geyingchao.workerqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerManager {
	
		private static final Logger LOGGER = LoggerFactory.getLogger(WorkerManager.class);
		private static WorkerManager instance = null;
		private QueueProcessWorker[] queueWorkers;
		private Thread[] queueThreads;
		private int threadAmount = 1;	
		
		public static WorkerManager getInstance() {
			if(instance == null){
				synchronized (WorkerManager.class) {
					if(instance==null){
						instance = new WorkerManager();
						return instance;
					}
				}
			}
			return instance;
		}
		
		private WorkerManager(){
		}

		public synchronized void initWorker(Integer nThreads,BaseHandler handler) {
			if(nThreads==null || nThreads<=0){
				nThreads=Runtime.getRuntime().availableProcessors() * 2 + 1;
			}
			if(handler==null){
				handler = new DefaultMessageHandler();
			}
			threadAmount = nThreads;
			queueWorkers = new QueueProcessWorker[nThreads];
			queueThreads = new Thread[nThreads];
			for(int i = 0; i < nThreads; i++) {
				QueueProcessWorker worker = 
						new QueueProcessWorker("queue_worker" + i,handler);
				queueWorkers[i] = worker;
		        Thread thread = new Thread(worker, "queue_worker _thread" + i);
		        queueThreads[i] = thread;
		        thread.start();
			}
		}
				
		public void dispatchMessage(Object request) {
					int m = Long.valueOf(Math.abs((long)request.hashCode()) % threadAmount).intValue();
					queueWorkers[m].addMessage(request);
					LOGGER.info(queueWorkers[m].getName()+":execute task");
					return;
			}
	
		public synchronized void closeServer() {
		}

}
