package panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;

/*
 * 最初のスタート画面
 * */
public class StartPanel extends JPanel implements ActionListener {
	// メインフレーム
	private MainFrame mainFrame;
	// レイアウト
	private BorderLayout borderLayout;

	// タイトル表示用コンポーネント
	private JLabel titleLabel;
	// 画像表示用コンポーネント
	private JLabel imageLabel;
	// 設定へすすむボタンコンポーネント
	private JButton settingButton;

	// 文言
	private static final String OLDMAID = "ババ抜き";
	private static final String GOSETTING = "はじめる";
	public static final String OLDMAIDSTARTIMGPATH = "./image/OldMaid_Main.png";

	/*
	 * コンストラクタ
	 */
	public StartPanel(MainFrame mainFrame, String name) {
		this.mainFrame = mainFrame;
		this.setName(name);

		// レイアウト設定
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		// タイトル作成
		JPanel titlePanel = new JPanel();
		titleLabel = new JLabel(OLDMAID);
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 70));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.CENTER);
		titlePanel.add(titleLabel);
		this.add(titlePanel, BorderLayout.NORTH);

		// レイアウト整理用パネル(画像と設定画面遷移ボタンを格納)
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));

		// 画像の読み込みとサイズ変更
		ImageIcon oldMaidImage = new ImageIcon(OLDMAIDSTARTIMGPATH);
		Image img = oldMaidImage.getImage();
		Image newimg = img.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		oldMaidImage = new ImageIcon(newimg);
		// 画像作成
		imageLabel = new JLabel(oldMaidImage);
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		imagePanel.add(imageLabel);

		// 隙間の作成
		imagePanel.add(Box.createRigidArea(new Dimension(10, 10)));

		// 設定画面遷移ボタン作成
		settingButton = new JButton(GOSETTING);
		settingButton.addActionListener(this);
		settingButton.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
		settingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		imagePanel.add(settingButton);
		this.add(imagePanel, BorderLayout.CENTER);
		this.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.SOUTH);
	}

	/*
	 * ボタンを押したとき
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		// 設定画面へすすむボタン
		case GOSETTING:
			mainFrame.panelChange(this, MainFrame.SETTINGPANEL);
			break;
		}
	}
}
