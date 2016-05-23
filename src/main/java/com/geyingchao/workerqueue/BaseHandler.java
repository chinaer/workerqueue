package com.geyingchao.workerqueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseHandler {

private static final Logger LOGGER = LoggerFactory.getLogger(BaseHandler.class);
	
	public void startProcess(final Object msg) throws Exception {
		this.process(msg);
	}
		
	public abstract void process(final Object msg);
}
