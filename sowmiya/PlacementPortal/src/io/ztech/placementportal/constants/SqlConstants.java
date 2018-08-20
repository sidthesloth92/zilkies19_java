package io.ztech.placementportal.constants;

public class SqlConstants {

	public final static String LOGIN_SQL = "select * from register where reg_no=? and password=? and isactive=?";
	public final static String SET_RECENT_LOGIN = "update register set updated_at=? where reg_no=?";
	public final static String CREATE_LOGIN = "insert into register (password,email,role,isactive,reg_no) values(?,?,?,?,?)";
	public static final String STUDENT = "insert into student (student_id,student_name,isplaced,department) values(?,?,?,?)";
	public static final String ACADEMIC_DETAILS = "insert into Marks(student_id,mark_x,mark_xii,cgpa,arrear_count) values(?,?,?,?,?)";
	public static final String COMPANY = "insert into company_details (company_name,company_description,company_type,job_location,designation,payment,day_of_recruitment,last_date)values(?,?,?,?,?,?,?,?)";
	public static final String GETSTUDENTDETAIL = "select * from student where student_id=?";
	public static final String GET_RECENT_COMPANY = "select max(company_id) from company_details";
	public static final String ELIGIBLITY = "insert into eligiblity(company_id,percent10,percent12,cgpa,arrear_count)values(?,?,?,?,?)";
	public static final String GETMARKDETAIL = "select * from Marks where student_id=?";
	public static final String GETPERSONALDETAIL = "select * from personal_info where student_id=?";
	public static final String GETPROJECT = "select * from project where student_id=?";
	public static final String GETACHIEVEMENT = "select * from achievement where student_id=?";
	public static final String GETCOURSES = "select * from certifications where student_id=?";
	public static final String GETCOMPANYDETAIL = "select * from company_details";
	public static final String GETLASTDATE = "select last_date from company_details where company_id=?";
	public static final String GETELIGIBLITYDETAIL = "select * from eligiblity where company_id=?";
	public static final String CHECKAPPLIED = "select * from student_log where company_id=? and student_id=?";
	public static final String APPLICATION = "insert into student_log (student_id,company_id,date_of_registration) values(?,?,?)";
	public static final String PLACED_DETAIL = "insert into placed_detail (student_id,company_id,job_status)values(?,?,?)";
	public static final String UPDATE_PLACED = "update student set isPlaced=1 where student_id=?";
	public static final String EDITED_MARK_DETAIL = "update Marks set mark_x=?,mark_xii=?,cgpa=?,arrear_count=? where student_id=?";
	public static final String EDITED_DETAIL = "update student set student_name=?,department=? where student_id=?";
	public static final String EDIT_COMPANY = "update company_details set company_name=?,company_type=?,company_description=?,job_location=?,designation=?,payment=?,day_of_recruitment=? where company_id=?";
	public static final String GETELIGIBLITYLISt = "select * from student_log join student where student.student_id=student_log.student_id and company_id=?";
	public static final String ADDACHIEVEMENT = "insert into achievement(student_id,title,description)values(?,?,?)";
	public static final String ADDPROJECT = "insert into project(student_id,project_title,description) values(?,?,?)";
	public static final String ADDCERTIFICATION = "insert into certifications(student_id,course_title,certified_institution)values(?,?,?)";
	public static final String UPDATEACHIEVEMENT = "update achievement set title=?,description=? where acheivement_id=?";
	public static final String UPDATEPROJECT = "update project set project_title=?,description=? where project_id=?";
	public static final String UPDATECERTIFICATION = "update certifications set course_title=?,certified_institution=? where course_id=?";
	public static final String GETPROFILE = "select * from project join acheivement on acheivement.student_id=project.student_id join certificatiobs on certifications.student_id=project.student_id where student_id=?";
	public static final String GETCERTIFICATION = "select * from certifications where student_id=?";
	public static final String PERSONAL_INFO = "insert into personal_info(student_id,date_of_birth,blood_group,phone_number,alternate_phone,email,alternate_email,location,gender)values(?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_PERSONAL_INFO = "update personal_info set date_of_birth=?,blood_group=?,phone_number=?,alternate_phone=?,email=?,alternate_email=?,location=?,gender=? where student_id=?";
	public static final String GETSPECIFICACHIEVEMENT = "select * from achievement where acheivement_id=?";
	public static final String GETSPECIFICPROJECT = "select * from project where project_id=?";
	public static final String GETSPECIFICCOURSES = "select * from certifications where course_id=?";
	public static final String RECENT_LOGIN = "update register set recent_login=? where reg_no=?";
	public static final String CHECK_USER = "select * from register where user_name=?";
	public static final String CHECK_EMAIL = "select * from register where email=?";
	public static final String CHECK_REGISTER_NO = "select * from register where reg_no=?";
	public static final String GETSPECIFICCOMPANY = "select * from company_details where company_id=?";

}
