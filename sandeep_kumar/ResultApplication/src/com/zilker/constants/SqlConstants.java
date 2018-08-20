package com.zilker.constants;

public class SqlConstants {
	public static final String GET_RESULTS_BY_ID_NOT_APPLIED = "SELECT * FROM result_details where student_registration_number=? and written_in=? and grade <> 'S' and result_id NOT IN (SELECT result_id FROM revaluation_details)";
	public static final String GET_RESULTS_BY_ID = "SELECT * FROM result_details where student_registration_number=? and written_in=?";
	public static final String GET_REVAL_DETAILS_BY_STUDENT_ID = "SELECT DISTINCT revaluation_details.revaluation_id,result_details.subject_code,revaluation_details.revaluation_status,revaluation_details.updated_grade FROM revaluation_details,result_details,student_details where result_details.student_registration_number=? and result_details.result_id=revaluation_details.result_id and written_in=?";
	public static final String GET_REVAL_DETAILS_BY_SUBJECT = "SELECT distinct revaluation_details.revaluation_id,result_details.subject_code,revaluation_details.revaluation_status,revaluation_details.updated_grade FROM revaluation_details,result_details,student_details,faculty_details,faculty_subject_details WHERE revaluation_details.result_id = result_details.result_id AND student_details.student_registration_number = result_details.student_registration_number AND student_details.college_code = ? AND revaluation_details.revaluation_status='applied' AND result_details.subject_code IN (SELECT faculty_subject_details.subject_code FROM faculty_subject_details WHERE faculty_registration_number =?)";
	public static final String GET_ALL_APPROVED_REVAL_REQUEST = "SELECT distinct revaluation_details.revaluation_id,result_details.subject_code,revaluation_details.revaluation_status,revaluation_details.updated_grade FROM revaluation_details,result_details WHERE revaluation_status='approved' and revaluation_details.result_id = result_details.result_id";
	public static final String GET_RESULT_BY_DEPT = "select * from result_details,student_details where result_details.student_registration_number=student_details.student_registration_number and student_details.college_code=? and student_details.department=? and result_details.written_in=?";
	public static final String INSERT_STUDENT = "INSERT INTO student_details values(?,?,?,?,?)";
	public static final String INSERT_FACULTY = "INSERT INTO faculty_details values(?,?,?,?)";
	public static final String INSERT_COLLEGE = "INSERT INTO college_details values(?,?)";
	public static final String REGISTER_STUDENT = "INSERT INTO login_details (registration_number, user_password, role) values(?,?,?)";
	public static final String REGISTER_FACULTY = "INSERT INTO login_details (registration_number, user_password, role) values(?,?,?)";
	public static final String LOGIN = "select * from login_details where registration_number=? and user_password=?";
	public static final String SELECT_STUDENT = "select * from student_details where student_registration_number=?";
	public static final String SELECT_FACULTY = "select * from faculty_details where faculty_registration_number=?";
	public static final String INSERT_RESULT = "insert into result_details (student_registration_number, subject_code, grade, written_in) values(?,?,?,?)";
	public static final String APPLY_REVAL = "insert into revaluation_details (result_id, revaluation_status) values(?,?)";
	public static final String REVAL_COUNT = "select count(*) from revaluation_details,result_details where revaluation_details.result_id=result_details.result_id and result_details.student_registration_number=? and result_details.written_in=?";
	public static final String UPDATE_STATUS_BY_FACULTY = "update revaluation_details set revaluation_status=? where revaluation_id=?";
	public static final String UPDATE_GRADE = "update revaluation_details set updated_grade=?,revaluation_status=? where revaluation_id=?";
	public static final String CURRENT_GRADE = "SELECT result_details.grade FROM result_details,revaluation_details WHERE result_details.result_id=revaluation_details.result_id and revaluation_details.revaluation_id=?";
	public static final String INSERT_SUBJECT = "INSERT INTO subject_details values(?,?)";
	public static final String DELETE_STUDENT = "DELETE FROM student_details where student_registration_number=?";
	public static final String DELETE_FACULTY = "DELETE FROM faculty_details where faculty_registration_number=?";
	public static final String DELETE_SUBJECT = "DELETE FROM subject_details where subject_code=?";
	public static final String DELETE_COLLEGE = "DELETE FROM college_details where college_code=?";
	public static final String INSERT_FACULTY_SUBJECT = "INSERT into faculty_subject_details values (default,?,?)";
	public static final String DELETE_FACULTY_SUBJECT = "DELETE FROM faculty_subject_details where faculty_subject_id=?";
	public static final String GET_SUBJECTS_NOT_ENROLLED = "SELECT distinct subject_code,subject_name FROM subject_details WHERE subject_code NOT IN (SELECT subject_code FROM faculty_subject_details WHERE faculty_registration_number = ?)";
	public static final String GET_SUBJECTS_ENROLLED = "SELECT distinct faculty_subject_details.faculty_subject_id,subject_details.subject_code,subject_details.subject_name from faculty_subject_details,subject_details where faculty_subject_details.subject_code=subject_details.subject_code and faculty_registration_number=?";
	public static final String GET_ALL_STUDENT_DETAILS="SELECT * FROM student_details";
	public static final String GET_ALL_FACULTY_DETAILS="SELECT * FROM faculty_details";
	public static final String GET_ALL_SUBJECT_DETAILS="SELECT * FROM subject_details";
	public static final String GET_ALL_COLLEGE_DETAILS="SELECT * FROM college_details";
}
