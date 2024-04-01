import java.io.Serializable;

public class Student implements Serializable {
    Integer userId;
    String userName;

    public Student(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
