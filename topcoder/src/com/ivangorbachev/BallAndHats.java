package com.ivangorbachev;

public class BallAndHats {

    public int getHat(String hats, int numSwaps) {
        double[] v = new double[3];
        for (int i = 0; i < 3; ++i)
            if (hats.charAt(i) == '.')
                v[i] = 0.0;
            else
                v[i] = 1.0;
        double[] v1 = new double[3];
        for (int it = 0; it < numSwaps; ++it) {
            v1[1] += v[0] * 0.5;
            v1[0] += v[1] * 0.5;
            v1[2] += v[1] * 0.5;
            v1[1] += v[2] * 0.5;

            System.arraycopy(v1, 0, v, 0, 3);
            v1[0] = v1[1] = v1[2] = 0.0;
        }
        double mx = 0.0;
        int ret = 0;
        for (int i = 0; i < 3; ++i)
            if (v[i] > mx) {
                mx = v[i];
                ret = i;
            }
        return ret;
    }

}

