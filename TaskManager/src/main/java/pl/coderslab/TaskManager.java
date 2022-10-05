package pl.coderslab;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
public class TaskManager {
    public static void main(String[] args) {
        String fileName = "tasks.csv";
        String[][] tasks = new String[1][1];

        Scanner scan = new Scanner(System.in);
        System.out.println("Please select an option");
        System.out.println("add"+"\n"+"remove"+"\n"+"list"+"\n"+"exit");
        String option = scan.next();
        if(option.equals("add")){

        }else if(option.equals("remove")){


        } else if (option.equals("list")) {
            readFile(fileName);
        }else if(option.equals("exit")){

        }


    }
    public static void readFile(String fileName) {

            Path path = Paths.get(fileName);
            String [][] tab = new String[5][5];
            for(int i = 0; i < tab.length; i++){
                for(int j = 0; j < tab[i].length; j++){
                    System.out.println(tab[i][j] + " ");
                }
                System.out.println();
            }


    }

}
//W pierwszym kroku dodać wczytywanie i wyświetlanie danych z pliku
//kurwa plik jest tablicą, pamiętaj