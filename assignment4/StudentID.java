package assignment4;

public class StudentID{
    private final String id;

    public StudentID(String id){
        this.id = id;
    }

    public string getID(){
        return id;
    }

    @Override
    public String toString(){
        return id; 
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        StudentID other = (StudentID) obj;
        return id != null ? id.equals(other.id) : other.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}