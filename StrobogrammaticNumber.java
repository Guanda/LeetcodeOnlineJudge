/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/

class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        if(num.length() == 0) {
            return true;
        }
        
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        
        int left = 0, right = num.length() - 1;
        while(left <= right) {
            if(!map.containsKey(num.charAt(left)) || !map.containsKey(num.charAt(right))) {
                return false;
            }
            
            if(map.get(num.charAt(left)) != num.charAt(right)) {
                return false;
            }
            
            left++;
            right--;
        }
        return true;
    }
}