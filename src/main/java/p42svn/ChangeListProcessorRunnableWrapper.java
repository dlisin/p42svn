package p42svn;

import java.util.concurrent.CountDownLatch;

/**
 * @author Pavel Belevich
 *         Date: 5/14/11
 *         Time: 8:57 PM
 */
public class ChangeListProcessorRunnableWrapper implements Runnable {

    private ChangeListProcessor changeListProcessor;
    private CountDownLatch cdl;

    public ChangeListProcessorRunnableWrapper(ChangeListProcessor changeListProcessor, CountDownLatch cdl) {
        this.changeListProcessor = changeListProcessor;
        this.cdl = cdl;
    }

    public void run() {
        try {
            changeListProcessor.process();
        } catch (Throwable e) {
            e.printStackTrace(System.out);
            System.exit(-1);
        } finally {
            cdl.countDown();
        }
    }
}
