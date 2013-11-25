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

	Interval(int degree, int distance) {
		this.distance = distance;
		this.degree = degree;
	}

	int distance;

	int degree;

	public int getDistance() {
		return distance;
	}

	public Note getAscending(Note tonic) {

		ReferenceNote next = tonic.getReferenceNote();

		int cycle = 0;

		for (int i = 0; i < degree; i++) {
			int currentDistance = next.distance;
			next = next.next();
			if (next.distance < currentDistance) {
				cycle++;
			}
		}

		return new Note(next, this.distance
				- (12 * cycle + next.distance - tonic.getDistance()));
	}
}