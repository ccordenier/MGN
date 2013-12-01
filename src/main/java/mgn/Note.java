/**
 * 
 */
package mgn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Note {

	/**
	 * List of note symbol in the international system.
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

		public String getName() {
			return name;
		}

		public int getDistanceFromC() {
			return distanceFromC;
		}

		public ReferenceNote next(int step) {

			if (step < 0) {
				throw new IllegalArgumentException("step arg must be positive or 0, was : " + step);
			}

			return ReferenceNote.values()[(ordinal() + step) % 7];
		}

	}

	private ReferenceNote referenceNote;

	private int alteration = 0;

	private int octave = 4;

	private static final String[] ALTERATION_SYMBOL = new String[] { "bb", "b", "", "#", "##" };

	private static final Pattern NOTE_PATTERN = Pattern.compile("^([A-G])([b#])?$");

	public Note(String note) {

		Matcher matcher = NOTE_PATTERN.matcher(note.trim());

		if (!matcher.find()) {
			throw new IllegalArgumentException("Unkown note expression : " + note);
		}

		referenceNote = ReferenceNote.valueOf(matcher.group(1));

		String alterationSymbol = matcher.group(2);

		if (alterationSymbol != null) {
			alteration = "b".equals(alterationSymbol) ? -1 : 1;
		}

	}

	private Note(ReferenceNote note, int alteration, int octave) {
		super();

		if (octave < 0 || octave > 8) {
			throw new IllegalArgumentException("Octave must be between 0 and 8, but was: " + octave);
		}

		referenceNote = note;
		this.alteration = alteration;
		this.octave = octave;
	}

	public Note getAscending(Interval i) {

		ReferenceNote result = referenceNote.next(i.getDegree());

		int cycle = (referenceNote.getDistanceFromC() + i.getDistance()) / 12;

		int remainder = (referenceNote.getDistanceFromC() + i.getDistance()) % 12;

		int alterationValue = (remainder - result.getDistanceFromC()) + alteration;

		return new Note(result, alterationValue, octave + cycle);
	}

	@Override
	public String toString() {
		return String.format("%s%s", referenceNote.getName(), ALTERATION_SYMBOL[alteration + 2]);
	}
}