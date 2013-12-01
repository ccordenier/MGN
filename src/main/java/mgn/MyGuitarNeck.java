package mgn;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class MyGuitarNeck {

	/**
	 * @param args
	 * @throws IOException
	 * @throws RecognitionException
	 * @throws InvalidMidiDataException
	 * @throws MidiUnavailableException
	 */
	public static void main(String[] args) throws IOException,
			RecognitionException, InvalidMidiDataException,
			MidiUnavailableException {

		ANTLRInputStream input = new ANTLRInputStream(new ByteArrayInputStream(
				"| Am | E7 | Am | E7 | Am / E7 | Am | Am / Dm | Am / E | Am / E7 | Am | F / E | Am / E7 |"
						.getBytes()));
		ChordProgressionLexer lexer = new ChordProgressionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ChordProgressionParser parser = new ChordProgressionParser(tokens);
		parser.chordGrid();

		parser.progression.play(104);
	}

}
