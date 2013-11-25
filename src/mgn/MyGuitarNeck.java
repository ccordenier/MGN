package mgn;

public class MyGuitarNeck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Note tonic = new Note(ReferenceNote.C);
		for (int i = 0; i < 7; i++) {
			Chord c = new Chord(tonic, ReferenceChord.SEVENTH_MAJOR);
			System.out.println(c);
			tonic = new Note(tonic.getReferenceNote().next());
		}
	}

}
