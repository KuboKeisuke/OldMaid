package test;

import javax.swing.JFrame;

import oldMaid.OldMaidPlayer;

/*
 * ババ抜きのテストに追加する前のお試し用テストクラス
 * */
public class UITest {

	public static void main(String[] args) {
		OldMaidPlayer player = new OldMaidPlayer("");
		// HandImagePanel imageTestPanel = new HandImagePanel();

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(0, 0, 1000, 600);
		f.setTitle("画像表示");
		// f.add(imageTestPanel);
		f.setVisible(true);
	}
}
