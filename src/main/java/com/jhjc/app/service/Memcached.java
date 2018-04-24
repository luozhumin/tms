package com.jhjc.app.service;


import net.rubyeye.xmemcached.MemcachedClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Author: yuanhy
 * Time: 2017-9-6  17:04
 * Description:这个类在xml中注入的
 *
 */

public class Memcached {
    private static Log log = LogFactory.getLog(Memcached.class);
    private boolean isOpen; // Memcached 开关
    private int expires;    // 默认过期时间
    private MemcachedClient mc;

    private String cachePre;

    /*
    add 仅当存储空间中不存在键相同的数据时才保存
     */
    public <T> void addWithNoReply(String key, T value) {
        this.addWithNoReply(key, expires, value);
    }

    /**
     * 设置键值对
     *
     * @param key     key
     * @param expires 单位:秒，0 表示永不过期
     * @param value   必须是一个可序列化的对象, 可以是容器类型如:List，但容器里面保存的对象必须是可序列化的
     */
    public <T> void addWithNoReply(String key, int expires, T value) {
        if (StringUtils.isEmpty(key)) return;
        try {
            if (isOpen && mc != null) {
                mc.addWithNoReply(this.cachePre + key, expires, value);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /*
    set 无论何时都保存
     */
    public <T> void set(String key, T value) {
        this.set(key, expires, value);
    }

    /**
     * 设置键值对
     *
     * @param key     key
     * @param expires 单位:秒，0 表示永不过期
     * @param value   必须是一个可序列化的对象, 可以是容器类型如:List，但容器里面保存的对象必须是可序列化的
     */
    public <T> void set(String key, int expires, T value) {
        if (StringUtils.isEmpty(key)) return;
        try {
            if (isOpen && mc != null) {
                mc.set(this.cachePre + key, expires, value);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 根据key获得值
     *
     * @param key key
     * @return value
     */
    public <T> T get(String key) {
        try {
            if (!StringUtils.isEmpty(key) && isOpen && mc != null) {
                return (T) mc.get(this.cachePre + key);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public void delete(String key) {
        try {
            if (!StringUtils.isEmpty(key) && isOpen && mc != null) {
                mc.delete(this.cachePre + key);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * 给每个原始key加上前缀后，再查。
     *
     * @param keys               原始key
     * @param memcachedKeyPrefix 前缀
     * @return
     */
    public <T> Map<String, T> getMulti(Collection keys, String memcachedKeyPrefix) {
        if (StringUtils.isEmpty(keys)) return null;
        if ("".equals(memcachedKeyPrefix)) {
            return this.get(keys);
        }
        List<String> strList = new ArrayList<String>();
        for (Object key : keys) {
            strList.add(memcachedKeyPrefix + String.valueOf(key));
        }
        return this.get(strList);
    }

    /**
     * 重载方法
     *
     * @param keys
     * @param <T>
     * @return
     */
    public <T> Map<String, T> getMulti(Collection keys) {
        return getMulti(keys, "");
    }

    /**
     * 批量获取
     *
     * @param keys keys
     * @return valueMap
     */
    private <T> Map<String, T> get(Collection<String> keys) {
        Map<String, T> map = null;
        try {
            if (keys != null && isOpen && mc != null) {
                map = mc.get(this.cachePre + keys);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return map;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public void setMemcachedClient(MemcachedClient mc) {
        this.mc = mc;
    }

    public void setCachePre(String cachePre) {
        this.cachePre = cachePre;
    }
}