grammar ChordProgression;

options {
	language=Java;
}

tokens {
  BAR= '|';
  SEMI_BAR= '/';
}

@header {

package mgn;
}

@lexer::header {
package mgn;
}

@members {
 
 ChordProgression progression = new ChordProgression();
 
}

chordGrid: (BAR (bar BAR)+ NEWLINE?)+ EOF;

bar: {progression.startBar();} WS? chord (WS? SEMI_BAR WS? chord)? WS?; 

alteration: 
    'b' 
  | '#';

note returns [Note value]: NOTE_SYMBOL alteration?;
 
chordFamily: 
    'm'
  | 'M'
  | 'm7'
  | 'M7'
  | '7';

chord: note chordFamily { progression.addChord(new Chord($chord.text));};

NOTE_SYMBOL: 'A'..'G';

NEWLINE:'\r'? '\n' ;

WS:   (' '|'\t')+ {skip();} ;