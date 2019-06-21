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

public class NameInputPanel extends JPanel {
	// 入力用コンポーネント
	JTextField text;

	/*
	 * コンストラクタ
	 */
	public NameInputPanel() {
		// 上から下に順にラベルを配置
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// タイトル作成
		JLabel titleLabel = new JLabel("名前");
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(titleLabel);

		// 人数入力
		text = new JTextField("プレイヤー1");
		JPanel textPanel = new JPanel();
		text.setPreferredSize(new Dimension(200, 40));
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(text);
		this.add(textPanel);

		// プレイヤー名自動設定
		JButton setNameButton = new JButton("「プレイヤー1」に設定");
		setNameButton.setPreferredSize(new Dimension(100, 50));
		setNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		setNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setNameAuto();
			}
		});
		this.add(setNameButton);
	}

	public String getName() {
		return text.getText();
	}

	public void setNameAuto() {
		text.setText("プレイヤー1");
	}
}