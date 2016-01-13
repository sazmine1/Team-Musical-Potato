import java.util.ArrayList;

public class Blackjack {
    private ArrayList<Card> deck;
    private final String CARDS="234567891JQKA";
    public Player user;

    public Blackjack() {
	deck=deck();
	shuffle();
	user=new Player("Bob");
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
	    ret+=deck.get(x).get()+" ";
	}
	return ret;
    }
    public void deal(){
	user.add(
    public void playTurn(){
    }
    public static void main(String[] args){
	Blackjack bo = new Blackjack();
	System.out.println(bo);
    }
}
