import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a hand of playing cards
 * @author jburge
 *
 */
public class Hand {
	
	private boolean hasOneAce = false; 
	
	public Hand(){
		
	}


	private ArrayList<PlayingCard> pcArray = new ArrayList<PlayingCard>();

	/**
	 * Adds a new card to the hand
	 * @param newcard - the card being added
	 */
	public void addCard(PlayingCard newcard)
	{
		pcArray.add(newcard);
		Collections.sort(pcArray);
	}
	
	
	
	/**
	 * Calculates the best value (closest to 21 without going over) for a hand. If the best value
	 * is still over 21 then return that.
	 * @return the value for the hand
	 */
	public int bestValue()
	{
		int bestValue = 0;
		int nAce = 0;
		for(int i=0; i<pcArray.size(); i++)
		{
			if (pcArray.get(i).getType()==1){
				nAce++; //count the number of aces in this hand
			}	
			bestValue += pcArray.get(i).value(); //this best value sees all aces as one
		}
		if (nAce>0){
			int bvWith11 = bestValue+10; //if one of the ace is 11
			int bvWith1 = bestValue;//if all of the aces are 1
			if ((bvWith11 > bvWith1) && (bvWith11<=21)) 
				return bvWith11;
			else //bvWith11>21 and else
				hasOneAce = true;
				return bvWith1;
		}
		else
			return bestValue;
		}
	

	/**
	 * Determines if the dealer has to get another card. If the bestValue is less than 17 it should return true. If a "soft 17" 
	 * (17 if an Ace is equal to one) they get a card (return true). Othewise, return false.
	 * @return - true if they should get another card
	 */
	public boolean hitMe()
	{
		if (bestValue()<17)
			return true;
		else if ((bestValue()==17) && hasOneAce)
			return true;
		else
			return false;

	}
	
	/**
	 * Checks to see if the best value for the hand is greater than 21 and if so, returns true
	 * @return - true if the value is over 21
	 */
	public boolean isBust()
	{
		if(bestValue()>21)
			return true;
		else
			return false;
	}
	
	/**
	 * Checks to see if this hand beats the other hand. If this hand is over 21, return false. Otherwise if 
	 * it is greater than the other hand or the other hand is over 21, return true.
	 * @param other - the hand we are comparing this one to
	 * @return - true if this hand beats the other hand
	 */
	public boolean beats(Hand other)
	{
		
		if ((bestValue()>other.bestValue() || other.isBust()) && !isBust())
			return true;
		else
			return false;
	}
	

	/**
	 * Used by the calling program to print out the hand of cards as shown in the sample run.
	 * @return - a string representing the hand of cards 
	 */
	public String toString()
	{
		String str = "";
		for(int i=0; i<pcArray.size();i++){
			str += pcArray.get(i).typeName() +" of "+pcArray.get(i).getSuite() +"\n";
		}
		str += "Value = "+bestValue() +"\n";
		return str;
	}
	

}
