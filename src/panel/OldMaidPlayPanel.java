package panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oldMaid.OldMaid;

/*
 * ゲーム情報を伝えるパネル
 * */
public class OldMaidPlayPanel extends JPanel implements ActionListener {
	// レイアウト
	private BorderLayout borderLayout;

	// プレイヤー名表示用コンポーネント
	private JLabel playerLabel;
	// ターン数表示用コンポーネント
	private JLabel turnLabel;
	// カードを引くための入力用コンポーネント(仮置き)
	private JTextField numberLabel;
	// カード選択完了ボタンコンポーネント(仮置き)
	private JButton selectButton;
	// ババ抜きクラス
	private OldMaid oldMaid;

	// 文言
	private static final String TURN = "ターン";
	private static final String SELECT = "選択";

	/*
	 * コンストラクタ
	 */
	public OldMaidPlayPanel() {
		// レイアウト設定
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		// ババ抜き
		oldMaid = new OldMaid();

		// プレイヤー
		playerLabel = new JLabel();
		playerLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 60));
		playerLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(playerLabel, BorderLayout.NORTH);

		// ターン数
		turnLabel = new JLabel(TURN);
		turnLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 60));
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(turnLabel, BorderLayout.CENTER);

		// 入力(仮置き)
		numberLabel = new JTextField("1");
		JPanel textPanel = new JPanel();
		numberLabel.setPreferredSize(new Dimension(200, 40));
		numberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(numberLabel);
		this.add(textPanel, BorderLayout.SOUTH);

		// カード選択ボタン作成
		selectButton = new JButton(SELECT);
		selectButton.setPreferredSize(new Dimension(100, 50));
		selectButton.addActionListener(this);
		this.add(selectButton, BorderLayout.EAST);
	}

	// トランプを引くときのインデックスラベルを数値に変換(仮置き)
	public int getText() {
		return Integer.parseInt(numberLabel.getText());
	}

	/*
	 * ゲームの前準備
	 */
	public void setGame(String name, int number) {
		playerLabel.setText(name);
		oldMaid.setGame(name, number);
		turnLabel.setText(TURN + oldMaid.getTurn());
	}

	/*
	 * ゲーム終了処理
	 */
	public void endGame() {
		oldMaid.endGame();
	}

	/*
	 * ボタンを押したとき
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		// カード選択ボタン
		case SELECT:
			oldMaid.playOldMaid(getText());
			turnLabel.setText(TURN + oldMaid.getTurn());
		}
	}
}
