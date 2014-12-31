package algo;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.TeacherDB;
import db.classSectionDB;


/**
 *  contain the information of class and section related to students and the teacher
 *   
 * @author MuhammadAbuBakar
 *
 */
public class classSection {
	
	private char section;
	/** Section contains the information about the section of the student*/
	private int clas;
	/** class contains the information about the Class of the student */
	
	/**
	 * 
	 * @return Return the Section of the student
	 */
	public char getSection() {
		return section;
	}

	/**
	 * set the section of the Student
	 * @param section set the info into the section variable of the class
	 * 
	 */
	public void setSection(char section) {
		this.section = section;
	}

	/**
	 * 
	 * @return Return the Class of the student
	 */
	public int getClas() {
		return clas;
	}

	
	/**
	 * set the Class of the Student
	 * @param clas set the info into the class variable of the class
	 * 
	 */
	public void setClas(int clas) {
		this.clas = clas;
	}


	/**
	 * Lists containing info about Class Section Subject 
	 * 
	 * @param Lid ID of the login user
	 * @return List array against Lid 
	 */
	public List[] IdsClassSectionSubject(int Lid) {
		
		// ArrayList <String>[] ls = (ArrayList<String>[])new ArrayList[2];
		// ls[0]=new ArrayList<String>();
		// ls[1]=new ArrayList<String>();
		// List qw=new List();
		TeacherDB objTeacherDB = new TeacherDB();
		ResultSet rs = objTeacherDB.getIdsClassSectionSubject(Lid);
		int subject = 0;
		int classAndSection = 0;
		int Tid = 0;
		List[] ls = new List[3];
		ls[0] = new List();
		ls[1] = new List();
		ls[2] = new List();
		try {

			while (rs.next()) {
				classAndSection = rs.getInt("CSid");
				subject = rs.getInt("Sub_id");
				Tid = rs.getInt("EReg_no");
				String temp = Integer.toString(classAndSection);
				String temp_1 = Integer.toString(subject);
				String temp_2 = Integer.toString(Tid);
				ls[0].add(temp);
				ls[1].add(temp_1);
				ls[2].add(temp_2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ls;
	}

	
	/**
	 * give ID to this function after getting the List from previous function and extracting the CSid 
	 * 
	 * @param CS ID for the to get class and section
	 * @return Class and Section
	 */
	public String showClassSection(int CS) {
		classSectionDB objClassSectionDB = new classSectionDB();
		ResultSet rsClassSection = objClassSectionDB.getClassSection(CS);
		String section;
		String subject1;

		int clas;
		String temp = null;
		try {
			while (rsClassSection.next()) {
				section = rsClassSection.getString("section");
				clas = rsClassSection.getInt("clas");

				temp = clas + "-" + section;
				// System.out.println(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;

	}

/**
 * get class and section against the csid  for which student is enrolled
 * 
 * @param csid  ID for the to get class and section
 * @return ResultSet back 
 */
	public ResultSet getClassSection(int csid) {
		ResultSet rs;
		classSectionDB csDb = new classSectionDB();
		rs = csDb.getClassSection(csid);
		return rs;
	}
	
	/**
	 * get ID against clas and section
	 * 
	 * @param clas
	 * @param sec
	 * @return 
	 */
	
	public int getCSid(int clas, char sec) {
		classSectionDB objClassSectionDB = new classSectionDB();
		ResultSet rs = objClassSectionDB.getCSidDB(clas, sec);
		int id = 0;
		try {
			while (rs.next()) {
				id = rs.getInt("CSid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * List of the subjects id for the Teacher login ID and the Class and Section id
	 * 
	 * @param CSid contain the Class and Section id
	 * @param Lid Login ID of the Teacher
	 * @return Returns the subject list 
	 */
	public List getSUBid(int CSid,int Lid ) {
		classSectionDB objClassSectionDB = new classSectionDB();
		ResultSet rs = objClassSectionDB.getSUBidDB(CSid,Lid);
		List idList = new List();
		int id=0;
		try {
			while (rs.next()) {
				id = rs.getInt("Sub_id");
				idList.add(Integer.toString(id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idList;
	}
	
}
