package org.example.trash;
/*import org.example.exception.ImageUtilsException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
public class ImageUtils {

    public static final int BITE_SIZE = 10 * 1024 * 1024;

    public static byte[] compressImage(byte[] data) {
        try {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[BITE_SIZE];

        while(!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp,0, size);
        }

        outputStream.close();

        return outputStream.toByteArray();
        } catch (IOException e) {
            throw new ImageUtilsException("Ошибка при сжатии изображения", e);
        }
    }

    public static byte[] decompressImage(byte[] data) throws DataFormatException, IOException {
        try {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[BITE_SIZE];

        while (!inflater.finished()) {
            int count = inflater.inflate(tmp);
            outputStream.write(tmp, 0, count);
        }

        outputStream.close();

        return outputStream.toByteArray();
        } catch (DataFormatException | IOException e) {
            throw new ImageUtilsException("Ошибка при разжатии изображения", e);
        }
    }
}*/
