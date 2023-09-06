package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Активность ошибки: " + active);
        System.out.println("Статус ошибки: " + status);
        System.out.println("Сообщение: " + message);
    }

    public static void main(String[] args) {
        Error testError = new Error();
        Error outOfBoundsError = new Error(false, 1, "Выход за пределы массива");
        Error divByZero = new Error(true, 2, "Деление на ноль");

        testError.printInfo();
        outOfBoundsError.printInfo();
        divByZero.printInfo();
    }
}
