package com.sample.listing04;
// listing 4

// An example of CyclicBarrier. 

import java.util.concurrent.*;

public class BarDemo {
	public static void main(String args[]) {
		CyclicBarrier cb = new CyclicBarrier(6, new BarAction());

		System.out.println("Starting");

		new MyThread(cb, "A");
		new MyThread(cb, "B");
		new MyThread(cb, "C");
		new MyThread(cb, "D");
		new MyThread(cb, "E");
		new MyThread(cb, "F");

	}
}

// A thread of execution that uses a CyclicBarrier.
class MyThread implements Runnable {
	CyclicBarrier cbar;
	String name;

	MyThread(CyclicBarrier c, String n) {
		cbar = c;
		name = n;
		new Thread(this).start();
	}

	public void run() {

		System.out.println(name);

		try {
			cbar.await();
		} catch (BrokenBarrierException exc) {
			System.out.println(exc);
		} catch (InterruptedException exc) {
			System.out.println(exc);
		}
	}
}

// An object of this class is called when the
// CyclicBarrier ends.
class BarAction implements Runnable {
	public void run() {
		System.out.println("Barrier Reached!");
	}
}
