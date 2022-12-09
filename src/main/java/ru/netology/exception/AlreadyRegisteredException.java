package ru.netology.exception;

public class AlreadyRegisteredException extends RuntimeException {

    public AlreadyRegisteredException(String name) {
        super("Игрок с именем " + name + " уже зарегистрирован");
    }
}