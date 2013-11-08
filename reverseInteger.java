package LeetcodeOnlineJudge;

public class reverseInteger {
    public int reverse(int x) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
    int r = 0;

    while(x != 0) {
        r = r*10 + x % 10;
        x /= 10;
    }
    return r;
    }
}
