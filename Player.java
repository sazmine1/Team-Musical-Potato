import java.utils.ArrayList;
public class Player(){
    public int money;
    public String name;
    public ArrayList<Card> hand;
    public Player(String name1){
	name=name1;
	money=500;
    }
    public String toString(){
	return name+"'s  money is $"+money;
    }
    public void deal(Card a,Card b){
	hand.add(a);
	hand.add(b);
    }
}
