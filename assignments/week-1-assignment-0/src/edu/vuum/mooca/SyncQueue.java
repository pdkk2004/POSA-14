package edu.vuum.mooca;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class SyncQueue<E> {
	
	private int size;
	
	private ReentrantLock lock;
	
	private LinkedList<E> data;
	
	public SyncQueue() {
		this.size = 0;
		this.lock = new ReentrantLock();
		this.data = new LinkedList<E>();
	}
	
	public void offer(E toAdd) {
		lock.lock();
		try {
			data.add(toAdd);
			size++;
		}
		finally {
			lock.unlock();
		}
	}
	
	public E poll() {
		lock.lock();
		try {
			E e = data.removeFirst();
			size--;
			return e;
		}
		finally {
			lock.unlock();
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
}
