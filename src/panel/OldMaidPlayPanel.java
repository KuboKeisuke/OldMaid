package panel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;
import oldMaid.OldMaid;

/*
 * ゲーム情報を伝えるパネル
 * */
public class OldMaidPlayPanel extends JPanel {
	// メインフレーム
	private MainFrame mainFrame;
	// ゲームパネル
	private GamePanel gamePanel;
	// レイアウト
	private BorderLayout borderLayout;

	// ターン数表示用コンポーネント
	private JLabel turnLabel;
	// 選択用手札表示用パネル
	private HandImagePanel nextPlayerHandImagePanel;
	// プレイヤー手札表示用パネル
	private HandImagePanel turnPlayerHandImagePanel;
	// ババ抜きクラス
	private OldMaid oldMaid;

	// 文言
	private static final String TURN = "ターン";

	/*
	 * コンストラクタ
	 */
	public OldMaidPlayPanel(MainFrame mainFrame, GamePanel gamePanel) {
		this.mainFrame = mainFrame;
		this.gamePanel = gamePanel;
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

	/*
	 * ゲームの前準備
	 */
	public void setGame(String name, int number) {
		oldMaid.setGame(name, number);
		drawHands();
	}

	/*
	 * ゲーム終了処理と結果画面への遷移
	 */
	public void endGame() {
		mainFrame.setWinPlayers(oldMaid.getOldMaidDealer().getWinPlayers());
		mainFrame.panelChange(gamePanel, MainFrame.RESULTPANEL);
		initializeOldMaid();
	}

	/*
	 * ババ抜きの初期化
	 */
	public void initializeOldMaid() {
		System.out.println("ババ抜き初期化");
		oldMaid = new OldMaid();
	}

	/*
	 * カードのパネルを押したとき
	 */
	public void selectCard(int number) {
		oldMaid.playOldMaid(number);
		if (oldMaid.getOldMaidDealer().judgeEndGame()) {
			endGame();
		} else {
			drawHands();
		}
	}

	/*
	 * 手札の描画(なぜかターン数のラベルをセットしないとうまく描画してくれない)
	 */
	public void drawHands() {
		turnLabel.setText(TURN + oldMaid.getTurn());
		nextPlayerHandImagePanel.drawCards(oldMaid.getOldMaidDealer().getNextPlayer(), false);
		turnPlayerHandImagePanel.drawCards(oldMaid.getOldMaidDealer().getTurnPlayer(), true);
	}
}
