/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)

Analysis:
	Consider the 2Sum question first: sort array and then set two pointers, one from start and 
	then other one from the last. time O(n)

	So based on 2Sum problem, we can figure out it by using time O(n^2).
*/

class ThreeSum {
	//Solution 1: no hashset needed
	public List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> results = new ArrayList<>();
        if(numbers == null || numbers.length < 3) {
            return results;
        }
        
        Arrays.sort(numbers);
        
        for(int i = 0; i < numbers.length - 2 && numbers[i] <= 0; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            
            int left = i + 1, right = numbers.length - 1;
            while(left < right) {
                int value = numbers[i] + numbers[left] + numbers[right];
                if(value == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(numbers[i]);
                    list.add(numbers[left]);
                    list.add(numbers[right]);
                    results.add(list);
                    left++;
                    right--;
                    
                    while(left > 0 && left < right && numbers[left] == numbers[left - 1]) {
                        left++;
                    }
                    
                    while(right < numbers.length - 1 && left < right && numbers[right] == numbers[right + 1]) {
                        right--;
                    }
                }
                else if(value < 0) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }
        
        return results;
    }



	//Solution 2: with hashset, more space needed
	public List<List<Integer>> 3sum(int[] num) {
		List<List<Integer>> result = new ArrayList<>();
		HashSet<List<Integer>> hashSet = new HashSet<>();
		if(num.length < 3)
			return result;

		Arrays.sort(num);
		
		int n = num.length;

		//i<n-2 because we have at lest three elements in num[]
		for(int i = 0; i < n-2 && num[i] <= 0; i++) {
			//skip the same int in the loop
			if (i > 0 && num[i] == num[i-1]) 
				continue;

			int j = i + 1;
			int k = n - 1;
			while(j < k) {
				int sum_two = num[i] + num[j];
				if(sum_two + num[k] < 0) {
					j++;
				}
				else if(sum_two + num[k] > 0) {
					k--;
				}
				else {
					List<Integer> arr = new ArrayList<Integer>();
					arr.add(num[i]);
					arr.add(num[j]);
					arr.add(num[k]);

					//only add the unique one
					if(!hashSet.contains(arr)) {
						hashSet.add(arr);
						result.add(arr);
					}
					j++;
					k--;
				}
			}
		}

		return result;
	}
}