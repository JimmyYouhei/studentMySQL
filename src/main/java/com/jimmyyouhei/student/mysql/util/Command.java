package com.jimmyyouhei.student.mysql.util;

import java.util.Scanner;

public interface Command {

    static String getString (String question){

        Scanner scanner = new Scanner(System.in);
        System.out.println(question);

        return scanner.nextLine();
    }
}
