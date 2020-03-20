package com.xfl;

public class JEP359 {

  public record Point(
      int x, int y) {

  }

  public static void main(String[] args)throws Exception{
    Point p = new Point(2,3);
    System.out.println(p.toString());
    int point = p.x()+p.y();
  }
}
