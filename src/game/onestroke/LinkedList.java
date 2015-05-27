package game.onestroke;

public class LinkedList<T> {
	
	private Node<T> head;
	
	public LinkedList<T> add(T e){
		if(head == null){
			head = new Node<T>(e);
		}else{
			getLastNode().next = new Node<T>(e);
		}
		return this;
	}
	/**
	 * 将两个链表合二为一
	 * @param another
	 * @return
	 */
	public LinkedList<T> add(LinkedList<T> another){
		if(another == null || another.head == null){
			return this;
		}
		if(head == null){
			head = another.head;
			return this;
		}
		if(head.e.equals(another.head.e)){
			Node<T> oldhead = head;
			head = another.head;
			another.getLastNode().next = oldhead;
		}
		Node<T> pre = head;
		Node<T> temp = head.next;
		while(temp != null){
			if(temp.e.equals(another.head.e)){
				Node<T> next = temp.next;
				temp = another.head;
				pre.next = temp;
				another.getLastNode().next = next;
				break;
			}
			pre = temp;
			temp = temp.next;
		}
		return this;
	}
	
	/**
	 * 获取最后的节点
	 * @return
	 */
	public Node<T> getLastNode() {
		if(head == null){
			return null;
		}
		Node<T> temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		return temp;
	}
	/**
	 * 获取最后一个元素的值
	 * @return
	 */
	public T getLastItem() {
		if(head == null){
			return null;
		}
		return getLastNode().e;
	}
	/**
	 * 获取最后的节点
	 * @return
	 */
	public Node<T> getFristNode() {
		if(head == null){
			return null;
		}
		return head;
	}
	
	/**
	 * 获取最后一个元素的值
	 * @return
	 */
	public T getFristItem() {
		if(head == null){
			return null;
		}
		return getFristNode().e;
	}
	static class Node<T>{
		T e;
		Node<T> next;
		public Node(T e) {
			super();
			this.e = e;
			this.next = null;
		}
		@Override
		public String toString() {
			return e + ((next == null) ? "" : ("--->" + next));
		}
		
		
	}
	public int size() {
		int count = 0;
		if(head == null){
			return 0;
		}
		Node<T> temp = head;
		while (temp != null) {
			count ++;
			temp = temp.next;
		}
		return count;
	}
	@Override
	public String toString() {
		return head.toString();
	}
	
	
}
