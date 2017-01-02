/*
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Analysis:
	Binary Search
	case 1: citations[mid] == len-mid, then it means there are citations[mid] papers that have at least 
	        citations[mid] citations.
	case 2: citations[mid] > len-mid, then it means there are citations[mid] papers that have more than 
	        citations[mid] citations, so we should continue searching in the left half
	case 3: citations[mid] < len-mid, we should continue searching in the right side

	After iteration, it is guaranteed that right+1 is the one we need to find (i.e. len-left papars 
	have at least len-left citations)
*/
class H-IndexII {
	public int hIndex(int[] citations) {
		int len = citations.length;
		int left = 0;
		int right = len - 1;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(citations[mid] == len - mid) {
				return citations[mid];
			}
			else if(citations[mid] > len - mid) {
				right = mid - 1;
			}
			else 
				left = mid + 1;
		}
		return len - left;
	}
}