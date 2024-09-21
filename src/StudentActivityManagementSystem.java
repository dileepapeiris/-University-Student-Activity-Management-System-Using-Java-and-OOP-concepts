import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentActivityManagementSystem {
    // Set Constants
    public static final int STUDENT_CAPACITY = 100;   //Set student capacity of university to 100
    //initialize variables
    private static int registeredStudentCount = 0;  //initialize variable called "registeredStudentCount"
    private static int registerdStudentCountWithMarks = 0; //initialize variable called "registeredStudentCountWithMarks"
    private static int reportCount;
    // Define arrays in system
    public static String[][] studentsDataArray = new String[STUDENT_CAPACITY][2];   //Define 2D array to store student data as student name and student id
    private static Student[] objectArrayOfStudents = new Student[STUDENT_CAPACITY]; //Define array of objects to store student name and id through "Student" class
    private static Module[] objectArrayOfMarks = new Module[STUDENT_CAPACITY]; //Define array of objects to store module marks through "Module" class
    private static String[][] arrayOfReportData = new String[registeredStudentCount][6]; //Define 2D array to store registered student to generate report

    // Define scanner class
    static Scanner getInput = new Scanner(System.in); // Set Scanner class as  "getInput"

    // Main menu

    /*
     * This method includes infinity loop.
     * It runs until user enter choice.
     * It calls methods according to user input
     *
     */
    public static void main(String[] args) {
        while (true) { // Start an infinite loop to keep showing the menu
            System.out.println("""
                    \n
                    ******************************************************************
                        Welcome to university student activity management system !
                    ******************************************************************
                    1.Check Available Slots
                    2.Register A New Student
                    3.Delete Student
                    4.Find Student
                    5.Export Student Details To File
                    6.Load Student Details From File
                    7.View List Of Students (Based on names)
                    8.Manage Students Results
                    9.Exit
                    -------------------------------------------------------------------"""); // Print the menu options

            int mainMenuChoice = 0; // Variable to store user's choice
            boolean isValidInput = false; // Variable to check if input is valid

            while (!isValidInput) { // Loop until valid input is entered
                try {
                    System.out.print("Enter Your Choice Between (1-9) :- "); // Ask the user to enter their choice
                    mainMenuChoice = getInput.nextInt(); // Read the user's choice
                    isValidInput = true; // Mark the input as valid if no exception is thrown
                } catch (InputMismatchException e) { // Catch invalid input
                    System.out.println("Error ! : Invalid input! Please enter a valid number between 1 and 9."); // Print error message
                    getInput.next(); // Clear the invalid input from the scanner
                }
            }

            // Execute the corresponding method based on user's choice
            switch (mainMenuChoice) {
                case 1:
                    checkAvailableSlots(); // Call method to check available slots
                    break;
                case 2:
                    registerANewStudent(); // Call method to register a new student
                    break;
                case 3:
                    deleteStudent(); // Call method to delete a student
                    break;
                case 4:
                    findStudent(); // Call method to find a student
                    break;
                case 5:
                    exportDataToFile(arrayOfReportData, reportCount); // Call method to export student details to a file
                    break;
                case 6:
                    loadDataFromFile(); // Call method to load student details from a file
                    break;
                case 7:
                    viewMarksWithStudentNameAndID(); // Call method to view the list of students
                    break;
                case 8:
                    manageStudentResults(); // Call method to manage student results
                    break;
                case 9 :
                    return;
                default:
                    System.out.println("Invalid Choice !.Enter option between 1-9 "); // Print error message for invalid choice
            }
        }
    }



    /*
    isAvailableSlots() method.
    * This checks if seats available or not and return true or false
    */
    public static boolean isAvailableSlots() {
        return registeredStudentCount < STUDENT_CAPACITY;
    }


    /*
    checkAvailableSlots() method
    * This method print whether slots available or not
    * It users return value of isAvailableSlots() method.
    */

    public static void checkAvailableSlots() { // Define the method to check available slots
        if (isAvailableSlots()) { // Check if there are available slots
            System.out.print("\nThere are " + (STUDENT_CAPACITY - registeredStudentCount) + " slots available in university"); // Print the number of available slots
        } else { // If no slots are available
            System.out.println("No seats available in university"); // Print that no seats are available
        }
    }


    /*
    registerANewStudent() method
    * This method used to add students
    */
    public static void registerANewStudent() { // Define the method to register a new student
        if (isAvailableSlots()) { // Check if there are available slots
            String StudentID = getValidStudentID(); // Get a valid student ID
            boolean isIDExitsOrNotLogic = false; // Initialize a flag to check if the ID already exists

            for (int index = 0; index < registeredStudentCount; index++) { // Loop through the registered students
                if (studentsDataArray[index][0].equals(StudentID)) { // Check if the current student's ID matches the new ID
                    isIDExitsOrNotLogic = true; // If it matches, set the flag to true
                    break; // Exit the loop
                }
            }

            if (!isIDExitsOrNotLogic) { // If the ID does not exist
                String StudentName = getValidStudentName(); // Get a valid student name
                studentsDataArray[registeredStudentCount][0] = StudentID; // Save the student ID in the array
                studentsDataArray[registeredStudentCount][1] = StudentName; // Save the student name in the array
                registeredStudentCount++; // Increase the count of registered students
                System.out.println("Student Registration Done"); // Print that the registration is done
            } else { // If the ID already exists
                System.out.println("Student ID Already Exists . It cannot be duplicated .Try again with different ID"); // Print an error message
            }
        } else { // If no slots are available
            System.out.println("No seats Available in university"); // Print that no seats are available
        }
    }

    /*
    Define the method to delete a student
    */
    public static void deleteStudent() {
        System.out.println("The Data in System :-"); // Print a message indicating data display is starting
        printReport(); // Call a method to print the current report of students

        System.out.print("Enter Student ID to delete: "); // Prompt the user to enter the student ID to delete
        String studentID = getValidStudentID(); // Get a valid student ID from user input

        boolean studentFound = false; // Initialize a flag to check if the student is found
        for (int i = 0; i < registeredStudentCount; i++) { // Loop through the registered students
            if (studentsDataArray[i][0].equals(studentID)) { // Check if the current student's ID matches the one to delete
                studentFound = true; // If found, set the flag to true

                // Shift elements to the left to delete the student's data
                for (int j = i; j < registeredStudentCount - 1; j++) {
                    studentsDataArray[j][0] = studentsDataArray[j + 1][0]; // Shift student IDs
                    studentsDataArray[j][1] = studentsDataArray[j + 1][1]; // Shift student names
                    objectArrayOfStudents[j] = objectArrayOfStudents[j + 1]; // Shift student objects
                    objectArrayOfMarks[j] = objectArrayOfMarks[j + 1]; // Shift student marks
                }

                registeredStudentCount--; // Decrease the count of registered students

                // If the deleted student had marks, adjust the count of students with marks
                if (objectArrayOfMarks[i].getModule1Mark() != -1) {
                    registerdStudentCountWithMarks--;
                }

                // Print confirmation message of deletion
                System.out.println("Student with ID " + studentID + " has been deleted.");

                // Print updated data after deletion
                System.out.println("The Updated Data After Deleting in System :-");
                printReport(); // Call method to print updated report
                break; // Exit the loop since the student is found and deleted
            }
        }

        // If student not found, print message indicating so
        if (!studentFound) {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }


    /*
    Method to find a student by ID
    */
    public static void findStudent() {
        System.out.print("Enter Student ID to search: "); // Prompt user to enter student ID
        String studentID = getValidStudentID(); // Get valid student ID from user input

        for (int i = 0; i < registeredStudentCount; i++) { // Iterate through registered students
            if (studentsDataArray[i][0].equals(studentID)) { // Check if student ID matches
                // Print student ID and name if found
                System.out.println("Student ID: " + studentsDataArray[i][0] + ", Name: " + studentsDataArray[i][1]);
                return; // Exit method after printing
            }
        }
        // Print message if student not found
        System.out.println("Student with ID " + studentID + " not found.");
    }


    //File Handling Methods

    /*
    Method to Export data to the txt file
    */
    private static void exportDataToFile(String[][] reportData, int reportCount) {
        try (FileWriter writer = new FileWriter("StudentData.txt")) { // Open FileWriter to write to file
            for (int i = 0; i < reportCount; i++) { // Iterate through report data
                if (reportData[i][2].equals("marks not recorded")) { // Check if marks are not recorded
                    // Write student ID and name with marks not recorded message
                    writer.write("Student ID: " + reportData[i][0] + ", Name: " + reportData[i][1] + " marks not recorded.\n");
                } else {
                    // Write student ID, name, modules, and total marks
                    writer.write("Student ID: " + reportData[i][0] + ", Name: " + reportData[i][1] +
                            ", module 1: " + reportData[i][2] +
                            ", module 2: " + reportData[i][3] +
                            ", module 3: " + reportData[i][4] +
                            ", total: " + reportData[i][5] + "\n");
                }
            }
            System.out.println("Done : Student data stored successfully to the StudentData.txt File"); // Success message
        } catch (IOException e) { // Catch IOException if file operations fail
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    /*
        Method to load data from the txt file
        */
    private static void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("StudentData.txt"))) { // Open FileReader to read from file
            String line; // String to store each line read from file
            registeredStudentCount = 0; // Initialize registered student count
            registerdStudentCountWithMarks = 0; // Initialize registered student count with marks

            while ((line = reader.readLine()) != null) { // Read each line until end of file
                String[] parts = line.split(", "); // Split line into parts based on comma and space
                String studentID = parts[0].split(": ")[1]; // Extract student ID from first part
                String studentName = parts[1].split(": ")[1]; // Extract student name from second part

                studentsDataArray[registeredStudentCount][0] = studentID; // Store student ID in array
                studentsDataArray[registeredStudentCount][1] = studentName; // Store student name in array

                objectArrayOfStudents[registeredStudentCount] = new Student(studentID, studentName); // Create Student object

                if (parts.length == 2) { // Check if line has only student ID and name
                    objectArrayOfMarks[registeredStudentCount] = new Module(studentID, -1, -1, -1); // Create Module object with marks not recorded
                } else {
                    // Extract module marks and total from parts
                    int module1 = Integer.parseInt(parts[2].split(": ")[1]);
                    int module2 = Integer.parseInt(parts[3].split(": ")[1]);
                    int module3 = Integer.parseInt(parts[4].split(": ")[1]);
                    int total = Integer.parseInt(parts[5].split(": ")[1]);

                    // Create Module object with module marks
                    objectArrayOfMarks[registeredStudentCount] = new Module(studentID, module1, module2, module3);
                    registerdStudentCountWithMarks++; // Increment count of students with recorded marks
                }

                registeredStudentCount++; // Increment registered student count
            }
            System.out.println("Done : Student data loaded successfully from StudentData.txt File"); // Success message

        } catch (FileNotFoundException e) { // Catch FileNotFoundException if file is not found
            System.out.println("File not found. Please ensure the file 'StudentData.txt' exists."); // Print error message
        } catch (IOException e) { // Catch IOException if file operations fail
            e.printStackTrace(); // Print stack trace for debugging
        }
    }


    public static void viewMarksWithStudentNameAndID() {
        // Sort studentsDataArray based on student names using Bubble Sort
        System.out.println("Sorted Student Names To alphabetical Order :-");
        for (int i = 0; i < registeredStudentCount - 1; i++) {  // Loop through the array
            for (int j = 0; j < registeredStudentCount - 1 - i; j++) {  // Inner loop for sorting
                // Compare student names ignoring case sensitivity
                if (studentsDataArray[j][1].toLowerCase().compareTo(studentsDataArray[j + 1][1].toLowerCase()) > 0) {
                    // Swap the positions of studentsDataArray[j] and studentsDataArray[j + 1]
                    String[] temporaryArray = studentsDataArray[j];
                    studentsDataArray[j] = studentsDataArray[j + 1];
                    studentsDataArray[j + 1] = temporaryArray;
                }
            }
        }

        // Print sorted student names and IDs
        for (int i = 0; i < registeredStudentCount; i++) {  // Loop through sorted array
            // Print each student's name and ID
            System.out.println("Name: " + studentsDataArray[i][1] +
                    ", ID: " + studentsDataArray[i][0]);
        }
    }


    // Sub Menu
    public static void manageStudentResults() {
        // Loop to keep the sub-menu running until user chooses to exit
        while (true) {
            // Printing the sub-menu options for managing student results
            System.out.println("""
                    \n 
                    ----------------------------------------------------------------------------
                                               Manage Student Results
                    *****************************************************************************
                    a . Add Student Name
                    b . Add Module Marks
                    c . Generate Summary
                    d . Generate Complete Report
                    e . Exit To Main Menu
                    ------------------------------------------------------------------------------
                    """);
            // Asking user for their choice
            System.out.print("Enter Your Choice Here :- ");

            // Reading user input for sub-menu choice
            String subMenuChoice = getInput.nextLine().toLowerCase();

            // Switch case to handle user's choice
            switch (subMenuChoice) {
                case "a":
                    registerANewStudent(); // Calling method to add a new student
                    break;
                case "b":
                    addModuleMarks(); // Calling method to add module marks for a student
                    break;
                case "c":
                    generateSummary(); // Calling method to generate summary of student data
                    break;
                case "d":
                    printReport(); // Calling method to generate and print complete report
                    break;
                case "e":
                    return; // Exiting to the main menu
                default:
                    System.out.println("Invalid Choice"); // Informing user of invalid input
            }
        }
    }

    // Method to generate summary of student data
    public static void generateSummary() {
    // Get the total number of registered students
    int totalStudents = registeredStudentCount;

    // Get the total number of students with marks recorded
    int totalStudentsWithMarks = registerdStudentCountWithMarks;

    // Calculate the number of students without marks recorded
    int totalStudentsWithoutMarks = totalStudents - totalStudentsWithMarks;

    // Print the total number of student registrations in the university
    System.out.println("Total Student Registrations in University : " + totalStudents);

    // Print the total number of students with marks
    System.out.println("Total Students With Marks : " + totalStudentsWithMarks);

    // Print the total number of students without marks
    System.out.println("Total Students Without Marks : " + totalStudentsWithoutMarks);

    // Initialize counters for the number of students passing each module
    int module1PassedCount = 0;
    int module2PassedCount = 0;
    int module3PassedCount = 0;

    // Loop through all students with marks
    for (int index = 0; index < registerdStudentCountWithMarks; index++) {
        // If the student scored more than 40 in module 1, increase the count
        if (objectArrayOfMarks[index].getModule1Mark() > 40) {
            module1PassedCount++;
        }
        // If the student scored more than 40 in module 2, increase the count
        if (objectArrayOfMarks[index].getModule2Mark() > 40) {
            module2PassedCount++;
        }
        // If the student scored more than 40 in module 3, increase the count
        if (objectArrayOfMarks[index].getModule3Mark() > 40) {
            module3PassedCount++;
        }
    }

    // Print the number of students who passed module 1
    System.out.println("Total count of students who scored more than 40 marks in Module 1 : " + module1PassedCount);

    // Print the number of students who passed module 2
    System.out.println("Total count of students who scored more than 40 marks in Module 2 : " + module2PassedCount);

    // Print the number of students who passed module 3
    System.out.println("Total count of students who scored more than 40 marks in Module 3 : " + module3PassedCount);
}

public static void generateReport() {
    // Create an array to store report data for all registered students
    arrayOfReportData = new String[registeredStudentCount][6];

    // Create an array to track printed student IDs
    int[] printedStudentIDs = new int[registeredStudentCount];

    // Initialize report count and printed count
    reportCount = 0;
    int printedCount = 0;

    // Loop through all registered students
    for (int index = 0; index < registeredStudentCount; index++) {
        // Get the student's ID and name from the student data array
        String studentID = studentsDataArray[index][0];
        String studentName = studentsDataArray[index][1];

        // Initialize flags for found and already printed
        boolean found = false;
        boolean alreadyPrinted = false;

        // Extract numeric ID from the student ID string
        int numericID = Integer.parseInt(studentID.substring(1));

        // Check if the student ID has already been printed
        for (int k = 0; k < printedCount; k++) {
            if (printedStudentIDs[k] == numericID) {
                alreadyPrinted = true;
                break;
            }
        }

        // Skip this student if already printed
        if (alreadyPrinted) {
            continue;
        }

        // Loop through all students with marks
        for (int j = 0; j < registerdStudentCountWithMarks; j++) {
            // Check if this student's marks are recorded
            if (objectArrayOfMarks[j].getStudentID().equals(studentID)) {
                // Store student data in the report array
                arrayOfReportData[reportCount][0] = studentID;
                arrayOfReportData[reportCount][1] = studentName;
                arrayOfReportData[reportCount][2] = String.valueOf(objectArrayOfMarks[j].getModule1Mark());
                arrayOfReportData[reportCount][3] = String.valueOf(objectArrayOfMarks[j].getModule2Mark());
                arrayOfReportData[reportCount][4] = String.valueOf(objectArrayOfMarks[j].getModule3Mark());
                arrayOfReportData[reportCount][5] = String.valueOf(objectArrayOfMarks[j].getTotalMarks());

                // Increment the report count and mark this student ID as printed
                reportCount++;
                printedStudentIDs[printedCount++] = numericID;
                found = true;
                break;
            }
        }

        // If no marks are recorded for this student, store that information
        if (!found) {
            arrayOfReportData[reportCount][0] = studentID;
            arrayOfReportData[reportCount][1] = studentName;
            arrayOfReportData[reportCount][2] = "marks not recorded";
            arrayOfReportData[reportCount][3] = "";
            arrayOfReportData[reportCount][4] = "";
            arrayOfReportData[reportCount][5] = "";
            reportCount++;
        }
    }

    // Bubble sort to sort the student IDs in descending order
    for (int i = 0; i < reportCount - 1; i++) {
        for (int j = 0; j < reportCount - i - 1; j++) {
            int id1 = Integer.parseInt(arrayOfReportData[j][0].substring(1));
            int id2 = Integer.parseInt(arrayOfReportData[j + 1][0].substring(1));
            if (id1 < id2) {
                // Swap the rows if the IDs are out of order
                String[] temp = arrayOfReportData[j];
                arrayOfReportData[j] = arrayOfReportData[j + 1];
                arrayOfReportData[j + 1] = temp;
            }
        }
    }
}

private static void printReport() {
    // Generate the report data
    generateReport();
    System.out.println("Sort Student IDs to descending order :-  ");
    // Loop through each student's data in the report
    for (int i = 0; i < reportCount; i++) {
        // Check if marks for module 1 are not recorded for the student

        if (arrayOfReportData[i][2].equals("marks not recorded")) {
            // Print student ID and name with a message indicating marks are not recorded
            System.out.println("Student ID: " + arrayOfReportData[i][0] + ", Name: " + arrayOfReportData[i][1] + " marks not recorded.");
        } else {
            // Print student ID, name, module marks, and total marks if available

            System.out.println("Student ID: " + arrayOfReportData[i][0] + ", Name: " + arrayOfReportData[i][1] +
                    ", module 1: " + arrayOfReportData[i][2] +
                    ", module 2: " + arrayOfReportData[i][3] +
                    ", module 3: " + arrayOfReportData[i][4] +
                    ", total: " + arrayOfReportData[i][5]);
        }
    }
}


    // Method to add module marks for a student
    public static void addModuleMarks() {
        System.out.print("Enter Student ID Here: "); // Prompting user to enter student ID
        String StudentID = getInput.nextLine().trim(); // Reading user input for student ID

        // Checking if student with entered ID exists
        boolean studentExists = false;
        for (int index = 0; index < registeredStudentCount; index++) {
            if (studentsDataArray[index][0].equals(StudentID)) {
                studentExists = true;
                break;
            }
        }

        if (!studentExists) {
            System.out.println("Error: Student not found. Please register the new student using option 'a'.");
            return;
        }

        // Checking if marks for this student ID already exist
        for (int indexWithMarks = 0; indexWithMarks < objectArrayOfMarks.length; indexWithMarks++) {
            if (objectArrayOfMarks[indexWithMarks] != null && objectArrayOfMarks[indexWithMarks].getStudentID().equals(StudentID)) {
                System.out.println("Error: The marks of student with this student ID already exist.");
                return; // Exiting method if marks already exist for the student
            }
        }

        int[] arrayOfMarks = validateMarks(); // Calling method to validate and get module marks for the student
        objectArrayOfMarks[registerdStudentCountWithMarks] = new Module(StudentID, arrayOfMarks[0], arrayOfMarks[1], arrayOfMarks[2]); // Storing validated marks for the student
        registerdStudentCountWithMarks++; // Incrementing count of students with recorded marks

        System.out.println("Done: Module Marks added successfully."); // Confirming successful addition of module marks
    }

    // Method to validate and return a valid student ID
    public static String getValidStudentID() {
        String studentID;

        // Loop to ensure valid student ID format is entered
        while (true) {
            // Providing instructions and format conditions for entering student ID
            System.out.println("""
                    ------------------------------------------------------------------------------------------
                    * Operation : Enter Student ID  
                    * Conditions : Student ID must start with letter capital W and contain 7 digits. (Ex: W1234567)
                    -----------------------------------------------------------------------------------------
                    """);
            System.out.print("Please Enter Student ID Here:-");

            studentID = getInput.next(); // Reading user input for student ID

            // Validating entered student ID format
            if (isIDInCorrectFormat(studentID)) {
                break; // Exiting loop if valid student ID format is entered
            } else {
                System.out.println("Invalid Student ID !. Try again with different ID"); // Prompting user to enter valid student ID
            }
        }

        return studentID; // Returning validated student ID
    }

    // Method to validate and return a valid student name
    public static String getValidStudentName() {
    String studentName; // Declaring a variable to store the student's name

    // Loop to ensure valid student name format is entered
    while (true) {
        // Providing instructions and format conditions for entering student name
        System.out.println("""
                * Operation : Enter Student Name 
                * Conditions : Name can be accept Upto 2 word . one word not valid .Name cannot contain numbers and must have space between two names
                -----------------------------------------------------------------------------------------
                """);
        System.out.println("Please Enter Student Name Here:-"); // Prompting user to enter the student's name

        studentName = getInput.nextLine().trim(); // Reading entire line for student name and trimming any leading/trailing spaces

        // Validating entered student name format
        if (isNameInCorrectFormat(studentName)) {
            break; // Exiting loop if valid student name format is entered
        } else {
            System.out.println("Invalid Student Name! Try again with a different name."); // Prompting user to enter valid student name
        }
    }

    return studentName; // Returning validated student name
}


    // Method to validate student ID format
    public static boolean isIDInCorrectFormat(String studentID) {
        // Checking if student ID length is not equal to 8 or does not start with 'W'
        if (studentID.length() != 8 || studentID.charAt(0) != 'W') {
            return false; // Returning false if conditions are not met
        }

        // Loop to check if all characters in student ID except first one are digits
        for (int charindex = 1; charindex < studentID.length(); charindex++) {
            if (!Character.isDigit(studentID.charAt(charindex))) {
                return false; // Returning false if any character is not a digit
            }
        }

        return true; // Returning true if all conditions are met
    }

    // Method to validate student name format
    public static boolean isNameInCorrectFormat(String studentName) {
        // Checking if student name is empty
        if (studentName.isEmpty()) {
            return false; // Returning false if student name is empty
        }

        // Loop to check each character in student name
        for (int letterIndex = 0; letterIndex < studentName.length(); letterIndex++) {
            char ch = studentName.charAt(letterIndex); // Extracting each character

            // Checking if character is not a letter or a space
            if (!Character.isLetter(ch) && ch != ' ') {
                return false; // Returning false if character is not a letter or a space
            }
        }

        return studentName.trim().contains(" "); // Returning true if there is at least one space between names
    }

    // Method to validate and return an array of module marks
    private static int[] validateMarks() {
        int[] arrayOfMarks = new int[3]; // Initializing array to store module marks

        // Loop to validate marks for each module
        for (int indexofmarksarray = 0; indexofmarksarray < arrayOfMarks.length; indexofmarksarray++) {
            // Loop to ensure valid marks are entered
            while (true) {
                System.out.println("Enter Marks Below"); // Prompting user to enter marks
                System.out.print("Enter Marks Of Module  " + (indexofmarksarray + 1) +":- "); // Prompting user to enter marks for current module

                try {
                    arrayOfMarks[indexofmarksarray] = Integer.parseInt(getInput.nextLine()); // Reading marks entered by user and converting to integer

                    // Checking if entered marks are between 0 and 100
                    if (arrayOfMarks[indexofmarksarray] < 0 || arrayOfMarks[indexofmarksarray] > 100) {
                        System.out.println("Error : Marks Should be between 0 and 100. Please enter valid mark "); // Prompting user to enter valid marks
                    } else {
                        break; // Exiting loop if valid marks are entered
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error : Marks Should be integer . Please enter valid mark "); // Prompting user to enter valid integer marks
                }
            }
        }

        return arrayOfMarks; // Returning validated array of module marks
    }

}

