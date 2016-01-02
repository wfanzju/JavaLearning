package gg;

import java.util.Random;

/**
 * Created by fan on 11/11/15.
 */
public class RainsOnSideWalk {
    private double[][] sideWalk = new double[100][2];
    private boolean[] partWet = new boolean[100];
    private int wetCount = 0;
    private final Random random = new Random();

    private void rainDrop(double start) {
        int idx = (int) (start * 100);
        if (idx == 100) {
            return;
        }
        double len = start - idx / 100.0;
        if (idx < 99) {
            sideWalk[idx + 1][0] = Math.max(sideWalk[idx + 1][0], len);
            if (!partWet[idx + 1]
                    && sideWalk[idx + 1][0] + sideWalk[idx + 1][1] >= 0.01) {
                partWet[idx + 1] = true;
                wetCount++;
            }
        }
        sideWalk[idx][1] = Math.max(sideWalk[idx][1], 0.01 - len);
        if (!partWet[idx] && sideWalk[idx][0] + sideWalk[idx][1] >= 0.01) {
            partWet[idx] = true;
            wetCount++;
        }
    }

    public boolean allWet() {
        return wetCount == 99;
    }

    public void randomRain() {
        double start = random.nextBoolean() ?
                random.nextDouble() : 1 - random.nextDouble();
        if (start == 0.0 || start == 1.0) {
            System.out.println(start);
        }
        rainDrop(start);
    }

    public static void main(String[] args) {
        RainsOnSideWalk test = new RainsOnSideWalk();
        long count = 0;
        while (!test.allWet()) {
            test.randomRain();
            count++;
        }
        System.out.println(test.partWet[0]);
        System.out.println(count);
    }
}
