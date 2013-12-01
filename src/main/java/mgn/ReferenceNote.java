package mgn;

/**
 * Diatonic scale is made of 12 semitones.
 * 
 */
public enum ReferenceNote {

	A("La", 9),

	B("Si", 11),

	C("Do", 0),

	D("Re", 2),

	E("Mi", 4),

	F("Fa", 5),

	G("Sol", 7);

	ReferenceNote(String name, int distance) {
		this.name = name;
		distanceFromC = distance;
	}

	String name;

	/**
	 * The distance in semi tones with C Middle.
	 */
	int distanceFromC;

	public ReferenceNote next(int step) {

		if (step < 0) {
			throw new IllegalArgumentException(
					"step arg must be positive or 0, was : " + step);
		}

		return ReferenceNote.values()[(ordinal() + step) % 7];
	}

}