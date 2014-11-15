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
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        return sum(root, 0);
    }
    
    private int sum(TreeNode root, int cur) {
        int next = cur * 10 + root.val;
        if (root.left == null && root.right == null)
            return next;
        int left = root.left != null ? sum(root.left, next) : 0;
        int right = root.right != null ? sum(root.right, next) : 0;
        return left + right;
    }
}