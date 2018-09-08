package com.job.interview;

/**
 * Created by robinlee on 2018/5/19.
 */

public class TestStaticB extends TestStaticA{

    static {
        System.out.print("a");
    }

    public TestStaticB() {

        System.out.print("b");
    }
}
