package gamer;

import cards.Hand;

/*
 * ディーラー
 * */
public abstract class Dealer extends Gamer {

	public Dealer() {
		this.hand = new Hand();
		this.name = "ディーラー";
	}

	/*
	 * カードを配る
	 */
	public abstract void dealCards();

	/*
	 * ゲームの開始
	 */
	public abstract void playGame();

}
