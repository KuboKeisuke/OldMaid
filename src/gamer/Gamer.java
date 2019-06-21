package gamer;

import cards.Cards;
import cards.Hand;

/*
 * ゲームに参加する人のインターフェイス
 * */
public class Gamer {
	// プレイヤー名
	protected String name;

	// 手札
	protected Hand hand;

	// 手札の公開
	public void showHand() {
		System.out.printf("%sのカード\n", name);
		for (Cards card : hand) {
			System.out.printf("%s %s\n", card.getNumber(), card.getSuit());
		}
	}

	// 手札のシャッフル
	public void cardShuffle() {
		System.out.printf("%sの手札シャッフル\n", name);
		hand.shuffle();
	}

	public Hand getHand() {
		return hand;
	}
}
