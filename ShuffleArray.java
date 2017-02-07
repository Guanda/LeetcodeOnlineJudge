/*
Shuffle a set of numbers without duplicates.

Example:
// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);
// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();
// Resets the array back to its original configuration [1,2,3].
solution.reset();
// Returns the random shuffling of array [1,2,3].
solution.shuffle();

Analysis:
	Proof: Suppose this algorithm works, i.e. for each position j (starting from 0), the probability of any 
	number in the range[0,j] to be at position j is 1/(1+j).

	Let's look at int i = random.nextInt(j + 1):
	(1) If i == j, nums[j] does not need to change its position, which has probability 1/(1+j).
	(2) if i !=j, nums[j] needs to be swapped with nums[i]. The probability of any number x in the range [0,j-1] 
	to be at position j = nums[j] changes its position * x is at position i
	= (1-1/(1+j)) * (1/j) = 1/(1+j)

	Each number has equal probability to be at any position.
*/
class ShuffleArray {
	private int[] nums;
    private Random random;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(nums == null) return null;
        int[] a = nums.clone();
        for(int j = 1; j < a.length; j++) {
            int i = random.nextInt(j + 1);
            swap(a, i, j);
        }
        return a;
    }
    
    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */