package assignment4;

public class Student{
    private final String name;
    private final String email;
    private final StudentID id;

    public Student(String name, String email, String id){
        this.name = name;
        this.email = email;
        this.id = new StudentID(id);
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
     
    public StudentID getID(){
        return id;
    }

    @Override
    public String toString() {
        return "Student[ID=" + id + ", Name=" + name + ", Email=" + email + "]";
    }
}