/*
 * *****************************************************************************
 * 
 *  by:  John H. Marks copyright 2018, all rights reserved
 *  
 *  Purpose:  A latitude or longitude DMS value
 * 
 *  Version   Date         Description
 *  1.0.0     2018/10/23   Initial Development
 * *****************************************************************************
 */
package ran;

import java.math.MathContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Ordinate {
  public final static boolean LATITUDE = Degree.LATITUDE;
  public final static boolean LONGITUDE = Degree.LONGITUDE;

  private Spin spin;
  private Degree degree;
  private MinuteSecond minute;
  private MinuteSecond second;
  private final BigInteger bi60 = new BigInteger("60");
  private final MathContext mc;
  private final BigDecimal bd60 = new BigDecimal(bi60, 0);
  
  //Constructor
  Ordinate (boolean latitudeOrLongitude) throws SimpleException {
    spin = new Spin();
    if (latitudeOrLongitude == LATITUDE) {
      degree = new Degree(LATITUDE);
      mc = new MathContext(8, RoundingMode.HALF_EVEN); 
    } else {
      degree = new Degree(LONGITUDE);
      mc = new MathContext(9, RoundingMode.HALF_EVEN); 
    }
    minute = new MinuteSecond();
    second = new MinuteSecond();
  }
  
  public BigDecimal setValue(int _direction, int _degree, int _minute, int _second ) 
      throws SimpleException {
    BigDecimal bd;

    try {
      bd = new BigDecimal(spin.setDirection(_direction), mc);
    } catch (SimpleException ex) {
      throw new SimpleException(ex.getMessage());
    }
    
    try {
      bd = bd.multiply(new BigDecimal(degree.setValue(_degree), mc), mc);
    } catch (SimpleException ex) {
      throw new SimpleException(ex.getMessage());
    }
    
    try {
      bd = bd.add(new BigDecimal(minute.setValue(_minute), mc).divide(bd60, mc), 
          mc);
    } catch (SimpleException ex) {
      throw new SimpleException(ex.getMessage());
    }
    
    try {
      bd = bd.add(new BigDecimal(minute.setValue(_second), mc).divide(bd60, mc)
          .divide(bd60, mc), mc);
    } catch (SimpleException ex) {
      throw new SimpleException(ex.getMessage());
    }
    
    return bd;
  }
  
}
