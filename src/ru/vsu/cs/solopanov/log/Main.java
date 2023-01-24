package ru.vsu.cs.solopanov.log;

import ru.vsu.cs.solopanov.util.ArrayUtils;
import ru.vsu.cs.solopanov.util.SwingUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class Main {
    public static class InputArgs {
        public String inputFile;
        public String outputFile;
        public boolean replaceMinVisMaxSumColumn;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static InputArgs parseCmdArgs(String[] args) {
        InputArgs params = new InputArgs();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (args[0].equals("-d")) {
                params.replaceMinVisMaxSumColumn = true;
            }
            params.inputFile = args[1];
            if (args.length > 2) {
                params.outputFile = args[2];
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }

    public static void winMain() throws Exception {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        InputArgs params = parseCmdArgs(args);
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("    -d  // reverse min and max sum column");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }
        if (params.window) {
            winMain();
        } else {
            try {
                String[] arr = ArrayUtils.readLinesFromFile(params.inputFile);
                if (arr == null) {
                    System.err.printf("Can't read array from \"%s\"%n", params.inputFile);
                    System.exit(2);
                }
                List<List<String>> arr2 = Util.stringArrayToListList(arr);
                Double sum = Double.valueOf(arr2.get(0).get(0));
                arr2.remove(0);
                String[][] result = Task.solve(arr2, sum);

                PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
                Util.write(params.outputFile, result);
//                out.println(Arrays.deepToString(result));
                out.close();
            } catch (Exception e) {
                System.err.printf("An empty file was passed \"%s\"%n", params.inputFile);
                System.exit(2);
            }
        }
    }
}