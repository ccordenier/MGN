package mgn;

import java.util.ArrayList;
import java.util.List;

public class ChordProgression {

	static class Bar {

		private List<Chord> chords = new ArrayList<Chord>();

		void add(Chord chord) {

			if (chords.size() < 2) {
				chords.add(chord);
			}

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

	public void play() {
		for (Bar b : bars) {
			for (Chord c : b.chords) {
				System.out.println("Playing chord: " + c);
			}
		}
	}

}
