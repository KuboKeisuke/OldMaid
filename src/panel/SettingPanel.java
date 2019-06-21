package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;

/*
 * ゲーム直前の設定画面
 * */
public class SettingPanel extends JPanel {

	private MainFrame mainFrame;
	private BorderLayout borderLayout;
	private NumberInputPanel numberPanel;
	private NameInputPanel namePanel;

	public SettingPanel(MainFrame mainFrame, String name) {
		this.mainFrame = mainFrame;
		this.setName(name);
		this.setSize(mainFrame.getSize());
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		// タイトル作成
		JLabel titleLabel = new JLabel("設定画面");
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 60));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(titleLabel, BorderLayout.NORTH);

		// 入力用パネル
		JPanel insertPanel = new JPanel();
		insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.LINE_AXIS));

		// 人数設定パネル作成
		numberPanel = new NumberInputPanel();
		insertPanel.add(numberPanel);

		// 名前設定パネル作成
		namePanel = new NameInputPanel();
		insertPanel.add(namePanel);
		this.add(insertPanel, BorderLayout.CENTER);

		// ボタン用のパネル
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

		// ゲーム画面遷移ボタン作成
		JButton gameButton = new JButton("ゲームへすすむ");
		gameButton.setPreferredSize(new Dimension(100, 50));
		gameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showGamePanel();
			}
		});
		buttonPanel.add(gameButton);

		// スタート画面遷移ボタン作成
		JButton startButton = new JButton("タイトルへもどる");
		startButton.setPreferredSize(new Dimension(100, 50));
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showStartPanel();
			}
		});
		buttonPanel.add(startButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	// ゲーム画面に遷移(今後変更予定)
	public void showGamePanel() {
		System.out.printf("プレイヤー名：%s\n", namePanel.getName());
		System.out.printf("NPC人数：%d人\n", numberPanel.getNumber());
		mainFrame.panelChange(this, MainFrame.GAMEPANEL);
		mainFrame.setGame(namePanel.getName(), numberPanel.getNumber());
	}

	// スタート画面に遷移
	public void showStartPanel() {
		mainFrame.panelChange(this, MainFrame.STARTPANEL);
	}
}
