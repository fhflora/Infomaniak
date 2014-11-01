package org.whu.info.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

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
	
	public Main(){
		this.frame=new JFrame();
		this.paneStudent =new JScrollPane();
		this.paneTeacher =new JScrollPane();
		this.paneCampus =new JScrollPane();
		
		this.panelLeft = new JPanel();
		this.paneCampus.setBackground(Color.lightGray);
		this.paneCampus.setBounds(0, 50, 330, 610);
		Border border=BorderFactory.createEtchedBorder(Color.black, Color.BLUE);
		this.paneCampus.setBorder(BorderFactory.createTitledBorder(border,"Campus" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
				
		this.panelRight=new JPanel();
		this.paneStudent.setBackground(Color.LIGHT_GRAY);
		this.paneStudent.setBounds(100, 50, 330, 610);
		this.paneStudent.setBorder(BorderFactory.createTitledBorder(border,"Student" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
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
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}
	public static void main(String args[]) {
			new Main();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
