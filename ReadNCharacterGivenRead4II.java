/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, 
it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) 
that reads n characters from the file.

Note:
The read function may be called multiple times.

Analysis:
	I used buffer pointer (buffPtr) and buffer Counter (buffCnt) to store the data 
	received in previous calls. In the while loop, if buffPtr reaches current buffCnt, 
	it will be set as zero to be ready to read new data.
*/

class ReadNCharacterGivenRead4II extends Reader4 {
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buffer = new char[4];
    
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buffer);
            }
            if (buffCnt == 0) 
            	break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buffer[buffPtr++];
            }
            
            if (buffPtr >= buffCnt) 
            	buffPtr = 0;
        }
        return ptr;
    }
}