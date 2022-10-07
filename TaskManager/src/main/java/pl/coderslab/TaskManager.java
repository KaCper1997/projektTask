package pl.coderslab;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class TaskManager {
    private static  List<String[]> csvData;
    ;
//nie wiem o co chodzi generalnie, program generalnie zapisuje to co wpisze w konsoli. Ale prosi mnie o to dwa razy
//    i generalnie pierwsze się nie zapisuje tylko to drugie co wpiszę do pliku. Zostawiam to nie widzę błedu czy
//    innego rozwiązanie generalnie. 
    public static void main(String[] args) {
       String filePath = "C:\\Users\\meblo\\Desktop\\TaskManager\\TaskManager\\src\\main\\java\\pl\\coderslab\\tasks.csv";
//      tab = writeToFile(filePath);

        Scanner scan = new Scanner(System.in);
        System.out.println("Please select an option");
        System.out.println("add"+"\n"+"remove"+"\n"+"list"+"\n"+"exit");
        String option = scan.next();
        if(option.equals("add")){
            writeToFile(filePath);
        }else if(option.equals("remove")){
//
        } else if (option.equals("list")) {
            readFile(filePath);
        }else if(option.equals("exit")){
//
        }
        csvData = creatCsvData(filePath);
    }
    public static List<String[]> creatCsvData(String filePath){
        List<String[]> csvData = writeToFile(filePath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath,true))) {
            writer.writeAll(csvData);
        }catch (IOException var5) {
            System.out.println("Nie można zapisać pliku");
        }
        return csvData;
    }
    public static void readFile(String filePath) {
        try {
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                System.out.print(sc.next());
            }
            sc.close();

        }catch (FileNotFoundException ex){
            System.out.println("Nie znaleziono pliku");
        }
    }
    public static List<String[]> writeToFile(String filePath){
        List<String[]> list = new ArrayList<>();

            Scanner scan = new Scanner(System.in);
            System.out.println("Please add task description");
            String description = scan.next();
            System.out.println("Please add task due date");
            String dueDate = scan.next();
            System.out.println("Is your task is important: true/false");
            String isImportant = scan.next();
            String[] task = {description,dueDate,isImportant};

            list.add(task);


        return list;




    }
}


