package google;

public class NormalizedGoogleDistance {

	private long hitsFirtsWord, hitsSecondWord, hitsBothWords, indexedDocuments;
	private double gnd;

	public NormalizedGoogleDistance( long hitsFirtsWord, long hitsSecondWord ) {
		this.hitsFirtsWord = hitsFirtsWord;
		this.hitsSecondWord = hitsSecondWord;
	}

	public NormalizedGoogleDistance( long hitsFirtsWord, long hitsSecondWord,
			long hitsBothWords ) {
		this( hitsFirtsWord, hitsSecondWord );
		this.hitsBothWords = hitsBothWords;
	}

	/**
	 * Gets the value of the GND property. Notice that must be get after executing the algorithm.
	 * 
	 * @return
	 */
	public double gnd() {
		return this.gnd;
	}

	/**
	 * Executes the Google Normalized Distance.
	 * 
	 * @return this object just to vectorize methods.
	 */
	public NormalizedGoogleDistance execute() {

		// Calculating minimum and maximum value over the logs.
		MinMaxObject minMax = new MinMaxObject( Math.log( hitsFirtsWord ),
				Math.log( hitsSecondWord ) );

		// Calculating the Google Normalized Distance Directly.
		gnd = ( minMax.max() - Math.log( hitsBothWords ) )
				/ ( Math.log( indexedDocuments ) - minMax.min() );

		return this;
	}

}
