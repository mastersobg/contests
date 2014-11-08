public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null)
            return false;
        int i = 0, j = s.length() - 1;
        while (true) {
            i = skip(s, i, 1);
            j = skip(s, j, -1);
            if (i >= j)
                break;
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            if (Character.toLowerCase(c1) != Character.toLowerCase(c2))
                return false;
            ++i;
            --j;
        }
        return true;
    }
    
    private int skip(String s, int idx, int step) {
        while (idx >= 0 && 
            idx < s.length() && 
            (!Character.isLetter(s.charAt(idx)) &&
            !Character.isDigit(s.charAt(idx))))
            idx += step;
        return idx;
    }
}