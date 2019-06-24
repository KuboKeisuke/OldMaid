package panel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oldMaid.OldMaid;
import oldMaid.OldMaidPlayer;

/*
 * ゲーム情報を伝えるパネル
 * */
public class OldMaidPlayPanel extends JPanel {
	// レイアウト
	private BorderLayout borderLayout;

	// ターン数表示用コンポーネント
	private JLabel turnLabel;
	// 選択用手札表示用パネル
	private HandImagePanel nextPlayerHandImagePanel;
	// プレイヤー手札表示用パネル
	private HandImagePanel turnPlayerHandImagePanel;
	// カードを引くための入力用コンポーネント(仮置き)
	private JTextField numberLabel;
	// ババ抜きクラス
	private OldMaid oldMaid;

	// 文言
	private static final String TURN = "ターン";

	/*
	 * コンストラクタ
	 */
	public OldMaidPlayPanel() {
		// レイアウト設定
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		// ババ抜き
		oldMaid = new OldMaid();

		// ターン数
		turnLabel = new JLabel(TURN);
		turnLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 60));
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(turnLabel, BorderLayout.NORTH);

		// 相手の手札
		nextPlayerHandImagePanel = new HandImagePanel(this);
		this.add(nextPlayerHandImagePanel, BorderLayout.CENTER);

		// 自分の手札
		turnPlayerHandImagePanel = new HandImagePanel(this);
		this.add(turnPlayerHandImagePanel, BorderLayout.SOUTH);
	}

	// トランプを引くときのインデックスラベルを数値に変換(仮置き)
	public int getText() {
		return Integer.parseInt(numberLabel.getText());
	}

	/*
	 * ゲームの前準備
	 */
	public void setGame(String name, int number) {
		oldMaid.setGame(name, number);
		nextPlayerHandImagePanel.drawCards(oldMaid.getOldMaidDealer().getNextPlayer(), false);
		turnPlayerHandImagePanel.drawCards(oldMaid.getOldMaidDealer().getTurnPlayer(), true);
		turnLabel.setText(TURN + oldMaid.getTurn());
	}

	/*
	 * ゲーム終了処理
	 */
	public void endGame() {
		System.out.println("ゲーム終了");
		oldMaid.endGame();
	}

	/*
	 * カードのパネルを押したとき
	 */
	public void selectCard(int number) {
		oldMaid.playOldMaid(number);
		turnLabel.setText(TURN + oldMaid.getTurn());
		// 一人になったときの仮処理(空のプレイヤーを入れてカードが表示されないようにする)
		if (oldMaid.getOldMaidDealer().getNextPlayer().equals(oldMaid.getOldMaidDealer().getTurnPlayer())) {
			nextPlayerHandImagePanel.drawCards(new OldMaidPlayer(""), false);
			turnPlayerHandImagePanel.drawCards(new OldMaidPlayer(""), true);
		} else {
			nextPlayerHandImagePanel.drawCards(oldMaid.getOldMaidDealer().getNextPlayer(), false);
			turnPlayerHandImagePanel.drawCards(oldMaid.getOldMaidDealer().getTurnPlayer(), true);
		}
	}
}
