package com.xfl;

public class JEP305 {

  private String s;

  public void simple(Object obj) {
    if (obj instanceof String s) {
      System.out.println(s);
    }
  }

  public void complex(Object obj) {
    if (obj instanceof String s && s.length() > 5) {
      System.out.println(s);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof JEP305 jep && jep.s.equals(s)) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    JEP305 jep305 = new JEP305();
    jep305.simple("341");
    jep305.complex("123456");
  }
}
