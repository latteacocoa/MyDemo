/**
 *
 */
package myAtCoder;

import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC085C {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		Scanner wScan = new Scanner(System.in);

		// 標準入力より値を取得 **********
		int wInOsatuNum = wScan.nextInt();
		int wInKingaku = wScan.nextInt();
		wScan.close();

		// 出力情報を生成し出力 **********
		for (int wIndex10000 = 0; wIndex10000 <= wInOsatuNum; wIndex10000++) {
			for (int wIndex5000 = 0; wIndex5000 <= wInOsatuNum - wIndex10000; wIndex5000++) {
				// 1000円札の枚数を求める
				int wOsatuNum1000 = wInOsatuNum - wIndex10000 - wIndex5000;

				// 金額一致の判定
				if ((wIndex10000 * 10000 + wIndex5000 * 5000 + wOsatuNum1000 * 1000) == wInKingaku) {
					System.out.printf("%d %d %d", wIndex10000, wIndex5000, wOsatuNum1000);
					return;
				}
			}
		}
		System.out.println("-1 -1 -1");
	}
}
