package cards;

/*
 * トランプ
 * ジョーカーは一旦放置で
 * */
public class Cards {
	// スペード、ハード、ダイアモンド、クラブの定義
	public static enum Suit {
		JOKER, SPADE, HEART, DIAMOND, CLUB;
	}

	// スーツ数
	public final static int SUITS_NUMBER = 4;
	// カードの最小数値
	public final static int CARDS_NUMBER_MIN = 1;
	// カードの最大数値
	public final static int CARDS_NUMBER_MAX = 13;

	// 番号
	private int number;

	// スーツ(図柄)
	private Suit suit;

	/*
	 * コンストラクタ カードの番号とスーツ
	 */
	public Cards(int number, Suit suit) {
		this.number = number;
		this.suit = suit;
	}

	/*
	 * 番号のゲッター
	 */
	public int getNumber() {
		return number;
	}

	/*
	 * スーツのゲッター
	 */
	public Suit getSuit() {
		return suit;
	}
}
