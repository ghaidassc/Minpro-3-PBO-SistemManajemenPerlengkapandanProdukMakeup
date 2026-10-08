package main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */

import controller.MakeupController;
import view.MakeupView;

/**
 *
 * @author gedascc
 */
public class Main {
    public static void main(String[] args) {
        new MakeupController(new MakeupView()).mulaiProgram();
    }
}