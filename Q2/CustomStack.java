package Q2;
//-----------------------------------------------------
// Title: CustomStack Class
// Author: Ayda Nil Özyürek
// Description: This class shows the implementation of a stack in linked list 
// 	            and contains the methods used in the main class.
//------------------------------------------------

import java.util.EmptyStackException;

public class CustomStack {

	private int length;
	private Node top;

	public Node peek() {
		return top;
	}

	public int size() {
		return length;
	}

	public boolean isEmpty() {
		return top == null; // length == 0;
	}

	public int getObject(int index) {
		Node current = top;

		int counter = 0;
		while (counter < index) {
			current = current.getNext();
			counter++;
		}
		return current.getData();
	}

	public void push(int data) {
		Node newNode = new Node(data);

		if (top == null) {
			top = newNode;
			length++;
			return;
		}

		Node current = top;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(newNode);
		length++;
	}

	public boolean contains(int data) {

		Node current = top;
		if (current != null && current.getData() == data) {
			return true;
		}

		boolean isExist = false;
		if (current != null) {
			while (current.getNext() != null) {
				current = current.getNext();
				if (current.getData() == data) {
					isExist = true;
					break;
				}
			}
		}

		return isExist;

	}

	public void clear() {
		if (top != null) {
			Node current = top;
			current.setNext(null);
			top = null;
			length = 0;
		}
	}

	public int pop() {

		if (isEmpty()) {
			throw new EmptyStackException();
		}

		Node current = top;
		top = current.getNext();
		length--;
		return current.getData();
	}

	public CustomStack addAll(CustomStack list) {
		CustomStack myStack = new CustomStack();
		length = 0;
		for (int i = 0; i < list.size(); i++) {
			myStack.push(list.getObject(i));
			length++;
		}

		return myStack;

	}
}
