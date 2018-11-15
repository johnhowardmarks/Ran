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

public class Degree {

  public static final boolean LATITUDE = true;
  public static final boolean LONGITUDE = false;
  private Digit hundreds;
  private Digit tens;
  private Digit ones;
  private ResourceBundle messages;
  private final int MINIMUM_VALUE;
  private final int MAXIMUM_VALUE;

  //Constructor
  Degree(boolean latitudeOrLongitude) throws SimpleException {
    Locale locale = Locale.getDefault();
    messages = ResourceBundle.getBundle("messagesDegree", locale);

    if (latitudeOrLongitude == true) {
      try {
        tens = new Digit(0, 9, Digit.TENS);
        ones = new Digit(0, 9, Digit.ONES);
      } catch (SimpleException ex) {
        throw new SimpleException(ex.getMessage());
      }
      MINIMUM_VALUE = 0;
      MAXIMUM_VALUE = 90;
    } else {
      try {
        hundreds = new Digit(0, 1, Digit.HUNDREDS);
        tens = new Digit(0, 9, Digit.TENS);
        ones = new Digit(0, 9, Digit.ONES);
      } catch (SimpleException ex) {
        throw new SimpleException(ex.getMessage());
      }
      MINIMUM_VALUE = 0;
      MAXIMUM_VALUE = 180;
    }
  }

  //get the Value returned as an integer
  public int getValue() {
    if (hundreds != null) {
      return hundreds.getValue() + tens.getValue() + ones.getValue();
    } else {
      return tens.getValue() + ones.getValue();
    }
  }

  //get the Value returned as a String
  public String getValueString() {
    int value;

    value = getValue();
    if (MAXIMUM_VALUE == 90) {
      return String.format("%02d", value);
    } else {
      return String.format("%03d", value);
    }
  }

  //set the Value of the individual Digits
  public int setValue(int value) throws SimpleException {
    int _hundreds = 0;
    int _tens;
    int _ones;

    if (MAXIMUM_VALUE > 90) {
      try {
        _hundreds = hundreds.setValue(value);
      } catch (SimpleException ex) {
        throw new SimpleException(String.format(messages.getString("hundredsError"),
            ex.getMessage()));
      }
    }
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

    if (_hundreds > 0) {
      return _hundreds + _tens + _ones;
    } else {
      return _tens + _ones;
    }
  }

  //Clear the object
  private void clear() {
    ones = null;
    tens = null;
    if (MAXIMUM_VALUE > 90) {
      hundreds = null;
    }
  }

}
