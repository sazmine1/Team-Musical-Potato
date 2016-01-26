import java.util.ArrayList;
import cs1.Keyboard;

public class Blackjack extends Casino{
    public Blackjack() {
	super();
	//deal();
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
	return (!(o.nums()>21||o.getStay()||o.getFold()));
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
	String choice="q";
	while((!(choice.equals("b")))&&(!(choice.equals("f")))){
	    System.out.println("Bet or fold?(b or f)");
	    choice=Keyboard.readString();
	}
	if(choice.equals("f")){
	    user.fold(true);
	}
	int betu=0;
	int bet1=0;
	int bet2=0;
	int high=-1;
	if(!(user.getFold())){
	    while (betu<1||betu>user.money){
		System.out.println("User place your bet from 1 to "+user.money);
		betu = Keyboard.readInt();
		high=betu;
	    }
	}
	if(comp1.money<high){
	    comp1.fold(true);
	    System.out.println(comp1.name+" folded");
	    bet1=high;
	}
	else{
	    if(comp1.nums()>18){
		if(user.getFold()){
		    bet1=comp1.money/2;
		    high=bet1;
		}
		else if(high<200&&comp1.money>200){
		    bet1=200;
		    high=200;
		}
		else{
		    bet1=betu;
		}
	    }
	    else{
		if(user.fold){
		    bet1=comp1.money/5;
		    high=bet1;
		}
		if(betu>300){
		    comp1.fold(true);
		    bet1=high;
		    System.out.println(comp1.name+" folded");
		}
		else{
		    bet1=betu;
		}
	    }
	}
	if(comp2.money<high){
	    comp2.fold(true);
	    System.out.println(comp2.name+" folded");
	    bet2=high;
	}
	else{
	    if(comp2.nums()>17){
		if(high<200||comp2.money<200){
		    bet2=200;
		    bet2=high;
		}
		else{
		    bet2=high;
		}
	    }
	    else{
		if(betu>300){
		comp2.fold(true);
		System.out.println(comp2.name+" folded");
		bet2=high;
		}
		else{
		    bet2=betu;
		}
	    }	    
	}
	if(user.fold){
	    betu=high;
	}
	while((betu!=high)||(bet1!=high)||(bet2!=high)){
	    if(!(user.getFold())){
		System.out.println("Your bet was "+betu);
	    }
	    if(!(comp1.getFold())){
		System.out.println(comp1.name+"'s bet was "+bet1);
	    }
	    if(!(comp2.getFold())){
		System.out.println(comp2.name+"'s bet was "+bet2);
	    }
	    if(!(user.getFold())){
		choice="q";
		while((!(choice.equals("b")))&&(!(choice.equals("f")))){
		    System.out.println("Bet or fold?(b or f)");
		    choice=Keyboard.readString();
		}
		if(choice.equals("f")){
		    user.fold(true);
		}
	    }
	    if(!(user.getFold())){		
		while (betu<high||betu>user.money){
		    System.out.println("place a new bet of at least "+high);
		    betu = Keyboard.readInt();
		    high=betu;
		}
	    }
	    else{
		betu=high;
	    }
	    if(!(comp1.getFold())&&comp1.money>=high){
		if(comp1.nums()>18){
		    if(high<200&&comp1.money>=200){
			bet1=200;
			high=200;
		    }
		    else{
			bet1=high;
		    }
		}
		else{
		    if(high>300){
			comp1.fold(true);
			System.out.println(comp1.name+" folded");
			bet1=high;
		    }
		    else{
			bet1=high;
		    }
		}
	    }
	    if(!(comp2.getFold())&&comp2.money>=high){
		if(comp2.nums()>17){
		    if(high<200&&comp2.money>=200){
		    bet2=200;
		    high=200;
		    }
		    else{
			bet2=high;
		    }
		}
		else{
		    if(betu>300){
			comp2.fold(true);
			System.out.println(comp2.name+" folded");
			bet2=high;
		    }
		    else{
			bet2=high;
		    }
		}
	    }
	}
	int x=0;
	if(!(user.getFold())){
	    user.bet(-1*betu);
	    x+=1;
	}
	if(!(comp1.getFold())){
	    comp1.bet(-1*bet1);
	    x+=1;
	}
	if(!(comp2.getFold())){
	    comp2.bet(-1*bet2);
	    x+=1;
	}
	pot=x*high;
	if(!(user.getFold())){
	    System.out.println("Your bet was "+betu);
	}
	if(!(comp1.getFold())){
	    System.out.println(comp1.name+"'s bet was "+bet1);
	}
	if(!(comp2.getFold())){
	    System.out.println(comp2.name+"'s bet was "+bet2);
	}
    }
    public int playTurn(){
	user.setStay(false);
	comp1.setStay(false);
	comp2.setStay(false);
	user.fold(false);
	comp1.fold(false);
	comp2.fold(false);
	shuffle();
	deal();
	bet();
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
	if(!(user.getFold())){
	    System.out.println(user+" Total:"+a);
	}
	if(!(comp1.getFold())){
	    System.out.println(comp1+" Total:"+b);
	}
	if(!(comp2.getFold())){
	    System.out.println(comp2+ " Total:"+ c);
	}
	int[] stuff = new int[3];
	int[] things = new int[3];
	if(a<22&&(!(user.getFold()))){
	    stuff[0]=a;
	}
	else if(!user.getFold()){
	    things[0]=a;
	}
	if(b<22&&(!(comp1.getFold()))){
	    stuff[1]=b;
	}
	else if((!(comp1.getFold()))){
	    things[1]=b;
	}
	if(c<22&&(!(comp2.getFold()))){
	    stuff[2]=c;
	}
	else if((!(comp2.getFold()))){
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
	    System.out.println("You win! You get $"+pot);
	    user.bet(pot);
	}
	else if(winner==1){
	    System.out.println(comp1.name+" wins! He gets $"+pot);
	    comp1.bet(pot);
	}
	else if(winner==2){
	    System.out.println(comp2.name+" wins! He gets $"+pot);
	    comp2.bet(pot);
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
	System.out.println("Your money: $"+user.money);
	System.out.println(comp1.name+"'s money: $"+comp1.money);
	System.out.println(comp2.name+"'s money: $"+comp2.money);
	String lup="t";
	if(user.money==0){
	    System.out.println("You are bankrupt!");
	    return 1;
	}
	if(comp1.money==0&&comp2.money==0){
	    System.out.println("You win!!!");
	    return 1;
	}
	while((!(lup.equals("p")))&&(!(lup.equals("q")))){
	    System.out.println("Play again or quit(p or q)");
	    lup=Keyboard.readString();
	}
	if(lup.equals("q")){
	    return 1;
	}
	return 2;
    }
    public void play(){
	int b=0;
	while(b!=1){
	    b=playTurn();
	    user.newHand();
	    comp1.newHand();
	    comp2.newHand();
	}
	System.out.println("Thanks for playing");
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
