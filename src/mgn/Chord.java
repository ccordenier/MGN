package mgn;

import java.util.ArrayList;
import java.util.List;

public class Chord {

	private Note tonic;

	private ReferenceChord referenceChord;

	private List<Note> notes;

	public Chord(Note tonic, ReferenceChord referenceChord) {
		super();
		this.tonic = tonic;
		this.referenceChord = referenceChord;

		notes = new ArrayList<Note>();

		for (Interval i : referenceChord.getIntervals()) {
			notes.add(i.getAscending(tonic));
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
