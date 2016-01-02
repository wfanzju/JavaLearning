package gg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fan on 10/24/15.
 */
public class RunLengthEncoding {
    public static String encode(String src) {
        StringBuilder dest = new StringBuilder();
        char currChar;
        for (int i = 0; i < src.length(); i++) {
            currChar = src.charAt(i);
            int count = 1;
            while (i + 1 < src.length() && src.charAt(i) == src.charAt(i + 1)) {
                count++;
                i++;
            }
            dest.append(count);
            dest.append(currChar);
        }
        return dest.toString();
    }

    public static String decode(String dest) {
        StringBuilder src = new StringBuilder();
        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
        pattern = Pattern.compile("[0-9]+|[^0-9]");
        Matcher matcher = pattern.matcher(dest);
        while (matcher.find()) {
            int n = Integer.parseInt(matcher.group());
            if (!matcher.find()) {
                throw new IllegalStateException("Wrong input format!");
            }
            while (n != 0) {
                src.append(matcher.group());
                n--;
            }
        }
        return src.toString();
    }

    public static void main(String[] args) {
        System.out.println(encode("aaaaaaaaaab///e"));
        System.out.println(decode("1a10/"));
        Pattern p = Pattern.compile("/[0-9]+/");
        Matcher m = p.matcher("211/2//");
        m.find();
        System.out.println(m.group());
        System.out.println(m.start());
        System.out.println(m.end());
        System.out.println(decodeStr("211/2//1aa/3/b"));
    }

    public static String encodeStr(String src) {
        StringBuilder dest = new StringBuilder();
        char currChar;
        for (int i = 0; i < src.length(); i++) {
            currChar = src.charAt(i);
            int count = 1;
            while (i + 1 < src.length() && src.charAt(i) == src.charAt(i + 1)) {
                count++;
                i++;
            }
            if (count == 1) {
                dest.append(currChar);
            } else {
                dest.append(currChar).append(currChar).append("/").append(count).append("/");
            }
        }
        return dest.toString();
    }

    public static String decodeStr(String dest) {
        StringBuilder src = new StringBuilder();
        for (int i = 0; i < dest.length(); i++) {
            if (i == dest.length() - 1 || dest.charAt(i) != dest.charAt(i + 1)) {
                src.append(dest.charAt(i));
                continue;
            }
            char c = dest.charAt(i);
            Pattern pattern = Pattern.compile("/[0-9]+/");
            Matcher matcher = pattern.matcher(dest.substring(i));
            matcher.find();
            String part = matcher.group();
            int n = Integer.parseInt(part.substring(1, part.length() - 1));
            while (n > 0) {
                src.append(c);
                n--;
            }
            i = matcher.end() + i - 1;
        }
        return src.toString();
    }
}
