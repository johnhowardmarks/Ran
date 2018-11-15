/*
 * *****************************************************************************
 * 
 *  by:  John H. Marks copyright 2018, all rights reserved
 *  
 *  Purpose:  Establish the direction from the prime or equitorial position  
 * 
 *  Version   Date         Description
 *  1.0.0     2018/10/23   Initial Development
 * *****************************************************************************
 */
package ran;

import java.util.Locale;
import java.util.ResourceBundle;


public class Spin {
  private int Direction;
  private ResourceBundle messages;
  public final static int NORTH = 1;
  public final static int EQUATOR = 0;
  public final static int SOUTH = -1;
  public final static int EAST = 1;
  public final static int PRIME = 0;
  public final static int WEST = -1;

  //Constructor
  Spin() {
    Locale locale = Locale.getDefault();
    messages = ResourceBundle.getBundle("messagesSpin", locale);
    Direction = 0;
  }
  
  //Direction getter
  public int getDirection (){
    return Direction;
  }
  
  //Direction setter
  public int setDirection(int direction) throws SimpleException {
    if (direction < -1 || direction > 1) {
      throw new SimpleException(String.format(messages.getString("invalid"), 
          direction));
    }
    Direction = direction;
    return Direction;
  }
  
}
