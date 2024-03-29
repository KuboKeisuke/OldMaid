package oldMaid;

/*
 * ババ抜きクラス
 * */
public class OldMaid {
	// ババ抜きのディーラー
	private OldMaidDealer oldMaidDealer;
	// ターン
	private int turn;

	// 文言
	public static final String NPC = "NPC";

	/*
	 * コンストラクタ
	 */
	public OldMaid() {
		oldMaidDealer = new OldMaidDealer();
		turn = 0;
	}

	/*
	 * ゲーム開始(ターミナル用)
	 */
	public void playOldMaid(String name, int number) {
		// 下準備
		setGame(name, number);
		// 最初のトランプ整理
		oldMaidDealer.firstJudge();
		// 一人になるまでゲームをやる
		while (!oldMaidDealer.judgeEndGame()) {
			System.out.printf("ターン%d\n", turn);
			if (!oldMaidDealer.getTurnPlayer().getName().contains(NPC)) {
				turn++;
			}
			// カードを取る
			oldMaidDealer.takeCard(-1);
		}
	}

	/*
	 * ゲーム開始(Swing用) プレイヤーの番を先にやったあと再びプレイヤーの番になるまでNPCの番を行う(下準備と最初の整理は別途行う必要がある)
	 */
	public void playOldMaid(int takeCardIndex) {
		System.out.printf("ターン%d\n", turn);
		// プレイヤーの番
		if (!oldMaidDealer.getTurnPlayer().getName().contains(NPC) && oldMaidDealer.getPlayersNumber() != 0) {
			oldMaidDealer.takeCard(takeCardIndex);
		}
		// 再びプレイヤーの番になるまでNPCの番を行う
		while (oldMaidDealer.getTurnPlayer().getName().contains(NPC) && oldMaidDealer.getPlayersNumber() != 0) {
			oldMaidDealer.takeCard(-1);
		}
		if (oldMaidDealer.getPlayersNumber() != 0) {
			turn++;
		}
	}

	/*
	 * ゲーム前の下準備
	 */
	public void setGame(String name, int number) {
		// NPC含めたプレイヤーの決定
		oldMaidDealer.decidePlayer(name);
		oldMaidDealer.decideNPC(number);
		System.out.println();

		System.out.printf("ターン%d\n", turn);
		// トランプ作成
		oldMaidDealer.getHand().makeDeck();
		// シャッフル
		oldMaidDealer.cardShuffle();
		// カードを配る
		oldMaidDealer.dealCards();
		// 開始前の下準備
		oldMaidDealer.firstJudge();
		// ターンプレイヤーの決定
		oldMaidDealer.decideTurnPlayer();
		turn++;
	}

	/*
	 * ゲームを終了させる すべての値の初期化
	 */
	public void endGame() {
		oldMaidDealer.endGame();
	}

	/*
	 * ターン数のゲッター
	 */
	public int getTurn() {
		return turn;
	}

	/*
	 * ディーラーのゲッター
	 */
	public OldMaidDealer getOldMaidDealer() {
		return oldMaidDealer;
	}
}
