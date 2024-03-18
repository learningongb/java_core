package ru.gb.notes;

import ru.gb.notes.core.Note;
import ru.gb.notes.writer.Writer;

public class Main {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Не указан файл заметок.");
			System.exit(64);
		}
		;

		Note note = new Note();
		if (!note.setCaption() || !note.setText()) {
			System.out.println("Действие отменено пользователем.");
			System.exit(0);
		}
		;
		Writer writer = new Writer(args[0]);
		if (writer.writeNote(note)) {
			System.out.println("Заметка записана.");
		} else {
			System.out.println("Ошибка при записи заметки");
			System.exit(1);
		}
		;

	}
}
