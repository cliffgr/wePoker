package edu.vub.at.nfcpoker.comm;

import java.util.Date;

import edu.vub.at.nfcpoker.Card;
import edu.vub.at.nfcpoker.ConcretePokerServer.GameState;

public interface Message {





	public static abstract class TimestampedMessage implements Message {
		public long timestamp;
			
		public TimestampedMessage() {
			timestamp = new Date().getTime();
		}
		
		@Override
		public String toString() {
			return this.getClass().getSimpleName() + "@" + timestamp;
		}
	}
	
	public static final class StateChangeMessage extends TimestampedMessage {
		public GameState newState;

		public StateChangeMessage(GameState newState_) {
			newState = newState_;
		}
		
		// for kryo
		public StateChangeMessage() {}

		@Override
		public String toString() {
			return super.toString() + ": State change to " + newState;
		}
	}
	
	public class ReceiveHoleCardsMessage extends TimestampedMessage {
		public Card card1, card2;
		
		public ReceiveHoleCardsMessage(Card one, Card two) {
			card1 = one;
			card2 = two;
		}
		
		//kryo
		public ReceiveHoleCardsMessage() {}
		
		@Override
		public String toString() {
			return super.toString() + ": Receive cards [" + card1 + ", " + card2 + "]";
		}
	}
	
	public class ReceivePublicCards extends TimestampedMessage {
		public Card[] cards;
		
		public ReceivePublicCards(Card[] cards_) {
			cards = cards_;
		}
		
		//kryo
		public ReceivePublicCards() {}
		
		@Override
		public String toString() {
			StringBuilder cardsStr = new StringBuilder(": Receive cards [");
			cardsStr.append(cards[0].toString());
			for (int i = 1; i < cards.length; i++)
				cardsStr.append(", ").append(cards[i].toString());
			
			return super.toString() + cardsStr.toString();
		}
	}
}