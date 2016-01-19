import cs1.Keyboard;

public class TexasHoldEm extends Casino{
    public TexasHoldEm(){
	super();
	deal();
    }

    public void deal() {
	user.deal(deck.get(0),deck.get(3));
	comp1.deal(deck.get(1),deck.get(4));
	comp2.deal(deck.get(2),deck.get(5));
	place=6;
    }

    public void bet() {
	System.out.println("Place your bet:");
	int b = 0;
	try {
	    b = Keyboard.readInt();
	    user.money -= b;
	    pot += b;
	    if (b > user.money || b < 0 || b < pot) {
		System.out.println("Be real.");
		bet();
	    }
	}
	catch (Exception e) {
	    System.out.println("Only cash bets accepted.");
	}
    }

    public void playTurn() {

    public static void main(String [] args) {
	TexasHoldEm a = new TexasHoldEm();
	a.bet();
	System.out.println(a.user.money);
    }
}
