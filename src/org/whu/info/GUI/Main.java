package org.whu.info.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import org.whu.info.teacher.ExternalTeacher;
import org.whu.info.teacher.InternalTeacher;
import org.whu.info.teacher.Teacher;

public class Main extends JFrame implements ActionListener {

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

	JButton btnAddMiddle;
	JButton btnDelMiddle;
	JButton btnBackupMiddle;
	JPanel panelMenuMiddle;

	JButton btnAddRight;
	JButton btnDelRight;
	JButton btnModRight;
	JButton btnBackupRight;
	JPanel panelMenuRight;

	UICampus addCampus;
	UIStudent addStudent;
	UITeacher addTeacher;
	UIBackup backup;
	Campus campus = ManageCampus.getAllCampus().get(0);

	public Main() {
		btnAddLeft = new JButton("Add");
		btnBackupLeft = new JButton("Backup");
		btnAddMiddle = new JButton("Add");
		btnDelMiddle = new JButton("Delete");
		btnBackupMiddle = new JButton("Backup");
		btnAddRight = new JButton("Add");
		btnModRight = new JButton("Modify");
		btnDelRight = new JButton("Delete");
		btnBackupRight = new JButton("Backup");

		this.btnAddLeft.addActionListener(this);
		this.btnBackupLeft.addActionListener(this);
		this.btnAddMiddle.addActionListener(this);
		this.btnDelMiddle.addActionListener(this);
		this.btnBackupMiddle.addActionListener(this);
		this.btnAddRight.addActionListener(this);
		this.btnModRight.addActionListener(this);
		this.btnDelRight.addActionListener(this);
		this.btnBackupRight.addActionListener(this);

		panelMenuLeft = new JPanel();
		panelMenuLeft.add(btnAddLeft);
		panelMenuLeft.add(btnBackupLeft);
		panelMenuMiddle = new JPanel();
		panelMenuMiddle.add(btnAddMiddle);
		panelMenuMiddle.add(btnDelMiddle);
		panelMenuMiddle.add(btnBackupMiddle);
		panelMenuRight = new JPanel();
		panelMenuRight.add(btnAddRight);
		panelMenuRight.add(btnDelRight);
		panelMenuRight.add(btnModRight);
		panelMenuRight.add(btnBackupRight);

		this.frame = new JFrame();
		this.panelLeft = new JPanel();
		campusList = new JTable(this.updateCampusList());
		this.paneCampus = new JScrollPane(campusList);
		this.campusList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = campusList.getSelectedRow();
				String ville = (String) campusList.getValueAt(selectedRow, 0);
				String region = (String) campusList.getValueAt(selectedRow, 1);
				campus = DaoUtil.getManageCampus().getCampus(ville, region);
				studentList.setModel(updateStudentList(campus));
				teacherList.setModel(updateTeacherList(campus));
				System.out.println(ville + region);
			}
		});

		this.panelMiddle = new JPanel();
		studentList = new JTable(this.updateStudentList(campus));
		this.paneStudent = new JScrollPane(studentList);

		this.panelRight = new JPanel();
		teacherList = new JTable(this.updateStudentList(campus));
		this.paneTeacher = new JScrollPane(teacherList);

		this.panelLeft.setLayout(new BorderLayout());
		this.panelLeft.add(this.panelMenuLeft, BorderLayout.NORTH);
		this.panelLeft.add(this.paneCampus, BorderLayout.CENTER);
		Border border = BorderFactory.createEtchedBorder(Color.black,
				Color.BLUE);
		this.panelLeft.setBackground(Color.lightGray);
		this.panelLeft.setBounds(0, 50, 330, 610);
		this.panelLeft.setBorder(BorderFactory.createTitledBorder(border,
				"Campus", TitledBorder.LEFT, TitledBorder.TOP, new Font(
						" Courier New", Font.ITALIC, 36), Color.black));

		this.panelMiddle.setLayout(new BorderLayout());
		this.panelMiddle.add(this.panelMenuMiddle, BorderLayout.NORTH);
		this.panelMiddle.add(this.paneStudent, BorderLayout.CENTER);
		this.panelMiddle.setBackground(Color.LIGHT_GRAY);
		this.panelMiddle.setBounds(100, 50, 330, 610);
		this.panelMiddle.setBorder(BorderFactory.createTitledBorder(border,
				"Student", TitledBorder.LEFT, TitledBorder.TOP, new Font(
						" Courier New", Font.ITALIC, 36), Color.black));

		this.panelRight.setLayout(new BorderLayout());
		this.panelRight.add(this.panelMenuRight, BorderLayout.NORTH);
		this.panelRight.add(this.paneTeacher, BorderLayout.CENTER);
		this.panelRight.setBackground(Color.LIGHT_GRAY);
		this.panelRight.setBounds(100, 50, 330, 610);
		this.panelRight.setBorder(BorderFactory.createTitledBorder(border,
				"Teacher", TitledBorder.LEFT, TitledBorder.TOP, new Font(
						" Courier New", Font.ITALIC, 36), Color.black));

		this.frame.setBounds(150, 50, 1000, 650);
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

	public TableModel updateCampusList() {
		List<Campus> campusList = ManageCampus.getAllCampus();
		String[][] campus = new String[campusList.size()][2];
		for (int i = 0; i < campusList.size(); i++) {
			campus[i][0] = campusList.get(i).getVille();
			campus[i][1] = campusList.get(i).getRegion();
		}
		String[] title = { "Ville", "Region" };
		DefaultTableModel model = new DefaultTableModel(campus, title);
		return model;

	}

	public TableModel updateStudentList(Campus campusSelected) {
		List<Student> studentList = DaoUtil.getCampus().getStudents(
				campusSelected);
		String[][] student = new String[studentList.size()][3];
		for (int i = 0; i < studentList.size(); i++) {
			student[i][0] = studentList.get(i).getID() + "";
			student[i][1] = studentList.get(i).getPrenom();
			student[i][2] = studentList.get(i).getNom();

		}
		String[] title = { "ID", "prenom", "nom" };
		DefaultTableModel model = new DefaultTableModel(student, title);
		System.out.println("---studentList: " + studentList.size() + "---");
		return model;

	}

	public TableModel updateTeacherList(Campus campusSelected) {
		List<Teacher> teacherList = DaoUtil.getCampus().getTeachers(
				campusSelected);
		String[][] teacher = new String[teacherList.size()][3];
		for (int i = 0; i < teacherList.size(); i++) {
			teacher[i][0] = teacherList.get(i).getID() + "";
			teacher[i][1] = teacherList.get(i).getPrenom();
			teacher[i][2] = teacherList.get(i).getNom();

		}
		String[] title = { "ID", "prenom", "nom" };
		DefaultTableModel model = new DefaultTableModel(teacher, title);
		System.out.println("---teacherList: " + teacherList.size() + "---");
		return model;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.btnAddLeft) {
			if (addCampus == null) {
				addCampus = new UICampus(this.frame);
			} else {
				addCampus.setVisible(true);
			}
			if(addCampus.getVille()==null||addCampus.getRegion()==null||addCampus.getCapacite()==0){
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Please Check input", "Please Check input", JOptionPane.WARNING_MESSAGE);
			}else{
				Campus newCampus = new Campus(addCampus.getVille(),
						addCampus.getRegion(), addCampus.getCapacite());
				DaoUtil.getManageCampus().addCampus(newCampus);
				campusList.setModel(this.updateCampusList());
			}
			
		} else if (source == this.btnAddMiddle) {
			if (addStudent == null) {
				addStudent = new UIStudent(this.frame);
			} else {
				addStudent.setVisible(true);
			}
			Student newStudent = new Student(addStudent.getID(),
					addStudent.getPrenom(), addStudent.getNom(),
					addStudent.getVille(), addStudent.getRegion());
			boolean resMess = DaoUtil.getCampus().addStudent(newStudent);
			if (resMess) {
				studentList.setModel(this.updateStudentList(campus));
			} else {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "The Capacite is Full",
						"Exception_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if (source == this.btnAddRight) {
			if (addTeacher == null) {
				addTeacher = new UITeacher(this.frame);
				if (addTeacher.getFlag() == 0) {
					Teacher newTeacher = new InternalTeacher(
							addTeacher.getID(), addTeacher.getPrenom(),
							addTeacher.getNom(), addTeacher.getVille(),
							addTeacher.getRegion());
					campus.addTeacher(newTeacher);
				}else{
					Teacher newTeacher = new ExternalTeacher(
							addTeacher.getID(), addTeacher.getPrenom(),
							addTeacher.getNom(), addTeacher.getVille(),addTeacher.getRegion(),addTeacher.getSalary());
					campus.addTeacher(newTeacher);
				}
				teacherList.setModel(this.updateTeacherList(campus));
			} else {
				addTeacher.setVisible(true);
			}
		} else if (source == this.btnBackupLeft) {
			if (backup == null) {
				backup =new UIBackup(this.frame);
			}else{
				backup.setVisible(true);
			}
			DaoUtil.getBackupData().campusBackup(backup.getPath());

		} else if (source == this.btnBackupMiddle) {
			if (backup == null) {
				backup =new UIBackup(this.frame);
			}else{
				backup.setVisible(true);
			}
			DaoUtil.getBackupData().studentsBackup(backup.getPath());

		} else if (source == this.btnBackupRight) {
			if (backup == null) {
				backup =new UIBackup(this.frame);
			}else{
				backup.setVisible(true);
			}
			DaoUtil.getBackupData().teachersBackup(backup.getPath());
		} else if (source == this.btnDelMiddle) {
			int selectedRow = studentList.getSelectedRow();
			String prenom = (String) studentList.getValueAt(selectedRow, 1);
			String nom = (String) studentList.getValueAt(selectedRow, 2);
			Student studentSelected = campus.getStudent(prenom, nom);
			campus.removeStudent(studentSelected);
			// this.updateStudentList(campus);
			studentList.setModel(this.updateStudentList(campus));

		} else if (source == this.btnDelRight) {
			int selectRow=teacherList.getSelectedRow();
			int ID=(Integer) teacherList.getValueAt(selectRow,1);
			DaoUtil.getTeacherDao().removeTeacher(ID);
			teacherList.setModel(this.updateTeacherList(campus));

		} else if (source == this.btnModRight) {

		}
	}
}
