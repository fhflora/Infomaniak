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

public class UIBackup implements ActionListener{
	
	JDialog dialog;
	JPanel dialogPanel;
	JPanel inputPanel;
	JPanel inputPathPanel;
	JPanel TipsPanel;
	JPanel btnPanel;
	JTextField txtPath;
	JLabel lblPath;
	JLabel lblTip;
	JButton btnSubmit;
	JButton btnCancel;
	String path;
	UIBackup(JFrame frame){
		dialog= new JDialog(frame,"Backup",true);
		dialogPanel= new JPanel();
		this.inputPanel=new JPanel();
		this.btnPanel=new JPanel();
		
		this.inputPathPanel=new JPanel();
		txtPath=new JTextField();
		txtPath.setPreferredSize(new Dimension(200,30));
		this.lblPath=new JLabel("Backup");
		this.lblTip = new JLabel("for example : C:/XXX.txt");
		this.TipsPanel=new JPanel();
		TipsPanel.add(this.lblTip);
		this.inputPathPanel.add(this.lblPath);
		this.inputPathPanel.add(this.txtPath);
		this.inputPanel.setLayout(new GridLayout(2, 1));
		this.inputPanel.add(this.inputPathPanel);
		this.inputPanel.add(this.TipsPanel);
		
		
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
			this.path=this.txtPath.getText();
			this.txtPath.setText("");
			this.dialog.setVisible(false);
		}
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setVisible(boolean visible){
		this.dialog.setVisible(visible);
	}
}
