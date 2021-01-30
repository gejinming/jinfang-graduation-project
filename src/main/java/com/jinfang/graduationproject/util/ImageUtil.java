package com.jinfang.graduationproject.util;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class ImageUtil {

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    @Builder
    @Getter
    public static class ImageSize {
        int width;
        int height;
    }

    public static ImageSize getImageSize(String path) {
        int width = DEFAULT_WIDTH;
        int height = DEFAULT_HEIGHT;
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path));
            width = bufferedImage.getWidth();
            height = bufferedImage.getHeight();

            if (width <= 0) {
                width = DEFAULT_WIDTH;
            }

            if (height <= 0) {
                height = DEFAULT_HEIGHT;
            }
        } catch (IOException e) {
            log.error("Getting image[{}] size failed", path, e);
        }

        return ImageSize.builder().height(height).width(width).build();
    }

}
