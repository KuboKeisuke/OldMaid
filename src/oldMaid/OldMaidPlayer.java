package oldMaid;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import cards.Cards;
import gamer.Player;

public class OldMaidPlayer extends Player {

	/*
	 * コンストラクタ
	 */
	public OldMaidPlayer(String name) {
		super(name);
	}

	/*
	 * 手札を捨てる処理
	 */
	private void handProcess() {
		for (int i = 0; i < hand.size(); i++) {
			for (int j = i + 1; j < hand.size(); j++) {
				if (hand.get(i).getNumber() == (hand.get(j).getNumber())) {
					// 配列のindexを崩さないため、先に値が大きいはずのjから削除する
					System.out.printf("%sは\"%sの%d\"と\"%sの%d\"を捨てた\n", getName(), hand.get(i).getSuit(),
							hand.get(i).getNumber(), hand.get(j).getSuit(), hand.get(j).getNumber());
					hand.remove(j);
					hand.remove(i);
					// 再帰的に行う
					handProcess();
				}
			}
		}
	}

	/*
	 * 手札の整理 手札がない場合はtrueを返して勝利させる
	 */
	public boolean handOrganize() {
		boolean victory = false;
		handProcess();
		if (hand.size() == 0) {
			victory = true;
		} else {
			cardShuffle();
		}
		return victory;
	}

	/*
	 * 相手の手札の枚数からカードを引くindexを選ぶ 1から引数の範囲内で選択する
	 */
	public int selectCard(int number) {
		int index = 0;
		// NPCならランダムにする
		if (getName().contains(OldMaid.NPC)) {
			Random random = new Random();
			index = random.nextInt(number);
		} else {
			// 配列を考慮して入力された数値から-1する
			index = scanIndex(number) - 1;
		}
		System.out.printf("%sは%d番目のカードを選択\n", getName(), index + 1);
		return index;
	}

	/*
	 * 入力で引くカードを選ぶ
	 */
	private static int scanIndex(int number) {
		Scanner scanner = new Scanner(System.in);
		int index = 0;

		try {
			while (1 > index || index > number) {
				System.out.printf("1から%dまでの値を入力してください\n", number);
				index = scanner.nextInt();
			}
			return index;
		} catch (InputMismatchException e) {
			System.out.println("数値以外が入力されました。");
			return scanIndex(number);
		}
	}

	/*
	 * カードを渡す Cardsクラスを返しつつ手札に持っているものは破棄する
	 */
	public Cards giveCard(int index) {
		Cards card = hand.get(index);
		hand.remove(index);
		return card;
	}
}
