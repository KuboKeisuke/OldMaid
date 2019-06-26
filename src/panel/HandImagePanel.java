package panel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oldMaid.OldMaidPlayer;

/*
 * 手札の画像パネル
 * */
public class HandImagePanel extends JPanel {
	// ババ抜きのパネル
	private OldMaidPlayPanel oldMaidPlayPanel;
	// レイアウト
	private BoxLayout boxLayout;
	// プレイヤー名表示用コンポーネント
	private JLabel nameLabel;
	// 手札表示用パネル
	private JPanel handPanel;

	// 文言
	private static final String HANDOF = "の手札";

	/*
	 * コンストラクタ
	 */
	public HandImagePanel(OldMaidPlayPanel oldMaidPlayPanel) {
		// レイアウト設定 上から下に順にラベルを配置
		boxLayout = new BoxLayout(this, boxLayout.PAGE_AXIS);
		this.setLayout(boxLayout);

		// ババ抜きパネル
		this.oldMaidPlayPanel = oldMaidPlayPanel;

		// タイトル作成
		nameLabel = new JLabel();
		nameLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 30));
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// プレイヤー名表示パネル
		handPanel = new JPanel();
	}

	/*
	 * カードを選択 クリックされたカードのインデックスが引数
	 */
	public void selectCard(int index) {
		oldMaidPlayPanel.selectCard(index);
	}

	/*
	 * プレイヤー名とカード
	 */
	public void drawCards(OldMaidPlayer player, boolean showCards) {
		// 一旦全てのコンポーネントを削除
		handPanel.removeAll();
		handPanel.repaint();

		// 表面か裏面かでコンストラクタの引数にカードを入れるか決める
		if (showCards) {
			for (int i = 0; i < player.getHand().size(); i++) {
				CardsLabel cardsLabel = new CardsLabel(this, i, player.getHand().get(i));
				handPanel.add(cardsLabel);
				nameLabel.setText(oldMaidPlayPanel.getOldMaid().getOldMaidDealer().getTurnPlayer().getName() + HANDOF);
			}
		} else {
			for (int i = 0; i < player.getHand().size(); i++) {
				CardsLabel cardsLabel = new CardsLabel(this, i, null);
				handPanel.add(cardsLabel);
				nameLabel.setText(oldMaidPlayPanel.getOldMaid().getOldMaidDealer().getNextPlayer().getName() + HANDOF);
			}
		}
		this.add(nameLabel);
		this.add(handPanel);
	}
}
