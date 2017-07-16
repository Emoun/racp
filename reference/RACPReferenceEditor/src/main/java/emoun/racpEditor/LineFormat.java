package emoun.racpEditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineFormat {

//Fields
	private ArrayList<Integer> tabTo = new ArrayList<Integer>();
	
	
//Constructors
	
//methods
		
	@Override
	public boolean equals(Object o){
		if(o instanceof LineFormat){
			LineFormat other = (LineFormat) o;
			
			if(sameGroup(this, other)){
				
				for(int i = 0; i < tabTo.size(); i++){
					if(this.tabTo.get(i) != other.tabTo.get(i)){
						return false;
					}
				}
				return true;				
			}
		}
		return false;
	}
	
	public LineFormat duplicate(){
		LineFormat d = new LineFormat();
		d.tabTo.addAll(this.tabTo);
		return d;
	}
	
	public LineFormat add(int column){
		LineFormat d = duplicate();
		d.tabTo.add(column);
		return d;
	}
	
	public LineFormat add(int index, int column){
		LineFormat d = duplicate();
		d.tabTo.add(index, column);
		return d;
	}
	
	public LineFormat replace(int index, int column){
		LineFormat d = duplicate();
		
		d.tabTo.remove(index);
		d.tabTo.add(index, column);
		return d;
	}
	
//Accessors
	
	public List<Integer> getTabTo(){
		return Collections.unmodifiableList(tabTo);
	}
	
//Static methods
	
	public static boolean sameGroup(LineFormat f1, LineFormat f2){
		return f1.tabTo.size() == f2.tabTo.size();
	}
	
	/**
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static LineFormat groupFormat(LineFormat f1, LineFormat f2){
		if(! sameGroup(f1, f2)){
			return f1;
		}
		
		LineFormat format = new LineFormat();
		if(f1.getTabTo().size() != 0){
			format = format.add(Math.max(f1.getTabTo().get(0), f2.getTabTo().get(0)));
			
			for(int i = 1; i< f1.getTabTo().size(); i++){
				int f1Space = f1.getTabTo().get(i)-f1.getTabTo().get(i-1);
				int f2Space = f2.getTabTo().get(i)-f2.getTabTo().get(i-1);
				format = format.add(format.getTabTo().get(i-1) + Math.max(f1Space, f2Space));
			}
		}
		return format;
	}
	
}


















