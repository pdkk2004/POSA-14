package edu.vuum.mocca;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 */
public class SimpleSemaphore {
	
    /**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	this.permits = permits;
    	this.lock = new ReentrantLock(fair);
    	this.notZero = lock.newCondition();
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here
    	lock.lockInterruptibly();
    	try {
    		while (permits == 0) {
    			notZero.await();
    		}
    		permits--;
    	}
    	finally {
    		lock.unlock();
    	}
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     * @throws InterruptedException 
     */
    public void acquireUninterruptibly() {
		// TODO - you fill in here
		lock.lock();
		try {
			while (permits == 0) {
				notZero.awaitUninterruptibly();
			}
			permits--;
		} finally {
			lock.unlock();
		}
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    	lock.lock();
    	try {
    		permits++;
    		notZero.signalAll();
    	}
    	finally {
    		lock.unlock();
    	}
    }
    
    /**
     * Return the number of permits available.
     */
    public int availablePermits(){
    	// TODO - you fill in here
    	lock.lock();
    	try {
    		return permits;
    	}
    	finally {
    		lock.unlock();
    	}
    }
    
    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
    private final ReentrantLock lock;
    

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
    private final Condition notZero;

    /**
     * Define a count of the number of available permits.
     */
	private int permits;

}

