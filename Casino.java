
import java.util.ArrayList;
import cs1.Keyboard;
public class Casino{
    public ArrayList<Card> deck;
    public final String CARDS="234567891JQKA";
    public Player user;
    public Player comp1;
    public Player comp2;
    public int place;
    public int pot;
    public Casino(){
	deck=deck();
	shuffle();
	user=new Player("Bob");
	comp1=new Player("Mo");
	comp2=new Player("Mike");
	pot = 0;
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
    
    public static void main(String [] args) {
    	System.out.println("Welcome to the Casino!");
    	String pick="p";
    	while(!(pick.equals("q"))){
	    System.out.println("Please choose a game: BlackJack or Texas Hold'Em (b or t) or enter q to exit");
	    while((!(pick.equals("t")))&&(!(pick.equals("b")))&&(!(pick.equals("q")))){
		pick = Keyboard.readString();
	    }	
	    if (pick.equals("b")) {
    		System.out.println("You chose Blackjack");
    		Blackjack bo=new Blackjack();
    		bo.play();
	    }
	    if (pick.equals("t")){
		System.out.println("You chose Texas HoldEm");
		TexasHoldEm blip=new TexasHoldEm();
		blip.play();
	    }
    	}
    }
}
