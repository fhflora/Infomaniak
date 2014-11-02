package org.whu.info.campus;

import java.util.ArrayList;
import java.util.List;

import org.whu.info.GUI.Main;
import org.whu.info.dbutil.DaoUtil;
import org.whu.info.exception.FullCampusException;
import org.whu.info.student.Student;


public class ManageCampus {
	public static List<Campus> getAllCampus(){
		List<Campus> Campus = new ArrayList<Campus>();
		Campus = DaoUtil.getCampusDao().getAllCampus();
		return Campus;
	}
	/**
	 * 获取校区
	 */
	public Campus getCampus(String ville, String region) {
		Campus campus = new Campus();
		campus = DaoUtil.getCampusDao().getCampus(ville, region);
		return campus;
	}
	/**
	 * 增加校区
	 */
	public boolean addCampus(Campus c) {
		boolean res = false;
		boolean checkCampus=DaoUtil.getCampusDao().checkCampus(c);
		if (!checkCampus) {
			DaoUtil.getCampusDao().addCampus(c);
			res = true;
		}
		return res;
	}


}
