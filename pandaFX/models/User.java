package pandaFX.models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interface User
 */
public interface User {

    String getName();

    String getDoB();

    String getDiscipline();

    String getCampus();

    String getContact();

    String getGender();

    double getSalary();

    String getEmail();

    ArrayList<Semester> getTeacherSemester();

    public void loadUser(String email, String pass);

    boolean setDates(String SemesterTitle, LocalDate openDate, LocalDate closeDate, LocalDate withDrawDate);

    boolean addAttendance(String std, String status, String date, String semester, String course, String section);

    boolean createEvaluation(String title, Integer tmarks, Float weight,String std, String semester, String crs, String sec);

    boolean updateEvaluation(String title, Integer obmarks, String std, String semester, String crs, String sec);

    boolean updateGrade(String semester, String crs, String sec, String std, String g);
}
