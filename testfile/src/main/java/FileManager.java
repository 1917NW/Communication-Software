import io.ObjectInputStreamWithNoHeader;
import io.ObjectOutputStreamWithNoHeader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
public class FileManager {
 

 
    public static List<ChatRecordDto> readObjectsFromFile(String filename) throws IOException, ClassNotFoundException {
        List<ChatRecordDto> objects = new ArrayList<>();
        File file = new File(filename);
        if(!file.exists())
            file.createNewFile();
        try (ObjectInputStreamWithNoHeader ois = new ObjectInputStreamWithNoHeader(new FileInputStream(file))) {
            while (true) {
                try {
                    ChatRecordDto obj = (ChatRecordDto) ois.readObject();
                    objects.add(obj);
                }catch (EOFException e){
                    break;
                }
            }
        }catch (Exception e){
            return objects;
        }
        return objects;
    }
 
    public static void writeObjectsToFile(String filename, List<ChatRecordDto> objects) throws IOException {
        try (ObjectOutputStreamWithNoHeader oos = new ObjectOutputStreamWithNoHeader(new FileOutputStream(filename, true))) {
            for (ChatRecordDto obj : objects) {
                oos.writeObject(obj);
            }
        }
    }
}