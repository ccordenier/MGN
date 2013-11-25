package mgn;

public class MyGuitarNeck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Note tonic = new Note(ReferenceNote.C);
		for (int i = 0; i < 7; i++) {
			System.out.println(tonic + " " + Interval.FIFTH.getAscending(tonic));
			tonic = new Note(tonic.getReferenceNote().next());
		}
	}

}
