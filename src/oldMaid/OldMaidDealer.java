package oldMaid;

import java.util.ArrayList;
import java.util.Collections;

import cards.Cards;
import gamer.Dealer;

public class OldMaidDealer extends Dealer {

	// プレイヤーたち
	private ArrayList<OldMaidPlayer> players;
	// 勝者プレイヤーたち
	private ArrayList<OldMaidPlayer> winPlayers;

	// ゲーム中にカードを引く側
	private OldMaidPlayer turnPlayer;
	// ゲーム中にカードを引かれる側
	private OldMaidPlayer nextPlayer;

	// ターンプレイヤーの配列のインデックス
	private int turnPlayerIndex;

	/*
	 * コンストラクタ
	 */
	public OldMaidDealer() {
		super();
		players = new ArrayList<OldMaidPlayer>();
		winPlayers = new ArrayList<OldMaidPlayer>();
		turnPlayerIndex = 0;
	}

	// プレイヤーの数のゲッター
	public int getPlayersNumber() {
		return players.size();
	}

	// ターンプレイヤーのゲッター
	public OldMaidPlayer getTurnPlayer() {
		return turnPlayer;
	}

	// 次のプレイヤーのゲッター
	public OldMaidPlayer getNextPlayer() {
		return nextPlayer;
	}

	/*
	 * プレイヤーの決定
	 */
	public void decidePlayer(String name) {
		OldMaidPlayer player = new OldMaidPlayer(name);
		players.add(player);
		System.out.println(name + "を追加しました");
	}

	/*
	 * NPCの決定
	 */
	public void decideNPC(int number) {
		for (int i = 1; i <= number; i++) {
			String name = OldMaid.NPC + i;
			OldMaidPlayer player = new OldMaidPlayer(name);
			players.add(player);
			System.out.println(name + "を追加しました");
		}
	}

	/*
	 * カードを配る(ババ抜きで使用) ディーラーの持っているカードすべてを各プレイヤーに一枚ずつ渡す
	 */
	public void dealCards() {
		while (hand.size() != 0) {
			for (int i = 0; i < players.size(); i++) {
				if (hand.size() != 0) {
					players.get(i).addHand(hand.get(0));
					hand.remove(0);
				}
			}
		}
	}

	/*
	 * カードの公開(プレイヤー全員のカード)
	 */
	public void showHand() {
		for (OldMaidPlayer player : players) {
			player.showHand();
		}
	}

	/*
	 * プレイヤー全員の残りカード枚数の公開
	 */
	public void showNumberCards() {
		for (OldMaidPlayer player : players) {
			System.out.printf("%sの残り手札枚数：%d枚\n", player.getName(), player.getHand().size());
		}
		System.out.println();
	}

	/*
	 * プレイヤーの順番入れ替え
	 */
	public void playerShuffle() {
		Collections.shuffle(players);
		System.out.println("プレイヤーの順番");
		for (int i = 0; i < players.size(); i++) {
			System.out.printf("%d番 %s\n", i + 1, players.get(i).getName());
		}
		System.out.println();
	}

	/*
	 * 最初のトランプ整理
	 */
	public void firstJudge() {
		// まず全員のカードを整理する
		for (int i = 0; i < players.size(); i++) {
			judgeWin(players.get(i));
		}
		// 残り枚数の公開
		showNumberCards();
	}

	/*
	 * ターンプレイヤーの決定
	 */
	public void decideTurnPlayer() {
		// ターンプレイヤーはインデックスからそのまま取得
		turnPlayer = players.get(turnPlayerIndex);
		System.out.printf("・%sの番\n", turnPlayer.getName());
		// 次のプレイヤーのindexを取得(最後の人は最初の人からカードを取る)、ターン数を+1する
		if (turnPlayerIndex >= players.size() - 1) {
			nextPlayer = players.get(0);
		} else {
			nextPlayer = players.get(turnPlayerIndex + 1);
		}
	}

	/*
	 * カードを取る 引数が-1の場合はNPC処理かターミナル上での直接入力になる それ以外は引数から-1してカードを取得する
	 */
	public void takeCard(int number) {
		// 次のプレイヤーの手札から取るカードを選択
		int handNumber = nextPlayer.getHand().size();
		// numberの例外処理(out of bounds)考えていない→どこかしらで考える
		int takeCardIndex = 0;
		if (number == -1) {
			takeCardIndex = turnPlayer.selectCard(handNumber);
		} else {
			takeCardIndex = number - 1;
		}
		// 次のプレイヤーからカードを取得
		Cards card = nextPlayer.giveCard(takeCardIndex);
		// カードを加える
		turnPlayer.addHand(card);
		// カードを取られたプレイヤーのジャッジ
		judgeWin(nextPlayer);
		// カードを取った後のターンプレイヤーのジャッジ
		judgeWin(turnPlayer);
		// 残り手札枚数の公開
		showNumberCards();
		if (turnPlayerIndex >= players.size() - 1) {
			turnPlayerIndex = 0;
		} else {
			turnPlayerIndex++;
		}
		// プレイヤーが一人なら終了
		if (players.size() == 1) {
			showWinner();
		}
	}

	/*
	 * 手札がなくなったプレイヤーを勝利させて、ゲームから除外する
	 */
	private void judgeWin(OldMaidPlayer player) {
		if (player.handOrganize()) {
			System.out.printf("%sが抜けました。\n", player.getName());
			winPlayers.add(player);
			// 勝利するプレイヤーのindexを探して除外する
			int index = players.indexOf(player);
			players.remove(index);
		}
	}

	/*
	 * 勝者を順番に表示する
	 */
	public void showWinner() {
		for (int i = 0; i < winPlayers.size(); i++) {
			System.out.printf("%d位：%s\n", i + 1, winPlayers.get(i).getName());
		}
		System.out.printf("敗者：%s\n", players.get(0).getName());
	}

	/*
	 * ゲームを終了させる すべての値の初期化
	 */
	public void endGame() {
		players.clear();
		winPlayers.clear();
		hand.clear();
		turnPlayerIndex = 0;
	}
}
