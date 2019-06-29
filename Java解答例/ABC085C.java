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
		int wOutOsatuNum10000 = 0;
		int wOutOsatuNum5000 = 0;
		int wOutOsatuNum1000 = 0;
		int wBufferZangaku = wInKingaku;

		// お札の枚数分，額の大きい方から減算し，各お札の枚数を計算する
		for (int wIndex = 0; wIndex < wInOsatuNum; wIndex++) {

			if (wBufferZangaku >= 10000) {
				wOutOsatuNum10000++;
				wBufferZangaku -= 10000;
				continue;
			}

			if (wBufferZangaku >= 5000) {
				wOutOsatuNum5000++;
				wBufferZangaku -= 5000;
				continue;
			}

			if (wBufferZangaku >= 1000) {
				wOutOsatuNum1000++;
				wBufferZangaku -= 1000;
				continue;
			}

			if (wBufferZangaku == 0) {
				break;
			}
		}

		// 計算後の残額が残っている場合，嘘
		if (wBufferZangaku > 0) {
			wOutOsatuNum10000 = -1;
			wOutOsatuNum5000 = -1;
			wOutOsatuNum1000 = -1;
		}
		System.out.printf("%d %d %d%n", wOutOsatuNum10000, wOutOsatuNum5000, wOutOsatuNum1000);
	}



}
