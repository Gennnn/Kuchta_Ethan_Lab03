package me.ethan.lab3;

import java.awt.*;

public class BigRectangleFilter implements Filter{

    @Override
    public boolean accept(Object x) {
        if (10 <= (2 * ((Rectangle) x).getHeight()) + (2 * ((Rectangle)x).getWidth())) {
            return true;
        }
        else {
            return false;
        }
    }
}
