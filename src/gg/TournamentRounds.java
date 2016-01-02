package gg;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by fan on 11/21/15.
 */
public class TournamentRounds {

    public void printRound(List<String[]> round) {
        for (String[] pair : round) {
            System.out.print(Arrays.toString(pair));
        }
        System.out.println();
    }

    public List<List<String[]>> getPossibleNextRounds(List<String[]> currRound) {
        List<List<String[]>> nextRound = new ArrayList<>();
        dfs(currRound, new ArrayList<>(), nextRound);
        return nextRound;
    }

    private void dfs(List<String[]> currRound, List<String[]> nextRound, List<List<String[]>> res) {
        if (currRound.isEmpty()) {
            res.add(new ArrayList<>(nextRound));
            return;
        } else if (currRound.size() == 1) {
            String[] players = currRound.remove(0);
            for (String p : players) {
                nextRound.add(new String[]{p});
                dfs(currRound, nextRound, res);
                nextRound.remove(nextRound.size() - 1);
            }
            currRound.add(players);
        } else {
            for (int i = 0; i < currRound.size() - 1; i++) {
                for (int j = i + 1; j < currRound.size(); j++) {
                    List<String[]> tmp = new ArrayList<>(currRound);
                    String[] pair1 = currRound.get(i);
                    String[] pair2 = currRound.get(j);
                    tmp.remove(j);
                    tmp.remove(i);
                    for (String p1 : pair1) {
                        for (String p2 : pair2) {
                            nextRound.add(new String[]{p1, p2});
                            dfs(tmp, nextRound, res);
                            nextRound.remove(nextRound.size() - 1);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TournamentRounds test = new TournamentRounds();
        List<String[]> start = new ArrayList<>();
        start.add(new String[]{"A", "B"});
        start.add(new String[]{"C", "D"});
        start.add(new String[]{"E"});
        List<List<String[]>> res = test.getPossibleNextRounds(start);
        for (List<String[]> round : res) {
            System.out.println("nextRound:");
            test.printRound(round);
        }
    }
}
