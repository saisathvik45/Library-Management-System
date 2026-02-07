package main;

import dao.*;

public class Main {

    public static void main(String[] args) {
        try {
            Books.insert(1, "Java", "Tech", "Oracle", "Gosling", "ISBN1");
            Member.addMem(1, "Sai", "Kumar", "B.Tech", "99999", "sai@gmail.com");
            Borrow.issueBook(1, 1, 1, 7);
            Borrow.returnBook(1);
            Fine.addFine(1, 1, 50);
            Fine.payFine(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
