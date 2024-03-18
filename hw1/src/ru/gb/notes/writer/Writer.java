package ru.gb.notes.writer;

import java.io.FileWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ru.gb.notes.core.Note;

/**
 * Класс, осуществляющий запись заметок в файл.
 * При создании объекта указывается путь к файлу.
 */
public class Writer {

	private String fileName;

	/**
	 * Конструктор, в котором передается имя файла
	 * 
	 * @param fileName Имя файла
	 */
	public Writer(String fileName) {
		this.fileName = fileName;
	};

	/**
	 * Записывает заметку в файл
	 * 
	 * @param note Заметка
	 * @return Результат записи: успешно или не успешно
	 */
	public boolean writeNote(Note note) {
		Timestamp date = new Timestamp(System.currentTimeMillis());
		LocalDateTime localDateTime = date.toLocalDateTime();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		try {
			FileWriter fileWriter = new FileWriter(fileName, true);
			fileWriter.append(dateTimeFormatter.format(localDateTime) + " ");
			fileWriter.append(note.getCaption() + "\n");
			fileWriter.append(note.getText() + "\n");
			fileWriter.flush();
			fileWriter.close();
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	};

}
