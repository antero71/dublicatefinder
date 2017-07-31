package duplicateFinder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Antero Oikkonen
 */
public class FileFinder {

    private String startDir;
    private HashMap<String, List<File>> duplicates = null;

    public void setStartDir(String startDir) {
        this.startDir = startDir;
    }

    public HashMap<String, List<File>> getDuplicates() {
        return duplicates;
    }

    public List<File> getFileList(String dir) throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get(dir))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        return filesInFolder;
    }

    public Map<String, List<File>> getNamesAndLocations(String dir) throws IOException {
        List<File> files = getFileList(dir);

        long start = System.nanoTime();
        Map<String, List<File>> suspectedDuplicate = files.stream().collect(groupingBy(File::getName,
                mapping(File::getAbsoluteFile, toList())));
        long end = System.nanoTime();

        System.out.println("time used " + (end - start) + " nanoseconds");
        return suspectedDuplicate;
    }

    public HashMap<String, List<File>> resolveRealDuplicates(String dir) throws IOException {

        HashMap<String, List<File>> real = new HashMap<>();

        HashMap<String, List<File>> suspected = (HashMap) getNamesAndLocations(dir);

        //System.out.println("suspected "+suspected);
        Set keys = suspected.keySet();

        Iterator<String> iter = keys.iterator();

        while (iter.hasNext()) {

            List<File> files = (List<File>) suspected.get(iter.next());

            File[] f = new File[files.size()];

            files.toArray(f);

            Set<File> duplicates = new HashSet<File>();

            //System.out.println("files " + files);
            if (files.size() > 1) {
                for (int i = 0; i < files.size(); i++) {
                    for (int j = 1; j < files.size(); j++) {
                        if (i == j) {
                            continue;
                        }
                        if (f[i].length() == f[j].length()) {
                            duplicates.add(f[i]);
                            duplicates.add(f[j]);
                            //System.out.println("f[i] " + f[i] + " last modified " + new Date(f[i].lastModified()));
                            //System.out.println("f[j] " + f[j] + " last modified " + new Date(f[j].lastModified()));
                        }
                    }
                }
                List<File> duplicateFile = duplicates.stream()
                        .collect(Collectors.toList());
                real.put(f[0].getName(), duplicateFile);
            }
            //System.out.println("duplicates "+duplicates);

        }
        this.duplicates = real;
        return real;

    }

}
