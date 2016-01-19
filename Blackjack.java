import java.util.ArrayList;
import cs1.Keyboard;

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
	    while(!(user.getStay())){
		System.out.println(user);
		System.out.println("Hit or Stay(h or s)?");
		String choice=Keyboard.readString();
		if (choice.equals("h")){
		    System.out.println(place);
		    System.out.println(deck.get(place));
		    hit(user);
		    break;
		}
		else if (choice.equals("s")){
		    user.setStay(true);
		    break;
		}
	    }
	}
	if (turn(comp1)){
	    while(comp1.nums()<16){
		hit(comp1);
	    }
	    comp1.setStay(true);
	}
	if (turn(comp2)){
	    while(comp2.nums()<17){
		hit(comp1);
	    }
	    comp1.setStay(true);
	}
	int a=user.nums();
	int b=comp1.nums();
	int c=comp2.nums();
	System.out.println(user+" Total:"+a);
	System.out.println(comp1+" Total:"+b);
        System.out.println(comp2+ "Total:"+ c);
	if(a==b&&b==c&&a<22){
	    System.out.println("Tie! You get the pot!");
	}
	if(a<22&&a>b&&a>c){
	    System.out.println("You win");
	}
	else if(b<22&&b>c&&b>a){
	    System.out.println(comp1.name+" wins!");
	}
	else if(c<22&&c>b&&c>a){
	    System.out.println(comp2.name+" wins!");
	}
	else{
	    System.out.println("You all busted");
	    if(a<b&&a<c){
		System.out.println("You are closest to 21 so you win");
	    }
	    else if(b<a&&b<c){
		System.out.println(comp1.name+" is closest to 21 so he wins");
	    }
	    else{
		System.out.println(comp2.name+" is closest to 21 so he wins");
	    }
	}
    }
    public static void main(String[] args){
	Blackjack bo = new Blackjack();
	//System.out.println(bo);
	//System.out.println(bo.user);
	//System.out.println(bo.comp1);
	//System.out.println(bo.comp2);
	bo.playTurn();
    }
}
