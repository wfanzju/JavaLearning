package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/15/15.
 */
public class MaxVacation {
    private class Office {
        int[] vacations;
        List<Office> adjOffices = new ArrayList<>();
    }

    private List<Office> offices;
    private int maxVac = 0;

    public int maxVacation() {
        for (Office o : offices) {
            dfs(o, 1, 0);
        }
        return maxVac;
    }

    private void dfs(Office o, int currMon, int currVac) {
        if (currMon > 12) {
            if (currVac > maxVac) {
                maxVac = currVac;
            }
            return;
        }
        currVac += o.vacations[currMon - 1];
        for (Office next : o.adjOffices) {
            dfs(next, currMon + 1, currVac);
        }
    }
}
