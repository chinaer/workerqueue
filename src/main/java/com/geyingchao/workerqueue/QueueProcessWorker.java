package com.geyingchao.workerqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueProcessWorker extends BaseWorker{
	
	private static final Logger logger = LoggerFactory.getLogger(QueueProcessWorker.class);
	
	public QueueProcessWorker() {
		this.name = "message process woker";
		this.messageHandler = new DefaultMessageHandler();
	}
    
	public QueueProcessWorker(BaseHandler handler) {
		this.name = "message process woker";
		this.messageHandler = handler;
	}
	
	public QueueProcessWorker(String name, BaseHandler handler) {
		this.name = name;
		this.messageHandler = handler;
	}
	
	@Override
    public void run() {
		logger.info(this.name + " running!");
		super.run();
	}

}
