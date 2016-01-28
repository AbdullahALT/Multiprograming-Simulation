

public class Node<Type> {
	public Type data;
	public int priority;
	public Node<Type> next;
	public Node<Type> prev;

	public Node() {
		data = null;
		prev = next = null;
	}

	public Node(Type val, int priority) {
		data = val;
		this.priority = priority;
		next = prev = null;
	}
	
	

}
