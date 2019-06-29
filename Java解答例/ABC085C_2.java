/**
 *
 */
package myAtCoder;

import java.util.Scanner;

/**
 * @author
 *
 */
public class ABC085C_2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 標準入力取得用オブジェクト **********
		var wSc = new Scanner(System.in);

		// 標準入力より値を取得 **********
		var wInOsatuNum = wSc.nextInt();
		var wInKingaku = wSc.nextInt();
		wSc.close();

		// 出力情報を生成し出力 **********
		// 金額を10000, 5000, 1000 の順に除算した時の商を求め、お札の枚数・合計金額 それぞれの条件をチェック
		var wOutOsatuNum10000 = wInKingaku / 10000;
		var wOutOsatuNum5000 = (wInKingaku - (10000 * wOutOsatuNum10000)) / 5000;
		var wOutOsatuNum1000 = (wInKingaku - (10000 * wOutOsatuNum10000) - (5000 * wOutOsatuNum5000)) / 1000;

		if (wInOsatuNum < (wOutOsatuNum10000 + wOutOsatuNum5000 + wOutOsatuNum1000) ||
			wInKingaku != (wOutOsatuNum10000 * 10000 + wOutOsatuNum5000 * 5000 + wOutOsatuNum1000 * 1000)) {
			// 求めたお札の合計枚数が足りない 又は、金額が計算値と合わない場合

			wOutOsatuNum10000 = -1;
			wOutOsatuNum5000 = -1;
			wOutOsatuNum1000 = -1;
		}

		System.out.printf("%d %d %d%n", wOutOsatuNum10000, wOutOsatuNum5000, wOutOsatuNum1000);

		return;
	}



}
