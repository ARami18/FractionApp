/**
 * Program Name:  LongOperandException.java
 * Purpose:       Throws an exception if an operand exceeds the max value for the int data type
 * @author        Saurabh Ashishkumar Darji, Alejandro Ramirez, CPA3
 * @version       1.0.0
 * @since         Aug. 4, 2021      
 */

public class LongOperandException extends Exception
{
    private int value;
    
    public LongOperandException(int value) {
        super("Value exceeds an Integer max value: 2147483647\nEnter a valid " + ((value == 1) ? "numerator" : " denominator"));
        this.value = value;
    }
    
    /**
	 * Returns the value to identify an operand (1 = num / 0 = den)
	 * @return int
	 */
    public int getValue() {
        return this.value;
    }
}
