import java.io.*;
import java.util.*;
public class Main {
    //Register User
    public static void registerInfo(String userName, String email,String passcode){
        String fileName = userName+".txt";
        File file = new File(fileName);
        if(file.exists()){
            System.out.println("User Already Exist.");
        } else {
            try {
                RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
                raf.seek(raf.length());
                raf.writeBytes(userName + "," + email + "," + passcode + ",");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File Not Found");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to Write file");
            }
        }
    }
    //Login User
     public static boolean loginUser(String userName, String passcode){
        String fileName = userName+".txt";
        try {
            RandomAccessFile raf = new RandomAccessFile(fileName,"r");
            String line;
            while((line=raf.readLine())!=null){
                String[] parts = line.split(",");
                String pass = parts[2];
                if(pass.equals(passcode)){
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException ex){
            System.out.println("File Not Found "+ex.getMessage());
            return false;
        } catch (IOException e){
            System.out.println("Failed to Read file "+e.getMessage());
            return false;
        }
    }
    //Student Register
    public static void studentRegister(StudentInfo student){
        String fileName = student.getId()+".txt";
        File file = new File(fileName);
        if(file.exists()){
            System.out.println("User Already Exist.");
        } else {
            try {
                RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
                raf.seek(raf.length());
                raf.writeBytes(student.getId() + "," + student.getName() + "," + student.getProgram() + "," + student.getBatch() + "," + student.getCgpa() + "\n");
                student.display();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File Not Found");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to Write file");
            }
        }
    }
    // Display info by ID
    public static void displayInfo(String ID){
        String fileName = ID+".txt";
        try {
            RandomAccessFile raf = new RandomAccessFile(fileName,"r");
            //id1,name1,program1,batch1,cgpa1
            String line;
            while((line=raf.readLine())!=null){

                String[] parts = line.split(",");
                if(parts.length<=3){
                    continue;
                }
                String ID1 = parts[0];
                String name1 = parts[1];
                String program1 = parts[2];
                String batch1 = parts[3];
                String cgpa1 = parts[4];
                System.out.println(": Information of Student "+ID1+" :");
                System.out.println("ID: "+ID1);
                System.out.println("Name: "+name1);
                System.out.println("Program: "+program1);
                System.out.println("Batch: "+batch1);
                System.out.println("CGPA: "+cgpa1);
            }
        } catch (FileNotFoundException ex){
            System.out.println("File Not Found "+ex.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to Read file"+e.getMessage());
        }
    }
    //Add course
    public static void addCourse(String id){
        String fileName = id+".txt";
        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("User Does Not Exist");
        }else {
            Scanner add = new Scanner(System.in);
            try {
                RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
                raf.seek(raf.length());
                System.out.print("Enter Course Name : ");
                String courseName = add.nextLine();
                raf.writeBytes(courseName + "\n");
                System.out.println("Course added Successful");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File Not Found");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to Write file");
            }
        }
    }
    //Display All Courses
    public static void displayCourse(String id){
        ArrayList<String> courseAll = new ArrayList<>();
        String fileName = id+".txt";
        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("User Does Not Exist");
        } else {
            try{
                RandomAccessFile raf = new RandomAccessFile(fileName,"r");
                String line;
                while((line=raf.readLine())!=null){
                    courseAll.add(line);
                }
                System.out.println("----All Advised Course----");
                for (int i=1;i<courseAll.size();i++){
                    System.out.print("Course "+i+" : ");
                    System.out.println(courseAll.get(i));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found"+e.getMessage());
            } catch (IOException ex) {
                System.out.println("Failed to Read file");
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Scanner  in = new Scanner (System.in);
        while(true){
            //Main Menu
            System.out.println(": MENU :");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choice :");
            //Main Menu Choice
            int choice = in.nextInt();
            in.nextLine();
            outerswitch:
            switch (choice){
                case 1:
                    System.out.print("Enter New Email : ");
                    String newEmail = in.nextLine();
                    System.out.print("Enter User Name : ");
                    String userName = in.nextLine();
                    System.out.print("Enter New Password : ");
                    String newPassword = in.nextLine();
                    System.out.print("Enter Confirm Password : ");
                    String confirmPassword = in.nextLine();
                    if (newPassword.equals(confirmPassword)){
                        if(confirmPassword.length()<=8 && confirmPassword.length()>=6) {
                            registerInfo(userName, newEmail, newPassword);
                            System.out.println("Sign Up Successful");
                        } else {
                            System.out.println("Invalid Password");
                            System.out.println("* Your password must have 6 to 8 characters.");
                        }
                    } else {
                        System.out.println("Invalid Password");
                        System.out.println("* Your Password Does not Match.");

                    }
                    break;
                case 2:
                    System.out.println(": Login :");
                    System.out.print("Enter User Name : ");
                    String user = in.nextLine();
                    System.out.print("Enter Password : ");
                    String pass = in.nextLine();
                    boolean loginIN = loginUser(user,pass);
                    if(loginIN){
                        System.out.println("Login Successful");
                        while (true) {
                            //Sub Menu
                            System.out.println(": Student Information Management System :");
                            System.out.println("1. Add Student Information");
                            System.out.println("2. Search Student Information");
                            System.out.println("3. Add Course");
                            System.out.println("4. Display Student Courses");
                            System.out.println("5. Log Out");
                            System.out.print("Choice :");
                            int choice2 = in.nextInt();
                            in.nextLine();
                            switch (choice2){
                                case 1:
                                    System.out.println(":-Student Profile create-:");
                                    System.out.print("Enter Student Name : ");
                                    String name1 = in.nextLine();
                                    System.out.print("Enter Student ID :");
                                    String id1 = in.nextLine();
                                    System.out.print("Enter Student Program : ");
                                    String program1 = in.nextLine();
                                    System.out.print("Enter Student Batch : ");
                                    String batch1 = in.nextLine();
                                    System.out.print("Enter Student CGPA : ");
                                    String cgpa1 = in.nextLine();
                                    StudentInfo newStudent = new StudentInfo(id1,name1,program1,batch1,cgpa1);
                                    studentRegister(newStudent);
                                    break;
                                case 2:
                                    System.out.print("Enter Student Id :");
                                    String id2 = in.nextLine();
                                    displayInfo(id2);
                                    break;
                                case 3:
                                    System.out.print("Enter ID :");
                                    String id3 = in.nextLine();
                                    addCourse(id3);
                                    break;
                                case 4:
                                    System.out.print("Enter Student Id:");
                                    String id4 = in.nextLine();
                                    displayCourse(id4);
                                    break;
                                case 5:
                                    break outerswitch;

                            }
                        }
                    } else {
                        System.out.println("Login Failed");
                    }
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}