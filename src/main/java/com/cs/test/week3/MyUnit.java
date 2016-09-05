package com.cs.test.week3;

import java.util.Arrays;

public class MyUnit {
	private short ashort;
	private char achar;
	private byte abyte;
	private boolean abool;
	private long along;
	private float afloat;
	private double adouble;
	private Unit aObject;
	private int[] ints;
	private Unit[] units;

	public boolean equals(Object o) {
		if (!(o instanceof MyUnit))
			return false;
		MyUnit unit = (MyUnit) o;
		return unit.ashort == ashort && unit.achar == achar 
				&& unit.abyte == abyte && unit.abool == abool
				&& unit.along == along 
				&& Float.floatToIntBits(unit.afloat) == Float.floatToIntBits(afloat)
				&& Double.doubleToLongBits(unit.adouble) == Double.doubleToLongBits(adouble)
				&& unit.aObject.equals(aObject) 
				&& equalsInts(unit.ints) 
				&& equalsUnits(unit.units);
	}

	private boolean equalsInts(int[] aints) {
		return Arrays.equals(ints, aints);
	}

	private boolean equalsUnits(Unit[] aUnits) {
		return Arrays.equals(units, aUnits);
	}

	public int hashCode() {
		int result = 17;
		result = 31 * result + (int) ashort;
		result = 31 * result + (int) achar;
		result = 31 * result + (int) abyte;
		result = 31 * result + (abool ? 0 : 1);
		result = 31 * result + (int) (along ^ (along >>> 32));
		result = 31 * result + Float.floatToIntBits(afloat);
		long tolong = Double.doubleToLongBits(adouble);
		result = 31 * result + (int) (tolong ^ (tolong >>> 32));
		result = 31 * result + aObject.hashCode();
		result = 31 * result + intsHashCode(ints);
		result = 31 * result + unitsHashCode(units);
		return result;
	}

	private int intsHashCode(int[] aints) {
		int result = 17;
		for (int i = 0; i < aints.length; i++)
			result = 31 * result + aints[i];
		return result;
	}

	private int unitsHashCode(Unit[] aUnits) {
		int result = 17;
		for (int i = 0; i < aUnits.length; i++)
			result = 31 * result + aUnits[i].hashCode();
		return result;
	}
}
