public class Card {
    public String suit,number;
    public Card(String suit1, String number1){
	suit=suit1;
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
    public static void main(String[] args){
	Card King = new Card("h","K");
	System.out.println(King);
    }
}
