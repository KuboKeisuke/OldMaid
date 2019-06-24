package test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import frame.MainFrame;

/*
 * Swingでのテスト用クラス
 * */
public class OldMaidTest {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
