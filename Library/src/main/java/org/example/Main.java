package org.example;
import org.example.model.Book;
import org.example.model.Member;
import org.example.service.MemberServices;
import org.example.service.StaffServices;
import org.example.controller.CLI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main() {
        CLI cli = new CLI();
        cli.HomePageCLI();
    }
}
