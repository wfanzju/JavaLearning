package gg;

/**
 * Created by fan on 11/14/15.
 */
public class UTF8Validation {
    /**
     * Returns the number of UTF-8 characters, or -1 if the array
     * does not contain a valid UTF-8 string.  Overlong encodings,
     * null characters, invalid Unicode values, and surrogates are
     * accepted.
     */
    public static int charLength(byte[] bytes) {
        int charCount = 0;
        int expectedLen;
        for (int i = 0; i < bytes.length; i++) {
            charCount++;
            if ((bytes[i] & (1 << 7)) == 0) {
                continue;
            }
            byte mask = (byte) (1 << 7) | (1 << 6);
            byte tmpMask = mask;
            for (expectedLen = 2; expectedLen < 7; expectedLen++) {
                byte tmpVal = tmpMask;
                tmpMask |= (1 << (7 - expectedLen));
                if ((bytes[i] & tmpMask) == tmpVal) {
                    break;
                }
            }
            if (expectedLen == 7) {
                return -1;
            }
            while (--expectedLen > 0) {
                if (++i >= bytes.length) {
                    return -1;
                } else if ((bytes[i] & mask) != (byte) (1 << 7)) {
                    return -1;
                }
            }
        }
        return charCount;
    }

    public static void main(String[] args) {
        System.out.println(0b10000000 == 0x80);
        System.out.println(1 << 7);
        System.out.println((byte) (1 << 7));
        System.out.println(charLength(new byte[]{0b01111111}));

        byte[] bytes1 = {(byte) 0b11001111, (byte) 0b10111111};
        System.out.println(charLength(bytes1)); // true

        byte[] bytes2 = {(byte) 0b11101111, (byte) 0b10101010, (byte) 0b10111111};
        System.out.println(charLength(bytes2)); // true

        byte[] bytes3 = {(byte) 0b10001111, (byte) 0b10111111};
        System.out.println(charLength(bytes3));

        byte[] bytes4 = {(byte) 0b11101111, (byte) 0b10101010, (byte) 0b00111111};
        System.out.println(charLength(bytes4));
    }
}
