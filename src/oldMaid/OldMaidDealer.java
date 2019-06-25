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

	// 勝者のゲッター
	public ArrayList<OldMaidPlayer> getWinPlayers() {
		return winPlayers;
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
		// すべてのカードがなくなるまで
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
		// 次のプレイヤーのindexを取得(最後の人は最初の人からカードを取る)、ターン数を+1する
		if (turnPlayerIndex >= players.size() - 1) {
			nextPlayer = players.get(0);
		} else {
			nextPlayer = players.get(turnPlayerIndex + 1);
		}
		System.out.printf("次は%sの番\n", turnPlayer.getName());
	}

	/*
	 * カードを取る 引数が-1の場合はNPC処理かターミナル上での直接入力になる それ以外は引数から-1してカードを取得する
	 */
	public void takeCard(int number) {
		int takeCardIndex = 0;
		if (number == -1) {
			// 次のプレイヤーの手札の枚数から次取るカードのインデックスを取得
			takeCardIndex = turnPlayer.selectCard(nextPlayer.getHand().size());
		} else {
			takeCardIndex = number;
		}
		// 次のプレイヤーからカードを取得
		Cards card = nextPlayer.giveCard(takeCardIndex);
		// カードを加える
		turnPlayer.addHand(card);
		// カードを取られたプレイヤーのジャッジ
		judgeWin(nextPlayer);
		// カードを取った後のターンプレイヤーのジャッジ
		judgeWin(turnPlayer);
		// ゲーム続行の場合
		if (!judgeEndGame()) {
			// 残り手札枚数の公開
			showNumberCards();
			if (turnPlayerIndex >= players.size() - 1) {
				turnPlayerIndex = 0;
			} else {
				turnPlayerIndex++;
			}
			decideTurnPlayer();
		} else {
			endGame();
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
			players.remove(players.indexOf(player));
		}
	}

	/*
	 * ゲームが終わったかどうか判断する
	 */
	public boolean judgeEndGame() {
		boolean endGame = false;
		if (players.size() == 1 || players.size() == 0) {
			endGame = true;
		}
		return endGame;
	}

	/*
	 * ゲームを終了させる すべての値の初期化
	 */
	public void endGame() {
		// 一人残っているものも勝者配列に保存
		winPlayers.add(players.get(0));
		players.remove(0);
		System.out.println("ゲーム終了");
		for (int i = 0; i < winPlayers.size(); i++) {
			System.out.printf("%d位：%s\n", i + 1, winPlayers.get(i).getName());
		}
	}
}
