package com.kyle.im;

import com.kyle.im.common.util.BitMap;
import com.kyle.im.common.util.Console;


public class BitMapTest {
    public static void main(String[] args){
        BitMap bitMap = new BitMap(30);
        Console.print("",bitMap.at(10));
        bitMap.atPut(10,true);
        Console.print("",bitMap.at(10),
                bitMap.at(1));
        Console.print("",bitMap.toString());

        bitMap = new BitMap(new byte[]{1,1});
        Console.print("",bitMap.at(8));
        Console.print("",bitMap.size());
        Console.print("",bitMap.toString());
        int a = 100;
        Console.print("",BitMap.setBits(a,30));
    }
}
