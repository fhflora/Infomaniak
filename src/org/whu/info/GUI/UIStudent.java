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
public class UIStudent implements ActionListener{
	JDialog dialog;
	JPanel dialogPanel;
	JPanel inputPanel;
	JPanel inputIDPanel;
	JPanel inputPrenomPanel;
	JPanel inputNomPanel;
	JPanel alertPanel;
	JPanel btnPanel;
	JTextField txtID;
	JTextField txtPrenom;
	JTextField txtNom;
	JLabel lblAlert;
	JLabel lblId;
	JLabel lblPrenom;
	JLabel lblNom;
	JLabel lblVille;
	JLabel lblRegion;
	JButton btnSubmit;
	JButton btnCancel;
	

	int	   ID;
	String prenom;
	String nom;
	
	UIStudent(JFrame frame){
		dialog= new JDialog(frame,"Add a new student",true);
		dialogPanel= new JPanel();
		this.inputPanel=new JPanel();
		this.btnPanel=new JPanel();
		
		this.inputIDPanel=new JPanel();
		this.inputPrenomPanel=new JPanel();
		this.inputNomPanel=new JPanel();
		this.alertPanel=new JPanel();
		txtID=new JTextField();
		txtID.setPreferredSize(new Dimension(200,30));
		txtPrenom=new JTextField();
		txtPrenom.setPreferredSize(new Dimension(200,30));
		txtNom=new JTextField();
		txtNom.setPreferredSize(new Dimension(200,30));
		
		this.lblId=new JLabel("ID");
		this.lblPrenom=new JLabel("prenom");
		this.lblNom=new JLabel("nom");
		this.inputIDPanel.add(this.lblId);
		this.inputIDPanel.add(this.txtID);
		this.inputPrenomPanel.add(this.lblPrenom);
		this.inputPrenomPanel.add(this.txtPrenom);
		this.inputNomPanel.add(this.lblNom);
		this.inputNomPanel.add(this.txtNom);
		this.lblAlert=new JLabel("");
		this.alertPanel.add(this.lblAlert);
		this.inputPanel.setLayout(new GridLayout(4,1));
		this.inputPanel.add(this.inputIDPanel);
		this.inputPanel.add(this.inputPrenomPanel);
		this.inputPanel.add(this.inputNomPanel);
		this.inputPanel.add(this.lblAlert);
		this.btnCancel=new JButton("Cancel");
		this.btnSubmit=new JButton("Submit");
        this.btnSubmit.addActionListener(this);
        this.btnCancel.addActionListener(this);
		this.btnPanel.add(btnSubmit);
		this.btnPanel.add(btnCancel);
		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.add(this.inputPanel,BorderLayout.CENTER);
		dialogPanel.add(this.btnPanel,BorderLayout.SOUTH);
		dialog.setBounds(100, 100, 400, 250);
		dialog.add(dialogPanel);
		dialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		if(source==this.btnSubmit){
			if(!this.txtID.getText().equals("")){
				this.ID=Integer.parseInt(this.txtID.getText());
			}else{
				this.ID=0;
			}
			
			//String txt=this.txtPrenom.getText();
			if(this.txtPrenom.getText().equals("")||this.txtNom.getText().equals("")){
				this.lblAlert.setText("Please entre a first name or name");
				return;
			}
			
			this.prenom=this.txtPrenom.getText();
			this.nom=this.txtNom.getText();			
			this.txtID.setText("");
			this.txtPrenom.setText("");
			this.txtNom.setText("");
			this.lblAlert.setText("");
			this.dialog.setVisible(false);
		}else if(source==this.btnCancel){
			this.txtID.setText("");
			this.txtPrenom.setText("");
			this.txtNom.setText("");
			this.lblAlert.setText("");
			this.dialog.setVisible(false);
		}
	}
	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setVisible(boolean visible){
		this.dialog.setVisible(visible);
	}
	
}
