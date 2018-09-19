package lockDev.com.lock.reent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @see �����̻�����
 */
public class ReentrantLockTest implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();  // ������Ϳ���
            //lock.lock(); ��
            try {
                i++;
                System.out.println(Thread.currentThread().getName() + i);
            } finally {
                lock.unlock(); // ������Ϳ���
                //lock.unlock();��
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();t2.start();
        t1.join(); t2.join(); // main�̻߳�ȴ�t1��t2����������ִ���Ժ������
        System.err.println(i);
    }
}