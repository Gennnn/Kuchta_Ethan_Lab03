package me.ethan.lab3;

import java.util.ArrayList;
import java.util.List;

public class ShortWordFilter implements Filter {

    @Override
    public boolean accept(Object x) {
        if (x.toString().length() < 5) {
            return true;
        }
        else {
            return false;
        }
    }
}
