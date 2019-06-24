package panel;

import javax.swing.JPanel;

import oldMaid.OldMaidPlayer;

/*
 * 手札の画像パネル
 * */
public class HandImagePanel extends JPanel {
	// ババ抜きのパネル
	private OldMaidPlayPanel oldMaidPlayPanel;

	/*
	 * コンストラクタ
	 */
	public HandImagePanel(OldMaidPlayPanel oldMaidPlayPanel) {
		this.oldMaidPlayPanel = oldMaidPlayPanel;
	}

	/*
	 * カードを選択 クリックされたカードのインデックスが引数
	 */
	public void selectCard(int index) {
		oldMaidPlayPanel.selectCard(index);
	}

	public void drawCards(OldMaidPlayer player, boolean showCards) {
		// 一旦全てのコンポーネントを削除
		this.removeAll();
		this.repaint();

		// 表面か裏面かでコンストラクタの引数にカードを入れるか決める
		if (showCards) {
			for (int i = 0; i < player.getHand().size(); i++) {
				CardsLabel cardsLabel = new CardsLabel(this, i, player.getHand().get(i));
				this.add(cardsLabel);
			}
		} else {
			for (int i = 0; i < player.getHand().size(); i++) {
				CardsLabel cardsLabel = new CardsLabel(this, i, null);
				this.add(cardsLabel);
			}
		}
	}
}
