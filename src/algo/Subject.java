package algo;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.subjectDB;


/**
 * 
 * Contain the information of subject taught by the teacher and studied by the student
 * @author MuhammadAbuBakar
 *
 */
public class Subject {

	private String Name;
/** Name of the subject*/
	
	/**
	 * 
	 * @return name of the subject
	 */
	public String getName() {
		return Name;
	}

	/**
	 * 
	 * @param name subject name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * 
	 * @param Sname name of the subject
	 * @return id of the subject
	 */
	public int SubID(String Sname) {
		subjectDB objSubjectDB = new subjectDB();
		ResultSet rs = objSubjectDB.getID(Sname);
		int ID = 0;
		try {
			while (rs.next()) {
				ID = rs.getInt("Sub_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ID;
	}

	
	/**
	 * 
	 * 
	 * @param reg ID of the subject
	 * @return the subject name against the ID
	 */
	public String subject(int reg) {
		subjectDB objSubjectDB = new subjectDB();
		ResultSet rsSubject = objSubjectDB.getSubject(reg);
		String subject1 = null;

		try {
			while (rsSubject.next()) {
				subject1 = rsSubject.getString("SubName");
				// System.out.println(subject1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subject1;
	}

}
