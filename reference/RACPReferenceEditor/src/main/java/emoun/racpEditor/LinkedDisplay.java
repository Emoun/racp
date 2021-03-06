package emoun.racpEditor;

public abstract class LinkedDisplay<T extends LinkedDisplay<T,K>,K> extends DoublyLinkedPanel<T>{
	
//Abstract methods
	public abstract void display(K k);
	
	public abstract K displaying();
	
	public abstract T newDisplay();
	
//Methods
	public void clear(){
		getParent().remove(this);
		getPrevious().unlinkNext();
	}
	
	public void pushAndDisplay(K k){
		if(last()){
			T newDis = newDisplay();
			DoublyLinkedPanel.link((T)this, newDis);
			getParent().add(newDis);
			newDis.display(displaying());
		}else{
			getNext().pushAndDisplay(displaying());
		}
		display(k);
	}
	
	public K pullDisplayAndClearLast(){
		System.out.println("Pull");
		if(last()){
			System.out.println("Last");
			clear();
			return displaying();
		}else{
			K old = displaying();
			K nextDisp = getNext().pullDisplayAndClearLast();
			display(nextDisp);
			System.out.println("" + old + "->" + displaying());
			return old;
		}
	}
}
