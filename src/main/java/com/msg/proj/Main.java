package com.msg.proj;

import com.msg.proj.utils.Menu;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        int option = 0;

        do {
            try {
                menu.show();
                option = Integer.parseInt(menu.readFromKeyboard("Please select an option: "));
                switch (option) {
                    case 1:
                        menu.createCV();
                        break;
                    case 2:
                        menu.searchInCV();
                        break;
                    case 3:
                        menu.closeEntityManager();
                        break;
                    default:
                        System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (option != 3);
    }


}
