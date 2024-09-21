// Creating a class named Module
public class Module {
    // Declaring private variables to store student information and module marks
    private String StudentID;
    private int Module1Mark;
    private int Module2Mark;
    private int Module3Mark;
    private int TotalMarks; // Variable to store total marks
    private double Average; // Variable to store average marks
    private String Grade; // Variable to store grade

    // Constructor to initialize Module object with student ID and module marks
    public Module(String StudentID, int module1Mark, int module2Mark, int module3Mark) {
        this.StudentID = StudentID; // Setting the value of StudentID using constructor parameter
        this.Module1Mark = module1Mark; // Setting the value of Module1Mark using constructor parameter
        this.Module2Mark = module2Mark; // Setting the value of Module2Mark using constructor parameter
        this.Module3Mark = module3Mark; // Setting the value of Module3Mark using constructor parameter

        // Calculating total marks by adding module marks
        TotalMarks = module1Mark + module2Mark + module3Mark;

        // Calculating average marks by dividing total marks by 3.0 (to get decimal result)
        Average = TotalMarks / 3.0;

        // Calling a method to determine the grade based on the average marks
        Grade = CheckGrade(Average);
    }

    // Method to get (access) the average marks
    public double getAverage() {
        return Average; // Returning the value of Average
    }

    // Method to get (access) the total marks
    public int getTotalMarks() {
        return TotalMarks; // Returning the value of TotalMarks
    }

    // Method to get (access) the grade
    public String getGrade() {
        return Grade; // Returning the value of Grade
    }

    // Private method to determine the grade based on average marks
    private String CheckGrade(double Average) {
        // Checking average marks against grade boundaries and returning the corresponding grade
        if (Average >= 75) {
            return "A PASS"; // Returning "A PASS" if average is 75 or higher
        } else if (Average >= 65) {
            return "B PASS"; // Returning "B PASS" if average is between 65 and 74
        } else if (Average >= 55) {
            return "C PASS"; // Returning "C PASS" if average is between 55 and 64
        } else if (Average >= 35) {
            return "S PASS"; // Returning "S PASS" if average is between 35 and 54
        } else {
            return "F PASS"; // Returning "F PASS" if average is below 35
        }
    }

    // Method to get (access) the student ID
    public String getStudentID() {
        return StudentID; // Returning the value of StudentID
    }

    // Method to get (access) the mark for Module 1
    public int getModule1Mark() {
        return Module1Mark; // Returning the value of Module1Mark
    }

    // Method to get (access) the mark for Module 2
    public int getModule2Mark() {
        return Module2Mark; // Returning the value of Module2Mark
    }

    // Method to get (access) the mark for Module 3
    public int getModule3Mark() {
        return Module3Mark; // Returning the value of Module3Mark
    }
}
