package com.design.patterns;

/**
 * Created by robinlee on 2017/11/16.
 *
 *  1. 懒汉式（Lazy-load Singleton）
 *     缺点：线程不安全，多线程时会实例化多个实例，违背 SingleInstancePattern 设计理念
 */
public class ErrorInfoPromptLazyLoad {

    private static ErrorInfoPromptLazyLoad ERROR_INFO_PROMPT;

    public static ErrorInfoPromptLazyLoad getInstance(){
        if(ERROR_INFO_PROMPT == null){
            ERROR_INFO_PROMPT = new ErrorInfoPromptLazyLoad();
        }
        return ERROR_INFO_PROMPT;
    }

    private ErrorInfoPromptLazyLoad() {

    }
}
