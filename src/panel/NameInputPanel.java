package panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * 設定画面のプレイヤー名入力パネル
 * */
public class NameInputPanel extends JPanel implements ActionListener {
	// レイアウト
	private BoxLayout boxLayout;

	// タイトル表示用コンポーネント
	private JLabel titleLabel;
	// プレイヤー名入力用コンポーネント
	private JTextField playerNameLabel;
	// プレイヤー名自動入力用ボタンコンポーネント
	private JButton setNameButton;

	// 文言
	private static final String PLAYER1 = "プレイヤー1";
	private static final String TITLELABEL = "名前";
	private static final String SETNAMEBUTTON = "「プレイヤー1」に設定";

	/*
	 * コンストラクタ
	 */
	public NameInputPanel() {
		// レイアウト設定 上から下に順にラベルを配置
		boxLayout = new BoxLayout(this, boxLayout.PAGE_AXIS);
		this.setLayout(boxLayout);

		// タイトル作成
		titleLabel = new JLabel(TITLELABEL);
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(titleLabel);

		// プレイヤー名入力
		playerNameLabel = new JTextField(PLAYER1);
		JPanel textPanel = new JPanel();
		playerNameLabel.setPreferredSize(new Dimension(200, 40));
		playerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(playerNameLabel);
		this.add(textPanel);

		// プレイヤー名自動設定
		setNameButton = new JButton(SETNAMEBUTTON);
		setNameButton.setPreferredSize(new Dimension(100, 50));
		setNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		setNameButton.addActionListener(this);
		this.add(setNameButton);
	}

	/*
	 * プレイヤー名のゲッター
	 */
	public String getName() {
		return playerNameLabel.getText();
	}

	/*
	 * ボタンを押したとき
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		// プレイヤー名自動設定ボタン
		case SETNAMEBUTTON:
			playerNameLabel.setText(PLAYER1);
			break;
		}
	}
}
