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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.whu.info.campus.Campus;
import org.whu.info.campus.ManageCampus;
import org.whu.info.dbutil.DaoUtil;
import org.whu.info.student.Student;
import org.whu.info.teacher.Teacher;

public class Main extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	/**
	 * 界面分左中右
	 */
	private JPanel panelLeft;
	private JPanel panelRight;
	private JPanel panelMiddle;
	
	JScrollPane paneStudent;
	JScrollPane paneTeacher;
	JScrollPane paneCampus;
	
	JTable studentList;
	JTable campusList;
	JTable teacherList;
	
	JButton btnAddLeft;
	JButton btnBackupLeft;
	JPanel panelMenuLeft;
	Campus campus=ManageCampus.getAllCampus().get(0);
	 
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
				campus=DaoUtil.getManageCampus().getCampus(ville, region);
				studentList.setModel(updateStudentList(campus));
				teacherList.setModel(updateTeacherList(campus));
				System.out.println(ville+region);
			}
		});
		
		Border border=BorderFactory.createEtchedBorder(Color.black, Color.BLUE);
		
		this.panelMiddle=new JPanel();
		studentList=new JTable(this.updateStudentList(campus));
		this.paneStudent=new JScrollPane(studentList);
		this.paneStudent.setBackground(Color.LIGHT_GRAY);
		this.paneStudent.setBounds(100, 50, 330, 610);
		this.paneStudent.setBorder(BorderFactory.createTitledBorder(border,"Student" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
				
		this.panelRight=new JPanel();	
		teacherList=new JTable(this.updateStudentList(campus));
		this.paneTeacher=new JScrollPane(teacherList);
		this.paneTeacher.setBackground(Color.LIGHT_GRAY);
		this.paneTeacher.setBounds(100, 50, 330, 610);
		this.paneTeacher.setBorder(BorderFactory.createTitledBorder(border,"Teacher" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
		 btnAddLeft=new JButton("Add");
		 btnBackupLeft=new JButton("Backup");
		 
		 this.btnAddLeft.addActionListener(this);
		 this.btnBackupLeft.addActionListener(this);
		 panelMenuLeft=new JPanel();
		panelMenuLeft.add(btnAddLeft);
		panelMenuLeft.add(btnBackupLeft);
		this.panelLeft.setLayout(new BorderLayout());
		this.panelLeft.add(this.panelMenuLeft,BorderLayout.NORTH);
		this.panelLeft.add(this.paneCampus,BorderLayout.CENTER);
		this.panelLeft.setBackground(Color.lightGray);
		this.panelLeft.setBounds(0, 50, 330, 610);

		this.panelLeft.setBorder(BorderFactory.createTitledBorder(border,"Campus" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
		
		
		
		this.panelMiddle.setLayout(new BorderLayout());
		this.panelMiddle.add(this.paneStudent);
		this.panelRight.setLayout(new BorderLayout());
		this.panelRight.add(this.paneTeacher);
		
		
	
		this.frame.setBounds(150,50,1000,650);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new GridLayout(1, 3));
		this.frame.add(this.panelLeft);
		this.frame.add(this.panelMiddle);
		this.frame.add(this.panelRight);
		this.frame.setResizable(true);
		this.frame.setVisible(true);
	}
	public static void main(String args[]) {
			new Main();
	}
	
	public TableModel updateCampusList(){
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
	
	public TableModel updateStudentList(Campus campusSelected ){
		List<Student> studentList=DaoUtil.getCampus().getStudents(campusSelected);
		String[][] student=new String[studentList.size()][3];
		for(int i=0;i<studentList.size();i++){
			student[i][0]=studentList.get(i).getID()+"";
			student[i][1]=studentList.get(i).getPrenom();
			student[i][2]=studentList.get(i).getNom();
			
		}
		String[] title={"ID","prenom","nom"};
		DefaultTableModel model=new DefaultTableModel(student,title);
		System.out.println("---studentList: "+studentList.size()+"---");
		return model;
		
	}

	public TableModel updateTeacherList(Campus campusSelected){
		List<Teacher> teacherList=DaoUtil.getCampus().getTeachers(campusSelected);
		String[][] teacher=new String[teacherList.size()][3];
		for(int i=0;i<teacherList.size();i++){
			teacher[i][0]=teacherList.get(i).getID()+"";
			teacher[i][1]=teacherList.get(i).getPrenom();
			teacher[i][2]=teacherList.get(i).getNom();
			
		}
		String[] title={"ID","prenom","nom"};
		DefaultTableModel model=new DefaultTableModel(teacher,title);
		System.out.println("---teacherList: "+teacherList.size()+"---");
		return model;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		if(source==this.btnAddLeft){
			
		}
	}
}
