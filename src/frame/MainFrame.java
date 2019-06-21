package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import panel.GamePanel;
import panel.SettingPanel;
import panel.StartPanel;

/*
 * メインフレーム
 * */
public class MainFrame extends JFrame {
	public static final String STARTPANEL = "STARTPANEL";
	public static final String SETTINGPANEL = "SETTINGPANEL";
	public static final String GAMEPANEL = "GAMEPANEL";

	private StartPanel startPanel;
	private SettingPanel settingPanel;
	private GamePanel gamePanel;

	public MainFrame() {
		this.setBounds(150, 80, 1000, 600);
		this.setResizable(false);

		startPanel = new StartPanel(this, STARTPANEL);
		settingPanel = new SettingPanel(this, SETTINGPANEL);
		gamePanel = new GamePanel(this, GAMEPANEL);

		// フレームに画面追加
		this.add(startPanel);
		this.add(settingPanel);
		this.add(gamePanel);

		startPanel.setVisible(true);
		settingPanel.setVisible(false);
		gamePanel.setVisible(false);
		this.setVisible(true);
	}

	/*
	 * 画面遷移 名前を指定してそこに変える
	 */
	public void panelChange(JPanel nowPanel, String name) {
		switch (name) {
		// スタートパネルに遷移
		case STARTPANEL:
			nowPanel.setVisible(false);
			startPanel.setVisible(true);
			break;
		// 設定パネルに遷移
		case SETTINGPANEL:
			nowPanel.setVisible(false);
			settingPanel.setVisible(true);
			break;
		// ゲーム画面に遷移
		case GAMEPANEL:
			nowPanel.setVisible(false);
			gamePanel.setVisible(true);
			break;
		}
	}

	/* プレイ画面にデータを送りゲームを行う */
	public void setGame(String name, int number) {
		gamePanel.setGame(name, number);
	}
}
