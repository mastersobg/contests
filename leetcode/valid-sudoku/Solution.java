import java.util.*;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean []used = new boolean[10];
        for (int i = 0; i < 9; ++i) {
            if (!check(board, used, i, 0, i + 1, 9, 1, 1))
                return false;
            if (!check(board, used, 0, i, 9, i + 1, 1, 1))
                return false;
            int row = (i / 3) * 3;
            int col = (i % 3) * 3;
            if (!check(board, used, row, col, row + 3, col + 3, 1, 1))
                return false;
        }
        return true;
    }
    
    private boolean check(char [][]b, boolean []used, int xs, int ys, int xe, int ye, int xstep, int ystep) {
        Arrays.fill(used, false);
        for (int i = xs; i < xe; i += xstep)
            for (int j = ys; j < ye; j += ystep) {
                if (b[i][j] == '.')
                    continue;
                int a = b[i][j] - '0';
                if (used[a])
                    return false;
                used[a] = true;
            }
        return true;
    }
}