package org.whu.info.GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class UITeacher implements ActionListener {
	JDialog dialog;
	JPanel dialogPanel;
	JPanel inputPanel;
	JPanel inputIDPanel;
	JPanel inputPrenomPanel;
	JPanel inputNomPanel;
	JPanel inputSalaryPanel;// �����
	JPanel alertPanel;
	JPanel btnPanel;
	JPanel choosePanel;
	JTextField txtID;
	JTextField txtPrenom;
	JTextField txtNom;
	JTextField txtSalary;

	JLabel lblId;
	JLabel lblPrenom;
	JLabel lblNom;
	JLabel lblSalary;// �����
	JLabel lblAlert;

	JButton btnSubmit;
	JButton btnCancel;
	
	JRadioButton InternalT;
	JRadioButton ExternalT ;

	int ID;
	String prenom;
	String nom;
	String ville;
	String region;
	int flag;
	

	int salary;// �����

	UITeacher(JFrame frame) {
		
		
		dialog = new JDialog(frame, "Add a new teacher", true);
		dialogPanel = new JPanel();
		this.inputPanel = new JPanel();
		this.btnPanel = new JPanel();
		this.choosePanel=new JPanel();

		this.inputIDPanel = new JPanel();
		this.inputPrenomPanel = new JPanel();
		this.inputNomPanel = new JPanel();
		this.inputSalaryPanel = new JPanel();
		this.alertPanel = new JPanel();
		txtID = new JTextField();
		txtID.setPreferredSize(new Dimension(200, 30));
		txtPrenom = new JTextField();
		txtPrenom.setPreferredSize(new Dimension(200, 30));
		txtNom = new JTextField();
		txtNom.setPreferredSize(new Dimension(200, 30));
		txtSalary = new JTextField(); // �����
		txtSalary.setPreferredSize(new Dimension(200, 30));

		this.lblId = new JLabel("ID");
		this.lblPrenom = new JLabel("prenom");
		this.lblNom = new JLabel("nom");
		this.lblAlert=new JLabel("");
		this.lblSalary = new JLabel("Salary");// �����

		this.inputIDPanel.add(this.lblId);
		this.inputIDPanel.add(this.txtID);
		this.inputPrenomPanel.add(this.lblPrenom);
		this.inputPrenomPanel.add(this.txtPrenom);
		this.inputNomPanel.add(this.lblNom);
		this.inputNomPanel.add(this.txtNom);
		this.alertPanel.add(this.lblAlert);
		this.inputSalaryPanel.add(this.lblSalary);
		this.inputSalaryPanel.add(this.txtSalary);// �����
		this.inputPanel.setLayout(new GridLayout(5, 1));
		this.inputPanel.add(this.inputIDPanel);
		this.inputPanel.add(this.inputPrenomPanel);
		this.inputPanel.add(this.inputNomPanel);
		this.inputPanel.add(this.inputSalaryPanel);// �����
		this.inputPanel.add(this.alertPanel);
        
		InternalT = new JRadioButton("Internal");
		InternalT.addActionListener(this);
		ExternalT = new JRadioButton("External");
		ExternalT.addActionListener(this);
		ButtonGroup chooseT=new ButtonGroup();
		chooseT.add(InternalT);
		chooseT.add(ExternalT);
		choosePanel.add(InternalT);
		choosePanel.add(ExternalT);
		
		this.btnCancel = new JButton("Cancel");
		this.btnSubmit = new JButton("Submit");
		this.btnSubmit.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.btnPanel.add(btnSubmit);
		this.btnPanel.add(btnCancel);
		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.add(this.choosePanel,BorderLayout.NORTH);
		dialogPanel.add(this.inputPanel, BorderLayout.CENTER);
		dialogPanel.add(this.btnPanel, BorderLayout.SOUTH);
		dialog.setBounds(100, 100, 500, 400);
		dialog.add(dialogPanel);
		dialog.setVisible(true);
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == this.btnSubmit) {
			
			if(this.txtPrenom.getText().equals("")||this.txtNom.getText().equals("")||this.txtID.getText().equals("")){
				this.lblAlert.setText("Please entre all infomations");
				this.dialog.repaint();
				return;
			}
			this.ID = Integer.parseInt(this.txtID.getText());
			this.prenom = this.txtPrenom.getText();
			this.nom = this.txtNom.getText();
			if(this.flag==1){
				if(this.txtSalary.getText().equals("")){
					this.salary=0;
				}else{
				this.salary = Integer.parseInt(this.txtSalary.getText());// �����
				}
			}
			this.txtID.setText("");
			this.txtPrenom.setText("");
			this.txtNom.setText("");
			this.txtSalary.setText("");
			this.lblAlert.setText("");
			this.dialog.setVisible(false);
		}
		if(source==this.InternalT){
			this.inputSalaryPanel.setVisible(false);
			this.flag=0;
		}
		else if(source==this.ExternalT){
			this.inputSalaryPanel.setVisible(true);
			this.flag=1;
		}else if(source==this.btnCancel){
			this.txtID.setText("");
			this.txtPrenom.setText("");
			this.txtNom.setText("");
			this.txtSalary.setText("");
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

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setVisible(boolean visible) {
		this.dialog.setVisible(visible);
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public static void main(String[] args) {
		new UIStudent(new JFrame());
	}
	public int getFlag() {
		return flag;
	}
}
