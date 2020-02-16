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
		
		if(contains(s)) return false;
		
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
		
		BST_Node currentNode = root;
		BST_Node parent = root;
		
		boolean isSmallChild = false;
		
		while(currentNode.data != s) {
			parent = currentNode;
			if(s.compareTo(currentNode.data) < 0) {
				isSmallChild = true;
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
				isSmallChild = false;
			}
			if(currentNode == null) return false;
		}
		
		BST_Node deleteNode = currentNode;
		
		if(isLeafNode(deleteNode)) {
			
			if(deleteNode == root) {
				root = null;
			} else if(isSmallChild) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			
		}
		
		else if(deleteNode.right == null) {
			
			if(deleteNode == root) {
				root = deleteNode.left;
			} else if(isSmallChild) {
				parent.left = deleteNode.left;
			} else {
				parent.right = deleteNode.left;
			}
			
		}
		
		else if(deleteNode.left == null) {
			
			if(deleteNode == root) {
				root = deleteNode.right;
			} else if(isSmallChild) {
				parent.left = deleteNode.right;
			} else {
				parent.right = deleteNode.right;
			}
			
		} else {
			
			BST_Node suc = findSuc(deleteNode);
			
			if(deleteNode == root) root = suc;
			else if(isSmallChild) parent.left = suc;
			else parent.right = suc;
			
			suc.left = deleteNode.left;
			
		}
		
		return goodDelete();
	}
	
	private boolean isLeafNode(BST_Node node) {
		return node.left == null && node.right == null;
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
	
	private BST_Node findSuc(BST_Node deleteNode) {
		// TODO Auto-generated method stub
		
		BST_Node sucPar = deleteNode;
		BST_Node suc = deleteNode;
		
		BST_Node cur = deleteNode.right;
		
		while(cur != null) {
			sucPar = suc;
			suc = cur;
			cur = cur.left;
		}
		
		if(suc != deleteNode.right) {
			sucPar.left = suc.right;
			suc.right = deleteNode.right;
		}
		
		return suc;
		
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
		
		int comResult = node.data.compareTo(s);
		
		if(comResult == 0) 
			return true;
		else if(comResult > 0)
			return contains(s, node.left);
		else 
			return contains(s, node.right);
			
		
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