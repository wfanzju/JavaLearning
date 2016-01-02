package amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fan on 11/26/15.
 */
public class ShortenURL {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    private int id = 0;
    private final Map<Integer, String> idToURL = new HashMap<>();

    public String shortenURL(String url) {
        idToURL.put(id++, url);
        StringBuilder res = new StringBuilder();
        int tmp = id - 1;
        do {
            int digit = tmp % 62;
            res.append(ALPHABET.charAt(digit));
            tmp /= 62;
        } while (tmp > 0);
        while (res.length() < 6) {
            res.append('a');
        }
        return res.reverse().toString();
    }

    public String decode(String shortUrl) {
        int num = 0;
        for (int i = 0; i < shortUrl.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(shortUrl.charAt(i));
        }
        return idToURL.get(num);
    }

    public static void main(String[] args) {
        ShortenURL test = new ShortenURL();
        String short1 = test.shortenURL("hello");
        String short2 = test.shortenURL("world");
        System.out.println(short1);
        System.out.println(short2);
        System.out.println(test.decode(short1));
        System.out.println(test.decode(short2));

    }
}
