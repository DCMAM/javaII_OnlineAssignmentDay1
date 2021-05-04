package java2H1;

public class Classes {
	String classCode, courseCode, courseName;

	public Classes(String classCode, String courseCode, String courseName) {
		super();
		this.classCode = classCode;
		this.courseCode = courseCode;
		this.courseName = courseName;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
