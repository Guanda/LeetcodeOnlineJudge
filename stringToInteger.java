package LeetcodeOnlineJudge;

/*
 Implement atoi to convert a string to an integer.  
 */
public class stringToInteger {
	public int atoi(String str) {
		if (str == null || str.length() == 0)
			return 0;
		long num = 0;
		int j = 0;
		str = str.trim();
		if (str.charAt(0) == '+' || str.charAt(0) == '-') {
			j++;
		}
		boolean isNeg = false;
		if (str.charAt(0) == '-') {
			isNeg = true;
		}

		for (int i = j; i < str.length(); i++) {
			if (((int) str.charAt(i) >= 48) && ((int) str.charAt(i) <= 57)) {
				num = num * 10 + ((int) str.charAt(i) - 48);
				if (num > Integer.MAX_VALUE) {
					if (isNeg == false)
						return Integer.MAX_VALUE;
					else
						return Integer.MIN_VALUE;
				} else if (num == Integer.MAX_VALUE) {
					if (isNeg == false)
						return Integer.MAX_VALUE;
					else
						return Integer.MIN_VALUE + 1;
				}
			} else {
				break;
			}
		}

		if (isNeg)
			return (int) num * (-1);
		else
			return (int) num;
	}
}
