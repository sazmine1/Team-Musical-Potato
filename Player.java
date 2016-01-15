import java.util.ArrayList;
public class Player{
    public int money;
    public String name;
    public ArrayList<Card> hand;
    public boolean stay;
    public Player(String name1){
	name=name1;
	money=500;
	hand =new ArrayList<Card>();
	stay=false;
    }
    public String toString(){
	return name+"'s  money is $"+money+"\nCards:"+hand;
    }
    public void deal(Card a,Card b){
	hand.add(a);
	hand.add(b);
    }
    public void add(Card a){
	hand.add(a);
    }
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
}
