package ru.netology.exception;

public class NotRegisteredException extends Exception {

    public NotRegisteredException(String name) {
        super("Игрок с именем " + name + " не зарегистрирован в игре");
    }
}
