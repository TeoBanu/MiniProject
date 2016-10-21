package com.msg.proj;

import com.msg.proj.utils.Menu;

public class Main {


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
                    default:
                        System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (option != 3);
    }


}
