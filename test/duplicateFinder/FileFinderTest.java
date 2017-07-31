/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duplicateFinder;

import duplicateFinder.FileFinder;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class FileFinderTest {

    private FileFinder finder;

    public FileFinderTest() {
    }

    @Before
    public void setUp() {
        finder = new FileFinder();
    }

    @Test
    public void testGetFileList() throws Exception {
    }

    @Test
    public void testPrintSuspectedDuplicates() throws Exception {
        HashMap<String, List<File>> files = (HashMap)finder.getNamesAndLocations("dir_test");

        //System.out.println("files "+files);
        assertEquals("Wrong number of the files", 4, files.keySet().size());

    }

    @Test
    public void testRealDuplicates() throws Exception {
        HashMap<String, List<File>> files = finder.resolveRealDuplicates("dir_test");

         assertEquals("Wrong number of the files", 3, files.get("readme.txt").size());

    }

}
