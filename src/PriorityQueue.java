
public class PriorityQueue<T> {
	private Node<T> head, tail;
	private int length;
	private int totalPriorities;
	
	public PriorityQueue() {
		head = tail = null;
		length = 0;
	}
	
	public boolean full() {
		return false;
	}

	public int length() {
		return length;
	}
	
	public void enqueue(T node, int priority) {
		if(head == null)
			head = tail = new Node<T>(node, priority);
		else{
			//int newNodeHuristic = node.getHuristicValue(huristicType);
			Node<T> newNode = new Node<T>(node, priority);
			Node<T> cur = head;
			while(cur != null){
				if(cur.priority >= priority){
					if(cur != head){
						cur.prev.next = newNode;
						newNode.prev = cur.prev;
					}
					newNode.next = cur;
					cur.prev = newNode;
					if(cur == head)
						head = newNode;
					break;
				}
				cur = cur.next;
			}
			if(cur == null){
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
			}
			
		}
		length++;
		totalPriorities += priority;
	}

	public T serve() {
		T x = head.data;
		totalPriorities -= head.priority;
		head = head.next;
		length--;
		if (length == 0)
			tail = null;
		return x;
	}
	
	public int getTotalPriorities(){
		return totalPriorities;
	}
	
	public boolean isExist(T val){
		Node<T> cur = head;
		while(cur != null){
			if(cur.data.equals(val))
				return true;
			cur = cur.next;
		}
		return false;
	}
	
	
}
