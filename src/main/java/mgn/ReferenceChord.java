package mgn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ReferenceChord {

	MAJOR("M", Interval.TONIC, Interval.MAJOR_THIRD, Interval.FIFTH),

	minor("m", Interval.TONIC, Interval.MINOR_THIRD, Interval.FIFTH),

	SEVENTH("7", MAJOR, Interval.MINOR_SEVENTH),

	MAJOR_SEVENTH("M7", MAJOR, Interval.SEVENTH),

	MINOR_SEVENTH("m7", minor, Interval.MINOR_SEVENTH);

	private ReferenceChord(String symbol, Interval... intervals) {
		this.intervals = Arrays.asList(intervals);
		this.symbol = symbol;
	}

	private ReferenceChord(String symbol, ReferenceChord chord, Interval... intervals) {
		this.intervals = new ArrayList<Interval>(chord.intervals);
		this.intervals.addAll(Arrays.asList(intervals));
		this.symbol = symbol;
	}

	private String symbol;

	private List<Interval> intervals;

	public List<Interval> getIntervals() {
		return intervals;
	}

	public static ReferenceChord find(String symbol) {
		for (ReferenceChord rc : ReferenceChord.values()) {
			if (rc.symbol.equals(symbol)) {
				return rc;
			}
		}
		throw new IllegalArgumentException("Unknow chord family : " + symbol);
	}

}
