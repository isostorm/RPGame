package rpg.item;

import java.math.BigDecimal;

import be.kuleuven.cs.som.annotate.Value;

//totaal
@Value
public enum WeightUnit {
	KG, G, POUND;
	public double toUnit(WeightUnit other)
	{
		if(other == null); //TODO other == null p.294
		if(conversionRates[this.ordinal()][other.ordinal()] == 0.0)
			conversionRates[this.ordinal()][other.ordinal()] = 1/conversionRates[other.ordinal()][this.ordinal()];
		return conversionRates[this.ordinal()][other.ordinal()];
	}
	private static double[][] conversionRates = new double[3][3];
	static {
		conversionRates[KG.ordinal()][KG.ordinal()] = 1;
		conversionRates[G.ordinal()][G.ordinal()] = 1;
		conversionRates[POUND.ordinal()][POUND.ordinal()] = 1;
		conversionRates[KG.ordinal()][G.ordinal()] = 0.001;
		conversionRates[KG.ordinal()][POUND.ordinal()] =  2.20462262;
		conversionRates[G.ordinal()][POUND.ordinal()] = 0.00220462262;
	}
}
