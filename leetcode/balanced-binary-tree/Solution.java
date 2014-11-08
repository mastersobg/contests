/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    static class Triple {
        int hl, hr;
        boolean balanced = true;
        Triple(int a, int b, boolean c) {
            hl = a;
            hr = b;
            balanced = c;
        }
    }
    public boolean isBalanced(TreeNode root) {
        return check(root).balanced;
    }
    
    private Triple check(TreeNode root) {
        if (root == null)
            return new Triple(0, 0, true);
        Triple l = check(root.left);
        Triple r = check(root.right);
        int left = Math.max(l.hl, l.hr);
        int right = Math.max(r.hl, r.hr);
        return new Triple(left + 1, right + 1, Math.abs(left - right) <= 1 && l.balanced && r.balanced);
    }
}