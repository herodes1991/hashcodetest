package com.hashcode.bbq;

import java.util.ArrayList;
import java.util.List;

public class CookieCutterCreator {

    public List<int[]> getCookieCutters(int minimumSizeOfSlice, int maximumSizeOfSlice) {
        List<int[]> cookieCutter = new ArrayList<>();
        for (int i = maximumSizeOfSlice; i >= minimumSizeOfSlice; i--) {
            for (int j = 1; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    cookieCutter.add(new int[]{i/j, j});
                    if (i != j) {
                        cookieCutter.add(new int[]{j, i/j});
                    }
                }
            }
        }
        return cookieCutter;
    }
}
