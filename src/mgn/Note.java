/**
 * 
 */
package mgn;

public class Note {

	private ReferenceNote note;

	private int alteration;

	/**
	 * By default the note will be played on octave n°4.
	 */
	private int octave;

	final static String[] alterationSymbols = new String[] { "bb", "b", "", "#", "##" };

	public Note(ReferenceNote note) {
		this(note, 0);
	}

	public Note(ReferenceNote note, int alteration) {
		this(note, alteration, 4);
	}

	public Note(ReferenceNote note, int alteration, int octave) {
		super();
		if (alteration > 2 || alteration < -2) {
			throw new IllegalArgumentException("Alteration cannot exceed 2 but was: " + alteration);
		}
		if (octave < 0 || octave > 8) {
			throw new IllegalArgumentException("Octave must be between 0 and 8, but was: " + octave);
		}

		this.note = note;
		this.alteration = alteration;
		this.octave = octave;
	}

	public ReferenceNote getReferenceNote() {
		return note;
	}

	public int getDistance() {
		return note.getDistance() + alteration;
	}

	public int getOctave() {
		return octave;
	}

	@Override
	public String toString() {
		return String.format("%s%s[%d]", note.getName(), alterationSymbols[alteration + 2], octave);
	}
}