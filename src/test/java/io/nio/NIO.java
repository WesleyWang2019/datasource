package io.nio;


import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

/**
 * @author Wesley Wang
 * @date 2020/6/28 10:43
 */
public class NIO {

    public static void main(String[] args) {
        //chanel 通道，服务端和通信端之间简历的io连接
        //buffer 缓冲区 负责数据的存取，缓冲区本质就是数据
        ByteBuffer buf = ByteBuffer.allocate(16);
        ByteBuffer.allocateDirect(32);

        //position  limit(第一个不应读取或写入的数据索引) capacity
        String str = "12345";
        buf.put(str.getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst,0,2);
        System.out.println("str===========" + new String(dst));
        System.out.println("position======" + buf.position());
        buf.mark();
        buf.get(dst,0,2);
        System.out.println("str===========" + new String(dst));
        //
        buf.reset();//恢复到mark的标记位
        System.out.println("position======" + buf.position());
        buf.get(dst,0,2);
        System.out.println("str===========" + new String(dst));


//
//        buf.get(dst);
//        System.out.println("get======"+new String(dst));
//        System.out.println("capacity====="+buf.capacity());
//        System.out.println("limit=====" + buf.limit());
//        System.out.println("position======" + buf.position());
        //rewind 复位读  将position位置置0
        //clear 清空缓冲区
        //mark标记当前position的位置
        //channel 只能与Buffer进行交互 CPU<->Channel(DMA)<->IO

    }
}
