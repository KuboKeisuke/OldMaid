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

public class NumberInputPanel extends JPanel {

	// 人数
	private int number = 3;
	// 入力用コンポーネント
	private JTextField text;

	/*
	 * コンストラクタ
	 */
	public NumberInputPanel() {
		// 上から下に順にラベルを配置
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// タイトル作成
		JLabel titleLabel = new JLabel("人数");
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(titleLabel);

		// 人数入力
		text = new JTextField("3");
		JPanel textPanel = new JPanel();
		text.setPreferredSize(new Dimension(200, 40));
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(text);
		this.add(textPanel);

		// CPU自動設定
		JButton startButton = new JButton("CPUを3人に設定");
		startButton.setPreferredSize(new Dimension(100, 50));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showStartPanel();
			}
		});
		this.add(startButton);
	}

	public int getNumber() {
		return number;
	}

	public void showStartPanel() {
		text.setText("3");
		number = 3;
	}
}
