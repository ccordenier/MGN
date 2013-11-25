/**
 * 
 */
package mgn;

public enum ReferenceNote {

	A("La", 0),

	B("Si", 2),

	C("Do", 3),

	D("Re", 5),

	E("Mi", 7),

	F("Fa", 8),

	G("Sol", 10);

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

	public ReferenceNote previous() {
		return ReferenceNote.values()[(this.ordinal() + 6) % 7];
	}

	public ReferenceNote next() {
		return ReferenceNote.values()[(this.ordinal() + 1) % 7];
	}

}