package ran;

/*
 * *****************************************************************************
 * 
 *  by:  John H. Marks copyright 2018, all rights reserved
 *  
 *  Purpose:  Provides a simple exception class for the Digit class
 * 
 *  Version   Date        Description
 *  1.0.0     2018/10/14  Initial development
 * *****************************************************************************
 */


public class SimpleException extends Exception {
  
  SimpleException(String message) {
    //Constructor
    super(message);
  }
  
}
