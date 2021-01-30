package pandaFX.models;
import pandaFX.controller.*;
import java.util.ArrayList;

public class GUI {

    private static GUI instance;

    private ArrayList<Semester> allSemesterData;
    private User person;


    private GUI() {

    }

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI();
        }
        return instance;
    }

    public GUI(ArrayList<Semester> allSemesterData) {
        this.allSemesterData = allSemesterData;
    }

    public ArrayList<Semester> getAllSemesterData() {
        return allSemesterData;
    }

    public void setAllSemesterData(ArrayList<Semester> allSemesterData) {
        this.allSemesterData = allSemesterData;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    //Class Methods:
    public String userType(String e) {
        if (e.matches(".*\\d.*")) {
            return "Student";
        } else if (e.equals("aftab.khadim@nu.pk")) {
            return "AC_Officer";
        } else {
            return "Teacher";
        }
    }

    public boolean findInDatabase(String email, String password) {
        if (userType(email).equals("Student")) {
            person = new Student();
        } else if (userType(email).equals("Teacher")) {
            person = new Teacher();
        } else {
            person = new AC_Officer();
        }

        person.loadUser(email, password);
        return person.getName() != null;
    }

    public void loadFromDatabase() {
        Semester_DataBase sd = new Semester_DataBase();
        this.allSemesterData = sd.querySemester();

        for (Semester semester : this.allSemesterData) {

            Course_DataBase cd = new Course_DataBase();
            semester.setCourseArray(cd.queryCourse(semester));

            for (Course course : semester.getCourseArray()) {
                Sections_DataBase sc = new Sections_DataBase();
                course.setSecArray(sc.querySection(course));

                for (Sections sec : course.getSecArray()) {
                    CourseRegisteration_DataBase crd = new CourseRegisteration_DataBase();
                    sec.setCR(crd.queryCourseReg(sec));

                    for (CourseRegistration courseregistration : sec.getCR()) {
                        Evaluation_DataBase edb = new Evaluation_DataBase();
                        Attendence_DataBase adb = new Attendence_DataBase();

                        courseregistration.setCourseEval(edb.queryEvaluations(courseregistration));
                        courseregistration.setCourseAtt(adb.queryAttendance(courseregistration));
                    }
                }
            }
        }
    }

    public ArrayList<CourseRegistration> fetchAllRegistrations(String sem, String crs, String sec) {
        for (Semester s : allSemesterData) {
            if (s.getTitle().equals(sem)) {
                return s.fetchAllRegistrations(crs, sec);
            }
        }
        return null;
    }

    public boolean createEvaluation(String title, Integer tmarks, Float weight, String std, String semester, String crs, String sec) {
        for (Semester s : allSemesterData) {
            if (s.getTitle().equals(semester)) {
                return s.createEvaluation(title, tmarks, weight, std, semester, crs, sec);
            }
        }
        return false;
    }

    public boolean updateEvaluation(String title, Integer obmarks, String std, String semester, String crs, String sec) {
        for (Semester s : allSemesterData) {
            if (s.getTitle().equals(semester)) {
                return s.updateEvaluation(title, obmarks, std, semester, crs, sec);
            }
        }
        return false;
    }

    public boolean updateGrade(String semester, String crs, String sec, String std, String g) {
        for (Semester s : allSemesterData) {
            if (s.getTitle().equals(semester)) {
                return s.updateGrade(semester,crs,sec,std,g);
            }
        }
        return false;
    }
}
