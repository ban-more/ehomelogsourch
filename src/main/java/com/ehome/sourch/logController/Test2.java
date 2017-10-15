package com.ehome.sourch.logController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wzw on 2017/10/12.
 */
public class Test2 {
    public static void main(String [] args){
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        List<String> list1 = new ArrayList<String>();
        list1.add("ddd");
        list1.add("eee");
        list1.add("fff");
        list.addAll(list1);
//        Iterator it = list.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next());
//        }
        for(int i=0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

    }
}
