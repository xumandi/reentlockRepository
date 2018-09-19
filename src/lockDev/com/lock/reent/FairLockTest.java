package lockDev.com.lock.reent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @see ��ƽ��
 */
public class FairLockTest implements Runnable{
    public static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.err.println(Thread.currentThread().getName() + "��ȡ��������");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FairLockTest test = new FairLockTest();
        Thread t1 = new Thread(test, "�߳�1");
        Thread t2 = new Thread(test, "�߳�2");
        t1.start();t2.start();
    }
}
/**
 * ���н��:
 * �߳�1��ȡ��������
 * �߳�2��ȡ��������
 * �߳�1��ȡ��������
 * �߳�2��ȡ��������
 * �߳�1��ȡ��������
 * �߳�2��ȡ��������
 * �߳�1��ȡ��������
 * �߳�2��ȡ��������
 * �߳�1��ȡ��������
 * �߳�2��ȡ��������
 * �߳�1��ȡ��������
 * �߳�2��ȡ��������
 * �߳�1��ȡ��������
 * �߳�2��ȡ��������
 * �߳�1��ȡ��������
 * �߳�2��ȡ��������
 * ......���ϱ��ǽ�ȡ��һ�Σ�
 */