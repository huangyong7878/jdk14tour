package com.xfl;

public class JEP358 {

  public void throwSimpleNPE(){
    String a = null;
    a.getBytes();
  }

  public void throwComplex(){
    String[] a = new String[2];
    a[0] = "s1";
    a[1].getBytes();
  }
  //-XX:+ShowCodeDetailsInExceptionMessages
  public static void main(String[] args)throws Exception{
    JEP358 jep358 = new JEP358();
//    jep358.throwSimpleNPE();
    jep358.throwComplex();
  }


}
