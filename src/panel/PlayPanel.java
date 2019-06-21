package panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oldMaid.OldMaidDealer;

public class PlayPanel extends JPanel implements ActionListener {
	private BorderLayout borderLayout = new BorderLayout();
	private JLabel playerLabel;
	private JLabel turnLabel;
	private JTextField text;
	private JButton startButton;
	private OldMaidDealer oldMaidDealer;

	public PlayPanel() {
		this.setLayout(borderLayout);
		oldMaidDealer = new OldMaidDealer();

		// プレイヤー
		playerLabel = new JLabel("");
		playerLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 60));
		playerLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(playerLabel, BorderLayout.NORTH);

		// ターン数
		turnLabel = new JLabel("ターン");
		turnLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 60));
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(turnLabel, BorderLayout.CENTER);

		// 入力
		text = new JTextField("1");
		JPanel textPanel = new JPanel();
		text.setPreferredSize(new Dimension(200, 40));
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		textPanel.add(text);
		this.add(textPanel, BorderLayout.SOUTH);

		// 選択ボタン作成
		startButton = new JButton("選択");
		startButton.setPreferredSize(new Dimension(100, 50));
		startButton.addActionListener(this);
		this.add(startButton, BorderLayout.EAST);
	}

	public void setTurn(String name) {
		turnLabel.setText(name);
	}

	public int getText() {
		return Integer.parseInt(text.getText());
	}

	/* NPC含めたプレイヤーの設定 */
	public void setPlayers(String name, int number) {
		oldMaidDealer.decidePlayer(name);
		oldMaidDealer.decideNPC(number);
		playerLabel.setText(name);
	}

	/*
	 * ゲームの前準備
	 */
	public void setGame() {
		// トランプ作成
		oldMaidDealer.getHand().makeDeck();
		// シャッフル
		oldMaidDealer.cardShuffle();
		// カードを配る
		oldMaidDealer.dealCards();
		// 開始前の下準備
		oldMaidDealer.startGame();
		// ターンプレイヤーの決定
		oldMaidDealer.decideTurnPlayer();
	}

	/* ゲーム終了処理 */
	public void endGame() {
		oldMaidDealer.endGame();
	}

	/*
	 * ボタンを押したとき プレイヤーの番を先にやったあと再びプレイヤーの番にするためNPCの番を行う
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 入力された値を取得
		int takeCardIndex = getText();
		// プレイヤーの番
		if (!oldMaidDealer.getTurnPlayer().getName().contains("NPC") && oldMaidDealer.getPlayersNumber() != 1) {
			oldMaidDealer.takeCard(takeCardIndex);
			oldMaidDealer.decideTurnPlayer();
		}
		// 再びプレイヤーの番になるまでNPCの番を行う
		while (oldMaidDealer.getTurnPlayer().getName().contains("NPC") && oldMaidDealer.getPlayersNumber() != 1) {
			oldMaidDealer.takeCard(-1);
			oldMaidDealer.decideTurnPlayer();
		}
	}
}
