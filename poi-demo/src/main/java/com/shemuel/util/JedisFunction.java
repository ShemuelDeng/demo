package com.shemuel.util;

/**
 * Created by dengshaoxiang on 2019/12/6 16:28
 * description:
 */
@FunctionalInterface
public interface JedisFunction<T,E> {
    T callback(E e);
}
