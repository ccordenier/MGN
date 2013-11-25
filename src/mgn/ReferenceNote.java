/**
 * 
 */
package mgn;

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
		this.distance = distance;
	}

	String name;

	int distance;

	public String getName() {
		return name;
	}

	public int getDistance() {
		return distance;
	}

	public ReferenceNote next() {
		return ReferenceNote.values()[(ordinal() + 1) % 7];
	}

}