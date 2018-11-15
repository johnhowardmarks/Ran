/*
 * *****************************************************************************
 * 
 *  by:  John H. Marks copyright 2018, all rights reserved
 *  
 *  Purpose:  
 * 
 *  Version   Date         Description
 * 
 * *****************************************************************************
 */
package ran;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SandBox {

  public static void main(String[] args) {
    MathContext mc;
    BigDecimal bd;

    mc = new MathContext(1, RoundingMode.HALF_EVEN);
    
    bd = new BigDecimal(3.5, mc);
    
    System.out.println(bd);
    
  }
}
