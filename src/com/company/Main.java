package com.company;

public class Main {

    public static void main(String[] args) {
	int someInt = 4;
	int someResult = switch (someInt) {
	    case 1 -> 4;
	    case 2 -> 6;
        default -> 8;
    };
    }
}
