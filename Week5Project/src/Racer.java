import java.util.ArrayList;
import java.util.Collections;

public class Racer {
    private ArrayList<Integer> race;   // The cards in the hand.

    public Racer() {//Temporal Cohesion- Initializing variables
        race = new ArrayList<Integer>();
    }
    //Functional-clear and defined method
    public void clear() {
        race.clear();
    }
    //Functional-clear and defined method
    public void add(int number) {
        race.add(number);
    }
    public void remove(int index) { //Functional-clear and defined method
        if (index < 0 || index >= race.size())
            throw new IllegalArgumentException("Position does not exist: "
                    + index);
        race.remove(index);
    }

    public int getCount() { //Logical-Action performed by calling input
        return race.size();
    }
    
    public int getTop() {//Logical-Action performed by calling input
    	return race.get(getCount() - 1);
    }

    public int getIndex(int index) {//Logical-Action performed by calling input
        if (index < 0 || index >= race.size())
            throw new IllegalArgumentException("Position does not exist: " + index);
        return race.get(index);
    }
    
    public String displayRacer(Racer group) {//Logical-Action performed by calling input
		String str = "";
    	for(int i = 0; i < group.getCount(); i++){
			str += group.getIndex(i) + "\t";
		}
    	return str;
    }

	public boolean isEmpty() {//Functional-clear and defined method
		return race.size() == 0;
	}
}//end of class
