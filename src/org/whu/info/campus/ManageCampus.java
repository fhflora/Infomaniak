package org.whu.info.campus;

import java.util.ArrayList;
import java.util.List;

import org.whu.info.dbutil.DaoUtil;


public class ManageCampus {
	public List<Campus> getAllCampus(){
		List<Campus> Campus = new ArrayList<Campus>();
		Campus = DaoUtil.getCampusDao().getAllCampus();
		return Campus;
	}
}