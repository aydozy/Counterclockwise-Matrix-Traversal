package Q1B;
//-----------------------------------------------------
// Title: LinkedList Class
// Author: Ayda Nil Özyürek
// Description: This class helps us to write  method with using Node Class.
//------------------------------------------------
public class LinkedListClass {
	private int length;
	private Node head;

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;

	}

	public int size() {
		return length;
	}

	public void addToEnd(int value) {
		Node newNode = new Node(value);

		if (head == null) {
			head = newNode;
			length++;
			return;
		}

		Node current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(newNode);
		length++;
	}

	public Node getObject(int index) {
		Node current = head;

		int counter = 0;
		while (counter < index) {
			current = current.getNext();
			counter++;
		}
		return current;
	}

	public int indexOf(Node value) {
		Node current = head;
		int counter = 0;
		while (current.getData() != value.getData()) {
			current = current.getNext();
			counter++;
		}
		return counter;
	}

	public void printList() {
		Node current = head;

		while (current != null) {
			System.out.print(current.getData());
			if (current.getNext() != null) {
				System.out.print(", ");
			}
			current = current.getNext();
		}
	}

}
