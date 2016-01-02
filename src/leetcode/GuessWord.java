package leetcode;

/**
 * Created by fan on 11/21/15.
 */
public class GuessWord {
    public String getHint(String secret, String guess) {
        int same = 0;
        int exist = 0;
        int[] numberCount = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                same++;
            } else {
                if (numberCount[s - '0'] < 0) {
                    exist++;
                }
                if (numberCount[g - '0'] > 0) {
                    exist++;
                }
                numberCount[s - '0']++;
                numberCount[g - '0']--;
            }
        }
        return same + "A" + exist + "B";
    }

    public static void main(String[] args) {
        GuessWord test = new GuessWord();
        System.out.println(test.getHint("123", "321"));
    }
}
