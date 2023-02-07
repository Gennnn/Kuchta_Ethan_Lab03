package me.ethan.lab3;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList list = new ArrayList();
        int selection = SafeInput.getRangedInt(scanner, "Would you to like to filter words (1), or filter rectangles (2)? Enter (3) to exit", 1, 3);
        if (selection==1) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt");
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            ArrayList<String> readArray = new ArrayList<>();
            int result = chooser.showOpenDialog(null);
            if (result == chooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("File Selected: " + file);
                Path path = Paths.get(file.getAbsolutePath());

                List lines = Files.readAllLines(path);

                for (int i = 0; i < lines.size(); i++) {
                    String fullLine = (String) lines.get(i);
                    String splitLine[] = fullLine.split("\\s");
                    for (int k = 0; k < splitLine.length; k++) {
                        list.add(splitLine[k]);
                    }
                }
                String retString = collectAll(list);
                System.out.println(retString);
            }
        } else if (selection==2) {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                Rectangle newRect = new Rectangle();
                int width = random.nextInt(4) + 1;
                int height = random.nextInt(4) + 1;
                newRect.setSize(width, height);
                list.add(newRect);
            }
            String retString = collectAll(list);
            System.out.println(retString);
            boolean showAll = SafeInput.getYNConfirm(scanner, "\nWould you like to display all rectangles generated?");
            if (showAll) {
                for (int i = 0; i<list.size();i++) {

                    System.out.println("\nRectangle #" + (i + 1) + ": Width of " + ((Rectangle) list.get(i) ).getWidth() + ", height of " + ((Rectangle) list.get(i)).getHeight() + ", perimeter of " + ((2 *((Rectangle) list.get(i) ).getWidth()) + (2 * ((Rectangle) list.get(i) ).getHeight()))) ;
                }
            } else {
                return;
            }
        } else if (selection==3) {
            return;
        }

    }

    public static String collectAll(ArrayList list) {
        Filter wordFilter = new ShortWordFilter();
        Filter rectFilter = new BigRectangleFilter();
        String retString = "";
        if ( list.get(0) instanceof String ) {
            for (Object i: list) {
                if (wordFilter.accept(i.toString()) == true) {
                    retString = retString + i.toString() + "\n";
                }
            }
        } else if (list.get(0) instanceof Rectangle) {
            int num = 0;
            for (Object i : list) {
                num++;
                if(rectFilter.accept(i)) {
                    retString = retString + "\nRectangle #" + (num) + ": Width of " + ((Rectangle) i ).getWidth() + ", height of " + ((Rectangle) i).getHeight() + ", perimeter of " + ((2 *((Rectangle) i ).getWidth()) + (2 * ((Rectangle) i ).getHeight())) + "\n" ;
                }
            }
        }

        return retString;
    }
}
