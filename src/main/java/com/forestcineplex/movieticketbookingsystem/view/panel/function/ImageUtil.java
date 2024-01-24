/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.forestcineplex.movieticketbookingsystem.view.panel.function;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author LENOVO
 */
class ImageUtil {

    public static ImageIcon resize(ImageIcon icon, int width, int height) {
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
    
    public static ImageIcon resize(ImageIcon icon, Dimension dimension) {
        return resize(icon, (int) dimension.getHeight(), (int) dimension.getHeight());
    }
}
