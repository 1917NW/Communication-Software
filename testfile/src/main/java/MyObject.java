import java.io.Serializable;
 
public class MyObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Student data;
 
    // Constructor, getters, and setters
    public MyObject(int id, Student data) {
        this.id = id;
        this.data = data;
    }
 
    public int getId() {
        return id;
    }
 
    // Method to check if the object should be deleted
    public boolean isToBeDeleted(int idToDelete) {
        return this.id == idToDelete;
    }
 
    @Override
    public String toString() {
        return "id: " + id + ", data: " + data;
    }
}