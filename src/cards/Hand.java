package cards;

import java.util.ArrayList;
import java.util.Collections;

import cards.Cards.Suit;

/*
 * トランプの山札(手札)
 * */
public class Hand extends ArrayList<Cards> {

	/*
	 * 山札の生成
	 */
	public void makeDeck() {
		for (Suit suit : Suit.values()) {
			for (int i = Cards.CARDS_NUMBER_MIN; i <= Cards.CARDS_NUMBER_MAX; i++) {
				if (suit.equals(Suit.JOKER)) {
					Cards cards = new Cards(0, suit);
					add(cards);
					break;
				} else {
					Cards cards = new Cards(i, suit);
					add(cards);
				}
			}
		}
	}

	/*
	 * 山札のシャッフル
	 */
	public void shuffle() {
		Collections.shuffle(this);
	}
}
