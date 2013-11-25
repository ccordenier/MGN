/**
 * 
 */
package mgn;

public class Note {

	private ReferenceNote note;

	private int alteration;

	final static String[] alterationSymbols = new String[] { "bb", "b", "",
			"#", "##" };

	public Note(ReferenceNote note) {
		this(note, 0);
	}
	
	public Note(ReferenceNote note, int alteration) {
		super();
		if (alteration > 2 || alteration < -2) {
			throw new IllegalArgumentException(
					"Alteration cannot exceed 2 but was: " + alteration);
		}
		this.note = note;
		this.alteration = alteration;
	}

	public ReferenceNote getReferenceNote() {
		return note;
	}

	public int getDistance() {
		return note.getDistance() + alteration;
	}

	public String toString() {
		return note.getName() + alterationSymbols[alteration + 2];
	}
}