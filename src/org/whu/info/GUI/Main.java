package org.whu.info.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.whu.info.campus.Campus;
import org.whu.info.campus.ManageCampus;

public class Main extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	/**
	 * 界面分两边 左边是学校列表
	 * 右边 又分两个部分，是学生列表和教师列表
	 */
	private JPanel panelLeft;
	private JPanel panelRight;
	
	JScrollPane paneStudent;
	JScrollPane paneTeacher;
	JScrollPane paneCampus;
	
	JTable studentList;
	JTable campusList;
	JTable teacherList;
	
	Campus campus;
	public Main(){
		this.frame=new JFrame();
		
		this.panelLeft = new JPanel();
		campusList=new JTable(this.updateCampusList());
		this.paneCampus=new JScrollPane(campusList);
		this.campusList.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				int selectedRow=campusList.getSelectedRow();
				String ville=(String) campusList.getValueAt(selectedRow, 0);
				String region=(String) campusList.getValueAt(selectedRow, 1);
				System.out.println(ville+region);
			}
		});
		this.paneCampus.setBackground(Color.lightGray);
		this.paneCampus.setBounds(0, 50, 330, 610);
		Border border=BorderFactory.createEtchedBorder(Color.black, Color.BLUE);
		this.paneCampus.setBorder(BorderFactory.createTitledBorder(border,"Campus" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
				
		this.panelRight=new JPanel();
		this.paneStudent=new JScrollPane();
		this.paneStudent.setBackground(Color.LIGHT_GRAY);
		this.paneStudent.setBounds(100, 50, 330, 610);
		this.paneStudent.setBorder(BorderFactory.createTitledBorder(border,"Student" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
		this.paneTeacher=new JScrollPane();
		this.paneTeacher.setBackground(Color.LIGHT_GRAY);
		this.paneTeacher.setBounds(100, 50, 330, 610);
		this.paneTeacher.setBorder(BorderFactory.createTitledBorder(border,"Teacher" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
		
		this.panelLeft.setLayout(new BorderLayout());
		this.panelLeft.add(this.paneCampus,BorderLayout.CENTER);
		this.panelRight.setLayout(new GridLayout(1,1));
		this.panelRight.add(this.paneStudent);
		this.panelRight.add(this.paneTeacher);
		this.frame.setBounds(150,50,1000,650);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new GridLayout(1, 1));
		this.frame.add(this.panelLeft);
		this.frame.add(this.panelRight);
		this.frame.setResizable(true);
		this.frame.setVisible(true);
	}
	public static void main(String args[]) {
			new Main();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public DefaultTableModel updateCampusList(){
		List<Campus> campusList=ManageCampus.getAllCampus();
		String[][] campus=new String[campusList.size()][2];
		for(int i=0;i<campusList.size();i++){
			campus[i][0]=campusList.get(i).getVille();
			campus[i][1]=campusList.get(i).getRegion();
		}
		String[] title={"Ville","Region"};
		DefaultTableModel model=new DefaultTableModel(campus,title);
		return model;
		
	}
	
	public DefaultTableModel updateStudentList(Campus campusSelected){
		return null;
		
	}

	public DefaultTableModel updateTeacherList(Campus campusSelected){
		return null;
		
	}
}
