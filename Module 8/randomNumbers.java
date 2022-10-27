package module8;

import java.util.Random;

class multiThread implements Runnable {
	Random die = new Random();
	int dice = die.nextInt(10 + 1);
	static int sum = 0;
	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 200000000; i++) {
			sum = sum + dice;
			dice = die.nextInt(10 + 1);
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Multithreading produced: " + sum + " in " + (endTime - startTime) + " milliseconds");
	}
}

class singleThread implements Runnable {
	Random die2 = new Random();
	int dice2 = die2.nextInt(10 + 1);
	static int sum2 = 0;
	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 200000000; i++) {
			sum2 = sum2 + dice2;
			dice2 = die2.nextInt(10 + 1);
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Single thread produced: " + sum2 + " in " + (endTime - startTime) + " milliseconds");
	}
}

public class randomNumbers {
	public static void main(String[] args) throws Exception {
		Thread mThread = new Thread(new multiThread());
		Thread mThread2 = new Thread(new multiThread());
		mThread.start();
		mThread2.start();
		
		Thread sThread = new Thread(new singleThread());
		sThread.start();
	}
}
