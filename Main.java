package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FileManager manager = new FileManager();
        manager.trueExecutor(args);
        EncDec ed = new ShiftEncDec(manager.getCypher());
        if ("unicode".equals(manager.getAlgorithm())) {
             ed = new UnicodeEncDec(manager.getCypher());
        }

        if ("enc".equals(manager.getAction())) {
            manager.setMessage(ed.encrypt(manager.getMessage()));
        }
        if ("dec".equals(manager.getAction())) {
            manager.setMessage(ed.decrypt(manager.getMessage()));
        }
        if (manager.isWorkWithFiles()) {
            manager.exportMessage(manager.getOutPath(), manager.getMessage());
        } else {
            System.out.println(manager.getMessage());
        }

    }
}



class FileManager {

    private int cypher = 0;
    private String action = "enc";
    private String message = "";
    private String inPath;
    private String outPath;
    private String algorithm = "shift";
    boolean workWithFiles = false;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }


    public boolean isWorkWithFiles() {
        return workWithFiles;
    }

    public void setWorkWithFiles(boolean workWithFiles) {
        this.workWithFiles = workWithFiles;
    }


    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }


    public int getCypher() {
        return cypher;
    }

    public void setCypher(int cypher) {
        this.cypher = cypher;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public void importMessage(String fileName) {
        try (var fileScanner = new Scanner(new FileReader(fileName))) {
            while (fileScanner.hasNextLine()) {
                setMessage(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, file not found.");
        }

    }

    public void exportMessage(String fileName, String message) {
        try (var fileWriter = new PrintStream(new FileOutputStream(fileName, false))) {
            fileWriter.println(message);
        } catch (FileNotFoundException e) {
            System.out.println("Error, file not found.");
        }
    }

    public void trueExecutor(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0) {
                switch (args[i]) {
                    case "-mode":
                        setAction(args[i + 1]);
                        break;
                    case "-key":
                        setCypher(Integer.parseInt(args[i + 1]));
                        break;
                    case "-in":
                        importMessage(args[i + 1]);
                        break;
                    case "-data":
                        setMessage(args[i + 1]);
                        break;
                    case "-out":
                        setWorkWithFiles(true);
                        setOutPath(args[i + 1]);
                        break;
                    case "-alg":
                        setAlgorithm(args[i + 1]);
                        break;
                }
            }
        }
    }

}


