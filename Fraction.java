/**
 * Program Name:  Fraction.java
 * Purpose:		  A class to store and use data about Fraction objects
 * @author        Saurabh Ashishkumar Darji, Alejandro Ramirez, CPA3
 * @version       1.0.0
 * @since         Jul. 24, 2021      
 */
import java.util.Comparator;
import java.util.stream.Stream;

public class Fraction implements Comparable<Fraction> {
	// 1. Variable declaration
	private int num;
	private int den;

	// 2. Create Constructors
	/**
	 * Constructs a new Fraction object, initializing its attributes to 1
	 */
	public Fraction() {
		this.num = 1;
		this.den = 1;
	}
	
	/**
	 * Constructs a new Fraction object 
	 * @param num numerator of the Fraction
	 * @param den denominator of the Fraction
	 */
	public Fraction(int num, int den) throws DivisionByZeroException, LongOperandException {
		if (num > Integer.MAX_VALUE) {
            throw new LongOperandException(1);
        }
        if (den > Integer.MAX_VALUE) {
            throw new LongOperandException(0);
        }
        if (den == 0) {
            throw new DivisionByZeroException();
        }

		this.num = num;
		this.den = den;
	}

	// 3. Create getters and setters
	/**
	 * Returns the value of numerator
	 * @return num
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * Returns the value of denominator
	 * @return den
	 */
	public int getDen() {
		return den;
	}
	
	/**
	 * sets the numerator
	 * @param num numerator
	 */
	public void setNum(int num) throws LongOperandException{
		if (num > Integer.MAX_VALUE) {
            throw new LongOperandException(1);
        }
		this.num = num;
	}
	
	/**
	 * sets the denominator
	 * @param den denominator
	 */
	public void setDen(int den) throws DivisionByZeroException, LongOperandException{
		if (den > Integer.MAX_VALUE) {
            throw new LongOperandException(1);
        }
		if (den <= 0) {
            throw new DivisionByZeroException();
        }
		this.den = den;
	}

	// 4. Create Utitlity methods
	/**
	 * Returns the value of fraction in decimal form(double)
	 * @return decimalValue
	 */
	public double toDecimal() {
		double decimalValue = 0;
		decimalValue = num / (double) den;
		return decimalValue;
	}
	
	/**
	 * Changes the fraction to its reciprocal form
	 * @return Fraction
	 * @throws DivisionByZeroException
	 * @throws LongOperandException
	 */
	public Fraction toReciprocal() throws DivisionByZeroException, LongOperandException {
        if (this.den <= 0) {
            throw new DivisionByZeroException();
        }
        return new Fraction(this.den, this.num);
    }

	/**
	 * Returns the sum of two Fraction objects
	 * @param f Fraction object
	 * @return Fraction
	 * @throws DivisionByZeroException
	 * @throws LongOperandException
	 */
	public Fraction add(Fraction f) throws DivisionByZeroException, LongOperandException {
		Fraction addedFr = new Fraction();
		addedFr.setNum((num * f.getDen() + den * f.getNum()));
		addedFr.setDen(den * f.getDen());
		addedFr.lowestTerms();
		return addedFr;
	}

	/**
	 * Returns the product of two Fraction objects
	 * @param f Fraction object
	 * @return Fraction
	 */
	public Fraction multiply(Fraction f) throws DivisionByZeroException, LongOperandException {
	  Fraction multFr = new Fraction();
	  multFr.setNum(num * f.getNum());
	  multFr.setDen(den * f.getDen());
	  multFr.lowestTerms();
	  return multFr;
	}
	
	/**
	  * Returns a boolean value (true or false) based on comparing the two fractions
	  * @param f Fraction object
	  * @return boolean
	  */
	public boolean equals(Fraction f) {
	  boolean areEqual = false;

	  if (this.toDecimal() == f.toDecimal()) {
		  areEqual = true;
	  }
	  else {
		  areEqual = false;
	  }
	  return areEqual;
	}
	
	/**
	  * Returns boolean value after checking if a fraction is greater than other
	  * @param f Fraction object
	  * @return boolean
	  */
	public boolean greaterThan(Fraction f) {
	  boolean isGreater = false;
	  
	  if (this.toDecimal() > f.toDecimal()) {
		isGreater = true;
	  }
	  else {
		isGreater = false;
	  }
	  return isGreater;
	}
	
	 /**
     * Returns the value of greatest common divisor
     * @param den1
     * @param den2
     * @return G.C.D
     */
	private int gcd(int den1, int den2) {
	  if (den2 == 0) {
		  return den1;
	  }
	  else {
		  return gcd(den2, den1 % den2);
	  }
	}

	/**
	 * Converts the Fraction to its simplest terms
	 * @return
	 * @throws DivisionByZeroException
	 * @throws LongOperandException
	 */
	public Fraction lowestTerms() throws DivisionByZeroException, LongOperandException {
		int gcdValue = 0;
		if (this.num > this.den) {
            gcdValue = this.gcd(this.num, this.den);
        }
        else if (this.num < this.den) {
            gcdValue = this.gcd(this.den, this.num);
        }
        else {
            gcdValue = this.num;
        }
		this.num /= gcdValue;
		this.den /= gcdValue;
	  	
		return this;
	}
 
	/**
	  * Returns an int value(1, 0, or -1) based on comparing two fractions
	  * @param f Fraction object
	  * @return boolean
	  */
	@Override
	public int compareTo(Fraction f) {
		int compareVal = 0;
		if (this.greaterThan(f))
		{
			compareVal = 1;
		}
		else if (this.equals(f))
		{
			compareVal = 0;
		} 
		else
		{
			compareVal = -1;
		}
		return compareVal;
	}
	
	/**
	  * Returns a String statement with the value of fraction
	  * @return String
	  */
	@Override
    public String toString() {
    	return this.num + "/" + this.den;
    }
	 
}
