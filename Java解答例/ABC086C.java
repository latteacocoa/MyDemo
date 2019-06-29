/**
 *
 */
package myAtCoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC086C {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		int wInValueN = wScan.nextInt();

		class Position {	// 時刻：T，座標：X, Y を保持するローカルクラス
			private int valueT = 0;
			private int valueX = 0;
			private int valueY = 0;
			public int getValueT() { return valueT; }
			public void setValueT(int valueT) { this.valueT = valueT; }
			public int getValueX() { return valueX; }
			public void setValueX(int valueX) {this.valueX = valueX; }
			public int getValueY() { return valueY; }
			public void setValueY(int valueY) { this.valueY = valueY; }
		}
		ArrayList<Position> wInPositions = new ArrayList<Position>();
		for (int wIndex = 0; wIndex < wInValueN; wIndex++) {
			Position wPosition = new Position();
			wPosition.setValueT(wScan.nextInt());
			wPosition.setValueX(wScan.nextInt());
			wPosition.setValueY(wScan.nextInt());
			wInPositions.add(wPosition);
		}
		wScan.close();

		// 出力情報を生成し出力 **********

		System.out.println(true ? "YES" : "NO");
	}
}
