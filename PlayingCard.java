
public class PlayingCard implements Comparable{
	private String Suite;


	private int value;
	private int type;
	public static String SPADES = "Spades";
	public static String DIAMONDS = "Diamonds";
	public static String CLUBS = "Clubs";
	public static String HEARTS = "Hearts";
	public static int JACK = 11;
	public static int QUEEN = 12;
	public static int KING = 13;
	public static int ACE = 1;
	
	
	/**
	 * Constructor of PlayingCard, taking Suite and type as parameter.
	 * If the type is greater than 10(jack,queen,king), the value of the card equals 10.
	 * Otherwise, the value is the same as the type.
	 * @param Suite
	 * @param type
	 */
	public PlayingCard(String Suite, int type){
		this.Suite = Suite;
		this.type = type;
		if(type>=10)
			this.value = 10;
		else
			this.value = this.type;
	}
	

	/**
	 * equals function for two PlayingCards
	 * If both of their suite and type are the same, then they are equal.
	 * @param other
	 * @return
	 */
	public boolean equals(PlayingCard other){
		if ((getSuite() == other.getSuite()) && (getType() == other.getType()))
			return true;
		else
			return false;
	}
	
	

	/**
	 * A method helps to return an integer type to letter.
	 * @return
	 */
	public String typeName(){
		switch (getType()) {
			case 1:
				return "Ace";
			case 2:
				return "Two";
			case 3:
				return "Three";
			case 4:
				return "Four";
			case 5:
				return "Five";
			case 6:
				return "Six";
			case 7:
				return "Seven";
			case 8:
				return "Eight";
			case 9:
				return "Nine";
			case 10:
				return "Ten";
			case 11:
				return "Jack";
			case 12:
				return "Queen";
			case 13:
				return "King";
			default:
				return "default";
		}
	}
	
	/**
	 * getter for type
	 * @return
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * setter for type
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}


	/**
	 * getter for value
	 * @return
	 */
	public int value(){
		return value;		
	}
	
	/**
	 * getter for suite
	 * @return
	 */
	public String getSuite() {
		return Suite;
	}
	
	/**
	 * setter for suite
	 * @param suite
	 */
	public void setSuite(String suite) {
		Suite = suite;
	}
	
	/**
	 * compareTo method to compare the value of two PlayingCards.
	 */
	public int compareTo(Object o){
		if (o instanceof PlayingCard){
			PlayingCard other = (PlayingCard) o;
			if(value()>other.value())
				return 1;
			else if (value() == other.value())
				return 0;
			else if (value()<other.value())
				return -1;
			else
				return -10000;}
		else
			return -10000;
	}
	
	/**
	 * If the PlayingCard is an Ace, return true. Otherwise return false.
	 * @return
	 */
	public boolean isAce(){
		if (type == 1)
			return true;
		else
			return false;
	}
	
	

}
