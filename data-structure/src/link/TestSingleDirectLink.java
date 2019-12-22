package link;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSingleDirectLink {
    public static void main(String[] args) {
        List<User> l2 = new ArrayList<>();
//        l2.add(new User("1",1));
//        l2.add(new User("2",2));
//        l2.add(new User("3",3));
//        l2.add(new User("4",4));
//        l2.add(new User("5",5));
////        l2.add(5,new User("9",9));
//        Iterator<User> it = l2.iterator();
//        it.next();
//        it.next();
//        it.next();
//        it.next();
//        it.next();
//
//
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }

        List<Integer> list = new LinkedList();


        SingleDirectLink<Integer> l = new SingleDirectLink();
        for (int i = 0 ;i <300000; i++){
            l.add(i);
        }

        Integer integer = l.get(29999);
        System.out.println(integer);

    }

}
 class User {
    public String name;
    public int age;
    public User(String name,int age){
        this.age=age;
        this.name = name;
    }

     @Override
     public String toString() {
         return "User{" +
                 "name='" + name + '\'' +
                 ", age=" + age +
                 '}';
     }
 }