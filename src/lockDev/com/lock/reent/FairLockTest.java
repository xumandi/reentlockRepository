package lockDev.com.lock.reent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @see 公平锁
 */
public class FairLockTest implements Runnable{
    public static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.err.println(Thread.currentThread().getName() + "获取到了锁！");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FairLockTest test = new FairLockTest();
        Thread t1 = new Thread(test, "线程1");
        Thread t2 = new Thread(test, "线程2");
        t1.start();t2.start();
    }
}
/**
 * 运行结果:
 * 线程1获取到了锁！
 * 线程2获取到了锁！
 * 线程1获取到了锁！
 * 线程2获取到了锁！
 * 线程1获取到了锁！
 * 线程2获取到了锁！
 * 线程1获取到了锁！
 * 线程2获取到了锁！
 * 线程1获取到了锁！
 * 线程2获取到了锁！
 * 线程1获取到了锁！
 * 线程2获取到了锁！
 * 线程1获取到了锁！
 * 线程2获取到了锁！
 * 线程1获取到了锁！
 * 线程2获取到了锁！
 * ......（上边是截取的一段）
 */