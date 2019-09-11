package com.kyle.im.common.util;

/**
 * 简单的BitMap
 * 参照 sun.jvm.hotspot.utilities.BitMap
 * hotspot 的 BitMap 是用 int[] 实现的，改写为 byte[] 实现
 * @author yangkaile
 * @date 2019-09-11 14:59:46
 */
public class BitMap {
    /**
     * BitMap可容纳的位数
     */
    private int size;
    private byte[] data;

    /**
     * 每个"词"的大小为 8 ，即 一个byte的位数
     */
    private static final int WORD_SIZE = 1 << 3;

    public BitMap(int size){
        this.size = size;
        int nofWords = sizeInWords();
        this.data = new byte[nofWords];
    }
    public BitMap(byte[] data){
        this.data = data;
        this.size = size();
    }

    public int size(){
        return data.length * WORD_SIZE;
    }

    /**
     * 取BitMap某一位的值，如果是1 返回 true;否则返回 false
     * @param offset
     * @return
     */
    public boolean at(int offset){
        if(offset < 0 || offset > size){
            return false;
        }
        return hasBits(wordFor(offset), posFor(offset));
    }

    public void atPut(int offset,boolean value){
        int index = indexFor(offset);
        int pos = posFor(offset);
        if(value){

            data[index] = (byte) setBits(data[index],pos);
        }else {
            data[index] = (byte) clearBits(data[index],pos);
        }
    }

    /**
     * 判断一个int值的某位是否是1
     * @param x
     * @param pos
     * @return
     */
    public static boolean hasBits(int x,int pos){
        return (x & (1 << pos)) != 0;
    }
    
    /**
     * 将一个int值的某位设置为1
     * @param x
     * @param pos
     * @return
     */
    public static int setBits(int x,int pos){
        return x | (1 << pos);
    }

    /**
     * 将一个int值的某位设置为0
     * @param x
     * @param pos
     * @return
     */
    public static int clearBits(int x,int pos){
        return x & ~pos;
    }

    /**
     * 根据要创建的BitMap大小计算出需要的byte数组大小
     * @return
     */
    private int sizeInWords(){
        return (this.size + WORD_SIZE - 1) / WORD_SIZE;
    }

    /**
     * 计算要查找的值在byte数组中的位置
     * @param offset
     * @return
     */
    private int indexFor(int offset){
        return offset / WORD_SIZE;
    }
    /**
     * 取出包含要查找的位置的byte值
     * @param offset
     * @return
     */
    private byte wordFor(int offset){
        return data[indexFor(offset)];
    }

    /**
     * 计算要查找的位在byte中是哪一位
     * @param offset
     * @return
     */
    private int posFor(int offset){
        return offset % WORD_SIZE;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for(int i = size() - 1 ; i >= 0 ; i --){
            if(at(i)){
                builder.append(1);
            }else {
                builder.append(0);
            }
            if(++ index % WORD_SIZE == 0){
                builder.append(",");
            }
        }
        return builder.toString();
    }

}
