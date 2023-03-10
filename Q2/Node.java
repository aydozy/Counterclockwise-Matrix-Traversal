package Q2;
//-----------------------------------------------------
// Title: Node Class
// Author: Ayda Nil Özyürek
// Description: This class holds the data which we will use
//------------------------------------------------

public class Node {
	private int data;
	private Node next;

	public Node() {

	}

	public Node(int data) {
		this.setData(data);
		this.setNext(null);
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
