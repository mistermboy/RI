/**
 * 
 */
package google;

/**
 * BidimensionalDouble.java
 *
 * @author willy
 * @version
 * @since 2017"5"482017
 * @formatter Oviedo Computing Community
 */
class MinMaxObject {

	private double min, max;

	public MinMaxObject( double x, double y ) {
		compute( x, y );
	}

	private void compute( double x, double y ) {
		if (x < y) {
			this.min = x;
			this.max = y;
		} else {
			this.min = y;
			this.max = x;
		}
	}

	public double min() {
		return this.min;
	}

	public double max() {
		return this.max;
	}
}
