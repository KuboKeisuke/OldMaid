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

	private MainFrame mainFrame;
	private PlayPanel playPanel;
	private BorderLayout borderLayout;

	public GamePanel(MainFrame mainFrame, String name) {
		this.mainFrame = mainFrame;
		this.setName(name);
		this.setSize(mainFrame.getSize());
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		// プレイ画面パネル
		playPanel = new PlayPanel();
		this.add(playPanel);

		// スタート画面遷移ボタン作成
		JButton startButton = new JButton("タイトルへもどる");
		startButton.setPreferredSize(new Dimension(100, 50));
		startButton.addActionListener(this);
		this.add(startButton, BorderLayout.SOUTH);
	}

	/*
	 * プレイヤーの決定とゲームの準備
	 */
	public void setGame(String name, int number) {
		// プレイヤーの決定
		playPanel.setPlayers(name, number);
		// ゲームの準備
		playPanel.setGame();
	}

	// スタート画面に遷移
	public void showStartPanel() {
		mainFrame.panelChange(this, MainFrame.STARTPANEL);
	}

	/*
	 * ボタンを押したとき
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// ゲーム終了
		playPanel.endGame();
		// スタート画面に戻る
		showStartPanel();
	}

}
