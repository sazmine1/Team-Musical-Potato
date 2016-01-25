import java.util.ArrayList;
import cs1.Keyboard;

public class TexasHoldEm extends Casino{

    public ArrayList<Card> community;
    
    public TexasHoldEm(){
	super();
	//deal();
	//user.sort();
	//comp1.sort();
	//comp2.sort();
	community = new ArrayList<Card> ();
    }

    public void deal() {
	int x = 0;
	while(x < 15) {
	    user.add(deck.get(x));
	    comp1.add(deck.get(x + 1));
	    comp2.add(deck.get(x + 2));
	    x += 3;
	}
	place=15;
    }
    public void newCommunity(){
	for(int x=community.size()-1;x>-1;x--){
	    community.remove(x);
	}
    }
    public int longestStreak(Player a){
    	int ret=1;
    	int temp=1;
    	for(int x=0;x<a.hand.size()-1;x++){
	    if(a.hand.get(x).number.equals(a.hand.get(x+1).number)){
		temp+=1;
		if(temp>ret){
		    ret=temp;
		}
	    }
	    else{
		temp=1;
	    }
    	}
    	return ret;
    }
    public boolean hasFour(Player a){
    	return longestStreak(a)==4;
    }
    public boolean hasThree(Player a){
	if(longestStreak(a)==3){
	    return true;
	}
	return false;
    }
    public boolean hasTwo(Player a){
	if(longestStreak(a)==2){
	    return true;
	}
	return false;
    }
    public int high(Player a){
	return CARDS.indexOf(a.hand.get(0).p);
    }
    public boolean fullHouse(Player a){
	if(longestStreak(a)!=3){
	    return false;
	}
	for(int x=0;x<a.hand.size()-1;x++){
	    if(a.hand.get(x).number.equals(a.hand.get(x+1).number)&&(a.hand.get(x).number.equals(a.hand.get(x+2).number))){
		x+=2;
	    }
	    else if(a.hand.get(x).number.equals(a.hand.get(x+1).number)&&(!(a.hand.get(x).number.equals(a.hand.get(x+2).number)))){
		return true;
	    }
	}
	return false;
    }
    public boolean hasFlush(Player a) {
	for (int x = 0; x < a.hand.size() - 1; x++) {
	    if (a.hand.get(x).suit != a.hand.get(x + 1).suit) {
		return false;
	    }
	}
	return true;
    }
	
    public boolean hasStraight(Player a) {
    	for(int x=0;x<a.hand.size()-1;x++){
	    if(CARDS.indexOf(a.hand.get(x).p)!=CARDS.indexOf(a.hand.get(x).p)+1){
		return false;
	    }
    	}
    	return true;
    }
    public boolean straightFlush(Player a){
    	return hasStraight(a)&&hasFlush(a);
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
	//System.out.println(user.getFold());
	int betu=-1;
	int bet1=0;
	int bet2=0;
	int high=-1;
	if(!(user.getFold())){
	    while (betu<0||betu>user.money){
		System.out.println("User place your bet from 0 to "+user.money);
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
	    if(hasTwo(comp1) || hasThree(comp1) || hasFlush(comp1) || hasStraight(comp1) || fullHouse(comp1) || hasFour(comp1) || straightFlush(comp1)){
		if(user.getFold()){
		    bet1=comp1.money/5;
		    high=bet1;
		}
		else if(high<50&&comp1.money>50){
		    bet1=50;
		    high=50;
		}
		else{
		    bet1=betu;
		}
	    }
	    else{
		if(user.fold){
		    bet1=comp1.money/10;
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
	    if(hasTwo(comp2) || hasThree(comp2) || hasFlush(comp2) || hasStraight(comp2) || fullHouse(comp2) || hasFour(comp2) || straightFlush(comp2)){
		if(high<50||comp2.money<50){
		    bet2=50;
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
	if(user.getFold()){
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
		if(hasTwo(comp1) || hasThree(comp1) || hasFlush(comp1) || hasStraight(comp1) || fullHouse(comp1) || hasFour(comp1) || straightFlush(comp1)){
		    if(high<50&&comp1.money>=50){
			bet1=50;
			high=50;
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
		if(hasTwo(comp2) || hasThree(comp2) || hasFlush(comp2) || hasStraight(comp2) || fullHouse(comp2) || hasFour(comp2) || straightFlush(comp2)){
		    if(high<50&&comp2.money>=50){
			bet2=50;
			high=50;
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
	pot+=x*high;
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
	newCommunity();
	shuffle();
	deal();
	user.sort();
	comp1.sort();
	comp2.sort();
	System.out.println(user.name + "'s money:\t" + user.money);
	System.out.println(comp1.name + "'s money:\t" + comp1.money);
	System.out.println(comp2.name + "'s money:\t" + comp2.money);
	System.out.println("First round of betting");
	bet();
	if(user.getFold() == (true)) {
	    System.out.println(user.name + " has folded and lost all their money.");
	    //return;
	}
	community.add(deck.get(place));
	community.add(deck.get(place + 1));
	community.add(deck.get(place + 2));
	place = 18;
	System.out.println("Community:\t" + community);
	System.out.println(user.name + "'s hand:\t" +user.hand);
	int cardNum = 3;
	while (cardNum < 6) {
	    bet();
	    community.add(deck.get(place + 1));
	    place++;
	    cardNum ++;
	    System.out.println("Community:\t" + community);
	    System.out.println(user.name + "'s hand:\t" +user.hand);
	}
	System.out.println(comp1.name+"'s hand:\t"+comp1.hand);
	System.out.println(comp2.name+"'s hand:\t"+comp2.hand);
	for(int x=0;x<5;x++){
	    user.hand.add(community.get(x));
	}
	user.sort();
	for(int x=0;x<5;x++){
	    comp1.hand.add(community.get(x));
	}
	comp1.sort();
	for(int x=0;x<5;x++){
	    comp2.hand.add(community.get(x));
	}
	comp2.sort();
	win();
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
    public int check(){
	boolean a=straightFlush(user);
	boolean b=straightFlush(comp1);
	boolean c=straightFlush(comp2);
	if(a){return 0;}
	if(b){return 1;}
	if(c){return 2;}
	a=hasFour(user);
	b=hasFour(comp1);
	c=hasFour(comp2);
	if(a){return 0;}
	if(b){return 1;}
	if(c){return 2;}
	a=fullHouse(user);
	b=fullHouse(comp1);
	c=fullHouse(comp2);
	if(a){return 0;}
	if(b){return 1;}
	if(c){return 2;}
	a=hasFlush(user);
	b=hasFlush(comp1);
	c=hasFlush(comp2);
	if(a){return 0;}
	if(b){return 1;}
	if(c){return 2;}
	a=hasStraight(user);
	b=hasStraight(comp1);
	c=hasStraight(comp2);
	if(a){return 0;}
	if(b){return 1;}
	if(c){return 2;}
	a=hasThree(user);
	b=hasThree(comp1);
	c=hasThree(comp2);
	if(a){return 0;}
	if(b){return 1;}
	if(c){return 2;}
	a=hasTwo(user);
	b=hasTwo(comp1);
	c=hasTwo(comp2);
	if(a){return 0;}
	if(b){return 1;}
	if(c){return 2;}
	int d=high(user);
	int e=high(comp1);
	int f=high(comp2);
	if(d>e&&d>f){
	    return 0;
	}
	else if(e>f){
	    return 1;
	}
	else{
	    return 2;
	}
    }
    public void win(){
	int winner=check();
	if(winner==0){
	    System.out.println("You win! You get $"+pot);
	    user.bet(pot);
	}
	else if(winner==1){
	    System.out.println(comp1.name+"wins! He gets $"+pot);
	    comp1.bet(pot);
	}
	else{
	    System.out.println(comp2.name+"wins! He gets $"+pot);
	    comp2.bet(pot);
	}
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
    /*   public void hands() {
	System.out.println(user.hand);
	System.out.println(comp1.hand);
	System.out.println(comp2.hand);
	System.out.println(fullHouse(user));
	System.out.println(fullHouse(comp1));
	System.out.println(fullHouse(comp2));
	}*/
    
    public static void main(String [] args) {
	TexasHoldEm a = new TexasHoldEm();
	a.play();
	//a.hands();
    }
}
