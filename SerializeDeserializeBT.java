/*
 Serialization is the process of converting a data structure or object into a sequence of bits
 so that it can be stored in a file or memory buffer, or transmitted across a network connection
 link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how
 your serialization/deserialization algorithm should work. You just need to ensure that a binary
 tree can be serialized to a string and this string can be deserialized to the original tree
 structure.

 For example, you may serialize the following tree

     1
    / \
   2   3
      / \
     4   5
 as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not
 necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 Analysis:
     The idea is simple: print the tree in pre-order traversal and use "X" to denote null node and split node with ",".
     We can use a StringBuilder for building the string on the fly. For deserializing, we use a Queue to store the pre-order
     traversal and since we have "X" as null node, we know exactly how to where to end building subtrees.
 */

// Solution 1:
public class SerializeDeserializeBT {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append(NN).append(spliter);
        }
        else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<String>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.remove();
        if(val.equals(NN))
            return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}


// Solution2: 
class SerializeDeserializeBT {
    //Use BFS for serialize and deserialize
    public String serialize(TreeNode root) {
        if(root == null) {
            return "{}";
        }
        
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        
        for(int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if(node == null) {
                continue;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
        
        //clear the null nodes in the end of queue
        while(queue.get(queue.size() - 1) == null) {
            queue.remove(queue.size() - 1);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(queue.get(0).val);
        for(int i = 1; i < queue.size(); i++) {
            if(queue.get(i) == null) {
                sb.append(",#");
            }
            else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    public TreeNode deserialize(String data) {
        if(data == "{}") {
            return null;
        }
        String[] vals = data.substring(1, data.length() - 1).split(",");
        List<TreeNode> list = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        list.add(root);
        int index = 0;
        boolean isLeftChild = true;
        for(int i = 1; i < vals.length; i++) {
            if(!vals[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                if(isLeftChild) {
                    list.get(index).left = node;
                }
                else {
                    list.get(index).right = node;
                }
                list.add(node);
            }
            if(!isLeftChild) {
                index++;
            }
            isLeftChild = !isLeftChild;
        }
        return root;
    }
}

// Treat SerializeDeserializeBT as Codec
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));