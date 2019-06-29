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
		var wSc = new Scanner(System.in);

		// 標準入力より値を取得 **********
		var wInOsatuNum = wSc.nextInt();
		var wInKingaku = wSc.nextInt();
		wSc.close();

		// 出力情報を生成し出力 **********
		var wOutOsatuNum10000 = 0;
		var wOutOsatuNum5000 = 0;
		var wOutOsatuNum1000 = 0;
		var wBufferZangaku = wInKingaku;

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

		if (wBufferZangaku > 0) {
			wOutOsatuNum10000 = -1;
			wOutOsatuNum5000 = -1;
			wOutOsatuNum1000 = -1;
		}
		System.out.printf("%d %d %d%n", wOutOsatuNum10000, wOutOsatuNum5000, wOutOsatuNum1000);

		return;
	}



}
