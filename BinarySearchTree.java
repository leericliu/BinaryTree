
public class BinarySearchTree {
	
	private BinaryNode root; // sets root node
	
	public BinarySearchTree(){
		root = new BinaryNode(); // intializes for empty root
	}
	
	public BinaryNode getcall(BinaryNode current, Key k) {
		if(current.isLeaf()) return current;
		else {
			if (k.compareTo(current.getContent().getKey()) == 0) {
					
					return current;
			}
			else if (current.getContent().getKey().compareTo(k) < 0)
				return getcall(current.getLeft(), k);
			else 
				return getcall(current.getRight(), k);
		}
		
	}
	
	public Boolean remove(BinaryNode current, Key k) {
		BinaryNode position = getcall(current,k);
		
		if (position.isLeaf()) return false; // if the position is a leaf node then return false
		else {
			if(position.getLeft().isLeaf() || position.getRight().isLeaf()) { // if position node has left or right child 
				BinaryNode aboveParent = position.getParent(); // sets aboveParent as the parent of position
				BinaryNode secondChild; // the other child of the position node
				if(position.getLeft().isLeaf()) // if left child of position is a leaf then set the secondchild as the other leaf which is the right child
					secondChild = position.getRight();
				else 
					secondChild = position.getLeft(); // else set secondChild (or other child) as the Left child
				if(position == root) { // if position node is equal to the root 
					secondChild.setParent(null); 
					root = secondChild; // sets secondChild the new root of the entire tree
				
			}
				else {
					if (aboveParent.getRight()==position) aboveParent.setRight(secondChild); //makes the second child a child of the aboveParent also replacing the position or orginal parent
					else aboveParent.setLeft(secondChild);
				}
				return true;
				}
			
			else {
				BinaryNode small = smallest(position.getRight());// give small the smallest on the right
				position.setContent(small.getContent()); // copy record stored in small into position
				return remove(small,small.getContent().getKey()); //gets rid of the duplicate
						
			}
			
		}
		
		
			
		
	}
	
	public BinaryNode largest(BinaryNode current) { // finds the largest key in the tree
		if (current.isLeaf()) return null;
		else {
			BinaryNode position = current;
			while(!position.getRight().isLeaf()) {
				position  = position.getRight();
			}
			return position;
		}
		
	}
	
	
	public BinaryNode smallest(BinaryNode current) {//finds the smallest key in the tree
		if (current.isLeaf()) return null;
		else {
			BinaryNode position = current;
			while(!position.getLeft().isLeaf()) {
				position  = position.getLeft();
			}
			return position;
		}
		
	}
	
	public BinaryNode successor(BinaryNode current, Key k) {
		BinaryNode parent_node; 
		BinaryNode position = null; 
		
		if (current.isLeaf()) {
			return null;
		} else {
			 position = getcall(current, k);
			if (!position.isLeaf() && !position.getRight().isLeaf()) 
					return smallest(position.getRight());
				
			 else {
				parent_node = position.getParent();
				while (position != current && parent_node.getRight() == position) {
					position = parent_node;
					parent_node = parent_node.getParent();
				}
				if (position == current) 
					return null;
				else
					return parent_node;
			}
		}
		
	}
	
	public BinaryNode predecessor (BinaryNode current, Key k) {
		BinaryNode parent_node; 
		BinaryNode position = null; 
		
		if (current.isLeaf()) 
			return null;
		else {
			 position = getcall(current, k);
			if (!position.isLeaf() && !position.getLeft().isLeaf()) 
					return largest(position.getLeft());
				
		    else {
				parent_node = position.getParent();
				while (position != current && parent_node.getLeft() == position) {
					position = parent_node;
					parent_node = parent_node.getParent();
				}
				if (position == current) 
					return null;
				else
					return parent_node;
			}
		}
}}
