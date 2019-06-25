package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		// メインフレームのサイズだとおかしくなる(仮置き)
		this.setSize(new Dimension(950, 550));

		// レイアウト設定
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		// タイトル作成
		titleLabel = new JLabel(OLDMAID);
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 60));
		titleLabel.setPreferredSize(new Dimension(100, 50));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(titleLabel, BorderLayout.NORTH);

		// 画像の読み込みとサイズ変更
		ImageIcon oldMaidImage = new ImageIcon(OLDMAIDSTARTIMGPATH);
		Image img = oldMaidImage.getImage();
		Image newimg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		oldMaidImage = new ImageIcon(newimg);
		// 画像作成
		imageLabel = new JLabel(oldMaidImage);
		this.add(imageLabel, BorderLayout.CENTER);

		// 設定画面遷移ボタン作成
		settingButton = new JButton(GOSETTING);
		settingButton.addActionListener(this);
		this.add(settingButton, BorderLayout.SOUTH);
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
