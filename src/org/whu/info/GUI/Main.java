package org.whu.info.GUI;

import java.awt.Color;
import java.awt.Font;
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
	private JPanel panelMiddle;
	
	JScrollPane paneStudent;
	JScrollPane paneTeacher;
	JScrollPane paneCampus;
	
	public Main(){
		this.frame=new JFrame();
		this.panelLeft = new JPanel();
		this.panelLeft.setBackground(Color.lightGray);
		this.panelLeft.setBounds(0, 50, 330, 610);
		Border border=BorderFactory.createEtchedBorder(Color.black, Color.BLUE);
		this.panelLeft.setBorder(BorderFactory.createTitledBorder(border,"Campus" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
		this.panelMiddle=new JPanel();
		this.panelMiddle.setBackground(Color.lightGray);
		this.panelMiddle.setBounds(50, 50, 330, 610);
		this.panelMiddle.setBorder(BorderFactory.createTitledBorder(border,"Teacher" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
		
		this.panelRight=new JPanel();
		this.panelRight.setBackground(Color.LIGHT_GRAY);
		this.panelRight.setBounds(100, 50, 330, 610);
		this.panelRight.setBorder(BorderFactory.createTitledBorder(border,"Student" , TitledBorder.LEFT,
				TitledBorder.TOP,new Font(" Courier New",Font.ITALIC,36),Color.black));
		
		this.frame.setBounds(150,50,1000,650);
		
		this.paneStudent =new JScrollPane();
		this.paneTeacher =new JScrollPane();
		this.paneCampus =new JScrollPane();
		
		this.panelLeft.add(this.paneCampus);
		this.panelRight.add(this.paneStudent);
		this.panelMiddle.add(this.paneTeacher);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.add(this.panelLeft);
		this.frame.add(this.panelRight);
		this.frame.add(this.panelMiddle);
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
