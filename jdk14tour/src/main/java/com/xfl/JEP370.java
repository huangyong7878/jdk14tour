package com.xfl;

import java.lang.invoke.VarHandle;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemoryHandles;
import jdk.incubator.foreign.MemoryLayout;
import jdk.incubator.foreign.MemoryLayout.PathElement;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.SequenceLayout;

/**
 * foreign-memory access api
 */
public class JEP370 {

  public static void tryMemorySeg() {
    //创建了100个字节，写入25个int
    try (MemorySegment segment = MemorySegment.allocateNative(100)) {
      VarHandle varHandle = MemoryHandles.varHandle(int.class, ByteOrder.BIG_ENDIAN);
      MemoryAddress baseAddr = segment.baseAddress();
      for (int i = 0; i < 25; i++) {
        varHandle.set(baseAddr.addOffset(i * 4), i);
      }
      ByteBuffer byteBuffer = segment.asByteBuffer();
      for (int i = 0; i < 25; i++) {
        System.out.println(byteBuffer.getInt());
      }

    }
  }

  public static void tryVarHandler() {
    try (MemorySegment segment = MemorySegment.allocateNative(100)) {
      VarHandle varHandle = MemoryHandles.varHandle(int.class, ByteOrder.BIG_ENDIAN);
      VarHandle intHandle = MemoryHandles.withStride(varHandle, 4);
      MemoryAddress baseAddr = segment.baseAddress();
      for (int i = 0; i < 25; i++) {
        //第1个i表示步长，第二个是值
        intHandle.set(baseAddr, (long) i, i + 1);
      }
      ByteBuffer byteBuffer = segment.asByteBuffer();
      for (int i = 0; i < 25; i++) {
        System.out.println(byteBuffer.getInt());
      }

    }
  }

  public static void tryLayout() {
    SequenceLayout intArrayLayout = MemoryLayout
        .ofSequence(25, MemoryLayout.ofValueBits(32, ByteOrder.BIG_ENDIAN));
    VarHandle intHandle = intArrayLayout.varHandle(int.class, PathElement.sequenceElement());
    try (MemorySegment segment = MemorySegment.allocateNative(intArrayLayout)) {
      MemoryAddress baseAddr = segment.baseAddress();
      for (int i = 0; i < intArrayLayout.elementCount().getAsLong(); i++) {
        intHandle.set(baseAddr, (long) i, i + 1);
      }
      ByteBuffer byteBuffer = segment.asByteBuffer();
      for (int i = 0; i < 25; i++) {
        System.out.println(byteBuffer.getInt());
      }

    }
  }

  public static void main(String[] args) throws Exception {
    tryMemorySeg();
    tryVarHandler();
    tryLayout();
  }

}
