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
public class StartPanel extends JPanel {

	private MainFrame mainFrame;
	private BorderLayout borderLayout;

	public StartPanel(MainFrame mainFrame, String name) {
		this.mainFrame = mainFrame;
		this.setName(name);
		this.setSize(new Dimension(900, 500));
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		// タイトル作成
		JLabel titleLabel = new JLabel("ババ抜き");
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 60));
		titleLabel.setPreferredSize(new Dimension(100, 50));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(titleLabel, BorderLayout.NORTH);

		// 画像の読み込みとサイズ変更
		ImageIcon oldMaidImage = new ImageIcon("./image/OldMaid_Main.png");
		Image img = oldMaidImage.getImage();
		Image newimg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		oldMaidImage = new ImageIcon(newimg);

		// 画像作成
		JLabel imageLabel = new JLabel(oldMaidImage);
		this.add(imageLabel, BorderLayout.CENTER);

		// 設定画面遷移ボタン作成
		JButton settingButton = new JButton("はじめる");
		settingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSettingPanel();
			}
		});
		this.add(settingButton, BorderLayout.SOUTH);
	}

	// 設定画面に遷移
	public void showSettingPanel() {
		mainFrame.panelChange(this, MainFrame.SETTINGPANEL);
	}
}
