/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, 
it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) 
that reads n characters from the file.

Note:
The read function will only be called once for each test case.
*/
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

class ReadNCharacterGivenRead4 extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        boolean endOfFile = false;
        int readBytes = 0;

        while(readBytes < n && !endOfFile) {
        	int currReadBytes = read4(buffer);
        	if(currReadBytes != 4) {
        		endOfFile = true;
        	}

        	int len = Math.min(n - readBytes, currReadBytes);
        	for(int i = 0; i < len; i++) {
        		buf[readBytes + i] = buffer[i];
        	}
        	readBytes += len;
        }

        return readBytes;
    }
}