
public class Student {
    int id;     // the USN can be taken as the id same as SIS
    String name;
    
    Student(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return "Student ID:"+id+"\nStudent name"+name;
    }
}