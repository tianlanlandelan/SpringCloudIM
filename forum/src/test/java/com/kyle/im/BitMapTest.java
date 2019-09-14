package com.kyle.im;

import com.kyle.im.common.util.BitMap;
import com.kyle.im.common.util.Console;
import org.junit.Test;

import java.util.Arrays;


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

    @Test
    public void tryResize(){
        byte[] array = new byte[]{100};
        BitMap bitMap = new BitMap(array);
        Console.print("扩容前",bitMap,bitMap.size(),bitMap.at(2));
        if(bitMap.tryResize(17)){
            Console.print("扩容后",bitMap,bitMap.size(),bitMap.at(2));
        }
    }
}
