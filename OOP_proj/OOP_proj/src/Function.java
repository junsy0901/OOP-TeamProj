import java.util.Scanner;
import java.util.regex.Pattern;

public class Function {
	static Scanner scanner = new Scanner(System.in);

	// 예 아니요 형태의 사용자 입력을 boolean 값으로 반환하는 함수
	public static boolean answer() {
		while (true) {
			System.out.print(">>(y/n)");
			String answer = scanner.nextLine();
			System.out.println("");
			if (answer.matches(".*(예|응|네|그래|오냐|ㅇ|(?i)y|(?i)yes)+.*"))
				return true;
			else if (answer.matches(".*(아니|별로|그닥|글쎄|그만|ㄴ|(?i)no|(?i)n)+.*"))
				return false;
		}
	}

	// 예 아니요 형태의 사용자 입력을 boolean 값으로 반환하는 함수
	public static int answer(String str) {
		if (str.matches(".*(예|응|네|그래|오냐|ㅇ|웅|(?i)y|(?i)yes)+.*"))
			return 1;
		else if (str.matches(".*(아니|별로|그닥|글쎄|그만|ㄴ|(?i)no|(?i)n)+.*"))
			return 0;
		return -1;
	}

	// 주문 내역을 출력하는 함수
	public static void printOrder() {
		int totalAmount = calculateTotal();
		System.out.println("");
		System.out.println("=== 주문 내역 ===");
		for (Order.OrderHistory item : Order.OrderHistory.orderhistory) {
			if (null == (item.option))
				System.out.println(item.name + "\t\t\t" + item.price + "원");
			else
				System.out.println(item.name + " (" + item.option + ")\t\t" + item.price + "원");
		}
		System.out.println("합계 \t\t\t" + totalAmount + "원");
		System.out.println("=================");
	}

	// 가격 총합을 계산하는 함수
	static int calculateTotal() {
		int sum = 0;
		int burger = 0, dessert = 0, beverage = 0;
		for (Order.OrderHistory item : Order.OrderHistory.orderhistory) {
			for (MenuList.MenuItem menu : MenuList.hamburgers) {
				if (menu instanceof MenuList.Hamburger) {
					if (item.name.equals(menu.name) && item.price == menu.price) {
						System.out.println("버거");
						sum = sum + item.price;
						burger++;
					}
				}
			}
			for (MenuList.MenuItem menu : MenuList.desserts) {
				if (item.name.equals(menu.name)) {
					System.out.println("디저트");
					sum = sum + item.price;
					dessert++;
				}
			}
			for (MenuList.MenuItem menu : MenuList.beverages) {
				if (item.name.equals(menu.name)) {
					System.out.println("음료");
					sum = sum + item.price;
					beverage++;
				}
			}
		}
		int min = Math.min(Math.min(burger, dessert), beverage);
		sum = sum - min * (1800 + 2000);	// 세트 가격만큼 감산
		return sum;
	}

	// 1초씩 딜레이를 주는 함수(다형성)
	static void timer() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 1초씩 딜레이를 주는 함수(다형성)
	static void timer(double n) {
		try {
			Thread.sleep((long) (n * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
