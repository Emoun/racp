package emoun.racpEditor;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.swing.JPanel;

import emoun.racpEditor.listeners.TextFieldMouseListener;

public class DoublyLinkedPanel<T extends DoublyLinkedPanel<T>> extends JPanel{
	
//Fields
	private T previous, next;
	
//Constructors
	
	public DoublyLinkedPanel(){}

//methods	
	
	public T getPrevious() {
		return previous;
	}

	public T getNext() {
		return next;
	}

	public void setNext(T next) {
		this.next = next;
	}

	public void setPrevious(T previous) {
		this.previous = previous;
	}	
	
	public void unlinkNext(){
		if(next != null){
			next.setPrevious(null);
			next = null;
		}
	}
	
	public boolean last(){
		return next == null;
	}
	
	public boolean first(){
		return previous == null;
	}

//Static methods
	
	public static <V extends DoublyLinkedPanel<V>> void link(V first, V second){
		if(first != null){
			first.setNext(second);
		}
		if(second != null){
			second.setPrevious(first);
		}
	}
	
	public static <V extends DoublyLinkedPanel<V>> void createList(int count, Function<Integer, V> constructor, BiConsumer<Integer,V> forEach){
		V prevField = null;
		for(int i = 0; i<count; i++){
			V newField = constructor.apply(i);
			forEach.accept(i, newField);
			DoublyLinkedPanel.link(prevField, newField);
			prevField = newField;
		}
	}

	public static <V extends DoublyLinkedPanel<V>> void forAll(Consumer<V> forEach, V start){
		//For the given
		forEach.accept(start);
		
		//For all previous
		V prev = start;
		while(!prev.first()){
			prev = prev.getPrevious();
			forEach.accept(prev);
		}
		
		//For all next
		V next = start;
		while(!next.last()){
			next = next.getNext();
			forEach.accept(next);
		}
	}
}
