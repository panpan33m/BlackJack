import static org.junit.Assert.*;

import org.junit.Test;

public class HandTest  {
	private PlayingCard ace;
	private PlayingCard two;
	private PlayingCard jack;
	private PlayingCard ace2;
	private PlayingCard six;
	private PlayingCard seven;
	private PlayingCard four;
	private PlayingCard nine;

	public HandTest() {
		ace = new PlayingCard(PlayingCard.SPADES, PlayingCard.ACE);
		two = new PlayingCard(PlayingCard.DIAMONDS, 2);
		jack = new PlayingCard(PlayingCard.CLUBS, PlayingCard.JACK);
		ace2 = new PlayingCard(PlayingCard.DIAMONDS, PlayingCard.ACE);
		six = new PlayingCard(PlayingCard.HEARTS, 6);
		seven = new PlayingCard(PlayingCard.SPADES, 7);
		four = new PlayingCard(PlayingCard.DIAMONDS, 4);
		nine = new PlayingCard(PlayingCard.HEARTS, 9);
	}


	@Test
	/**
	 * Tests the bestValue method
	 */
	public void testBestValue() {

		//Test a hand with a single face card
		Hand testhand = new Hand();
		testhand.addCard(jack);
		assertEquals("Single face card", 10, testhand.bestValue());

		//Test a hand with two cards, neither an ace
		testhand.addCard(two);
		assertEquals("Two cards, no ace", 12, testhand.bestValue());

		//Test a hand with an ace and a face card (ace = 11)
		testhand = new Hand();
		testhand.addCard(ace);
		testhand.addCard(jack);
		assertEquals("Ace and face card", 21, testhand.bestValue());

		//Test a hand with two aces - one should be 1, one 11
		testhand = new Hand();
		testhand.addCard(ace);
		testhand.addCard(ace2);
		assertEquals("Two aces", 12, testhand.bestValue());

		//Add a third ace - yes, this is really a duplicate card
		testhand.addCard(ace);
		assertEquals("Three aces", 13, testhand.bestValue());

		//If we add a card other than an ace, still correct?
		testhand.addCard(two);
		assertEquals("Three aces, two", 15, testhand.bestValue());

		//Add a face card to force all our aces to be equal to one
		testhand.addCard(jack);
		assertEquals("Three aces, two, face card", 15, testhand.bestValue());
	}

	@Test
	/**
	 * tests the hitMe method
	 */
	public void testHitMe() {
		//Test a hand with no cards
		Hand testhand = new Hand();
		assertTrue("No cards yet", testhand.hitMe());
		//Test a hand with a single face card
		testhand.addCard(jack);
		assertTrue("Single face card", testhand.hitMe());
		//Test a hand with a soft seventeen
		testhand.addCard(six);
		testhand.addCard(ace);
		assertTrue("Soft 17", testhand.hitMe());
		//Test a hand with two aces that goes over
		testhand.addCard(ace);
		assertFalse("Eighteen", testhand.hitMe());
		testhand = new Hand(); //reset
		//Test a card with a hard seventeen
		testhand.addCard(jack);
		testhand.addCard(seven);
		assertFalse("Seventeen", testhand.hitMe());
		//Test a card that is under with the Ace as low but over with the Ace as high
		testhand = new Hand(); //reset
		testhand.addCard(ace);
		testhand.addCard(seven);
		assertFalse("Eighteen, not eight", testhand.hitMe());
		//Test a card that has two aces and is close to 21
		testhand.addCard(ace);
		assertFalse("Once ace as one, one as 11", testhand.hitMe());	
		
	}

	@Test
	public void testIsBust() {
		//test a hand with no cards
		Hand testHand = new Hand();
		assertFalse("No cards yet", testHand.isBust());
		//test a hand with value 10
		testHand.addCard(jack);
		assertFalse("jack = 10,", testHand.isBust());
		//test a hand with value 21 without ace
		testHand.addCard(seven);
		testHand.addCard(four);
		assertFalse("jack(10)+7+4=21", testHand.isBust());
		//test a hand with value 21 with ace
		testHand = new Hand(); //reset
		testHand.addCard(jack);
		testHand.addCard(jack);
		testHand.addCard(ace);//when ace is one
		assertFalse("jack(10)+jack(10)+ace(1)=21", testHand.isBust());
		testHand = new Hand(); //reset
		testHand.addCard(four);
		testHand.addCard(six);
		testHand.addCard(ace);//when ace is 11
		assertFalse("4+6+ace(11)=21", testHand.isBust());
		//test a hand with value more than 21 without ace
		testHand = new Hand(); //reset
		testHand.addCard(six);
		testHand.addCard(seven);
		testHand.addCard(jack);
		assertTrue("6+7+jack(10)=23", testHand.isBust());
		//test a hand with value more than 21 with ace
		//ace would never be 11 when the best value is more than 21
		testHand.addCard(ace); //when ace is one
		assertTrue("6+7+jack(10)+ace(1)=24", testHand.isBust());
		
		
		
	}

	@Test
	public void testBeats() {
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();
		//test no card
		assertFalse("no card", hand1.beats(hand2));
		//test when hand1 is over 21
		hand1.addCard(jack);
		hand1.addCard(seven);
		hand1.addCard(jack); 
			//when hand2 has no card
		assertFalse("hand1: 10+7+10=27; hand2: no card", hand1.beats(hand2));
			//when hand2 is less than 21
		hand2.addCard(four);
		assertFalse("hand2: 10+7+10=27; hand2: 4", hand1.beats(hand2));
		//test when hand1 is less than 21
		hand1 = new Hand(); //reset
		hand1.addCard(nine);
		hand1.addCard(seven);
			//when hand2 is less than hand1
		assertTrue("hand1: 9+7=16; hand2: 4", hand1.beats(hand2));
			//when hand2 equals hand1
		hand2.addCard(nine);
		hand2.addCard(ace); //ace is one
		hand2.addCard(two);
		assertFalse("hand1: 9+7=16; hand2: 4+9+1+2=16;", hand1.beats(hand2));
			//when hand2 is over hand1
		hand2.addCard(four);
		assertFalse("hand1: 9+7=16; hand2: 4+9+1+2+4=20;", hand1.beats(hand2));
		//test when hand2 is over 21
		hand2 = new Hand(); //reset
		hand2.addCard(jack);
		hand2.addCard(nine);
		hand2.addCard(seven);
			//when hand1 is less than 21
		assertTrue("hand1: 9+7=16; hand2: 10+9+7=26;", hand1.beats(hand2));
	}

}
