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
 * 設定画面のNPC人数入力パネル
 * */
public class NumberInputPanel extends JPanel implements ActionListener {
	// レイアウト
	private BoxLayout boxLayout;

	// タイトル表示用コンポーネント
	private JLabel titleLabel;
	// 人数入力用コンポーネント
	private JTextField numberLabel;
	// 人数自動入力用ボタンコンポーネント
	private JButton setNumberButton;

	// 文言
	private static final int THREE = 3;
	private static final String TITLELABEL = "人数";
	private static final String SETNUMBERBUTTON = "CPUを3人に設定";

	/*
	 * コンストラクタ
	 */
	public NumberInputPanel() {
		JPanel jpanel = new JPanel();
		// レイアウト設定 上から下に順にラベルを配置
		boxLayout = new BoxLayout(jpanel, boxLayout.PAGE_AXIS);
		jpanel.setLayout(boxLayout);

		// タイトル作成
		titleLabel = new JLabel(TITLELABEL);
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpanel.add(titleLabel);

		// 人数入力
		numberLabel = new JTextField(String.valueOf(THREE));
		JPanel textPanel = new JPanel();
		numberLabel.setPreferredSize(new Dimension(200, 40));
		numberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(numberLabel);
		jpanel.add(textPanel);

		// CPU自動設定
		setNumberButton = new JButton(SETNUMBERBUTTON);
		setNumberButton.setPreferredSize(new Dimension(100, 50));
		setNumberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		setNumberButton.addActionListener(this);
		jpanel.add(setNumberButton);
		this.add(jpanel);
	}

	// 人数のゲッター
	public int getNumber() {
		int number = 0;
		number = Integer.valueOf(numberLabel.getText());
		return number;
	}

	/*
	 * ボタンを押したとき
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		// 人数自動設定ボタン
		case SETNUMBERBUTTON:
			numberLabel.setText(String.valueOf(THREE));
		}
	}
}
