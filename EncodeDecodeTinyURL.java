/*
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode 
algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be 
decoded to the original URL.

*/

class EncodeDecodeTinyURL {

	///////////////// Solution 1 ///////////////////////////////////////////////
	// just append one by one based on index of list
	List<String> urls = new ArrayList<String>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        urls.add(longUrl);
        return String.valueOf(urls.size()-1);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = Integer.valueOf(shortUrl);
        return (index<urls.size()) ? urls.get(index) : "";
    }


    ///////////////// Solution 2 ///////////////////////////////////////////////
    // do hash based on hashCode function
    Map<Integer, String> map = new HashMap();
    String host = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int key = longUrl.hashCode();
        while(map.containsKey(key) && !map.get(key).equals(longUrl)) {
        	String newString = longUrl + (int) Math.random() * 1000;
        	key = newString.hashCode();
        }
        map.put(key, longUrl);
        return host + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int key = Integer.parseInt(shortUrl.replace(host,""));
        return map.get(key);
    }
}


/////////////More detailed version////////////////////////////////////
public class URLService {
    HashMap<String, Integer> ltos;
    HashMap<Integer, String> stol;
    static int COUNTER;
    String elements;
    URLService() {
        ltos = new HashMap<String, Integer>();
        stol = new HashMap<Integer, String>();
        COUNTER = 1;
        elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }
    public String longToShort(String url) {
        String shorturl = base10ToBase62(COUNTER);
        ltos.put(url, COUNTER);
        stol.put(COUNTER, url);
        COUNTER++;
        return "http://tiny.url/" + shorturl;
    }
    public String shortToLong(String url) {
        url = url.substring("http://tiny.url/".length());
        int n = base62ToBase10(url);
        return stol.get(n);
    }
    
    public int base62ToBase10(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n * 62 + convert(s.charAt(i));
        }
        return n;
        
    }
    public int convert(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }
    public String base10ToBase62(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, elements.charAt(n % 62));
            n /= 62;
        }
        while (sb.length() != 6) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }
}