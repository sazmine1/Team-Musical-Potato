import java.util.ArrayList;

public class Blackjack extends Casino{
    public Blackjack() {
	super();
	deal();
    }
    public String toString(){
        String ret="";
	for(int x=0;x<deck.size();x++){
	    ret+=deck.get(x)+" ";
	}
	return ret;
    }
    public void deal(){
	user.deal(deck.get(0),deck.get(3));
	comp1.deal(deck.get(1),deck.get(4));
	comp2.deal(deck.get(2),deck.get(5));
	place=6;
    }
    public void hit(Player p){
	p.add(deck.get(place));
	place+=1;
    }
    public boolean turn(Player o){
	return (!(o.nums()>21||user.getStay()));
    }
    public void playTurn(){
	if (turn(user)){
	    System.out.println("Hit or Stay?");
	    boolean choice;
	}	    
    }
    public static void main(String[] args){
	Blackjack bo = new Blackjack();
	System.out.println(bo);
	System.out.println(bo.user);
	System.out.println(bo.comp1);
	System.out.println(bo.comp2);
    }
}
