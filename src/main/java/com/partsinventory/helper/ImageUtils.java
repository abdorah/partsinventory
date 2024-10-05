package com.partsinventory.helper;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtils {

    // Convert JavaFX Image to byte array
    public static byte[] imageToBytes(Image image, String format) throws IOException {
        // Convert JavaFX Image to BufferedImage
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        // Convert BufferedImage to byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, outputStream);  // format can be "png", "jpg", etc.

        return outputStream.toByteArray();
    }
}
