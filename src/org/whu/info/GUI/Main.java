package org.whu.info.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main extends JFrame implements ActionListener{

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
		this.panelLeft = new JPanel();
		this.panelRight=new JPanel();
		this.frame .setBounds(150,50,1000,650);
		
		this.paneStudent =new JScrollPane();
		
		this.panelLeft.add(this.paneCampus);
		this.panelRight.add(this.paneStudent);
		this.panelRight.add(this.paneTeacher);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.add(this.panelLeft);
		this.frame.add(this.panelRight);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new Main();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
