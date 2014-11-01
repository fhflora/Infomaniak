package org.whu.info.campus;

import java.util.ArrayList;
import java.util.List;

import org.whu.info.GUI.Main;
import org.whu.info.dbutil.DaoUtil;


public class ManageCampus {
	public static List<Campus> getAllCampus(){
		List<Campus> Campus = new ArrayList<Campus>();
		Campus = DaoUtil.getCampusDao().getAllCampus();
		return Campus;
	}
	/**
	 * 获取校区
	 * 
	 * @param ville
	 * @param region
	 * @return
	 */
	public Campus getCampus(String ville, String region) {
		Campus campus = new Campus();
		campus = DaoUtil.getCampusDao().getCampus(ville, region);
		return campus;
	}

}
