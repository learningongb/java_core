package ru.gb.jcore.shop;

import java.time.LocalDate;

public class Client {
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

    public Client(String FIO, LocalDate birthDate, String telephonNumber, Gender gender){
        this.FIO = FIO;
        this.birthDate = birthDate;
        this.telephonNumber = telephonNumber;
        this.gender = gender;
    }

    public enum Gender {
        MALE,
        FEMALE
    }
}
