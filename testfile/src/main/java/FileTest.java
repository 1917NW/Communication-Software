import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTest {
    public static void main(String[] args) {
        String filenameDir = "src/main/resources/localCache123";
        String fileName = "/objects.dat";
        File dir = new File(filenameDir);
        if(!dir.exists())
            dir.mkdir();

        String filename = filenameDir + fileName;
        int idToDelete = 3;

        List<ChatRecordDto> myObjectList = new ArrayList<>();
        ChatRecordDto chatRecordDto = new ChatRecordDto();
        chatRecordDto.setUserId("lxy");
        myObjectList.add(chatRecordDto);

        try {
            FileManager.writeObjectsToFile(filename, myObjectList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        try {
//            FileManager.deleteObjectById(filename, idToDelete);
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        List<ChatRecordDto> myObjects = null;
        try {
            myObjects = FileManager.readObjectsFromFile(filename);
            System.out.println(myObjects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
