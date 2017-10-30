import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlackjackGame {

	public static void main(String[] args) {
		ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Blackjack Game \n");

		do{
			System.out.print("Please specify number of decks (1-5): ");
			int deckNumber = sc.nextInt();
			sc.nextLine();
			//add 1-5 decks of cards in ArrayList deck
			if (deckNumber>0 && deckNumber<=5){
				for(int i=0; i<deckNumber; i++){
					addSuite(deck, PlayingCard.SPADES);
					addSuite(deck, PlayingCard.CLUBS);
					addSuite(deck, PlayingCard.DIAMONDS);
					addSuite(deck, PlayingCard.HEARTS);
				}
				break;
			}
			else{
				System.out.println("Invalid number of decks entered");
			}			
		}
		while(true);

		do{
			Collections.shuffle(deck);
			//reset both hands
			Hand dealerHand = new Hand();
			Hand playerHand = new Hand();
			//both hands get two new cards in the first round
			playerHand.addCard(deck.get(0));
			deck.remove(0);
			dealerHand.addCard(deck.get(0));
			deck.remove(0);
			
			playerHand.addCard(deck.get(0));
			deck.remove(0);
			dealerHand.addCard(deck.get(0));
			deck.remove(0);

			System.out.println("Player hand:");
			System.out.println(playerHand);
			System.out.println("Dealer hand:");
			System.out.println(dealerHand);


			do{
				System.out.print("Would you like another card (yes, no)?: ");
				String anotherC = sc.next();
				if (anotherC.equals("yes")){
					//give the player another card
					deal(playerHand, dealerHand, deck, true);
					System.out.println("Player hand:");
					System.out.println(playerHand);
					System.out.println("Dealer hand:");
					System.out.println(dealerHand);
					//if the new card makes player's hand over 21, while deal's hand is not,
					//dealer wins
					if (playerHand.isBust() && !dealerHand.isBust()){
						System.out.println("Dealer wins!");
						break;
					}
					//if the new card makes player's hand over 21, and deal's hand is already over 21
					//compare who is closer to 21
					else if (playerHand.isBust() && dealerHand.isBust()){
						compareValue(playerHand, dealerHand);
						break;
					}
				}
				else if (anotherC.equals("no")){
					//do not give the player another card
					//mark if dealer is given another card
					boolean dealt = deal(playerHand, dealerHand, deck, false);
					System.out.println("Player hand:");
					System.out.println(playerHand);
					System.out.println("Dealer hand:");
					System.out.println(dealerHand);
					//if player beats dealer (dealerHand>21 or dealerHand<playerHand<21) 
					//and dealer didn't get another card,
					//(both of them refuse to take another card)
					//player wins
					if (playerHand.beats(dealerHand) && dealt==false){
						System.out.println("Player wins!");
						break;
					}
					//if both of them refuse to take a card, but both hands are over 21,
					//compare who is closer to 21
					else if(dealt==false){
						compareValue(playerHand, dealerHand);
						break;
					}
				}
				else{
					System.out.println("Incorrect response. Please enter yes or no.");
				}
			}
			while(true);
			
			boolean loop = true;
			do{
			System.out.print("Would you like to play another round (yes, no)? ");
			String anotherRound = sc.next();
			if (anotherRound.equals("yes")){
				loop = true;
				break;
			}
			else if (anotherRound.equals("no")){
				System.out.println("Thank you for playing!");
				loop = false;
				break;
			}	
			else
				System.out.println("Incorrect response. Please enter Yes or No.");
			}
			while(true);
			if (loop == false)
				break;
		}
		while(true);
	}

	/**
	 * a void method to compare the values of both hands
	 * It is called only when both hands' bestValue are over 21.
	 * @param playerHand
	 * @param dealerHand
	 */
	public static void compareValue(Hand playerHand, Hand dealerHand){
		if (playerHand.bestValue()>dealerHand.bestValue())
			System.out.println("Player wins!");
		else if (playerHand.bestValue()<dealerHand.bestValue())
			System.out.println("Dealer wins!");
		else if (playerHand.bestValue() == dealerHand.bestValue())
			System.out.println("This is a tie.");
	}

	/**
	 * An ArrayList which takes deck and Suite as parameter, and returns an ArrayList after PlayingCards
	 * being added in it.
	 * @param deck
	 * @param Suite
	 * @return
	 */
	public static ArrayList<PlayingCard> addSuite (ArrayList<PlayingCard> deck, String Suite){
		for(int i=0; i<13; i++){
			PlayingCard card = new PlayingCard(Suite, i+1);
			deck.add(card);
		}
		return deck;
	}

	/**
	 * If the player wants a card, this method adds a card to player from the deck and remove the card from the deck.
	 * Then if the dealer is less than 17 or has a soft 17, the dealer gets another card too.
	 * It returns the boolean of whether a card is dealt to either hand.
	 * @param playerHand
	 * @param dealerHand
	 * @param deck
	 * @param wantCard
	 * @return
	 */
	public static boolean deal(Hand playerHand, Hand dealerHand, ArrayList<PlayingCard> deck, boolean wantCard){
		if (wantCard){
			playerHand.addCard(deck.get(0));
			deck.remove(0);
		}
		if (dealerHand.hitMe()){
			dealerHand.addCard(deck.get(0));
			deck.remove(0);
			return true;
		}
		return wantCard;
	}

}
