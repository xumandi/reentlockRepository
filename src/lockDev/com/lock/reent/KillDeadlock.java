package lockDev.com.lock.reent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @see �ж���Ӧ
 */
public class KillDeadlock implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public KillDeadlock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();  // �Կ�����Ӧ�жϵķ�ʽ����
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();  // �Կ�����Ӧ�жϵķ�ʽ����
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) lock1.unlock();  // ע���жϷ�ʽ
            if (lock2.isHeldByCurrentThread()) lock2.unlock();
            System.err.println(Thread.currentThread().getId() + "�˳���");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        KillDeadlock deadLock1 = new KillDeadlock(1);
        KillDeadlock deadLock2 = new KillDeadlock(2);
        Thread t1 = new Thread(deadLock1);
        Thread t2 = new Thread(deadLock2);
        t1.start();t2.start();
        Thread.sleep(1000);
        t2.interrupt(); // ��
    }
}