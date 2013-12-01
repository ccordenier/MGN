package mgn;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

public class ChordProgression {

	public static final int END_OF_TRACK = 47;

	static class Bar {

		private List<Chord> chords = new ArrayList<Chord>();

		void add(Chord chord) {

			if (chords.size() == 2) {
				throw new IllegalArgumentException(
						"Cannot have more than 2 chords per bar.");
			}
			
			chords.add(chord);
		}
	}

	private List<Bar> bars = new ArrayList<ChordProgression.Bar>();

	private Bar currentBar;

	public void startBar() {
		currentBar = new Bar();
		bars.add(currentBar);
	}

	public void addChord(Chord chord) {
		currentBar.add(chord);
	}

	public void play(int tempo) throws InvalidMidiDataException,
			MidiUnavailableException {

		Sequence sequence = new Sequence(Sequence.PPQ, 16);

		Track track = sequence.createTrack(); // Begin with a new track

		// Set the instrument on channel 0
		ShortMessage sm = new ShortMessage();
		sm.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 0, 0);
		track.add(new MidiEvent(sm, 0));

		for (int i = 0; i < bars.size(); i++) {

			Bar b = bars.get(i);

			int tickLength = b.chords.size() == 2 ? 32 : 64;

			for (int j = 0; j < b.chords.size(); j++) {

				Chord c = b.chords.get(j);

				c.fillTrack(track, i * 64 + j * tickLength, tickLength);
			}
		}

		Sequencer sequencer = MidiSystem.getSequencer();
		sequencer.open();
		Synthesizer synthesizer = MidiSystem.getSynthesizer();
		synthesizer.open();
		sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());

		// Specify the sequence to play, and the tempo to play it at
		sequencer.setSequence(sequence);
		sequencer.setTempoInBPM(tempo);

		// Let us know when it is done playing
		sequencer.addMetaEventListener(new MetaEventListener() {
			public void meta(MetaMessage m) {
				// A message of this type is automatically sent
				// when we reach the end of the track
				if (m.getType() == END_OF_TRACK)
					System.exit(0);
			}
		});
		
		// And start playing now.
		sequencer.start();
	}

}
