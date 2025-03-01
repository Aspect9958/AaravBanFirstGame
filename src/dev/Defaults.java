package dev;

import javax.swing.*;
import java.awt.*;

public class Defaults {

    public static ImageIcon getResizedImage(String path, int width, int height) {

        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(path)
                        .getImage()
                        .getScaledInstance(
                                width,
                                height,
                                Image.SCALE_SMOOTH
                        )
        );
        return imageIcon;
    }
}
