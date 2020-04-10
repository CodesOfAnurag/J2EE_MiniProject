public class Teacher {
	int id;
	String name;
	String subjects[];
	Teacher(int i, String n, String subjects[]){
		id=i;
		name=n;
		this.subjects = subjects;
	}
	public String toString(){
		return "User Name "+id+"\nTeacher's Name: "+name+"\nSUbjects:"+subjects;
	}
}