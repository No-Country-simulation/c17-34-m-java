package com.c174.tools;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.scheduling.annotation.Scheduled;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QrGeneration {

    private final static String charset = "UTF-8";

    /**
     *
     * @param file Archivo a identificar
     * @return Cadena de caracteres
     * @throws IOException
     *
     * Este metodo lee y transforma un archivo en una cadena de caracteres
     */
    public static String returnBase64(File file) throws IOException {
        InputStream inputFile = new FileInputStream(file);
        byte[] fileContent = inputFile.readAllBytes();
        inputFile.close();
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static String decodeQR(File file) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(file);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }

    /**
     *
     * @param path
     * @param height
     * @param width
     * @return if exist file
     * @throws IOException
     * @throws WriterException
     *
     *
     * Este metodo crea un codigo qr en formato .png
     * a apartir de un path ( uri ) y 2 medidas de tamaño
     * alto y ancho
     */
    public static File generateQr(String path, String chain, int height, int width)
            throws IOException, WriterException {

        Map<EncodeHintType, ErrorCorrectionLevel> map = new HashMap<>();

        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(chain.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf(".")+1),
                new File(path)
        );

        return new File(path);
    }

    /**
     *
     * @param image
     * @return qr
     *
     * Este metodo se encarga de tomar el archivo
     * creado previamente y retornarlo en forma de
     * MultiparFile
     */
    public static String takeQr(String image) throws IOException {

        Path path = Path.of(image);
        File file = new File(image);
        byte[] fileContent = Files.readAllBytes(path);
        String originalFilename = path.getFileName().toString();
        String contentType = Files.probeContentType(path);
        String finalFileString = Base64.getEncoder().encodeToString(fileContent);

        return finalFileString;

    }

    /**
     * @param image
     * @return if the archive can be delete
     *
     * Este metodo intenta borrar un archivo mediante un path
     * si lo logra retorna verdadedora de lo contrario
     * retorna falso
     */
    public static Boolean deleteQr(String image){

        File file = new File(image);

        if (file.exists()){
            System.out.println("el archivo existe");
            if (file.delete()){
                return true;
            }
            return false;
        }
        System.out.println("el archivo no existe");
        return false;
    }

    @Scheduled(fixedRate = 10000)
    public void regeneratQr(){
        System.out.println("esta andando esto de ejecutar cada cierto tiempo");
    }

}
