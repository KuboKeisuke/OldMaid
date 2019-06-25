package frame;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import oldMaid.OldMaidPlayer;
import panel.GamePanel;
import panel.ResultPanel;
import panel.SettingPanel;
import panel.StartPanel;

/*
 * メインフレーム
 * */
public class MainFrame extends JFrame {
	// スタート画面名
	public static final String STARTPANEL = "STARTPANEL";
	// 設定画面名
	public static final String SETTINGPANEL = "SETTINGPANEL";
	// ゲーム画面名
	public static final String GAMEPANEL = "GAMEPANEL";
	// 結果画面名
	public static final String RESULTPANEL = "RESULTPANEL";

	// スタート画面
	private StartPanel startPanel;
	// 設定画面
	private SettingPanel settingPanel;
	// ゲーム画面
	private GamePanel gamePanel;
	// 結果画面
	private ResultPanel resultPanel;

	/*
	 * コンストラクタ
	 */
	public MainFrame() {
		// 表示位置とサイズ
		this.setBounds(150, 80, 1000, 600);
		// サイズ変更不可
		this.setResizable(true);

		startPanel = new StartPanel(this, STARTPANEL);
		settingPanel = new SettingPanel(this, SETTINGPANEL);
		gamePanel = new GamePanel(this, GAMEPANEL);
		resultPanel = new ResultPanel(this, RESULTPANEL);

		// フレームに画面追加
		this.add(startPanel);
		this.add(settingPanel);
		this.add(gamePanel);
		this.add(resultPanel);

		// スタート画面を最初に表示
		startPanel.setVisible(true);
		settingPanel.setVisible(false);
		gamePanel.setVisible(false);
		resultPanel.setVisible(false);
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
		// ゲーム画面に遷移
		case RESULTPANEL:
			nowPanel.setVisible(false);
			resultPanel.setVisible(true);
			break;
		}
	}

	/*
	 * プレイ画面にデータを送りゲームを行う
	 */
	public void setGame(String name, int number) {
		gamePanel.setGame(name, number);
	}

	public void setWinPlayers(ArrayList<OldMaidPlayer> winPlayers) {
		resultPanel.setWinPlayers(winPlayers);
	}
}
