package com.lxy.imapp.util;


import com.lxy.imapp.element.chat_group.ElementTalk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CacheUtil {

    // 对话框组
    public static Map<String, ElementTalk> talkMap = new ConcurrentHashMap<String, ElementTalk>(16);

}
