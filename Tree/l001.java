import java.util.ArrayList;

public class l001 {
    public class TreeNode{
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
            System.out.println("In class: " + this);
        }
    }    

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }
    //-(long)1e18 <= long type <= (long)1e18
    public static int maximum(TreeNode root) {
        return root == null ? -(int)1e9 : Math.max(root.val, Math.max(maximum(root.left), maximum(root.right)));
    }

    public static int minimum(TreeNode root) {
        return root == null ? (int)1e9 : Math.max(root.val, Math.max(minimum(root.left), minimum(root.right)));
    }

    public static boolean find(TreeNode root, int data) {
        if(root == null) return false;
        if(root.val == data) return true;
        return find(root.left, data) || find(root.right, data); //false OR true = true (OR operation) 
        //Also this true will trace the node to root path
    }

    public static boolean nodeToRootPath_(TreeNode root, int data, ArrayList<TreeNode> ans) {

        if (root == null)
            return false;

        if (root.val == data) {
            ans.add(root);
            return true;
        }

        // if (nodeToRootPath_(root.left, data, ans))
        // {
        // ans.add(root);
        // return true;
        // }

        // if (nodeToRootPath_(root.right, data, ans))
        // {
        // ans.add(root);
        // return true;
        // }

        // return false;

        boolean res = nodeToRootPath_(root.left, data, ans) || nodeToRootPath_(root.right, data, ans);

        if (res)
            ans.add(root);
        return res;
    }

    ArrayList<TreeNode> nodeToRootPath_(TreeNode root, int data) {
        if (root == null) {
            return new ArrayList<>();
        }

        if (root.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode> left = nodeToRootPath_(root.left, data);
        if (left.size() != 0) {
            left.add(root);
            return left;
        }

        ArrayList<TreeNode> right = nodeToRootPath_(root.right, data);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }

        return new ArrayList<>();
    }

    public static void rootToAllLeafPath(TreeNode root, ArrayList<ArrayList<Integer>> ans,
            ArrayList<Integer> smallAns) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }

        smallAns.add(root.val);
        rootToAllLeafPath(root.left, ans, smallAns);
        rootToAllLeafPath(root.right, ans, smallAns);
        smallAns.remove(smallAns.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>();

        rootToAllLeafPath(root, ans, smallAns);
        return ans;
    }

    public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if (root.left == null || root.right == null) {
            ans.add(root.val);
        }

        exactlyOneChild(root.left, ans);
        exactlyOneChild(root.right, ans);
    }

    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root);
        return ans;
    }

    //Adds the answer to ans
    public static void burningTreeNode(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if(root == null || root == blockNode)
            return;
        
            if(time == ans.size()){
                ans.add(new ArrayList<>());
            }
            ans.get(time).add(root.val);

            burningTreeNode(root.left, time + 1, blockNode, ans);
            burningTreeNode(root.right, time + 1, blockNode, ans);
    }

    //main code logic executes here
    public static int burningTree(TreeNode root, int fireNode, ArrayList<ArrayList<Integer>> ans) {
        if(root == null){
            return -1;
        }

        if(root.val == fireNode){
            burningTreeNode(root, 0, null, ans);
            return 1;
        }

        int lt = burningTree(root.left, fireNode, ans);
        if(lt != -1){
            burningTreeNode(root, lt, root.left, ans);
            return lt + 1;
        }

        int rt = burningTree(root.right, fireNode, ans);
        if(rt != -1){
            burningTreeNode(root, rt, root.right, ans);
            return rt + 1;
        }
        return -1;
    }

    //base question function
    public static void burningTree(TreeNode root, int data) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, data, ans);
    }

}


