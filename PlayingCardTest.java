import static org.junit.Assert.*;

import org.junit.Test;

public class PlayingCardTest {
	
	private PlayingCard ace;
	private PlayingCard two;
	private PlayingCard jack;
	private PlayingCard queen;
	private PlayingCard king;
	private PlayingCard seven;
	private PlayingCard ace2;
	private PlayingCard seven2;
	
	public PlayingCardTest() {
		ace = new PlayingCard(PlayingCard.SPADES, PlayingCard.ACE);
		ace2 = new PlayingCard(PlayingCard.CLUBS, PlayingCard.ACE);
		two = new PlayingCard(PlayingCard.DIAMONDS, 2);
		jack = new PlayingCard(PlayingCard.CLUBS, PlayingCard.JACK);
		queen = new PlayingCard(PlayingCard.DIAMONDS, PlayingCard.QUEEN);
		king = new PlayingCard(PlayingCard.CLUBS, PlayingCard.KING);
		seven = new PlayingCard(PlayingCard.SPADES, 7);
		seven2 = new PlayingCard(PlayingCard.SPADES, 7);
	}


	@Test
	public void testValue() {
		assertEquals("test ace", 1, ace.value());
		assertEquals("test two", 2, two.value());
		assertEquals("test jack", 10, jack.value());
		assertEquals("test queen", 10, queen.value());
		assertEquals("test king", 10, king.value());
		assertEquals("test seven", 7, seven.value());
		
	}

	@Test
	public void testEqualsObject() {
		assertFalse("test cards with different types and different suites", ace.equals(two));
		assertFalse("test cards with same suite but different types", two.equals(queen));
		assertFalse("test cards with same type but different suites", ace.equals(ace2));
		assertFalse("test cards with same value, same suite, but different types", king.equals(jack));
		assertTrue("test cards with same value, same suite and same type", seven.equals(seven2));
	}
	
	@Test
	public void testCompareTo() {
		//smaller than
		assertEquals("test ace and two in different suites", -1, ace.compareTo(two));
		//equals
		assertEquals("test seven and seven2 in different suites", 0, seven.compareTo(seven2));
		//equals with face cards
		assertEquals("test queen and jack in different suites", 0, queen.compareTo(jack));
		//bigger than
		assertEquals("test seven and two in different suites", 1, seven.compareTo(two));
		//will having same suites affect the result?
			//equals with face cards
		assertEquals("test jack and king in same suites", 0, jack.compareTo(king));
			//bigger than with one face card and one non-face card
		assertEquals("test queen and two in same suites", 1, queen.compareTo(two));
	}
}
