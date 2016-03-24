/*
 Implement atoi to convert a string to an integer.  
 */

/*
 * Here I have some limitations for this problem:
 * 1. When input string is null or empty string, I will return null.
 * 2. I will use trim method to ignore all the spaces in the string.
 * 3. If the number is larger than max integer or smaller than min integer, throw exception.
 * 4. If there is any character is not between 0 to 9 except the first character are + or -, throw exception.
 *
 */
static int parseInteger(String str) {
    if(str == null || str.length() == 0)
        throw new NumberFormatException("Not a valid input");
    
    //make num long type since the number may overflow or underflow for integer.
    long num = 0;
    int start = 0;
    
    //ignore all spaces in input string
    str = str.trim();
    
    //handle the first char
    if(str.charAt(0) == '+' || str.charAt(0) == '-')
        start++;
    
    //handle the first char see if the integer will be positive or negative.
    boolean isNeg = false;
    if(str.charAt(0) == '-')
        isNeg = true;
    
    for(int i = start; i < str.length(); i++) {
        //check if the current character is between 0(ascii 48) to 9(ascii 57).
        if((int)str.charAt(i) >= 48 && (int)str.charAt(i) <= 57) {
            num = num * 10 + ((int)str.charAt(i) - 48);
            if(num > Integer.MAX_VALUE) {
                if(!isNeg)
                    throw new ArithmeticException("overflow occurred!");
                else if(num > (long) Integer.MAX_VALUE + 1)
                    throw new ArithmeticException("underflow occurred");
                else
                	break;
            }
        } else {
            throw new NumberFormatException("Not a valid integer");
        }
    }
    
    if(isNeg)
        return (int)num * (-1);
    else
        return (int)num;
}




