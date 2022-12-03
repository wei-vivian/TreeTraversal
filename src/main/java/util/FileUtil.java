package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
    public static boolean setPath(String path) {
        if (new File(path).isFile()) {
            return true;
        } else {
            System.out.println("Path not exist");
            return false;
        }
    }

    public static List<String> readFile(String inputPath) {
        if (setPath(inputPath)) {
            System.out.println(inputPath);
            try {
                return Files.lines(new File(inputPath).toPath())
                        .map(String::trim)
                        .filter(str -> !str.isEmpty())
                        .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return new ArrayList<>();
    }

    public static void writeFile(List<String> stringList, String outputPath) {
        File outputRecord = new File(outputPath);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(outputRecord));
            for (String string : stringList) {
                out.write(string);
                out.write("\n");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}