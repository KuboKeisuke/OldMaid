package panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import frame.MainFrame;
import oldMaid.OldMaidPlayer;

public class ResultPanel extends JPanel implements ActionListener {
	// メインフレーム
	private MainFrame mainFrame;
	// レイアウト
	private BorderLayout borderLayout;

	// タイトル表示用コンポーネント
	private JTextPane titleLabel;
	// タイトルに戻るボタンコンポーネント
	private JButton startButton;

	// 文言
	private static final String GOSTART = "タイトルへもどる";

	/*
	 * コンストラクタ
	 */
	public ResultPanel(MainFrame mainFrame, String name) {
		this.mainFrame = mainFrame;
		this.setName(name);
		// レイアウト設定
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		// タイトル作成
		titleLabel = new JTextPane();
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(titleLabel, BorderLayout.CENTER);

		// スタート画面遷移ボタン作成
		startButton = new JButton(GOSTART);
		startButton.setPreferredSize(new Dimension(100, 50));
		startButton.addActionListener(this);
		this.add(startButton, BorderLayout.SOUTH);
	}

	public void setWinPlayers(ArrayList<OldMaidPlayer> winPlayers) {
		String a = "";
		for (int i = 0; i < winPlayers.size(); i++) {
			a += i + 1 + "位：" + winPlayers.get(i).getName() + "\n";
		}
		titleLabel.setText(a);
	}

	/*
	 * ボタンを押したとき
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		// タイトルへもどるボタン
		case GOSTART:
			mainFrame.panelChange(this, MainFrame.STARTPANEL);
			break;
		}
	}
}
