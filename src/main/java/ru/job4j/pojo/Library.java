package ru.job4j.pojo;

import java.util.ArrayList;

public class Library {
    public static void main(String[] args) {
        Book harryPotter = new Book("Harry Potter", 450);
        Book foxHole = new Book("Fox Hole", 320);
        Book goida = new Book("Goida", 200);
        Book clanCode = new Book("Clean code", 400);
        Book[] bookList = new Book[4];

        bookList[0] = harryPotter;
        bookList[1] = foxHole;
        bookList[2] = goida;
        bookList[3] = clanCode;

        for (int i = 0; i < bookList.length; i++) {
            System.out.println(bookList[i].getName() + " - " + bookList[i].getPage());
        }

        Book temp = bookList[0];
        bookList[0] = bookList[3];
        bookList[3] = temp;

        for (int i = 0; i < bookList.length; i++) {
            System.out.println(bookList[i].getName() + " - " + bookList[i].getPage());
        }

        for (int i = 0; i < bookList.length; i++) {
            if ("Clean code".equals(bookList[i].getName())) {
                System.out.println(bookList[i].getName() + " - " + bookList[i].getPage());
            }
        }
    }
}
