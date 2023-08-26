
package CW3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrew.abel; Junru
 */
public class FileUtils {
       
    public static List<Person> readFile(String filename) {
        Path path = Paths.get(filename);
        ArrayList<Person> ps = new ArrayList<Person>();

        try{
        // Receive a filename String, and read a file, storing all person 
        // information in an arraylist, handling any file reading errors and
        // returning an array list
            BufferedReader reader = Files.newBufferedReader(path);
            String lineContent = reader.readLine();
            while (lineContent != null){
                System.out.println(lineContent);
                Person newP = new Person(lineContent);
                ps.add(newP);
                lineContent = reader.readLine();
             
                // if there is anything is null
                for (int i = 0; i < ps.size(); i++) {
                    try {
                        ps.get(i).toString();
                    } catch (NullPointerException nullerror) {
                        ps.remove(i);
                    }

                }
            }
            reader.close();
        }
        catch (IOException err){
            System.out.println("No file found");
        }
        return ps;
    }
}
