package com.geyingchao.workerqueue;

public class TestMain {
	
	public static void main(String[] args) {
		WorkerManager.getInstance().initWorker(null, null);
		WorkerManager.getInstance().dispatchMessage(new String("zhangsan"));
	}

}
