/**
 * 
 */
package mgn;

public enum Interval {

	TONIC(0, 0),

	MINOR_THIRD(2, 3),

	MAJOR_THIRD(2, 4),

	FIFTH(4, 7),

	MINOR_SEVENTH(6, 10),

	SEVENTH(6, 11),

	OCTAVE(7, 12);

	private Interval(int degree, int distance) {
		this.distance = distance;
		this.degree = degree;
	}

	int distance;

	int degree;

	public int getDistance() {
		return distance;
	}

	public int getDegree() {
		return degree;
	}

}