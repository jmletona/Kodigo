// idea: abro el archivo sino reescribo y capturo errores
// hago un bucle y pido entradas y solicito producto y precio ( aprovecho para ir sumando el total y guardando un hashmap)
// escribo todo en en archivo tanto un for de hashmap como de las operaciones independientes y capturo errores
// despliego lo mismo a pantalla para que el usuario pueda ver resultados
//
//*****************************************
//******** iva tax calculator *************
//*****************************************
import java.io.File; // Import the File class
import java.io.IOException; // Import the IOException class to handle errors
import java.io.FileWriter; // Import the FileWriter class
import java.util.HashMap;
import java.util.Scanner;

public class iva {
    public static void main(String[] args) {
        //user description class
        System.out.println("**************************************\n****** Iva de 3 productos -JML *******\n**************************************\n");
        //try to open file
        try {
            File myObj = new File("iva.txt");
            if (myObj.createNewFile()) {
                System.out.println("Archivo Creado: " + myObj.getName());
            } else {
                System.out.println("Este archivo ya existe " + myObj.getName() + " - se sobreescribira");
            }
        } catch (IOException e) {
            System.out.println("Un error ocurrio.");
            e.printStackTrace();
        }
        //creating variables
        HashMap<String,Double> productoPrecio = new HashMap<>();
        String producto;
        double precio, total = 0.0;
        int i; //for loop
        Scanner entrada = new Scanner(System.in);    //scan input
        //loop asking Name, Price, putting HashMap, incrementing tax iva
        for (i = 0; i < 3; i++){
            System.out.println("Ingrese el NOMBRE del producto # "+(i+1)+":");
                producto = entrada.next();
            System.out.println("Ingrese el PRECIO del producto "+producto.toUpperCase()+": (USD)");
                precio = entrada.nextDouble();
            productoPrecio.put(producto,precio);
            total += precio;
        }
        // Stream.Builder<String> s = Stream.builder();
        //
        //
        // Stream<String> stream = s.add("\nTotal: $")
        //         .add(String.format("%,.2f", total))
        //         .add(" (USD)\nIva: $")
        //         .add(String.format("%,.2f", (total*1.13)-total))
        //         .add(" (USD)\nTotal Neto: $")
        //         .add(String.format("%,.2f", total*1.13))
        //         .add(" (USD).")
        //         .build();
        // Print size, keys and values && write on file
        try {
            FileWriter myWriter = new FileWriter("iva.txt");
            myWriter.write("\nCANTIDAD DE PRODUCTOS: "+productoPrecio.size()+"\n");
            for (String j : productoPrecio.keySet()) {
                myWriter.write("\nPRODUCTO: " + j + ", \nPRECIO: $" + productoPrecio.get(j)+" (USD).\n");
            }
            myWriter.write("\nTotal: $"+String.format("%,.2f", total)+" (USD)\nIva: $"+String.format("%,.2f", (total*1.13)-total)+" (USD)\nTotal Neto: $" + String.format("%,.2f", total*1.13)+ " (USD).");
            myWriter.close();
            System.out.println("Archivo escrito con exito.");
        } catch (IOException e) {
            System.out.println("Un error ocurrio.");
            e.printStackTrace();
        }


        //output all info to user
        System.out.println("\nCANTIDAD DE PRODUCTOS: "+productoPrecio.size());
        for (String j : productoPrecio.keySet()) {
            System.out.println("PRODUCTO: " + j + ", PRECIO: " + productoPrecio.get(j));
        }
        //stream.forEach(System.out::println);
        System.out.println("\nTotal: $"+String.format("%,.2f", total)+" (USD)\nIva: $"+String.format("%,.2f", (total*1.13)-total)+" (USD)\nTotal Neto: $" + String.format("%,.2f", total*1.13)+ " (USD).");
        entrada.close();
    }
}