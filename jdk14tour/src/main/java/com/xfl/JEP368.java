package com.xfl;

public class JEP368 {

  public static void twoDMString() {
    String helloword = """
             <html>
                <body>
                  <p>Hello, world</p>
                </body>
              </html>
        """;
    String str = """
        Add text blocks to the Java language.
         A text block is a multi-line string literal that avoids the need f
         or most escape sequences, automatically formats the string in a predictable way, and gives the developer control over the format when desired. This is a preview language feature in JDK 14.
        """;
    String str1 = """
        ""
        """;
    String str_escape = """
        we are \""" here
        """;
    ///s is a single space
    String str_s = """
        red \s
        green\s
        blud \s
        """;

    System.out.println(str);
    System.out.println(str_escape);
    System.out.println(str_s);
  }

  public static void main(String[] args) throws Exception {
    twoDMString();
  }
}
