package tp4.server;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StockCounter implements Serializable {

    final Lock lock = new ReentrantLock();

    private Integer totalStock = 0;
    private Integer actualStock = 0;

    public StockCounter(int stock) {
        this.totalStock = stock;
        this.actualStock = stock;
    }

    public boolean incrementActualStock() {
        lock.lock();
        if (actualStock < totalStock) {
            actualStock++;
            return true;
        }
        lock.unlock();
        return false;
    }

    public boolean reduceActualStock() {
        lock.lock();
        if (actualStock > 0) {
            actualStock--;
            return true;
        }
        lock.unlock();
        return false;
    }
}
