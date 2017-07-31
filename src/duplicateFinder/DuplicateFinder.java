package duplicateFinder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antero Oikkonen
 */
public class DuplicateFinder implements Runnable {

    private String startDir;
    FileFinder finder = new FileFinder();

    public void setStartDir(String startDir) {
        this.startDir = startDir;
    }

    public void start() {
        finder.setStartDir(startDir);
        ExecutorService taskList = Executors.newFixedThreadPool(10);
        taskList.execute(this);
        taskList.execute(finder);
    }

    public HashMap<String, List<File>> findDuplicates(String startDirectory) {

        HashMap<String, List<File>> files = new HashMap<String, List<File>>();

        finder.setStartDir(startDirectory);

        finder.run();
        this.run();
        return files;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String startDir = "Demo.txt";

        DuplicateFinder dfinder = new DuplicateFinder();
        dfinder.setStartDir(startDir);
        dfinder.start();
        long start = System.currentTimeMillis();

        long end = System.currentTimeMillis();

        System.out.println("Time consumed " + (end - start) / 1000 + " seconds");

        //dfinder.run();
        //System.out.println("Files " + files);
    }

    @Override
    public void run() {
        System.out.println("run()");
        int count = 0;
        try {
            synchronized (this) {

                this.wait(1000 * 5);
                count++;

                System.out.println("--- seconds --- " + count * 5);

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DuplicateFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (finder.getDuplicates() != null) {
            System.out.println("dublicates " + finder.getDuplicates());
            printDuplicates(finder.getDuplicates());
            writeFilelist(finder.getDuplicates());
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
