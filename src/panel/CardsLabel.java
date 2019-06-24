package panel;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import cards.Cards;

/*
 * カード1枚ごとの表示ラベル
 * */
public class CardsLabel extends JLabel implements MouseListener {
	// マウスクリック時に実行される用のパネル
	private HandImagePanel imageTestPanel;
	// カードの並びのインデックス
	private int index;
	// トランプの中身 裏面の場合はnullとなる
	private Cards card;

	// 文言
	private static final String CARDSIMAGEPATH = "./image/Cards/card_";
	private static final String BACK = "back";
	private static final String PNG = ".png";

	/*
	 * トランプのコンストラクタ
	 */
	public CardsLabel(HandImagePanel imageTestPanel, int index, Cards card) {
		this.imageTestPanel = imageTestPanel;
		this.index = index;
		this.card = card;
		// 画像作成
		this.setCardsImage();
		this.addMouseListener(this);
	}

	// マウスをコンポーネント上でクリックしたとき
	@Override
	public void mouseClicked(MouseEvent e) {
		// 裏面の場合は選択したカードを取る
		if (card == null) {
			imageTestPanel.selectCard(index);
			this.setLocation(this.getX(), this.getY() + 10);
		}
	}

	// マウスのボタンがコンポーネント上で押されたとき
	@Override
	public void mousePressed(MouseEvent e) {

	}

	// マウスのボタンがコンポーネント上で離れたとき
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	// マウスがコンポーネントに入ったとき
	@Override
	public void mouseEntered(MouseEvent e) {
		// カーソル上にあることがわかるようにやや上に移動
		this.setLocation(this.getX(), this.getY() - 10);
	}

	// マウスがコンポーネントから出たとき
	@Override
	public void mouseExited(MouseEvent e) {
		// カーソル上から離れたことがわかるようにやや上に移動
		this.setLocation(this.getX(), this.getY() + 10);
	}

	/*
	 * カードイメージをセット
	 */
	private void setCardsImage() {
		ImageIcon oldMaidImage;
		// 裏面の場合は裏面の画像
		if (card == null) {
			oldMaidImage = new ImageIcon(CARDSIMAGEPATH + BACK + PNG);
		} else {
			String id = "";
			// ジョーカーの場合
			if (card.getSuit() == Cards.Suit.JOKER) {
				id = card.getSuit().toString().toLowerCase();
			}
			// それ以外の場合
			else {
				id = card.getSuit().toString().toLowerCase() + "_" + String.format("%02d", card.getNumber());
			}
			oldMaidImage = new ImageIcon(CARDSIMAGEPATH + id + PNG);
		}
		Image newimg = oldMaidImage.getImage().getScaledInstance(70, 105, Image.SCALE_SMOOTH);
		oldMaidImage = new ImageIcon(newimg);
		this.setIcon(oldMaidImage);
	}
}
