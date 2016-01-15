
import java.util.ArrayList;

public class Casino{
    public ArrayList<Card> deck;
    public final String CARDS="234567891JQKA";
    public Player user;
    public Player comp1;
    public Player comp2;
    public int place;
    public Casino(){
	deck=deck();
	shuffle();
	user=new Player("Bob");
	comp1=new Player("Mo");
	comp2=new Player("Mike");
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
}
