import java.util.ArrayList;
public class Player{
    public int money;
    public String name;
    public ArrayList<Card> hand;
    public boolean stay;
    public boolean fold;
    public final String CARDS="234567891JQKA";
    public Player(String name1){
	name=name1;
	money=500;
	hand =new ArrayList<Card>();
	stay=false;
    }
    public String toString(){
	return name/*+"'s  money is $"+money*/+"\nCards:"+hand;
    }
    public void deal(Card a,Card b){
	hand.add(a);
	hand.add(b);
    }
    public void add(Card a){
	hand.add(a);
    }
    public void bet(int cash){
	money+=cash;
    }
    public void newHand(){
	for(int x=hand.size()-1;x>-1;x--){
	    hand.remove(x);
	}
    }
    public void sort(){
	for(int y=0;y<hand.size()-1;y++){//Goes through data n-1 times 
	    for(int x=hand.size()-1;x>0;x--){//Goes through data
		if(CARDS.indexOf(hand.get(x).p)>CARDS.indexOf(hand.get(x-1).p)){//Compares values to see if sorted
		    Card a=hand.get(x);
		    Card b=hand.get(x-1);
		    hand.set(x,b);//Switches values of x and x-1 if they need to be switched
		    hand.set(x-1,a);
		}
	    }
	    //System.out.println(data);
	}
    }//end bubbleSortV -- O(n^2)

    public int nums(){
	int ret=0;
	for(int x=0;x<hand.size();x++){
	    try{
		ret+=Integer.parseInt(hand.get(x).number);
	    }
	    catch(Exception E){
		String num = hand.get(x).number;
		if (num.equals("J")||num.equals("Q")||num.equals("K")){
		    ret+=10;
		}
		else{
		    ret+=11;
		}
	    }
	}
	return ret;
    }
    public boolean getStay(){
	return stay;
    }
    public void setStay(boolean s){
	stay=s;
    }
    public void fold(boolean b){
	fold=b;
    }
    public boolean getFold(){
	return fold;
    }
}
