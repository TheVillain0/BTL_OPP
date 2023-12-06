package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HangmanController implements Initializable {
	@FXML
	ImageView img;
	Image image2 = new Image(getClass().getResourceAsStream("/resource/media/hangman/2.png"));
	Image image3 = new Image(getClass().getResourceAsStream("/resource/media/hangman/3.png"));
	Image image4 = new Image(getClass().getResourceAsStream("/resource/media/hangman/4.png"));
	Image image5 = new Image(getClass().getResourceAsStream("/resource/media/hangman/5.png"));
	Image image6 = new Image(getClass().getResourceAsStream("/resource/media/hangman/6.png"));
	Image image7 = new Image(getClass().getResourceAsStream("/resource/media/hangman/7.png"));
	Image image8 = new Image(getClass().getResourceAsStream("/resource/media/hangman/8.png"));

	@FXML
	Label tf1;
	@FXML
	TextField input;
	@FXML
	Label letter_count;

	ArrayList<String> wordList;

	private String word;
	private String wordEncode;

	private boolean startFirst = false;

	private Random rand = new Random();

	private void changeWord() {

		String guessWord = this.wordList.get(rand.nextInt(this.wordList.size()));

		this.word = guessWord;
	}

	@SuppressWarnings("unused")
	private void encodeWord() {
		String guessWordEncode = this.word;

		int guessTotal = 0;
		int halfTextNum = Math.round(guessWordEncode.length() / 2);
		while (guessTotal < 2) {
			for (int i = 0; i < guessWordEncode.length(); i++) {
				char wordEncodeChar = this.word.charAt(i);
				if (guessTotal > halfTextNum)
					break;
				if ((rand.nextInt(2) > 0 ? true : false) && (wordEncodeChar != ' ' && wordEncodeChar != '-')) {
					String partBefore = guessWordEncode.substring(0, i);
					String partAfter = guessWordEncode.substring(i + 1);

					guessWordEncode = partBefore + "_" + partAfter;
					guessTotal++;
				}
			}
		}

//			this.guessWord = guessWord;
		this.wordEncode = guessWordEncode;
	}

	public void generateWordList() {
		try {
			File fileReader = new File(System.getProperty("user.dir") + "/src/resource/vocab/eng_vie.txt");
			Scanner reader = new Scanner(fileReader);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				String word = data.split("<html>")[0];
				if (word.length() > 5)
					this.wordList.add(word);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public HangmanController() {
		this.wordList = new ArrayList<String>();
		this.generateWordList();
		this.restartGame();
	}

	public void CheckInput() {
		String str = input.getText();
		boolean find = false;
		for (int i = 0; i < this.word.length(); i++) {
			String wordAtPos = Character.toString(this.word.charAt(i));
			char wordEncodeChar = this.wordEncode.charAt(i);
			if (wordEncodeChar == '_' && wordAtPos.equalsIgnoreCase(str)) {
				String partBefore = this.wordEncode.substring(0, i);
				String partAfter = this.wordEncode.substring(i + 1);

				this.wordEncode = partBefore + wordAtPos + partAfter;
				find = true;
			}
		}
		if (!find)
			setImage();
		else
			setLetter();
	}

	public void setLetter() {
		this.tf1.setText(this.wordEncode);
	}

	public void initialize(URL url, ResourceBundle resourceBundle) {
		input.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 1) {
				newValue = newValue.substring(newValue.length() - 1, newValue.length());
				input.setText(newValue);
			}
		});
		setLetter();
		this.startFirst = true;
	}

	int defaultLife = 7;
	private int life = defaultLife;

	public void setImage() {
		if (life == 7)
			img.setImage(image2);
		if (life == 6)
			img.setImage(image3);
		else if (life == 5)
			img.setImage(image4);
		else if (life == 4)
			img.setImage(image5);
		else if (life == 3)
			img.setImage(image6);
		else if (life == 2)
			img.setImage(image7);
		else if (life == 1)
			img.setImage(image8);
		life--;
	}

	public void restartGame() {
		this.life = this.defaultLife;

		this.changeWord();
		this.encodeWord();

		if (this.startFirst)
			setLetter();
	}
}
