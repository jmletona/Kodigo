import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Statistics {

    public static void main(String[] args){
        final String APP_NAME="Statictics Students Calculator";
        final byte STUDENTS_NUMBER = 4;

        byte optionMenu;
        byte maxValueResult;
        byte minValueResult;
        float avgValueResult;

        HashMap<String,Byte> gradedStudents = new HashMap<>();
        ArrayList<Integer> mostRepeatedResult;
        ArrayList<Integer> lessRepeatedResult;

        appTitle(APP_NAME);

        //menu
        Scanner entrada = new Scanner(System.in);
        while(true){
            System.out.print("" +
                    "-Cargar datos de archivo [1]\n" +
                    "-Ingresar datos Manualmente [2]\n" +
                    "-salir [0]\n\n" +
                    "Ingrese su opcion: ");

            optionMenu=entrada.nextByte();
            if (optionMenu == 0){
                System.exit(0);
                break;
            }
            if (optionMenu == 1){
                loadDataBase(gradedStudents);
                break;
            }
            if (optionMenu == 2){
                inputData(STUDENTS_NUMBER, gradedStudents);
                break;
            }
            System.out.println("Ingrese una opcion valida.");
        }

        maxValueResult=maxValue(gradedStudents);
        System.out.println("Nota Maxima: "+ maxValueResult);
        minValueResult=minValue(gradedStudents);
        System.out.println("Nota Minima: "+ minValueResult);
        avgValueResult=avgValue(gradedStudents);
        System.out.println("Nota Promedio: "+ avgValueResult);
        mostRepeatedResult=mostRepeated(gradedStudents);
        lessRepeatedResult=lessRepeated(gradedStudents);
        writeFile(APP_NAME,maxValueResult, minValueResult, avgValueResult, mostRepeatedResult, lessRepeatedResult,gradedStudents);


    }
    public static void appTitle(String appTitle){
        System.out.println("********************************************\n"+appTitle+"\n********************************************\n");
    }
    public static void inputData(byte STUDENTS_NUMBER, HashMap<String, Byte> gradedStudents){
        byte i=1;
        String nombre;
        byte nota;

        Scanner entrada = new Scanner(System.in);
        while(true){

            System.out.print("\nIngrese el nombre del Alumno #" + i + ": ");
            nombre = entrada.nextLine();

            while(true) {
                System.out.print("Ingrese la nota de " + nombre + " (0-10): ");
                nota = entrada.nextByte();
                if(nota>=0 && nota<=10){
                    break;
                }
                System.out.println("Ingrese nota en rango 0-10.");
            }
            gradedStudents.put(nombre,nota);
            entrada.nextLine(); //eat \n of the last nextByte

            if(i==STUDENTS_NUMBER) {
                entrada.close();
                System.out.print("\n");
                break;
            }
            i++;

        }
        System.out.print("\n");
    }
    public static void loadDataBase(HashMap<String, Byte> gradedStudents) {
        try {
            File myObj = new File("./src/studentsData.txt");
            Scanner myReader = new Scanner(myObj);

            if (myObj.exists()) {
                System.out.println("File Name: " + myObj.getName());
            } else {
                System.out.println("The file does not exist.");
            }

            while (myReader.hasNextLine()) {
                String nombre = myReader.nextLine();
                byte nota = Byte.parseByte(myReader.nextLine());
                gradedStudents.put(nombre,nota);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static byte maxValue(HashMap<String, Byte> gradedStudents){
        byte maxValueResult=0;
        for (String student : gradedStudents.keySet()) {
            if(maxValueResult < gradedStudents.get(student))
                maxValueResult = gradedStudents.get(student);
        }
        //System.out.println("MaxValueResult: " + maxValueResult);
        return maxValueResult;
    }
    public static byte minValue(HashMap<String, Byte> gradedStudents){
        byte minValueResult=0;
        for (String student : gradedStudents.keySet()) {
            if(minValueResult > gradedStudents.get(student) || minValueResult==0)
                minValueResult = gradedStudents.get(student);
        }
        //System.out.println("MinValueResult: " + minValueResult);
        return minValueResult;
    }
    public static float avgValue(HashMap<String, Byte> gradedStudents){
        int totalGraded = 0;
        for (String student : gradedStudents.keySet()) {
            totalGraded+= gradedStudents.get(student);
        }
        //System.out.println("AvgValueResult: " + avgValueResult+", Total de Notas: "+totalGraded+", Cantidad de Notas: "+(gradedStudents.size()));
        return (float) totalGraded / gradedStudents.size();
    }
    public static ArrayList<Integer> mostRepeated(HashMap<String, Byte> gradedStudents) {

        byte[] arrangeList = new byte[(gradedStudents.size())];
        ArrayList<Integer> mostRepeatedResult = new ArrayList<>();
        int i = 0;

        for (String student : gradedStudents.keySet()) {
            arrangeList[i] = gradedStudents.get(student);
            i++;
        }

        Arrays.sort(arrangeList);
        int contador = 0;
        int maxRepeated = 0;
        int aux = arrangeList[0];
        for (i = 0; i < arrangeList.length; i++) {
            if (aux == arrangeList[i]) {
                contador++;
            } else {
                contador = 1;
                aux = arrangeList[i];
            }
            if (maxRepeated < contador) {
                maxRepeated = contador;
            }

        }
        System.out.print("Repeticiones Maximas: " + maxRepeated + ", Notas repetidas: ");
        mostRepeatedResult.add(maxRepeated);
        for (i = 0; i < arrangeList.length; i++) {
            if (aux == arrangeList[i]) {
                contador++;
            } else {
                contador = 1;
                aux = arrangeList[i];
            }
            if (maxRepeated == contador) {
                mostRepeatedResult.add((int)arrangeList[i]);
                System.out.print(" [ "+arrangeList[i]+" ] ");
            }
        }
        System.out.print("\n");
        return mostRepeatedResult;
    }
    public static ArrayList<Integer> lessRepeated(HashMap<String, Byte> gradedStudents) {

        byte[] arrangeList = new byte[(gradedStudents.size())];
        ArrayList<Integer> lessRepeatedResult = new ArrayList<>();
        int i = 0;

        for (String student : gradedStudents.keySet()) {
            arrangeList[i] = gradedStudents.get(student);
            i++;
        }

        Arrays.sort(arrangeList);

        int contador = 0;
        int minRepeated = gradedStudents.size();
        int aux = arrangeList[0];
        for (i = 0; i < arrangeList.length; i++) {

            if (aux == arrangeList[i]) {
                contador++;
            } else {
                contador = 1;
                aux = arrangeList[i];
            }

            if (minRepeated > contador) {
                if(arrangeList[i]!=arrangeList[i+1])
                minRepeated = contador;
            }
        }
        System.out.print("Repeticiones Minimas: " + minRepeated + ", Notas repetidas: ");

        lessRepeatedResult.add(minRepeated);
        for (i = 0; i < arrangeList.length; i++) {
            if (aux == arrangeList[i]) {
                contador++;
            } else {
                contador = 1;
                aux = arrangeList[i];
            }
            if (minRepeated == contador) {
                    if(i==arrangeList.length-1){
                        lessRepeatedResult.add((int) arrangeList[i]);
                        System.out.print(" [ " + arrangeList[i] + " ] ");
                    }
                    else if(arrangeList[i]!=arrangeList[i+1]) {
                        lessRepeatedResult.add((int) arrangeList[i]);
                        System.out.print(" [ " + arrangeList[i] + " ] ");
                    }
            }
        }
        System.out.print("\n");
        return lessRepeatedResult;
    }
    public static void writeFile(String appTitle, byte maxValueResult, byte minValueResult, float avgValueResult, ArrayList<Integer> mostRepeatedResult, ArrayList<Integer> lessRepeatedResult, HashMap<String, Byte> gradedStudents){
        /*System.out.print("\nIngrese el nombre del archivo a guardar: ");
        Scanner scanner = new Scanner(System.in);
            String fileName = (String) scanner.nextLine();*/

        try {
            FileWriter myWriter = new FileWriter("letona.txt");
            myWriter.write(appTitle+"\n"
                    +"\nNota Maxima:"+maxValueResult
                    +"\nNota Minima: "+minValueResult
                    +"\nNota Promedio: "+avgValueResult);
            for(int i = 0; i<mostRepeatedResult.size();i++){
                if(i==0)
                    myWriter.write("\nRepeticiones Maximas: "
                            + mostRepeatedResult.get(0)
                            + " Repetidas: ");
                else
                    myWriter.write(" [ "+mostRepeatedResult.get(i)+" ] ");
            }
            for(int i = 0; i<lessRepeatedResult.size();i++){
                if(i==0)
                    myWriter.write("\nRepeticiones Minimas: "
                            + lessRepeatedResult.get(0)
                            + " Repetidas: ");
                else
                    myWriter.write(" [ "+lessRepeatedResult.get(i)+" ] ");
            }
            myWriter.write("\n\nlista de Datos:");

            for(Map.Entry student:gradedStudents.entrySet()){
                    myWriter.write("\n"+student.getKey()+", Nota: "+student.getValue());
            }

            myWriter.close();
            System.out.println("Archivo escrito con exito.");
        } catch (IOException e) {
            System.out.println("Un error ocurrio.");
            e.printStackTrace();
        }
    }
}