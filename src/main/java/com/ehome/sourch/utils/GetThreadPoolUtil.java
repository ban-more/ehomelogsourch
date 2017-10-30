package com.ehome.sourch.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GetThreadPoolUtil {

    public ThreadPoolExecutor getThreadPool(){

        ThreadPoolExecutor pool = new ThreadPoolExecutor(// 自定义一个线程池

                20, // coreSize

                100, // maxSize

                30, // 30s

                TimeUnit.SECONDS,

                new LinkedBlockingQueue<Runnable>(200)

                , Executors.defaultThreadFactory()

                , new MyRejected()

        );
        return pool;
    }
}
