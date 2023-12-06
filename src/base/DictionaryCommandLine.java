package base;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandLine extends Dictionary {

	public static String showAllWords() {
		String ans = "";
		System.out.printf("%-6s%c %-15s%c %-20s%n", "No", '|', "English", '|', "Vietnamese");
		for (int i = 0; i < oldVocab.size(); i++) {
			System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1, '|', oldVocab.get(i).getSearching(), '|',
					oldVocab.get(i).getMeaning());
		}
		return ans;
	}

	public static void addWord() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter English word: ");
		String word = scanner.nextLine();
		System.out.print("Enter Vietnamese meaning: ");
		String meaning = scanner.nextLine();

		// Tạo một Word mới và thêm vào danh sách từ điển
		Word newWord = new Word(word, meaning);
		oldVocab.add(newWord);

		System.out.println("Word added successfully!");
	}

	public static void removeWord() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of the word to remove: ");
		int index = scanner.nextInt();

		// Kiểm tra xem index có hợp lệ không
		if (index >= 1 && index <= oldVocab.size()) {
			oldVocab.remove(index - 1);
			System.out.println("Word removed successfully!");
		} else {
			System.out.println("Invalid index!");
		}
	}

	public static void updateWord() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of the word to update: ");
		int index = scanner.nextInt();

		// Kiểm tra xem index có hợp lệ không
		if (index >= 1 && index <= oldVocab.size()) {
			System.out.print("Enter new English word: ");
			String newWord = scanner.next();
			System.out.print("Enter new Vietnamese meaning: ");
			String newMeaning = scanner.next();

			// Cập nhật thông tin từ điển
			oldVocab.get(index - 1).setSearching(newWord);
			oldVocab.get(index - 1).setMeaning(newMeaning);

			System.out.println("Word updated successfully!");
		} else {
			System.out.println("Invalid index!");
		}
	}

	public static void dictionaryAdvanced() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Welcome to my app");
			System.out.println("[0] Exit ");
			System.out.println("[1] Add");
			System.out.println("[2] Remove");
			System.out.println("[3] Update");
			System.out.println("[4] Display");
			System.out.println("[5] Lookup");
			System.out.println("[6] Search");
			System.out.println("[7] Game");
			System.out.println("[8] Import from file");
			System.out.println("[9] Export to file");

			System.out.print("Your action: ");

			int select = scanner.nextInt();
			switch (select) {
				case 0:
					System.exit(0);
					break;
				case 1:
					addWord();
					break;
				case 2:
					removeWord();
					break;
				case 3:
					updateWord();
					break;
				default:
					System.out.println("Action not supported");
			}
		}
	}

	public static void main(String[] args) {
		// Bạn có thể gọi các phương thức khác từ đây hoặc chạy chương trình của bạn tùy ý.
		// Ví dụ: DictionaryManagement.insertFromFile();
		dictionaryAdvanced();
	}
}
