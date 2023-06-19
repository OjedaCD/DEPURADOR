import java.io.*;//nos servira para comunicarnos fuera del compilador, con el sistema operativo 
import javax.swing.JOptionPane;// nos permitira saber si hay algun error en la ejecucion 

public class Depurador {

    public void depurar(String nombreL) {// Recibe el nombre del archivo que se leera

        File f2; //Accedemos a la clase file para escribir
        FileWriter w; //Un objeto que nos permite ir fuera del compilador 
        BufferedWriter bw;// Objeto que lee en memoria el archivo
        PrintWriter wr;// No permite escribir fuera del compilador 

        File f; //Accedemos a la clase file para leer
        FileReader r;// Accesamos a la clase FileReader
        BufferedReader br;// Accesamos a la clase br por medio del objeto

        try {
            f = new File(nombreL);// se manda un nombre a la clase file para generar el archivo
            r = new FileReader(f);//// se accede al archivo para leer el contenido
            br = new BufferedReader(r);// se asigna un espacio en el buffer 

            f2 = new File("Depurado.uno");//Asignamos un nombre y extencion al archivo que generaremos 
            w = new FileWriter(f2);// se abre el archivo f con el objeto w
            bw = new BufferedWriter(w);// espacio en memoria para editar el archivo w
            wr = new PrintWriter(bw);//accion para escribir en archivo f que esta en memoria en bw

            String linea;// es donde se almacenara el buffer
            int aux1;// controla la piciocion actual de i
            int aux2;// controla la posicion i-1 
            String c = "";
            while ((linea = br.readLine()) != null) {// permite leer renglon a renglon el archivo

                for (int i = 0; i < linea.length(); i++) {// Dependiendo de la linea sera el tamaÒo
                    if (linea.charAt(i) == '#') {// compara la cadena linea, caracter por caracter 
                    //si encuentra un # significa que esta en comentarios y sera borrada en la depuracion final
                        break;// sale del for al while y sigue con la iteracion 
                    }
                    if (i > 0) {// entramos a este bloque cuando ya estamos en la posicion 1 de la cadena

                        aux1 = (int) linea.charAt(i - 1);// se define la posicion anteririor a la iteracion en tipo entero
                        aux2 = (int) linea.charAt(i);// se define la posicion actual de la iteracion en tipo entero
                        // el valor ascii del espacio vacio es 32
                        // el valor ascii del tabulador es 9

                        if (aux1 == 9 && aux2 == 32 || aux1 == 32 && aux2 == 9 || aux1 == 9 && aux2 == 9 || aux1 == 32 && aux2 == 32) {
                            continue;
                        } else {
                            if (i == 1 && aux1 != 32 && aux2 != 32 && aux1 != 9 && aux2 != 9) {
                                c = c + linea.charAt(i - 1);// se acumulan los caracteres depurados
                            }
                            c = c + linea.charAt(i);// se acumulan los caracteres
                        }
                    }

                }// se cierra el for
                if (c.length() > 0) {
                    wr.write(c + "\n");// escribe la linea en el archivo
                    System.out.println(c);// imprime la linea en consola
                    c = "";// se limpa la linea
                }
            }
            r.close();// cuando abrimos fileReader y fileWriter debemos de cerrar obligatoriamente
            br.close();
            wr.close();
            bw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error " + e);//se√±ala la ecxepcion en cado de un error
        }

    }

}
