package ru.gb.notes.core;

import java.util.Scanner;

/**
 * Класс "Заметка".
 * Содержит заголовок и текст заметки.
 */
public class Note {

	String caption;
	String text;
	Scanner scanner;

	/**
	 * Конструктов без параметров
	 */
	public Note() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Получает заголовок заметки.
	 * 
	 * @return Заголовок
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Получает текст заметки.
	 * 
	 * @return Текст заметки.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Запрашивает у пользователя и получает с консоли заголовок заметки.
	 * 
	 * @return Результат операции: успешно или не успешно
	 */
	public boolean setCaption() {
		System.out.println("Введите заголовок заметки:");
		this.caption = scanner.nextLine();
		return true;
	}

	/**
	 * Запрашивает у пользователя и получает с консоли текст заметки.
	 * 
	 * @return Результат операции: успешно или не успешно
	 */
	public boolean setText() {
		System.out.println("Введите текст заметки:");
		this.text = scanner.nextLine();
		return true;
	};

}
