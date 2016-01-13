public class Card {
    public String suit,number;
    public Card(int suit1, String number1){
	if (suit1==0){
	    suit="h";
	}
	if (suit1==1){
	    suit="d";
	}
	if (suit1==2){
	    suit="s";
	}
	if (suit1==3){
	    suit="c";
	}
	number=number1;
    }
    public String toString(){
	String retStr="--------\n|"+number+"    "+number+"|";
	retStr+="\n|"+suit+"    "+suit+"|";
	for(int x=0;x<3;x++){
	    retStr+="\n|      |";
	}
	retStr+="\n|"+number+"    "+number+"|";
	retStr+="\n|"+suit+"    "+suit+"|";
	return retStr;
    }
    public String get(){
	return suit+number;
    }
    public static void main(String[] args){
	Card King = new Card(1,"K");
	System.out.println(King);
    }
}
