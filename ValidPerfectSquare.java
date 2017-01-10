/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False

Analysis:

Three Methods:
1. a square number is 1+3+5+7+... Time Complexity O(sqrt(N)).
2. binary search. Time Complexity O(logN)
3. Newton Method. See [this wiki page][1]. Time Complexity is close to constant, given a positive integer.
*/
class ValidPerfectSquare {
	//method 1
	public boolean isPerfectSquare(int num) {
      if (num < 1) return false;
      for (int i = 1; num > 0; i += 2)
        num -= i;
      return num == 0;
    }

    //method 2
    public boolean isPerfectSquare(int num) {
      if (num < 1) return false;
      long left = 1, right = num;// long type to avoid 2147483647 case
    
      while (left <= right) {
        long mid = left + (right - left) / 2;
        long t = mid * mid;
        if (t > num) {
          right = mid - 1;
        } 
        else if (t < num) {
          left = mid + 1;
        } 
        else {
          return true;
        }
      }
    
      return false;
    }

    //method 3
	boolean isPerfectSquare(int num) {
      if (num < 1) return false;
      long t = num / 2;
      while (t * t > num) {
        t = (t + num / t) / 2;
      }
      return t * t == num;
    }
}