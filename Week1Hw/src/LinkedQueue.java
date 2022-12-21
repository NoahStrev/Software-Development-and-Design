import java.util.ArrayList;

public class LinkedQueue<T> {
	private Node<T> front, rear;
	private int size;
	
	public LinkedQueue() {
		front = rear = null;
		size = 0;
	}
	
	
	public void enqueue(T item) {
		if(item == null) return;
		Node<T> newNode = new Node<T>(item);
		if(size == 0) {
			rear = front = newNode;
		}
		else {
			rear.setLink(newNode);
		}
		rear = newNode;
		size++;
	}
	
	public T dequeue() {
		T item = front.getInfo();
		front = front.getLink();
		size--;
		if(size == 0) {
			front = rear = null;
		}
		return item;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void removeSecond() {
		if(size == 0) return;
		T temp = front.getInfo();
		front = front.getLink();
		size--;
		front.setInfo(temp);
	}
	
	public String process(int number) {
		String str = "";
		if(number == 1) {
			str = "needs TV Service";
		}
		else if(number == 2) {
			str = "needs Internet Service";
		}
		else if(number == 3) {
			str = "needs Cell Phone Service";
		}
		else if(number == 4) {
			str = "needs Steam Service";
		}
		else if(number == 5) {
			str = "needs some unknown Service";
		}
		
		return str;
	}
	
	public String toString() {
		String str = "";
		String temp = "", temp2 = "";
		Node<T> cursor = front;
		int count = 0;
		int service;
		while(count < 2) {
			if((count % 2) == 1) {
				temp2 = cursor.getInfo().toString();
				service = Integer.valueOf(temp2);
				str += process(service) + "\n";
			}
			else{
				temp = cursor.getInfo().toString() + " ";
				str += temp;
			}
			
			cursor = cursor.getLink();
			count++;
		}
		str += ">Info about " + temp + " is removed";
		return str;
	}
}

class Node<T>{
	private T info;
	private Node<T> link;
	
	public Node(T inf){
		this.info = inf;
		link = null;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public Node<T> getLink() {
		return link;
	}

	public void setLink(Node<T> link) {
		this.link = link;
	}
}