public class StudentInfo {
    // ID, name, program, batch, password and CGPA
    private String id;
    private String Name;
    private String program;
    private String batch;
    private String cgpa;
    private String password;

    public StudentInfo() {
    }
    public StudentInfo(String id, String name, String program, String batch, String cgpa) {
        this.id = id;
        this.Name = name;
        this.program = program;
        this.batch = batch;
        this.cgpa = cgpa;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }

    public String getCgpa() {
        return cgpa;
    }
    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void display(){
        System.out.println(": Students Information :");
        System.out.println("Name: " + Name);
        System.out.println("ID: " + id);
        System.out.println("Program: " + program);
        System.out.println("Batch: " + batch);
        System.out.println("CGPA: " + cgpa);
    }
}
