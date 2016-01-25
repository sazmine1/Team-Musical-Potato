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
	return (!(o.nums()>21||o.getStay()));
    }
    public int maxIndex(int[] a){
	int ret=0;
	for(int x=0;x<a.length;x++){
	    if(a[x]>a[ret]){
		ret=x;
	    }
	}
	return ret;
    }
    public int minIndex(int[] a){
	int ret=0;
	for(int x=0;x<a.length;x++){
	    if(a[x]<a[ret]&&a[x]!=0){
		ret=x;
	    }
	}
	return ret;
    }
    public void bet(){
	System.out.println("User place your bet from 1 to "+user.money);
	int betu=0;
	int bet1=0;
	int bet2=0;
	int high=0;
	while (betu<1||betu>500){
	    betu = Keyboard.readInt();
	    high=betu;
	}
	if(comp1.nums()>18){
	    if(betu>200){
		bet1=betu;
	    }
	    else{
		bet1=200;
	    }
	}
	else{
	    if(betu>300){
		comp1.fold(true);
	    }
	    else{
		bet1=betu;
	    }
	}
	if(comp2.nums()>18){
	    if(betu>200){
		bet2=betu;
	    }
	    else{
		bet2=200;
	    }
	}
	else{
	    if(betu>300){
		comp2.fold(true);
	    }
	    else{
		bet2=betu;
	    }
	}  
	
    }
    public void play(){
	if (turn(user)){
	    while(!(user.getStay())){
		System.out.println(user);
		System.out.println("Hit or Stay(h or s)?");
		String choice=Keyboard.readString();
		if (choice.equals("h")){
		    //System.out.println(place);
		    //System.out.println(deck.get(place));
		    hit(user);
		}
		else if (choice.equals("s")){
		    user.setStay(true);
		    break;
		}
	    }
	}
	//System.out.println(turn(comp1));
	//System.out.println(turn(comp2));
	if (turn(comp1)){
	    while(comp1.nums()<16){
		hit(comp1);
	    }
	    comp1.setStay(true);
	}
	if (turn(comp2)){
	    while(comp2.nums()<17){
		hit(comp2);
	    }
	    comp2.setStay(true);
	}
	int a=user.nums();
	int b=comp1.nums();
	int c=comp2.nums();
	System.out.println(user+" Total:"+a);
	System.out.println(comp1+" Total:"+b);
        System.out.println(comp2+ " Total:"+ c);
	int[] stuff = new int[3];
	int[] things = new int[3];
	if(a<22){
	    stuff[0]=a;
	}
	else{
	    things[0]=a;
	}
	if(b<22){
	    stuff[1]=b;
	}
	else{
	    things[1]=b;
	}
	if(c<22){
	    stuff[2]=c;
	}
	else{
	    things[2]=c;
	}
	int winner;
	//System.out.println(maxIndex(stuff));
	if(maxIndex(stuff)>0){
	    winner=maxIndex(stuff);
	    //System.out.println("P");
	}
	else{
	    winner=minIndex(things);
	    //System.out.println("O");
	}
	//System.out.println(winner);
	if(winner==0){
	    System.out.println("You win");
	}
	else if(winner==1){
	    System.out.println(comp1.name+" wins!");
	}
	else if(winner==2){
	    System.out.println(comp2.name+" wins!");
	}
	/*
	  if(a==b&&b==c&&a<22){
	  System.out.println("Tie! You win!");
	  }
	  if(a<22){
	  if(b>21&&c>21){
	  System.out.println("You win");
	  }
	  else if(
	  }
	  else if(b<22&&b>c&&b>a){
	  System.out.println(comp1.name+" wins!");
	  }
	  else if(c<22&&c>b&&c>a){
	  System.out.println(comp2.name+" wins!");
	  }
	  else if(a>22&&b>22&&c>22) {
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
	*/
    }


    public static void main(String[] args){
	Blackjack bo = new Blackjack();
	//System.out.println(bo);
	//System.out.println(bo.user);
	//System.out.println(bo.comp1);
	//System.out.println(bo.comp2);
	bo.play();
    }
}
