// Creating a class named Student
class Student {
    private String StudentID; // Declaring a private variable to store Student ID
    private String StudentName; // Declaring a private variable to store Student Name

    // Constructor for initializing Student object with ID and Name
    public Student(String StudentID, String StudentName) {
        this.StudentID = StudentID; // Setting the value of StudentID using constructor parameter
        this.StudentName = StudentName; // Setting the value of StudentName using constructor parameter
    }

    // Method to get (access) the Student ID
    public String getStudentID() {
        return StudentID; // Returning the value of StudentID
    }

    // Method to get (access) the Student Name
    public String getStudentName() {
        return StudentName; // Returning the value of StudentName
    }
}
