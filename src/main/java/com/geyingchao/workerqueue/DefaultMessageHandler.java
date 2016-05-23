package com.geyingchao.workerqueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMessageHandler  extends BaseHandler{

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageHandler.class);

	//处理request和response返回
	@Override
	public void process(final Object msg) {
		try{
			System.out.println(msg.toString() +":task has been processed !");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
