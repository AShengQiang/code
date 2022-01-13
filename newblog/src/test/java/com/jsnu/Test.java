package com.jsnu;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        List<String> list=new LinkedList<>();
        Map<String,Integer> map=new HashMap<>();
        Set<String> set=new HashSet<>();


        map.put("sheng",15);
        map.put("tang",16);

        map.remove("sheng");

        System.out.println(map);
    }
}
