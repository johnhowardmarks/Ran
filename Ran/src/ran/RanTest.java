package ran;

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
import java.util.*;

public class RanTest {
  public static void main(String[] args) throws SimpleException {
    Ordinate latitude;
    
    latitude = new Ordinate(Ordinate.LONGITUDE);
    
    System.out.println(latitude.setValue(Spin.NORTH, 102, 22, 45));
    
  }
}
