package java2H1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	
	Scanner input = new Scanner(System.in);
	ArrayList<Classes> classesArray = new ArrayList<Classes>();
	ArrayList<Student> StudentArray = new ArrayList<Student>();
	
	public Main() {
		int menuOption=0;
		do {
			cls();
			printMenu();
			try {
				menuOption = input.nextInt();
				input.nextLine();
			} catch (Exception e) {
			
			}
			if(menuOption==1) addNewClass();
			else if(menuOption==2) viewAllClasses();
			else if(menuOption==3) updateClass();
			else if(menuOption==4) removeClass();
			pause();
		}while(menuOption != 5);
	}
	
	private void addNewClass() {
		String classCode, inputCourseCode;
		String[] splitedCourseString = new String[2];
		do {
			System.out.print("Input Class's Code [ex: BA01]: ");
			classCode = input.nextLine();
			if(Pattern.matches("[A-Z][A-Z][0-9][0-9]", classCode)) 
				break;
		}while(true);
		do {
			System.out.print("Input Class's Course [must contain '-' ex: COMP6048 - Data Structure]: ");
			inputCourseCode = input.nextLine();
			splitedCourseString = inputCourseCode.toString().split("-", 2);
			splitedCourseString[0].toUpperCase();
			if(splitedCourseString[0].trim().length() >= 8 && inputCourseCode.contains("-")) 
				break;
		}while(true);
		
		classesArray.add(new Classes(classCode, splitedCourseString[0], splitedCourseString[1]));
		System.out.println("Successfully added a new class!");
	}

	private void viewAllClasses() {
		if(classesArray.size() <= 0) {
			System.out.println("There are still no classes!");
			return;
		}
		classPage();
	}

	private void updateClass() {
		if(classesArray.size() <= 0) {
			System.out.println("There are still no classes!");
			return;
		}
		int option=0;
		viewAllClass();
		System.out.println("*===========================UPDATE CLASS================================*");
		do {
			System.out.printf("Choose class to be updated [1-%d]: ", classesArray.size());
			try {
				option = input.nextInt();
				input.nextLine();
			} catch (Exception e) { }
		}while(option < 1 || option > classesArray.size());
		
		String classCode, inputCourseCode;
		
		do {
			System.out.print("Input Class's Code [ex: BA01]: ");
			classCode = input.nextLine();
			if(Pattern.matches("[A-Z][A-Z][0-9][0-9]", classCode)) 
				break;
		}while(true);
		String[] splitedCourseString = new String[2];
		do {
			System.out.print("Input Class's Course [must contain '-' ex: COMP6048 - Data Structure]: ");
			inputCourseCode = input.nextLine();
			splitedCourseString = inputCourseCode.split("-", 2);
			splitedCourseString[0].toUpperCase();
			if(splitedCourseString[0].trim().length() >= 8 && inputCourseCode.contains("-")) 
				break;
		}while(true);
		
		
		classesArray.get(option).setClassCode(classCode);
		classesArray.get(option).setCourseCode(splitedCourseString[0]);
		classesArray.get(option).setCourseName(splitedCourseString[1]);
		
		System.out.println("Successfully updated the class!");
	}
	
	private void removeClass() {
		if(classesArray.size() <= 0) {
			System.out.println("There are still no classes!");
			return;
		}
		int option=0;
		viewAllClass();
		System.out.println("*===========================REMOVE CLASS================================*");
		do {
			System.out.printf("Choose class to be updated [1-%d]: ", classesArray.size());
			try {
				option = input.nextInt();
				input.nextLine();
			} catch (Exception e) { }
		}while(option < 1 || option > classesArray.size());
		
		classesArray.remove(option-1);
		System.out.println("Successfully removed the class!");
	}
	
	private void classPage() {
		int menuOption=0;
		do {
			cls();
			printClassPage();
			try {
				menuOption = input.nextInt();
				input.nextLine();
			} catch (Exception e) {
			
			}
			if(menuOption==1) addNewStudent();
			else if(menuOption==2) viewAllStudents();
			else if(menuOption==3) break;
			pause();
		}while(true);
	}

	private void addNewStudent() {
		int option=0;
		do {
			System.out.printf("Choose class to be updated [1-%d]: ", classesArray.size());
			try {
				option = input.nextInt();
				input.nextLine();
			} catch (Exception e) { }
		}while(option < 1 || option > classesArray.size());
		
		String studentId, studentName, studentDOB, studentAddress, studentEmail, studentGender;
		
		do {
			System.out.print("Input Student's ID [ex: 2401856732 | must be unique]: ");
			studentId = input.nextLine();
			if(isNumeric(studentId, 0) && uniqueID(studentId)) break;
		}while(true);
		do {
			System.out.print("Input Student's Name [more than 5 characters]: ");
			studentName = input.nextLine();
		}while(studentName.length() < 5);
		do {
			System.out.print("Input Student's DOB (dd/MM/yyyy) [ex: 04/10/2002]: ");
			studentDOB = input.nextLine();
			
			if(Pattern.matches("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]", studentDOB)) {
				String[] splitedDate = studentDOB.split("/", 3);
				int day = Integer.parseInt(splitedDate[0]);
				int month = Integer.parseInt(splitedDate[1]);
				int year = Integer.parseInt(splitedDate[2]);
				
				if(isAllowed(year, month, day, studentDOB)) break;
			}	
		}while(true);
		do {
			System.out.print("Input Student's Address [7 - 30 characters]: ");
			studentAddress = input.nextLine();
		}while(studentAddress.length() < 7 || studentAddress.length() > 30);
		do {
			System.out.print("Input Student's Email: ");
			studentEmail = input.nextLine();
			if(!studentEmail.endsWith(".@deluxe.ac.id") && studentEmail.endsWith("@deluxe.ac.id") && studentEmail.chars().filter(ch -> ch == '@').count() == 1)
				break;
		}while(true);
		do {
			System.out.print("Input Student's Gender [Male | Female]: ");
			studentGender = input.nextLine();
			if(studentGender.equals("Male") || studentGender.equals("Female")) break;
		}while(true);
		
		StudentArray.add(new Student(studentId, studentName, studentDOB, studentAddress, studentEmail, studentGender, classesArray.get(option-1).getClassCode()));
		System.out.println("Successfully added a new student to class!");
	}
	
	public static boolean isNumeric(String string, double db) {
	    if (string == null) return false;
	    try {
	        db = Double.parseDouble(string);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public boolean uniqueID(String string) {
	    for (int i = 0; i < StudentArray.size(); i++) {
			if(StudentArray.get(i).getStudentId().equals(string)) return false;
		}
	    return true;
	}
	
	private boolean isAllowed(int year, int month, int day, String date) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		if(year < currentYear ) {
			if(day >= 1 && day <= 31) 
				if(month >= 1 && month <= 12) 
					if(isValidDate(date)) return true;
		}
		else if(year == currentYear) {
			if(month == currentMonth) 
				if(day <= currentDay && day > 0) 
					if(isValidDate(date)) return true;
			if(month < currentMonth && month > 0)
				if(day >= 1 && day <= 31)
					if(isValidDate(date)) return true;
		}					
		if(!isValidDate(date)) return false;
		return false;
	}
	
	private static boolean isValidDate(String input) {
        String formatString = "dd/MM/yyyy";

        SimpleDateFormat format = new SimpleDateFormat(formatString);
        format.setLenient(false);
        try {
            format.parse(input);
        } catch (ParseException e) {
        	return false;
        } catch (IllegalArgumentException e) {
        	return false;
        }
        return true;
    }
	
	private void viewAllStudents() {
		if(classesArray.size() <= 0) {
			System.out.println("There are still no classes!");
			return;
		}
		int option=0;
		do {
			cls();
			viewAllClass();
			System.out.printf("Choose class [1 - %d]: ", classesArray.size());
			try {
				option = input.nextInt();
				input.nextLine();
			} catch (Exception e) { }
		}while(option < 1 || option > classesArray.size());
		
		System.out.println("*=========================================================================================================================================================*");
		System.out.printf("  %s\n", classesArray.get(option-1).getClassCode());
		System.out.println("*=========================================================================================================================================================*");
		System.out.printf("  %s - %s\n", classesArray.get(option-1).getCourseCode(), classesArray.get(option-1).getCourseName());
		System.out.println("*=========================================================================================================================================================*");
		System.out.printf("| %-10s | %-22s | %-36s | %-32s | %-30s | %-6s |\n", "ID", "Name", "DOB", "Address", "Email", "Gender");
		System.out.println("*=========================================================================================================================================================*");
		for (Student student : StudentArray) {
			if(student.getClassCode().equals(classesArray.get(option-1).getClassCode())) {
				System.out.printf("| %-10s | %-22s | %-36s | %-32s | %-30s | %-6s |\n", student.getStudentId(), student.getStudentName(), student.getStudentDOB(), student.getStudentAddress(), student.getStudentEmail(), student.getStudentGender());
				System.out.println("*=========================================================================================================================================================*");	
			}
		}
	}
	
	private void printClassPage() {
		viewAllClass();
		System.out.println("1. Add New Student to Class");
		System.out.println("2. View All Students");
		System.out.println("3. Exit");
		System.out.print(">> ");
	}
	
	private void mainLogo() {
		System.out.println("######  ####### ##      ##    ## ##   ## #######");
		System.out.println("##   ## ##      ##      ##    ##  ## ##  ##");
		System.out.println("##   ## #####   ##      ##    ##   ###   #####");
		System.out.println("##   ## ##      ##      ##    ##  ## ##  ##");
		System.out.println("######  ####### #######  ######  ##   ## #######");
		System.out.println();
		System.out.println("##    ## ###   ## ## ##    ## ####### ######   ###### ## ######## ##    ##");
		System.out.println("##    ## ####  ## ## ##    ## ##      ##   ## ##      ##    ##     ##  ##");
		System.out.println("##    ## ## ## ## ##  ##  ##  #####   ######   #####  ##    ##      ####");
		System.out.println("##    ## ##  #### ##   ####   ##      ##   ##      ## ##    ##       ##");
		System.out.println(" ######  ##   ### ##    ##    ####### ##   ## ######  ##    ##       ##");
		System.out.println();
	}
	
	private void classLogo() {
		System.out.println(" _____ _");
		System.out.println("/  __ \\ |");
		System.out.println("| /  \\/ |");
		System.out.println("| /  \\/ | __ _ ___ ___");
		System.out.println("| |   | |/ _` / __/ __|");
		System.out.println("| \\__/\\ | (_| \\__ \\__ \\");
		System.out.println(" \\____/_|\\__,_|___/___/");
	}

	private void viewAllClass() {
		classLogo();
		System.out.println("*=======================================================================*");
		System.out.printf("| %-4s | %-10s | %-11s | %-35s |\n", "No.", "Class Code", "Course Code", "Course Name");
		System.out.println("*=======================================================================*");
		for (int i = 0; i < classesArray.size(); i++) {
			System.out.printf("| %-4d | %-10s | %-11s | %-35s |\n", i+1, classesArray.get(i).getClassCode(), classesArray.get(i).getCourseCode(), classesArray.get(i).getCourseName());
			System.out.println("*=======================================================================*");
		}
	}
	
	private void printMenu() {
		mainLogo();
		System.out.println("1. Add New Class");
		System.out.println("2. View All Classes");
		System.out.println("3. Update Class");
		System.out.println("4. Remove Class");
		System.out.println("5. Exit!");
		System.out.print(">> ");		
	}
	
	private void cls() {
		for (int i = 0; i < 45; i++) 
			System.out.println();
	}
	
	private void pause() {
		System.out.println("Click enter to continue..");
		input.nextLine();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
