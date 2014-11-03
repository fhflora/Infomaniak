package org.whu.info.GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class UIModifySalary implements ActionListener {
	JDialog dialog;
	JPanel dialogPanel;
	JPanel inputPanel;
	JPanel inputPathPanel;
	JPanel btnPanel;
	JTextField txtSalary;
	JLabel lblSalary;
	JButton btnSubmit;
	JButton btnCancel;
	int  salary;
	
	UIModifySalary (JFrame frame){
		dialog= new JDialog(frame,"Modify the salary of InternalTeacher",true);
		dialogPanel= new JPanel();
		this.inputPanel=new JPanel();
		this.btnPanel=new JPanel();
		
		this.inputPathPanel=new JPanel();
		txtSalary=new JTextField();
		txtSalary.setPreferredSize(new Dimension(200,30));
		this.lblSalary=new JLabel("The salary of InternalTeacher");
		this.inputPathPanel.add(this.lblSalary);
		this.inputPathPanel.add(this.txtSalary);
		this.inputPanel.setLayout(new GridLayout(1, 1));
		this.inputPanel.add(this.inputPathPanel);
		
		
		this.btnCancel=new JButton("Cancel");
		this.btnSubmit=new JButton("Submit");
        this.btnSubmit.addActionListener(this);
        this.btnCancel.addActionListener(this);
		this.btnPanel.add(btnSubmit);
		this.btnPanel.add(btnCancel);
		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.add(this.inputPanel,BorderLayout.CENTER);
		dialogPanel.add(this.btnPanel,BorderLayout.SOUTH);
		dialog.setBounds(100, 100, 300, 180);
		dialog.add(dialogPanel);
		dialog.setVisible(true);
		}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		if(source==this.btnSubmit){
			this.salary=Integer.parseInt(this.txtSalary.getText());
			this.txtSalary.setText("");
			this.dialog.setVisible(false);
		}
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setVisible(boolean visible){
		this.dialog.setVisible(visible);
	}
}
