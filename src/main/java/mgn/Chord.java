package mgn;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Chord {

	private Note tonic;

	private ReferenceChord referenceChord;

	private List<Note> notes;

	public static final Pattern CHORD_PATTERN = Pattern
			.compile("([^mM7]*)(m|M|M7|m7|7)?$");

	public Chord(String chord) {
		super();

		Matcher matcher = CHORD_PATTERN.matcher(chord.trim());

		if (!matcher.find()) {
			throw new IllegalArgumentException("Unknown chord expression : "
					+ chord);
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

	public void fillTrack(Track track, int tick, int tickLength)
			throws InvalidMidiDataException {

		for (Note n : notes) {
			ShortMessage on = new ShortMessage();
			on.setMessage(ShortMessage.NOTE_ON, 0, n.getMidiKey(), 64);
			ShortMessage off = new ShortMessage();
			off.setMessage(ShortMessage.NOTE_OFF, 0, n.getMidiKey(), 64);
			track.add(new MidiEvent(on, tick));
			track.add(new MidiEvent(off, tick + tickLength));
		}

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
