package gamer;

import cards.Hand;

/*
 * ディーラー
 * */
public abstract class Dealer extends Gamer {
	/*
	 * コンストラクタ 名前はディーラーにしておく
	 */
	public Dealer() {
		this.hand = new Hand();
		this.name = "ディーラー";
	}

	/*
	 * カードを配る
	 */
	public abstract void dealCards();
}
