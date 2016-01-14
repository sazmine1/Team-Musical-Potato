import java.util.ArrayList;

public class Blackjack {
    private ArrayList<Card> deck;
    private final String CARDS="234567891JQKA";
    public Player user;
    public Player comp1;
    public Player comp2;
    public int place;
    public Blackjack() {
	deck=deck();
	shuffle();
	user=new Player("Bob");
	comp1=new Player("Mo");
	comp2=new Player("Mike");
	deal();
    }
    public ArrayList deck(){
	ArrayList ret= new ArrayList<Card>();
	for(int x=0;x<4;x++){
	    for(int y=0;y<CARDS.length();y++){
		ret.add(new Card(x,CARDS.substring(y,y+1)));
	    }
	}
	return ret;
    }
    public void shuffle(){
	for(int x=0;x<52;x++){
	    deck.set(x,(deck.set((int)(Math.random()*52),deck.get(x))));
	}
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
