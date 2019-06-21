package oldMaid;

public class OldMaid {
	OldMaidDealer oldMaidDealer;

	/*
	 * コンストラクタ
	 */
	public OldMaid() {
		oldMaidDealer = new OldMaidDealer();
	}

	/*
	 * プレイヤーを決定
	 */
	public void decidePlayers(String name, int number) {
		oldMaidDealer.decidePlayer(name);
		oldMaidDealer.decideNPC(number);
	}

	public void playOldMaid() {
		oldMaidDealer.playerShuffle();
		oldMaidDealer.cardShuffle();
		oldMaidDealer.dealCards();

		// ゲームスタート
		oldMaidDealer.playGame();

		// 勝者表示
		oldMaidDealer.showWinner();
	}
}
