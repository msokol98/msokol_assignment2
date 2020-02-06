package BST_A2;

public class BST implements BST_Interface {
	
    public BST_Node root;
    int size;
  
    public BST(){ size=0; root=null; }
  
    @Override
    //used for testing, please leave as is
    public BST_Node getRoot(){ return root; }

	@Override
	public boolean insert(String s) {
		// TODO Auto-generated method stub
		BST_Node newNode = new BST_Node(s);
		
		if(root == null) {
			root = newNode;
			return goodInsert();
		}
		
		BST_Node current = root;
		BST_Node parent;
		
		while(true) {
			parent = current;
			
			if(s.compareTo(current.data) < 0) {
				current = current.left;
				if(current == null) {
					parent.left = newNode;
					break;
				}
			}
			
			else if(s.compareTo(current.data) > 0) {
				current = current.right;
				if(current == null) {
					parent.right = newNode;
					break;
				}
			}
			
		}
		
		return goodInsert();
		
	}
	
	@Override
	public boolean remove(String s) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String findMin() {
		// TODO Auto-generated method stub
		BST_Node currentNode = root;
		while(currentNode.left != null) currentNode = currentNode.left;
		return currentNode.data;
		
	}
	
	@Override
	public String findMax() {
		// TODO Auto-generated method stub
		BST_Node currentNode = root;
		while(currentNode.right != null) currentNode = currentNode.right;
		return currentNode.data;
	}
	
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		return contains(s, root);
	}
	
	private boolean contains(String s, BST_Node node) {
		
		if(node == null) return false;
		if(node.data.contentEquals(s)) return true;
		
		contains(s, node.left);
		contains(s, node.right);
		
		return false;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public int height() {
		// TODO Auto-generated method stub
		return height(root);
	}
	
	private int height(BST_Node node) {
		
		if(node == null) return -1;
		
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		
		return Math.max(leftHeight, rightHeight)+1;
		
	}
	
	private boolean goodInsert() {
		size++;
		return true;
	}
	
	private boolean goodDelete() {
		size--;
		return true;
	}

}