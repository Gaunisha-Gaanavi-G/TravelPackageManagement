package org.TravelPackageManager.utils;

public enum Membership {
    STANDARD(1000),
    GOLD(2000),
    PREMIUM(0);

    final int balance;

    Membership(int balance) {
        this.balance = balance;
    }
}
