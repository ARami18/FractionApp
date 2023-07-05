/**
 * Program Name:  DivisionByZeroException.java
 * Purpose:       Throws an exception if the user tries to divide by zero
 * @author        Saurabh Ashishkumar Darji, Alejandro Ramirez, CPA3
 * @version       1.0.0
 * @since         Aug. 6, 2021      
 */

public class DivisionByZeroException extends Exception
{
    public DivisionByZeroException() {
        super("Invalid denominator. Enter a value > 0");
    }
}
