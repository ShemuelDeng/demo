package com.shemuel.原子引用;

import com.shemuel.bean.User;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by dengshaoxiang on 2019/10/12 16:24
 * description: 原子引用 代码示例
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        User zs = new User("zs",12);
        User li = new User("ls",13);
        userAtomicReference.set(zs);
        System.out.println(userAtomicReference.compareAndSet(zs,li));
        System.out.println(userAtomicReference.get().getName());
    }

}
