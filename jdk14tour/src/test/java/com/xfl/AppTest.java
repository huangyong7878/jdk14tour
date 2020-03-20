package com.xfl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void testJEP305Simple(){
         JEP305 jep305 =new JEP305();
        jep305.simple("341");
    }
    @Test
    public void testComplex(){
        JEP305 jep305 = new JEP305();
        jep305.complex("123456");
    }
}
