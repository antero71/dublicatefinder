/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duplicateFinder;

import duplicateFinder.DirPrinter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antero Oikkonen
 */
public class DirPrinterTest {
    
    private DirPrinter printer;
    
    public DirPrinterTest() {
        printer=new DirPrinter();
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testPrintContent() throws Exception {
       String dir = "/Users/anterooikkonen/dirtesti";
        printer.printContent(dir);
    }
    
}
