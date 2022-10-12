package pl.coderslab;

import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
//program nie wczytuje listy jeśli to jest pierwsza funkcja jaką wybraliśmy ponieważ za każdym razem plik csv
// ma posiadać dane z jednego uruchomienia programu
// nie można wejść do aplikacji i wyjść bo tablica jest pusta, bo nic jeszcze nie wpisaliśmy

public class TaskManager {
   static String[][] list;
    static String filePath = "tasks.csv";
    public static void main(String[] args) {
        list = toArray(filePath);
        Scanner scan = new Scanner(System.in);
        chooseOption();
        while (scan.hasNextLine()) {
            String option = scan.nextLine();
            if (option.equals("add")) {
                writeToFile(filePath);
            } else if (option.equals("remove")) {
                remove(list);
            } else if (option.equals("list")) {
                readFile(list);
            } else if (option.equals("exit")) {
                writeToCSV(filePath,list);
                System.out.println(ConsoleColors.RED + "Bye Bye");

                break;
            }
            else{
                System.out.println(ConsoleColors.RED +  "choose the correct options");
            }
            chooseOption();
        }
    }
    public static void chooseOption() {
        System.out.println(ConsoleColors.BLUE + "Please select an option");
        System.out.println(ConsoleColors.RESET + "add" + "\n" + "remove" + "\n" + "list" + "\n" + "exit");
    }
    public static void readFile(String[][] list) {
        if (!(list == null)) {
            for (int i = 0; i < list.length; i++) {
                System.out.print(i + " : ");
                for (int j = 0; j < list[i].length; j++) {
                    System.out.print(list[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            try {
                Scanner sc = new Scanner(new File(filePath));
                sc.useDelimiter(",");
                while (sc.hasNext()) {
                    System.out.print(sc.next());
                }
                sc.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Nie znaleziono pliku");
            }
        }
    }

    public static void remove(String[][] arr){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please sekect number to remove.");
        String number = scan.next();
        int num = Integer.parseInt(number);
        try {
            if (num < arr.length && num >= 0) {
                list = ArrayUtils.remove(arr, num);
            } else if (num < 0) {
                System.out.println("the value is less than zero");

            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Element not exist in tab");
        }
    }

    public static String[][] writeToFile(String filePath) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please add task description");
            String description = scan.nextLine();
            System.out.println("Please add task due date");
            String dueDate = scan.nextLine();
            System.out.println("Is your task is important: true/false");
            String isImportant = scan.nextLine();
            if(list == null){
                list = new String[1][3];
            } else {
               String [][] tempList = new String[list.length + 1][3];
                for (int i = 0; i < list.length ; i++) {
                    for (int j = 0; j < 3 ; j++) {
                        tempList[i][j] = list[i][j];
                    }
                }
                list = tempList;
            }
        list[list.length - 1][0] = description;
        list[list.length - 1][1] = dueDate;
        list[list.length - 1][2] = isImportant;
            return list;
            }
    public static String [][] toArray(String fileName){
        Path path = Paths.get(fileName);
        String arr[][] = null;
        if(!(list == null)) {
            try {
                List<String> strings = Files.readAllLines(path);
                arr = new String[strings.size()][strings.get(0).split(",").length];
                arr = new String[list.length][3];
                for (int i = 0; i < arr.length; i++) {
                    String[] split = strings.get(i).split(",");
                    for (int j = 0; j < arr[i].length; j++) {
                        arr[i][j] = split[j];
                    }
                }
            }catch(IOException e) {
                System.out.println("Błąd zapisu do tabeli");
            }
        }
return arr;
    }
        public static String[][] writeToCSV (String filePath, String[][] arr) {
                try {
                    FileWriter fileWriter = new FileWriter(filePath);
                    for (String[] data : arr) {
                        StringBuilder line = new StringBuilder();
                        for (int i = 0; i < data.length; i++) {
                            line.append(data[i].replaceAll("\"", "\"\""));
                        line.append(" ");
                            if (i != data.length - 1) {
                                line.append(',');
                            }
                        }
                        line.append("\n");
                        fileWriter.write(line.toString());
                    }
                    fileWriter.close();
                } catch (IOException var1) {
                    System.out.println("Bład zapisu pliku");
                }
            return list;
        }


}


