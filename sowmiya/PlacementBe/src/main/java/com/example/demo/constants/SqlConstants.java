package com.example.demo.constants;

public class SqlConstants {

	public final static String LOGIN_SQL = "select * from register where register.reg_no=? and register.password=? and isactive=?";
	public final static String SET_RECENT_LOGIN = "update register set updated_at=? where reg_no=?";
	public final static String CREATE_LOGIN = "insert into register (password,email,role,isactive,reg_no) values(?,?,?,?,?)";
	public static final String STUDENT = "insert into student (student_id,student_name,isplaced,department,mark_x,mark_xii,cgpa,arrear_count) values(?,?,?,?,?,?,?,?)";
	public static final String COMPANY = "insert into company_details (company_name,company_description,company_type,job_location,designation,payment,day_of_recruitment,last_date,cgpa,mark_x,mark_xii,arrear_count)values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GETSTUDENTDETAIL = "select * from student";
	public static final String GETSPECIFICSTUDENT = "select * from student where student_id=?";
	public static final String GET_RECENT_COMPANY = "select max(company_id) from company_details";
	public static final String ELIGIBLITY = "insert into eligiblity(company_id,percent10,percent12,cgpa,arrear_count)values(?,?,?,?,?)";
	public static final String GETMARKDETAIL = "select * from Marks where student_id=?";
	public static final String GETPERSONALDETAIL = "select * from personal_info where student_id=?";
	public static final String GETPROJECT = "select * from project where student_id=?";
	public static final String GETACHIEVEMENT = "select * from achievement where student_id=?";
	public static final String GETCOURSES = "select * from course where student_id=?";
	public static final String GETCOMPANYDETAIL = "select * from company_details";
	public static final String GETLASTDATE = "select last_date from company_details where company_id=?";
	public static final String GETELIGIBLITYDETAIL = "select * from eligiblity where company_id=?";
	public static final String CHECKAPPLIED = "select * from student_log where company_id=? and student_id=?";
	public static final String APPLICATION = "insert into student_log (student_id,company_id,date_of_registration) values(?,?,?)";
	public static final String PLACED_DETAIL = "insert into placed_detail (student_id,company_id,job_status)values(?,?,?)";
	public static final String UPDATE_PLACED = "update student set isPlaced=1 where student_id=?";
	public static final String EDIT_STUDENT = "update student set student_name=?,department=?, mark_x=?,mark_xii=?,cgpa=?,arrear_count=? where student_id=?";
	public static final String EDIT_COMPANY = "update company_details set company_name=?,company_type=?,company_description=?,job_location=?,designation=?,payment=?,day_of_recruitment=?, cgpa=?,mark_xii=?,mark_x=?,arrear_count=?,last_date=? where company_id=?";
	public static final String GETELIGIBLITYLISt = "select * from student_log join student where student.student_id=student_log.student_id and company_id=?";
	public static final String ADDACHIEVEMENT = "insert into achievement(student_id,title,description)values(?,?,?)";
	public static final String ADDPROJECT = "insert into project(student_id,project_title,description) values(?,?,?)";
	public static final String ADDCERTIFICATION = "insert into course(student_id,course_title,description)values(?,?,?)";
	public static final String UPDATEACHIEVEMENT = "update achievement set title=?,description=? where achievement_id=?";
	public static final String UPDATEPROJECT = "update project set project_title=?,description=? where project_id=?";
	public static final String UPDATECERTIFICATION = "update course set course_title=?,description=? where course_id=?";
	public static final String GETPROFILE = "select * from project join acheivement on acheivement.student_id=project.student_id join certificatiobs on certifications.student_id=project.student_id where student_id=?";
	public static final String GETCERTIFICATION = "select * from certifications where student_id=?";
	public static final String PERSONAL_INFO = "insert into personal_info(student_id,date_of_birth,blood_group,phone_number,alternate_phone,email,alternate_email,location,gender)values(?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_PERSONAL_INFO = "update personal_info set date_of_birth=?,blood_group=?,phone_number=?,alternate_phone=?,email=?,alternate_email=?,location=?,gender=? where student_id=?";
	public static final String GETSPECIFICACHIEVEMENT = "select * from achievement where achievement_id=?";
	public static final String GETSPECIFICPROJECT = "select * from project where project_id=?";
	public static final String GETSPECIFICCOURSES = "select * from course where course_id=?";
	public static final String RECENT_LOGIN = "update register set recent_login=? where reg_no=?";
	public static final String CHECK_USER = "select * from register where user_name=?";
	public static final String CHECK_EMAIL = "select * from register where email=?";
	public static final String CHECK_REGISTER_NO = "select * from register where reg_no=?";
	public static final String GET_STUDENT_NAME = "select * from student where student_id=?";
	public static final String GETSPECIFICCOMPANY = "select * from company_details where company_id=?";
	public static final String DELETESTUDENTDETAIL = "delete from student where student_id=?";
	public static final String DELETECOMPANYDETAIL = "delete from company_details where company_id=?";
	public static final String DELETECOURSE = "delete from course where course_id=?";
	public static final String DELETEACHIEVEMENT = "delete from achievement where achievement_id=?";
	public static final String DELETEPROJECT = "delete from project where project_id=?";
	public static final String GETREGISTRATIONLOG = "select * from student_log join company_details on student_log.company_id=company_details.company_id where student_id=? order by company_details.day_of_recruitment desc";
	public static final String GETVISITEDCOMPANY = "select * from company_details where date_of_recruitment<?";
	public static final String GETCOUNTOFSTUDENTSPLACED ="select count(*) as count from placed_detail group by company_id having company_id=?";
	public static final String GETDEPARTMENTCOUNT ="select count(*),department from placed_detail join student on student.student_id=placed_detail.student_id group by department,company_id having placed_detail.company_id=?";
	public static final String GETSTUDENTPLACEDINACOMPANYDETAIL ="select * from student join placed_detail on student.student_id=placed_detail.student_id where company_id=?";

}
