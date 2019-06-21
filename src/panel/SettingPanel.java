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
 * 設定画面
 * */
public class SettingPanel extends JPanel implements ActionListener {
	// メインフレーム
	private MainFrame mainFrame;
	// レイアウト
	private BorderLayout borderLayout;

	// タイトル表示用コンポーネント
	private JLabel titleLabel;
	// 入力用パネル
	private JPanel insertPanel;
	// 人数入力用コンポーネント
	private NumberInputPanel numberPanel;
	// プレイヤー名入力用コンポーネント
	private NameInputPanel namePanel;
	// ボタン用パネル
	private JPanel buttonPanel;
	// ゲームへすすむボタンコンポーネント
	private JButton gameButton;
	// タイトルへもどるボタンコンポーネント
	private JButton startButton;

	// 文言
	private static final String SETTINGPANEL = "設定画面";
	private static final String GOGAME = "ゲームへすすむ";
	private static final String GOSTART = "タイトルへもどる";

	/*
	 * コンストラクタ
	 */
	public SettingPanel(MainFrame mainFrame, String name) {
		this.mainFrame = mainFrame;
		this.setName(name);
		this.setSize(mainFrame.getSize());

		// レイアウト設定
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		// タイトル作成
		titleLabel = new JLabel(SETTINGPANEL);
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 60));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(titleLabel, BorderLayout.NORTH);

		// 入力用パネル(下の二つを入れる)
		insertPanel = new JPanel();
		insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.LINE_AXIS));

		// 人数設定パネル作成
		numberPanel = new NumberInputPanel();
		insertPanel.add(numberPanel);

		// 名前設定パネル作成
		namePanel = new NameInputPanel();
		insertPanel.add(namePanel);
		this.add(insertPanel, BorderLayout.CENTER);

		// ボタン用のパネル
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

		// ゲーム画面遷移ボタン作成
		gameButton = new JButton(GOGAME);
		gameButton.setPreferredSize(new Dimension(100, 50));
		gameButton.addActionListener(this);
		buttonPanel.add(gameButton);

		// スタート画面遷移ボタン作成
		startButton = new JButton(GOSTART);
		startButton.setPreferredSize(new Dimension(100, 50));
		startButton.addActionListener(this);
		buttonPanel.add(startButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	/*
	 * ボタンを押したとき
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		// ゲームへすすむボタン
		case GOGAME:
			mainFrame.panelChange(this, MainFrame.GAMEPANEL);
			mainFrame.setGame(namePanel.getName(), numberPanel.getNumber());
			break;
		// タイトルへもどるボタン
		case GOSTART:
			mainFrame.panelChange(this, MainFrame.STARTPANEL);
			break;
		}
	}
}
