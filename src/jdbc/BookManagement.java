/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.util.Scanner;

/**
 *
 * @author Quan
 */
public class BookManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book b = new Book();
        String opt;
        boolean flag = false;
        do {
            System.out.println("----------------------------");
            System.out.println("The functions of system");
            System.out.println("Add new book (add)");
            System.out.println("Update a book (update)");
            System.out.println("Delete a book (del)");
            System.out.println("1.Display all book");
            System.out.println("2.Display books by name");
            System.out.println("3.Display books by price");
            System.out.println("4.Display books in price range");
            System.out.println("5.Display ascending book by price");
            System.out.println("6.Display descending order by price");
            System.out.println("Exit applicaiton (exit)");
            System.out.println("----------------------------");
            System.out.print("Your choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "add":
                    b.addBook();
                    break;
                case "update":
                    b.updateBook();
                    break;
                case "del":
                    b.deleteBook();
                    break;
                case "1":
                    b.dab();
                    break;
                case "2":
                    b.dbbn();
                    break;
                case "3":
                    b.dbbp();
                    break;
                case "4":
                    b.dbipr();
                    break;
                case "5":
                    b.dabbp();
                    break;
                case "6":
                    b.ddobp();
                    break;
                case "exit":
                    System.out.println("Are you sure(y/n)?");
                    String x = sc.nextLine();
                    if (x.equals("y")) {
                        flag = true;
                    }
                    break;
                default:
                    System.out.println("Invalid function. Please choose again!");
            }
        } while (!flag);

    }
}
