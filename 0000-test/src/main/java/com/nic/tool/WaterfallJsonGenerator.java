package com.nic.tool;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class WaterfallJsonGenerator {

    private final static String[] suffixs = {".jpg", ".png", ".gif", ".ico", ".bmp"};
    private static final Integer IMG_WIDTH = 192;

    private static String imagePrefix = "";
    private static boolean genratePreview = false;

    static class ImageObj {
        private String name;
        private BufferedImage image;
        private File file;

        public ImageObj(File file) {
            this.getImage(file);
            this.getRelativePath(file);
            this.file = file;
        }

        private void getImage(File file) {
            try {
                this.image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void getRelativePath(File file) {
            String path;
            try {
                path = file.getCanonicalPath();
                path = path.replaceAll("\\\\", "/");
                String relativePath = path.substring(path.indexOf("/images") + 1);
                this.name = relativePath;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        public BufferedImage getImage() {
            return image;
        }

        public File getFile() {
            return file;
        }

    }

    public static void main(String[] args) throws IOException {
        readArgs(args);
        List<ImageObj> images = listImages();
        String json = generateJSON(images);
        writeFile("waterfall-images.json", json);

        if (genratePreview) {
            for (ImageObj imageObj : images) {
                genPreviewImage(imageObj.getFile());
            }
        }
    }

    public static void readArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("-prefix".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    imagePrefix = args[i + 1];
                }
            }
            if ("-generate-preview".equalsIgnoreCase(args[i])) {
                genratePreview = true;
            }
        }
    }

    public static List<ImageObj> listImages() throws IOException {
        List<ImageObj> images = new ArrayList<ImageObj>();
        File dir = new File(".");
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            for (String suffix : suffixs) {
                if (file.getName().endsWith(suffix)) {
                    images.add(new ImageObj(file));
                    break;
                }
            }
        }
        return images;
    }

    public static String generateJSON(List<ImageObj> images) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("\t\"total\": " + images.size() + ",\n");
        builder.append("\t\"result\": [\n");
        for (ImageObj image : images) {
            builder.append("\t\t{\n");
            builder.append("\t\t\t\"image\": \"" + imagePrefix + image.getFile().getName() + "\",\n");
            builder.append("\t\t\t\"width\": " + image.getImage().getWidth() + ",\n");
            builder.append("\t\t\t\"height\": " + image.getImage().getHeight() + "\n");
            builder.append("\t\t},\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\n");
        builder.append("\t]\n");
        builder.append("}");
        return builder.toString();
    }

    public static void writeFile(String fileName, String json) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        out.write(json.getBytes());
        out.flush();
        out.close();
    }

    public static void genPreviewImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        int newWidth = IMG_WIDTH;
        int newHeight = Math.round(IMG_WIDTH * height / width);

        BufferedImage previewImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        previewImage.getGraphics().drawImage(image, 0, 0, newWidth, newHeight, null);

        File previewFile = new File("./preview/" + file.getName());
        if (!file.exists()) {
            file.createNewFile();
        }
        ImageIO.write(previewImage, "PNG", previewFile);
    }
}
