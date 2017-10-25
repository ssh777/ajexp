package org.ajexp.samples.excel;

import org.ajexp.AjxException;
import org.ajexp.AjxExporter;
import org.ajexp.AjxLocale;
import org.ajexp.excel.HSSFExporter;
import org.ajexp.samples.excel.dto.SimpleDTO;
import org.ajexp.samples.excel.dto.SimpleEnum;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SSh on 25.10.2017.
 */
public class ExcelExport {
    private static List<SimpleDTO> data = Arrays.asList(
                new SimpleDTO(100L, "first", SimpleEnum.NONE),
                new SimpleDTO(200L, "second", SimpleEnum.SIMPLE),
                new SimpleDTO(300L, "third", SimpleEnum.SIMPLE)
        );

    public static void main(String[] args) throws AjxException, IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        AjxExporter ajxExporter = new HSSFExporter(data, SimpleDTO.class, AjxLocale.ENGLISH);
        ajxExporter.exportData(os);
        Files.write(Paths.get("excel.xls"), os.toByteArray(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
