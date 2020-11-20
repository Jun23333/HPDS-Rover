package com.codemanship.marsrover;

import static java.util.Arrays.asList;

public class XX_ {
    public static void main(String[] args) {
        int sum = asList(1,2,3,4).stream()
                .reduce((a,i)->a+i).get();
        System.out.println(sum);
    }
}
