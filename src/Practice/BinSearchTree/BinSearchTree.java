package Practice.BinSearchTree;

import Practice.Queue.MyQueue;

/**
 * Created by wenxi on 2017/10/9.
 */
public class BinSearchTree<T extends Comparable<? super T>>{

    private TreeNode<T> root;

    public BinSearchTree(){root = null;}

    public boolean isEmpty(){return root == null;}

    public void makeEmpty(){root = null;}

    public boolean contains(T data){return contains(data,root); }
    private boolean contains(T data, TreeNode<T> node){
        if(node == null) return false;
        else{
            //递归查找
            int cmpResult = data.compareTo(node.data);
            if(cmpResult<0) return contains(data,node.left);
            else if(cmpResult>0) return contains(data,node.right);
            else return true;
        }
    }

    public T findMin(){
        if(isEmpty()) throw new IndexOutOfBoundsException();
        return findMax(this.root).data;
    }
    private TreeNode<T> findMin(TreeNode<T> root){
        while (root.left!=null) root = root.left;
        return root;
    }

    public T findMax(){
        if(isEmpty()) throw new IndexOutOfBoundsException();
        return findMax(this.root).data;
    }
    private TreeNode<T> findMax(TreeNode<T> root){
        while (root.right!=null) root = root.right;
        return root;
    }


    public boolean insert(T data){
        this.root = insert(data,this.root);
        return true;
    }
    private TreeNode<T> insert(T data,TreeNode<T> root){
        if(root == null) return new TreeNode<T>(data, null ,null);

        int cmp = data.compareTo(root.data);
        if(cmp<0) root.left=insert(data, root.left);
        else if(cmp>0) root.right=insert(data,root.right);

        return root;
    }

    public boolean remove(T data){ this.root = remove(data, this.root); return true;}
    private TreeNode<T> remove(T data,TreeNode<T> node){
        if(node==null) return node;

        int cmp = data.compareTo(node.data);
        if(cmp<0) node.left=remove(data, node.left);
        else if(cmp>0) node.right = remove(data,node.right);
        else if(node.right!=null && node.left!=null){//有两个儿子
            node.data = findMin(node.right).data;//用右子树的最小值代替当前节点的数据
            remove(node.data,node.right);//删除右子树的最小值比较简单，因为那个节点必定没有左儿子
        }else
            node = (node.left==null)?node.right:node.left;

        return node;
    }


    public void print(Order order){
        switch (order){
            case in:
                inOrder(this.root);
                break;
            case pre:
                preOrder(this.root);
                break;
            case level:
                levelOrder(this.root);
                break;
        }

        System.out.println();
    }

    public void preOrder(TreeNode<T> node){
        if(node==null) return;
        System.out.print(node.data+"->");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(TreeNode<T> node){
        if(node==null) return;

        inOrder(node.left);
        System.out.print(node.data+"->");
        inOrder(node.right);
    }

    public void levelOrder(TreeNode<T> node){
        MyQueue<TreeNode<T>> queue = new MyQueue<>(200);
        queue.push(node);

        TreeNode<T> t;
        while (!queue.isEmpty()){
            t=queue.pop();
            System.out.print(t.data+"->");
            if(t.left!=null) queue.push(t.left);
            if(t.right!=null) queue.push(t.right);
        }
    }


    //树结点类，嵌套类
    private static class TreeNode<T>{
        T data;
        TreeNode<T> left;
        TreeNode<T> right;
        //constructor
        public TreeNode(T data){this(data,null,null);}
        public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }
}

enum Order{
    pre,
    in,
    post,
    level;

}
