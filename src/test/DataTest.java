package test;

import oldMaid.OldMaidDealer;

/*
 * お試しクラス
 * */
public class DataTest {
	public static void main(String[] args) {
		// プレイヤー作成
		OldMaidDealer dealer = new OldMaidDealer();

		dealer.decidePlayer("プレイヤー1");
		dealer.decideNPC(3);
		System.out.println();
		dealer.playerShuffle();

		// プレイヤーにカードを配る
		dealer.getHand().makeDeck();
		dealer.cardShuffle();
		dealer.dealCards();

		// ゲームスタート
		dealer.playGame();

	}
}
