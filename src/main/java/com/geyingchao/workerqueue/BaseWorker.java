package com.geyingchao.workerqueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseWorker implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(BaseWorker.class);
	
	protected volatile boolean isRunning = true;
	protected String name;
	protected Queue<Object> msgQueue = new ConcurrentLinkedQueue<Object>();
	protected BaseHandler messageHandler;
	
    public void closeWorker() {
        isRunning = false;
    }
    
    public void addMessage(Object c) {
        if (isRunning) {
            synchronized (msgQueue) {
                msgQueue.offer(c);
            }
        }
    }
    public void run() {
		while(true){
			if (msgQueue.isEmpty()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				Object message =  null;
				while ((message = msgQueue.poll()) != null) {
					try {
						messageHandler.startProcess(message);
					}catch(Throwable e) {
						logger.error("", e);
					}
				}
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	public int getQueueSize() {
		return this.msgQueue.size();
	}
}
