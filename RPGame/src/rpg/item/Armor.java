/**
 * 
 */
package rpg.item;

import rpg.creature.Creature;
import src.rpg.creature.Raw;

/**
 * A class of armors
 * Armors are a special type of itemimplementation, involving aditionally 
 * a maximum value and a protection
 * 
 * @author Mathias, Frederic
 * 
 * @invar Each armor has a valid maximum value
 * 	      | hasValidMaxValue()
 * @invar Each armor has a valid maximum protection
 * 	      | hasValidMaxProtection()
 * @invar Each armor must have a valid protection
 * 		  | hasValidProtection()
 *
 */
public class Armor extends ItemImplementation{
	/**
	 * 
	 * @param id
	 * @param weight
	 * @param holder
	 * @param maxValue
	 * @param maxProtection
	 * @post  If this armor can't have the given id as its id, the id equals 1
	 * 		  otherwise it equals the given id
	 * 		  | if(!canHaveAsId(id) then
	 * 		  |	    getId() == 1
	 * 		  | else then
	 * 		  | 	getId() == id
	 */
	public Armor(long id, Weight weight, Parent holder,
			int maxValue, int maxProtection) {
		super(id, weight, holder);

		this.maxValue = maxValue;
		this.maxProtection = maxProtection;
		
		if(!canHaveAsId(id))
			this.id = 1;
	}
	
	/**
	 * Checks whether this armor can have the given id as its id
	 * given number is a prime number.
	 * 
	 * @param  id
	 *         The id to check.
	 * @return False if and only if there is a divisor of the number between 2 and the number minus one.
	 *         | result == ( for each integer in 2..(number-1):
	 *         |				number%integer == 0 )
	 */
	@Override
	private static boolean canHaveAsId(long id)
	{
		for(int i = 2; i < id; i++)
			if(id%i == 0)
				return false;
		return true;
	}

	private final int maxValue;
	
	/**
	 * Return the maximum value of this armor
	 */
	public int getMaxValue() {
		return maxValue;
	}
	
	/**
	 * Check whether the given maximum value is valid
	 * 
	 * @param  value
	 * 		   The value to check
	 * @return True if and only if the given maximum value is positive
	 * 		   and less than or equal to 1000
	 * 		   | result == value>=0 && value<=1000
	 */
	public static boolean isValidMaxValue(int value){
		return value>=0 && value<=1000;
	}
	 
	/**
	  * Checks whether the maximum value of this armor is valid.
	  * 
	  * @return True if and only if the maximum value of this armor 
	  * 		is a valid maximum value.
	  *         | result == isValidMaxValue(getMaxValue())
	  */
	 public boolean hasValidMaxValue()
	 {
		return isValidMaxValue(getMaxValue()); 
	 }
	 
	 /**
	  * Return the value of this armor
	  * 
	  * @return The result is the product of the maximum value 
	  * 		of this armor and the quotient of the maximum protection factor
	  * 		and the actual protection factor
	  * 		| result == getMaxValue() * (getProtection()/getMaxProtection())
	  */
	 @Override
	 public int getValue(){
		return getMaxValue() * (getProtection()/getMaxProtection());
		 
	 }
	 
	 private final int maxProtection;
	 
	 /**
	  * Return the maximum protection factor of this armor
	  */
	 public int getMaxProtection() {
			return maxProtection;
	 }
	 
	 /**
		 * Check whether the given maximum protection is valid
		 * 
		 * @param  maxProtection
		 * 		   The maximum protection to check
		 * @return True if and only if the given maximum protection is 
		 * 		   greater than or equal to 1 and less than or equal to 100
		 * 		   | result == value>=1 && value<=100
		 */
		public static boolean isValidMaxProtection(int maxProtection){
			return maxProtection>=1 && maxProtection<=100;
		}
		 
		/**
		  * Checks whether the maximum protection of this armor is valid.
		  * 
		  * @return True if and only if the maximum protection of this armor 
		  * 		is a valid maximum protection.
		  *         | result == isValidMaxProtection(getMaxProtection())
		  */
		 public boolean hasValidMaxProtection()
		 {
			return isValidMaxProtection(getMaxProtection()); 
		 }
		 
		 private int protection;
		 
		 /**
		 * Return the protection factor of this armor
		 */
		public int getProtection() {
			return protection;
		}

		/**
		 * Set the protection factor of this armor to the given protection
		 * 
		 * @param protection 
		 * 		  The protection to set
		 * @post  The protection factor of this armor equals the given protection
		 * 		  | new.getProtection() == protection
		 */
		public void setProtection(int protection) {
			this.protection = protection;
		}
		
		/**
		 * Check whether this armor can have the given protection as its protection
		 * 
		 * @param  protection
		 * 		   The protection to check
		 * @return True if and only if the given protection is positive
		 * 		   and less than or equal to the maximum protection
		 * 		   | result == ( ( protection >= 0) && (protection <= getMaxProtection() ) )
		 */
		public boolean canHaveAsProtection(int protection){
			return protection >= 0 && protection <= getMaxProtection();
		}
		
		/**
		 * Check whether this armor can has a valid protection
		 * 
		 * @return True if and only if this armor can have its protection as its protection
		 * 		   | result == canHaveAsProtection(getProtection())
		 */
		public boolean hasValidProtection(){
			return canHaveAsProtection(getProtection());
		}
		
		
		
		 
}
