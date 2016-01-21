import java.util.ArrayList;
import cs1.Keyboard;

public class TexasHoldEm extends Casino{

    public ArrayList<Card> community;

    public TexasHoldEm(){
	super();
	deal();
	community = new ArrayList<Card>();

    public ArrayList<Card> community;
    
    public TexasHoldEm(){
	super();
	deal();
	community = new ArrayList<Card> ();
    }

    public void deal() {
	user.deal(deck.get(0),deck.get(3));
	comp1.deal(deck.get(1),deck.get(4));
	comp2.deal(deck.get(2),deck.get(5));
	place=6;
    }

    public void bet(Player a) {
	System.out.println("Place your bet:");
	int b = 0;
	try {
	    b = Keyboard.readInt();
	    a.money -= b;
	    pot += b;
	    if (b > a.money || b < 0 || b < pot) {
		System.out.println("Be real.");
		bet(a);
	    }
	}
	catch (Exception E) {
	    System.out.println("Only cash bets accepted.");
	}
    }

    public void call(Player a, Player b) {
	a.bet = b.bet;
    }

    public void play() {
    public void play(){
	System.out.println(user.name + "'s money:\t" + user.money);
	System.out.println(comp1.name + "'s money:\t" + comp1.money);
	System.out.println(comp2.name + "'s money:\t" + comp2.money);
	System.out.println("Initial betting time");
	bet(user);
	System.out.println("Size of pot:\t" + pot);
       	System.out.println(user.name + "'s money:\t" + user.money);
	System.out.println(user.name + "'s money:\t" + user.money);
	System.out.println(comp1.name + "'s money:\t" + comp1.money);
	System.out.println(comp2.name + "'s money:\t" + comp2.money);
	if (user.bet <= 100) {
	    call(comp1, user);
	    call(comp2, user);
	}
    }

    public static void main(String [] args) {
	TexasHoldEm a = new TexasHoldEm();
	//a.bet();
	//System.out.println(a.user.money);
	a.play();
    }
}
