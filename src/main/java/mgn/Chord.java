package mgn;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chord {

	private Note tonic;

	private ReferenceChord referenceChord;

	private List<Note> notes;

	public static final Pattern CHORD_PATTERN = Pattern.compile("([^mM7]*)(m|M|M7|m7|7)?$");

	public Chord(String chord) {
		super();

		Matcher matcher = CHORD_PATTERN.matcher(chord.trim());

		if (!matcher.find()) {
			throw new IllegalArgumentException("Unknown chord expression : " + chord);
		}

		tonic = new Note(matcher.group(1));
		referenceChord = ReferenceChord.find(matcher.group(2));

		notes = new ArrayList<Note>();

		for (Interval i : referenceChord.getIntervals()) {
			notes.add(tonic.getAscending(i));
		}
	}

	public List<Note> getNotes() {
		return notes;
	}

	public Note getTonic() {
		return tonic;
	}

	public ReferenceChord getReferenceChord() {
		return referenceChord;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Note n : notes) {
			result.append(n).append(" ");
		}
		return result.toString();
	}
}
