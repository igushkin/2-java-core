import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resources {
    public static File[] getFiles() {
        File folder = new File("./resources");
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    public static File[] getFiles(String regex) {
        File[] resources = getFiles();
        ArrayList<File> monthlyReports = new ArrayList<>();
        for (File file : resources) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(file.getName());
            if (m.find()) {
                monthlyReports.add(file);
            }
        }
        return monthlyReports.toArray(new File[0]);
    }

    public static String getContent(File file) {
        try {
            return Files.readString(Path.of(file.getPath()));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
