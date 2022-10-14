package ald;

import java.io.IOException;
import java.util.Scanner;

public class CheckoutGUI {
    Scanner scanner = new Scanner(System.in);
    CheckoutController checkoutController = new CheckoutController();
    public static void main(String[] args) throws IOException {
        CheckoutGUI g = new CheckoutGUI();
        g.main_menu_not_login();
    }
    public void main_menu_not_login() throws IOException {
        System.out.println("======================");
        System.out.println("Welcome to National Trade Show Services!");
        System.out.println("1:login");
        System.out.println("2:register account");
        System.out.print("Please choose the item you want to do:");
        int next_step = scanner.nextInt();
        switch (next_step){
            case 1:
                /*
                * 调用登陆函数
                * */
                break;
            case 2:
                System.out.print("please input your user name:");
                String userName = scanner.next();
                System.out.print("please input your email:");
                String email = scanner.next();
                String result = checkoutController.register(userName,email);
                System.out.println(result);
        }
    }

    public void main_menu_login(){
        System.out.println("======================");
        System.out.println("Welcome to National Trade Show Services!");
        System.out.println("Please choose the item you want to do:");
        System.out.println("1:");
        System.out.println("2:");
    }
}
