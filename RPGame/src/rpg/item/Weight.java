package rpg.item;

import rpg.item.WeightUnit;

//totaal
/**
 * A class of weights involving a numeral and a weight unit.
 * @invar The numeral of each weight must be a valid numeral.
 *        | isValidNumeral(getNumeral())
 * @invar The unit of each weight must be a valid unit.
 *        | TODO aanvullen
 * @author Frederic
 *
 */
public class Weight implements Comparable<Weight> {
	
	/**
	 * Initialize a new weight with the given numeral and unit
	 * 
	 * @param numeral
	 *        The numeral 
	 * @param unit
	 *        The unit
	 * @post  The numeral of this new weight equals the given numeral
	 * 		  | new.getNumeral() == numeral
	 * @post  The unit of this new weight equals the given weight
	 * 		  | new.getUnit() == unit
	 */
	public Weight(double numeral, WeightUnit unit)
	{
		this.numeral = numeral;
		this.unit = unit;
	}
	private final double numeral;
	private final WeightUnit unit;
	/**
	 * Compare this weight with the other weight taking the weight units into consideration.
	 * @param  other
	 *         The other weight to compare with.
	 * @return The result is equal to the comparison of the numeral of this weight
	 *         with the numeral of the other weight converted to this weight unit.
	 *         | let
	 *         |   otherConverted = other.toUnit(getUnit())
	 *         | in
	 *         |   getNumeral().compareTo(otherConverted.getNumeral())
	 * @return The result is equal to 0 if the other weight is not effective.
	 *         | if ( other == null )
	 *         |     result == 0
	 */
	@Override
	public int compareTo(Weight other) {
		if(other==null)
			return 0;
		Weight otherConverted = other.toUnit(getUnit());
		return new Double(getNumeral()).compareTo(otherConverted.getNumeral());
	}
	
	/**
	 * Check whether the given unit is a valid unit for any weight.
	 * 
	 * @param  weightUnit
	 *         The weightUnit to check.
	 * @return True if and only if the given weightUnit is effective.
	 *         | result == ( weightUnit != null )
	 */
	public static boolean isValidUnit(WeightUnit weightUnit)
	{
		return weightUnit!= null;
	}
	/**
	 * Check whether the given numeral is a valid numeral for any weight.
	 * @param  numeral
	 *         The numeral to check.
	 * @return True if and only if the numeral is greater than or equal to 0.
	 *         | result == ( numeral >= 0 )
	 */
	public static boolean isValidNumeral(double numeral)
	{
		return numeral >= 0;
	}
	/**
	 * Return a weight that has the same value as this weight expressed in the given weight unit.
	 * 
	 * @param  unit
	 *         The unit to convert to.
	 * @return The resulting weight has the given unit as its unit.
	 *         | result.getUnit() == unit
	 * @return The numeral of the resulting weight is equal to the numeral of this weight 
	 *         multiplied with the conversion rate from the unit of this weight to the given unit.
	 *         | result.getNumeral() == this.getUnit().toUnit(unit)*this.getNumeral()
	 */
	public Weight toUnit(WeightUnit unit)
	{
		if(unit == null)
			return null;
		if(this.getUnit() == unit)
			return this;
		double conversionRate = this.getUnit().toUnit(unit);
		return new Weight(getNumeral()*conversionRate, unit);
	}

	/**
	 * Return the numeral of this weight.
	 */
	public double getNumeral() {
		return numeral;
	}
	
	/**
	 * Return the weight unit of this weight.
	 */
	public WeightUnit getUnit() {
		return unit;
	}
	
}
