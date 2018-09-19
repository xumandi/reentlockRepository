package lockDev.com.lock.reent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithConditon implements Runnable{
    public static ReentrantLock lock = new ReentrantLock(true);
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        lock.newCondition();
        try {
            lock.lock();
            System.err.println(Thread.currentThread().getName() + "-�߳̿�ʼ�ȴ�...");
            condition.await();
            System.err.println(Thread.currentThread().getName() + "-�̼߳���������");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockWithConditon test = new ReentrantLockWithConditon();
        Thread t = new Thread(test, "�߳�ABC");
        t.start();
        Thread.sleep(1000);
        System.err.println("����1���...");
        lock.lock();
        condition.signal(); // ���ø÷���ǰ��Ҫ��ȡ�������ö��������������
                            // java.lang.IllegalMonitorStateException�쳣
        lock.unlock();
    }
}