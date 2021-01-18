package org.apframework.ac.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * http://devgou.com/article/Apache-Commons-Pool/
 */
public class CommonsPoolTest {

    public static void main(String[] args) throws Exception {
        CommonsPoolTest test = new CommonsPoolTest();
        test.test();
    }

    public void test() throws Exception {
        GenericObjectPool<StringBuffer> pool = new GenericObjectPool<StringBuffer>(new BasePooledObjectFactory<StringBuffer>() {
            @Override
            public StringBuffer create() throws Exception {
                return new StringBuffer();
            }
            @Override
            public PooledObject<StringBuffer> wrap(StringBuffer buffer) {
                return new DefaultPooledObject<StringBuffer>(buffer);
            }
            @Override
            public void passivateObject(PooledObject<StringBuffer> pooledObject) {
                pooledObject.getObject().setLength(0);
            }
        });
        pool.setMaxTotal(8);
        pool.setMaxIdle(4);
        pool.setMinIdle(2);
        printPoolStatus(pool);
        System.out.println("pool 初始化完成\n");
        StringBuffer[] buffer = new StringBuffer[9];
        for (int i = 0; i < 8; i++) {
            System.out.print("borrow " + i);
            buffer[i] = pool.borrowObject();
            buffer[i].append("string buffer " + i);
            printPoolStatus(pool);
        }
        // 如果 borrow 第 9 个，会阻塞
//        buffer[8] = pool.borrowObject();
        for (int i = 0; i < 8; i++) {
            System.out.print("return " + i);
            pool.returnObject(buffer[i]);
            printPoolStatus(pool);
        }
    }
    private void printPoolStatus(GenericObjectPool pool) {
        System.out.println(" [active: " + pool.getNumActive() + ", idle: " + pool.getNumIdle() + ", wait: " + pool.getNumWaiters() + "]");
    }
}
