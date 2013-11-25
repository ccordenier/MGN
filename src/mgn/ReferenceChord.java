package mgn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ReferenceChord {

	MAJOR(Interval.TONIC, Interval.MAJOR_THIRD, Interval.FIFTH),

	MINOR(Interval.TONIC, Interval.MINOR_THIRD, Interval.FIFTH),

	SEVENTH(MAJOR, Interval.MINOR_SEVENTH),

	SEVENTH_MAJOR(MAJOR, Interval.SEVENTH),

	SEVENTH_MINOR(MINOR, Interval.MINOR_SEVENTH);

	ReferenceChord(Interval... intervals) {
		this.intervals = Arrays.asList(intervals);
	}

	ReferenceChord(ReferenceChord chord, Interval... intervals) {
		this.intervals = new ArrayList<Interval>(chord.intervals);
		this.intervals.addAll(Arrays.asList(intervals));
	}

	private List<Interval> intervals;

	public List<Interval> getIntervals() {
		return intervals;
	}

}
