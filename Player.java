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
    public int nums(){
	int ret=0;
	for(int x=0;x<hand.length;x++){
	    ret+=parseInt(hand.get(x).number);
	}
	return ret;
    }
    public boolean getStay(){
	return stay;
    }
}
