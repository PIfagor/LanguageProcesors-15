package parser;

import java.io.FileNotFoundException;

/**
 *
 * @author Student
 */
public class Main {
    /** Whether an error occurred during compilation. */
    private static boolean errorHasOccurred;
    
     /**
     * Entry point.
     */
    public static void main(String args[]) {
        String caller = "java parser.Main";
        String sourceFile = "res/test3.txt";
//        for (int i = 0; i < args.length; i++) {
//            sourceFile = args[i];
//        }
//        if (sourceFile.equals("")) {
//            printUsage(caller);
//            return;
//        }
        
        LookaheadScanner scanner = null;
        try {
            scanner = new LookaheadScanner(sourceFile);
        } catch (FileNotFoundException e) {
            System.err.println("Error: file " + sourceFile + " not found.");
            return;
        }
        
                // Parse input
        Parser parser = new Parser(scanner);
        parser.define();
        errorHasOccurred |= parser.errorHasOccurred();
        if (!errorHasOccurred) {
            System.out.println("CORRECT INPUT STATEMENT in " + sourceFile + ".");
            return;
        }
        
    }
    
     /**
     * Print command usage to STDOUT.
     * 
     * @param caller
     *            denotes how this class is invoked.
     */

    private static void printUsage(String caller) {
        String usage = "Usage: "
                + caller
                + " <source file>";
        System.out.println(usage);
    }
}
