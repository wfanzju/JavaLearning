package test;

/**
 * Created by fan on 11/12/15.
 */

public class Solution {
    public String solution(String S, String T) {
        // write your code in Java SE 8
        if(S==null||T==null||Math.abs(S.length()-T.length())>1){
            return "IMPOSSIBLE";
        }
        if(S.equals(T)){
            return "NOTHING";
        }
        if(S.length()==T.length()){
            for(int i=0; i<S.length()-1; i++){
                if(S.charAt(i)==T.charAt(i)){
                    continue;
                }
                String newS = S.substring(0,i)+S.charAt(i+1)+S.charAt(i)+S.substring(i+1);
                if(newS.equals(T)){
                    return "SWAP "+S.charAt(i)+" "+S.charAt(i+1);
                }else{
                    return "IMPOSSIBLE";
                }
            }
            return "IMPOSSIBLE";
        }
        Character c = findDiffChar(S, T);
        if(c==null){
            return "IMPOSSIBLE";
        }else if(S.length()==T.length()-1){
            return "INSERT "+c;
        }else {
            return "DELETE "+c;
        }
    }
    private Character findDiffChar(String s, String t){
        if(s.length()>t.length()){
            return findDiffChar(t, s);
        }
        long sumC = 0;
        for (char c : s.toCharArray()) {
            sumC+=(int)c;
        }
        long sumT = 0;
        for (char c : t.toCharArray()) {
            sumT += (int) c;
        }
        return (char) (sumT - sumC);
    }
    public static void main(String[] args){
        Solution test = new Solution();
        System.out.println(test.solution("nice", "niece"));
    }
}
