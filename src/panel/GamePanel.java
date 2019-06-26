package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import frame.MainFrame;

/*
 * ゲーム画面
 * */
public class GamePanel extends JPanel implements ActionListener {
	// メインフレーム
	private MainFrame mainFrame;
	// レイアウト
	private BorderLayout borderLayout;

	// プレイパネルコンポーネント
	private OldMaidPlayPanel playPanel;
	// タイトルに戻るボタンコンポーネント
	private JButton startButton;

	// 文言
	private static final String GOSTART = "タイトルへもどる";

	/*
	 * コンストラクタ
	 */
	public GamePanel(MainFrame mainFrame, String name) {
		this.mainFrame = mainFrame;
		this.setName(name);

		// レイアウト設定
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		// プレイ画面パネル
		playPanel = new OldMaidPlayPanel(mainFrame, this);
		this.add(playPanel, BorderLayout.CENTER);

		// スタート画面遷移ボタン作成
		startButton = new JButton(GOSTART);
		startButton.setPreferredSize(new Dimension(100, 50));
		startButton.addActionListener(this);
		this.add(startButton, BorderLayout.SOUTH);
	}

	/*
	 * プレイヤーの決定とゲームの準備
	 */
	public void setGame(String name, int number) {
		// ゲームの準備
		playPanel.setGame(name, number);
	}

	/*
	 * ボタンを押したとき
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		// スタート画面に戻るボタン
		case GOSTART:
			// ゲーム終了
			System.out.println("途中終了");
			playPanel.initializeOldMaid();
			mainFrame.panelChange(this, MainFrame.STARTPANEL);
			break;
		}
	}
}
