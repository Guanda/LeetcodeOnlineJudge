/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

*/
class Candy {
	public int candy(int[] ratings) {
		int[] nums = new int[ratings.length];
		for(int i = 0; i < ratings.length; i++) {
			nums[i] = 1;
		}

		for(int i = 1; i < ratings.length; i++) {
			if(ratings[i] > ratings[i-1])
				nums[i] = nums[i-1] + 1;
		}

		for(int i = ratings.length-1; i >= 1; i--) {
			if(ratings[i-1] > ratings[i])
				nums[i-1] = Math.max(nums[i-1], nums[i] + 1);
		}

		//calculate the totoal candies
		int result = 0;
		for(int i = 0; i < nums.length; i++) {
			result += nums[i];
		}

		return result;
	}
}