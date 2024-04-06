package com.lxy.imapi.service.impl;

import com.lxy.imapi.service.ImServerURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImServerURLServiceImpl implements ImServerURLService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getImServerURL() {
        String  pattern ="im_server:*";

        // 使用scanOptions设置scan参数，如超时时间，匹配模式等
        ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).count(1000).build();

        // 存储所有找到的键
        List<String> keys = new ArrayList<>();


        // 使用StringRedisTemplate的scan方法获取匹配的键
        stringRedisTemplate.executeWithStickyConnection((connection) -> {
            Cursor<byte[]> cursor = connection.scan(scanOptions);
            while (cursor.hasNext()) {
                keys.add(new String(cursor.next()));
            }
            return null;
        });

        if(keys == null || keys.size() == 0)
            return "";

        // 获取所有键对应的值
        List<String> values = stringRedisTemplate.opsForValue().multiGet(keys);
        int minIndex = 0;
        if(values != null && !values.isEmpty()){
            List<Integer> collect = values.stream().map(value -> {
                return Integer.valueOf(value);
            }).collect(Collectors.toList());
            Integer minNum = collect.get(0);

            for(int i = 1; i < collect.size(); i++){
                if(collect.get(i) < minNum){
                    minNum = collect.get(i);
                    minIndex = i;
                }
            }
        }
        System.out.println("本次获取的ip为:" + keys.get(minIndex));
        stringRedisTemplate.opsForValue().increment(keys.get(minIndex));

        return keys.get(minIndex).split(":")[1];
    }
}
