/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.

Analysis;
	Classic Sweep Line problem. Create Point class to identify time and flag for each time point.
*/

class MeetingRoomII {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) {
        	return 0;
        }

        List<Point> list = new ArrayList<>();
        for(Interval i : intervals) {
        	list.add(new Point(i.start, 1));
        	list.add(new Point(i.end, 0));
        }

        Collections.sort(list, new Comparator<Point>() {
        	@Override
        	public int compare(Point p1, Point p2) {
        		if(p1.time == p2.time) {
        			return p1.flag - p2.flag;
        		}
        		return p1.time - p2.time;
        	}
        });

        int max = 0;
        int count = 0;
        for(Point p : list) {
        	if(p.flag == 1) {
        		count++;
        	}
        	else {
        		count--;
        	}
        	max = Math.max(max, count);
        }

        return max;
    }

    class Point {
    	int time;
    	int flag;
    	public Point(int time, int flag) {
    		this.time = time;
    		this.flag = flag;
    	}
    }
}
	
