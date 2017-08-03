package com.example.zissu.log_reg_daniel;

/**
 * Created by zissu on 26/07/2017.
 */

public class KeyValue<T> {
    public String key;
    public T value;

    public KeyValue(String key, T value) {
        this.key = key;
        this.value = value;
    }
}
