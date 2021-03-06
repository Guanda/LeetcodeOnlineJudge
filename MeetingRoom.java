/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] 
(si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
*/

class MeetingRoom {
    public boolean canAttendMeetings(Interval[] intervals) {
     	if(intervals == null || intervals.length == 0) {
     		return true;
     	}   

     	Arrays.sort(intervals, new Comparator<Interval>() {
     		@Override
     		public int compare(Interval i1, Interval i2) {
     			return i1.start - i2.start;
     		}
     	});

     	Interval last = intervals[0];
     	for(int i = 1; i < intervals.length; i++) {
     		Interval curr = intervals[i];
     		if(curr.start < last.end) {
     			return false;
     		}
     		last = curr;
     	}

     	return true;
    }
}