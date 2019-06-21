package gamer;

import cards.Cards;
import cards.Hand;

/*
 * ゲームプレイヤー
 * */
public class Player extends Gamer {

	/*
	 * コンストラクタ
	 */
	public Player(String name) {
		hand = new Hand();
		this.name = name;
	}

	/*
	 * プレイヤー名のゲッター
	 */
	public String getName() {
		return name;
	}

	/*
	 * 手札にカードを加える
	 */
	public void addHand(Cards card) {
		System.out.printf("%sはカードを加えた\n", getName());
		hand.add(card);
	}
}
