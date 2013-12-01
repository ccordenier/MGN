package mgn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ReferenceChord {

	MAJOR(Interval.TONIC, Interval.MAJOR_THIRD, Interval.FIFTH),

	MINOR(Interval.TONIC, Interval.MINOR_THIRD, Interval.FIFTH),

	SEVENTH(MAJOR, Interval.MINOR_SEVENTH),

	MAJOR_SEVENTH(MAJOR, Interval.SEVENTH),

	MINOR_SEVENTH(MINOR, Interval.MINOR_SEVENTH);

	private ReferenceChord(Interval... intervals) {
		this.intervals = Arrays.asList(intervals);
	}

	private ReferenceChord(ReferenceChord chord, Interval... intervals) {
		this.intervals = new ArrayList<Interval>(chord.intervals);
		this.intervals.addAll(Arrays.asList(intervals));
	}

	private static final Map<String, ReferenceChord> CHORDS_CATALOG = new HashMap<String, ReferenceChord>();

	static {
		CHORDS_CATALOG.put("M", MAJOR);
		CHORDS_CATALOG.put("m", MINOR);
		CHORDS_CATALOG.put("7", SEVENTH);
		CHORDS_CATALOG.put("m7", MINOR_SEVENTH);
		CHORDS_CATALOG.put("M7", MAJOR_SEVENTH);
	}

	private List<Interval> intervals;

	public List<Interval> getIntervals() {
		return intervals;
	}

	public static ReferenceChord find(String symbol) {
		
		if(symbol == null || "".equals(symbol.trim())) {
			return MAJOR;
		}
		
		if (CHORDS_CATALOG.containsKey(symbol)) {
			return CHORDS_CATALOG.get(symbol);
		}

		throw new IllegalArgumentException("Unknow chord family : " + symbol);
	}
}
