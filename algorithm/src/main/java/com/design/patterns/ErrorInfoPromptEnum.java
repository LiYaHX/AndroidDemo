package com.design.patterns;

/**
 * Created by robinlee on 2017/11/16.
 *
 *  7. 枚举 (Enum)
 *     优点：简单
 *          (1) 调用简单：<code>ErrorInfoPromptEnum.ERROR_INFO_PROMPT_ENUM</code>；
 *          (2) 创建枚举默认为线程安全，因此不用担心 double checked locking，还能防止反序列化导致重新创建对象；
 */
public enum ErrorInfoPromptEnum {

    ERROR_INFO_PROMPT_ENUM

}
