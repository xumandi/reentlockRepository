package lockDev.com.lock.reent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @see ������ȴ���ʱ
 */
public class TryLockTest implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) { // �ȴ�1��
                Thread.sleep(2000);  //����2��
            } else {
                System.err.println(Thread.currentThread().getName() + "��ȡ��ʧ�ܣ�");
            }
        } catch (Exception e) {
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TryLockTest test = new TryLockTest();
        Thread t1 = new Thread(test); t1.setName("�߳�1");
        Thread t2 = new Thread(test); t1.setName("�߳�2");
        t1.start();t2.start();
    }
}
/**
 * ���н��:
 * �߳�2��ȡ��ʧ�ܣ�
 */ 