package java2H1;

public class Student {
	String studentId, studentName, studentDOB, studentAddress, studentEmail, studentGender, classCode;

	public Student(String studentId, String studentName, String studentDOB, String studentAddress, String studentEmail,
			String studentGender, String classCode) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentDOB = studentDOB;
		this.studentAddress = studentAddress;
		this.studentEmail = studentEmail;
		this.studentGender = studentGender;
		this.classCode = classCode;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getStudentDOB() {
		return studentDOB;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public String getClassCode() {
		return classCode;
	}
}
