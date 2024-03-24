package ru.gb.jcore.shop;

import java.time.LocalDate;

public class Client {
    static int maxId = 0;
    int id;
    String FIO;
    LocalDate birthDate;
    String telephonNumber;
    Gender gender;

    @Override
    public String toString() {
        return "Client{" +
                "FIO='" + FIO + '\'' +
                ", birthDate=" + birthDate +
                ", telephonNumber='" + telephonNumber + '\'' +
                ", gender=" + gender +
                '}';
    }

    public int getId() {
        return id;
    }

    public Client(String line) {
        deserialize(line);
        if (maxId < id)
            maxId = id;
    }

    public Client(String FIO, LocalDate birthDate, String telephonNumber, Gender gender){
        this.id = ++maxId;
        this.FIO = FIO;
        this.birthDate = birthDate;
        this.telephonNumber = telephonNumber;
        this.gender = gender;
    }

    public String serialize() {
        return this.id + ";"
                + this.FIO + ";"
                + this.birthDate + ";"
                + this.telephonNumber + ";"
                + this.gender + "\n";
    }

    private void deserialize(String line) throws RuntimeException {
        String[] parts = line.split(";");
        if (parts.length != 5)
            throw new RuntimeException("Ошибка формата строки");
        this.id = Integer.parseInt(parts[0]);
        this.FIO = parts[1];
        this.birthDate = LocalDate.parse(parts[2]);
        this.telephonNumber = parts[3];
        this.gender = Gender.valueOf(parts[4]);
    }

    public enum Gender {
        MALE,
        FEMALE
    }
}
