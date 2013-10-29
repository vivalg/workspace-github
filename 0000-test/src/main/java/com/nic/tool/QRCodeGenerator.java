package com.nic.tool;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeGenerator {

    public static void main(String[] args) throws WriterException, IOException {
        String info = "no infomation input";
        if (args != null && args.length >= 1) {
            info = args[0];
        }
        int width = 200;
        int height = 200;
        String format = "png";

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix matrix = writer.encode(info, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToFile(matrix, format, new File("./QRCode.png"));
        System.out.println("done");
    }
}
