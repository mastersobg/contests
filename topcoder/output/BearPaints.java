
public class BearPaints {
    public long maxArea(int W, int H, long M) {
        int square = (int) Math.sqrt(M);
        long ret = 0;
        long x = 0, y = 0;
        for (long i = 1; i <= square; ++i) {
            long other = M / square;
            long a = Math.min(i, W);
            long b = Math.min(other, H);
            long c = Math.min(i, H);
            long d = Math.min(i, W);
            if (a * b > ret) {
                ret = a * b;
                x = a;
                y = other;
            }
            ret = Math.max(ret, a * b);
//            ret = max(ret, c * d);
        }
//        Dbg.dbg(x, y);
        return ret;
    }

}
