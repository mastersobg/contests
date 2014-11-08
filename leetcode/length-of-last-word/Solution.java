public class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null)
            return 0;
        int i = s.length() - 1;
        while (i >= 0 && Character.isSpace(s.charAt(i)))
            --i;
        int len = 0;
        while (i >= 0 && !Character.isSpace(s.charAt(i--)))
            ++len;
        return len;
    }
}