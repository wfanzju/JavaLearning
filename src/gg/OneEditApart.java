package gg;

/**
 * Created by fan on 10/8/15.
 */
public class OneEditApart {
    static boolean oneEdit(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return oneEdit(s2, s1);
        }
        if (s1.length() + 1 < s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (s1.length() == s2.length()) {
                    return s1.substring(i + 1).equals(s2.substring(i + 1));
                } else {
                    return s1.substring(i).equals(s2.substring(i + 1));
                }
            }
        }
        return s1.length() != s2.length();
    }

    public static void main(String[] args){
        System.out.println(oneEdit("hello","hella"));
    }
}
