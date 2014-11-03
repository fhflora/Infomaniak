package org.whu.info.campus;

import java.util.ArrayList;
import java.util.List;
import org.whu.info.dbutil.DaoUtil;


public class ManageCampus {
	public static List<Campus> getAllCampus(){
		List<Campus> Campus = new ArrayList<Campus>();
		Campus = DaoUtil.getCampusDao().getAllCampus();
		return Campus;
	}
	/**
	 * get a campus
	 */
	public Campus getCampus(String ville, String region) {
		Campus campus = new Campus();
		campus = DaoUtil.getCampusDao().getCampus(ville, region);
		return campus;
	}
	/**
	 * add a campus
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
