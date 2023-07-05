/**
 * Program Name:  EmptyOperandException.java
 * Purpose:       Throws an exception if an a text field is not filled by user
 * @author        Saurabh Ashishkumar Darji, Alejandro Ramirez, CPA3
 * @version       1.0.0
 * @since         Aug. 4, 2021      
 */

public class EmptyOperandException extends Exception
{
    private int value;
    
    public EmptyOperandException(int value) {
        super("Empty input field. Enter a " + ((value == 1) ? "numerator" : " denominator"));
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

