package duplicateFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author Antero Oikkonen
 */
public class DirPrinter {

    public void printContent(String dir) throws IOException {
        
        try (Stream<Path> paths = Files.walk(Paths.get(dir))){
            paths.filter(Files::isRegularFile)
                    .forEach(System.out::println);

        }
    }
}

