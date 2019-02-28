package com.toly1994.tolyservice.temp;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者：张风捷特烈
 * 时间：2019/1/25/025:18:26
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class ThreadLocalDemo {


    @Test
    public void test(){
        System.out.println(get());
    }
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);
    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override

        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
}
