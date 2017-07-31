package duplicateFinder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antero Oikkonen
 */
public class DuplicateFinder {

    private String startDir;
    FileFinder finder = new FileFinder();

    public void setStartDir(String startDir) {
        this.startDir = startDir;
    }

    public HashMap<String, List<File>> findDuplicates(String startDirectory) {

        HashMap<String, List<File>> files = new HashMap<String, List<File>>();

        finder.setStartDir(startDirectory);

        return files;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("Give a directory where you want to find duplicate files:");

        Scanner scanner = new Scanner(System.in);

        String startDir = scanner.nextLine();

        DuplicateFinder dfinder = new DuplicateFinder();
        dfinder.setStartDir(startDir);
        dfinder.start();

        //System.out.println("Files " + files);
    }

    private void start() {
        try {
            printDuplicates(finder.resolveRealDuplicates(startDir));
        } catch (IOException ex) {
            Logger.getLogger(DuplicateFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printDuplicates(HashMap<String, List<File>> files) {
        files.forEach((k, v) -> {
            System.out.println("File : " + k);
            v.stream().forEach(System.out::println);
        });
    }

    public void writeFilelist(HashMap<String, List<File>> files) {
        StringBuilder builder = new StringBuilder(files.size() * 10);
        files.forEach((k, v) -> {
            System.out.println("File : " + k);
            builder.append(k);
            builder.append("\n");
            for (File dir : v) {
                builder.append(dir);
                builder.append("\n");
            }
        });
        Path path = Paths.get("words.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(builder.toString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(DuplicateFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.exit(0);
    }
}
