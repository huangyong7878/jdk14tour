package com.xfl;

public class JEP361 {

  public void testYield() {
    int i = 4;
    String result = switch (i) {
      case 4:
        yield "end4";
      default:
        yield "end5";
    };
    System.out.println("end method");
  }

  public void testSwitch1() {
    int i = 4;
    String result = switch (i) {
      case 4 -> "end4";
      default -> "end5";
    };
    System.out.println("end method");
  }

  public void testSwitchBlock() {
    int i = 4;
    String result = switch (i) {
      case 4, 5 -> "end45";
      default -> {
        int today = i % 3;
        yield "today" + today;
      }
    };
  }

  public void testSwitchExpression(int i) {
    System.out.println(switch (i) {
      case 4, 5 -> "end45";
      default -> {
        int today = i % 3;
        yield "today" + today;
      }
    });
  }

  public static void main(String[] args) throws Exception {
    JEP361 jep361 = new JEP361();
    jep361.testYield();
  }
}
