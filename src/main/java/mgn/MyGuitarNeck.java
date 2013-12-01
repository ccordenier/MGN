package mgn;

import java.io.IOException;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class MyGuitarNeck {

	/**
	 * @param args
	 * @throws IOException
	 * @throws RecognitionException
	 */
	public static void main(String[] args) throws IOException, RecognitionException {
		
//		Note G = new Note("G#");
//		System.out.println(G);
//		System.out.println(G.getAscending(Interval.MINOR_THIRD));
//		System.out.println(G.getAscending(Interval.MAJOR_THIRD));
//		System.out.println(G.getAscending(Interval.FIFTH));
//		
//		Chord cSharpMajor = new Chord("C#m7");
//		System.out.println(cSharpMajor);

		ANTLRInputStream input = new ANTLRInputStream(System.in);
		ChordProgressionLexer lexer = new ChordProgressionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ChordProgressionParser parser = new ChordProgressionParser(tokens);
		parser.chordGrid();
		
		parser.progression.play();
	}

}
