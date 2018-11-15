/*
 * *****************************************************************************
 * 
 *  by:  John H. Marks copyright 2018, all rights reserved
 *  
 *  Purpose:  A single numeric character with minimum and maximum values and a
 *            power factor to provide a positive value in ones, tens or hundreds
 * 
 *  Version   Date         Description
 *  1.0.0     2018-10-16   Initial development
 * *****************************************************************************
 */
package ran;

import java.util.Locale;
import java.util.ResourceBundle;

public class Digit {

  public static final String ONES = "Ones";
  public static final String TENS = "Tens";
  public static final String HUNDREDS = "Hundreds";

  private int power;
  private final int MINIMUM;
  private final int MAXIMUM;
  private int value;
  private static java.util.Locale locale;
  private static java.util.ResourceBundle messages;

  //Constructor
  Digit(int minimum, int maximum, String IsOnesTensHundreds)
      throws SimpleException {
    int minimumMinimum = -9;
    int maximumMaximum = 9;
    locale = Locale.getDefault();
    messages = ResourceBundle.getBundle("messagesDigit", locale);
    String message;

    if (minimum < minimumMinimum) {
      message = String.format(messages.getString("belowMinimum"), minimum,
          minimumMinimum);
      throw new SimpleException(message);
    } else {
      this.MINIMUM = minimum;
    }

    if (maximum > maximumMaximum) {
      message = String.format(messages.getString("aboveMaximum"), maximum,
          maximumMaximum);
      throw new SimpleException(message);
    } else {
      this.MAXIMUM = maximum;
    }

    if (minimum >= maximum) {
      message = String.format(messages.getString("minimumGEMaximum"), minimum,
          maximum);
      throw new SimpleException(message);
    }

    switch (IsOnesTensHundreds) {
      case "Ones":
        this.power = 0;
        break;
      case "Tens":
        this.power = 1;
        break;
      case "Hundreds":
        this.power = 2;
        break;
      default:
        //Throw exception
        message = String.format(messages.getString("invalidPower"),
            IsOnesTensHundreds);
        throw new SimpleException(message);
    }
  }

  //value Getter
  public int getValue() {
    return this.value*(int)Math.pow(10, this.power); 
  }

  //value Setter
  public int setValue(int value) throws SimpleException {
    String stringValue;
    int index = 0;
    char charValue;

    //Validate the value is within range
    if (value < 0) {
      throw new SimpleException(String.format(
          messages.getString("noNegatives"), value));
    }
    if (value > 999) {
      throw new SimpleException(String.format(
          messages.getString("tooManyDigits"), value));
    }

    //Convert the value to a 3 characther leading 0 padded string
    stringValue = String.format("%03d", value);

    //Convert the power attribute to the index position of the digit
    switch (this.power) {
      case 0:
        index = 2;
        break;
      case 1:
        index = 1;
        break;
      case 2:
        index = 0;
    }

    //Extract the character from the padded string at the index
    try {
      charValue = stringValue.charAt(index);
    } catch (IndexOutOfBoundsException ex) {
      charValue = 0;
    }
    value = Character.getNumericValue(charValue);

    //Validate the value
    if (value < this.MINIMUM) {
      throw new SimpleException(String.format(
          messages.getString("belowMinimum"), value, MINIMUM));
    }
    if (value > this.MAXIMUM) {
      throw new SimpleException(String.format(
          messages.getString("aboveMaximum"), value, MAXIMUM));
    }

    //Set and return the value
    this.value = value;
    return this.value * (int)Math.pow(10, this.power);

  }

}
