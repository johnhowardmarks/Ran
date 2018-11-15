/*
 * *****************************************************************************
 * 
 *  by:  John H. Marks copyright 2018, all rights reserved
 *  
 *  Purpose:  A two digit object based on Digits for representing a minute or
 *            second in a time or DMS object with a valid range from 0 to 59.
 * 
 *  Version   Date         Description
 *  1.0.0     2018-10-18   Initial development
 * *****************************************************************************
 */
package ran;

import java.util.Locale;
import java.util.ResourceBundle;

public class MinuteSecond {

  private Digit tens;
  private Digit ones;
  private ResourceBundle messages;
  private final int MINIMUM_VALUE;
  private final int MAXIMUM_VALUE;

  //Constructor
  MinuteSecond() throws SimpleException {
    Locale locale = Locale.getDefault();
    messages = ResourceBundle.getBundle("messagesMinuteSecond", locale);
    try {
      tens = new Digit(0, 5, Digit.TENS);
      ones = new Digit(0, 9, Digit.ONES);
    } catch (SimpleException ex) {
      throw new SimpleException(ex.getMessage());
    }
    MINIMUM_VALUE = 0;
    MAXIMUM_VALUE = 59;
  }

  //get the Value returned as an integer
  public int getValue() {
    return tens.getValue() + ones.getValue();
  }

  //get the Value returned as a String
  public String getValueString() {
    int value;

    value = getValue();
    return String.format("%02d", value);
  }

  //set the Value of the individual Digits
  public int setValue(int value) throws SimpleException {
    int _tens;
    int _ones;

    try {
      _tens = tens.setValue(value);
    } catch (SimpleException ex) {
      throw new SimpleException(String.format(messages.getString("tensError"),
          ex.getMessage()));
    }
    try {
      _ones = ones.setValue(value);
    } catch (SimpleException ex) {
      throw new SimpleException(String.format(messages.getString("onesError"),
          ex.getMessage()));
    }

    //Validate the value
    if (value < MINIMUM_VALUE) {
      clear();
      throw new SimpleException(String.format(messages.getString("belowMinimum"),
          value, MINIMUM_VALUE));
    }
    if (value > MAXIMUM_VALUE) {
      clear();
      throw new SimpleException(String.format(messages.getString("aboveMaximum"),
          value, MAXIMUM_VALUE));
    }

    return _tens + _ones;
  }

  //Clear the object
  private void clear() {
    ones = null;
    tens = null;
  }
  
}
