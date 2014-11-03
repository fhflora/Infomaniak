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
	JPanel inputVillePanel;
	JPanel inputRegionPanel;
	JPanel btnPanel;
	JTextField txtID;
	JTextField txtPrenom;
	JTextField txtNom;
	JTextField txtVille;
	JTextField txtRegion;
	
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
	


	String ville;
	String region;
	
	
	UIStudent(JFrame frame){
		dialog= new JDialog(frame,"Add a new student",true);
		dialogPanel= new JPanel();
		this.inputPanel=new JPanel();
		this.btnPanel=new JPanel();
		
		this.inputIDPanel=new JPanel();
		this.inputPrenomPanel=new JPanel();
		this.inputNomPanel=new JPanel();
		this.inputVillePanel=new JPanel();
		this.inputRegionPanel=new JPanel();
		txtID=new JTextField();
		txtID.setPreferredSize(new Dimension(200,30));
		txtPrenom=new JTextField();
		txtPrenom.setPreferredSize(new Dimension(200,30));
		txtNom=new JTextField();
		txtNom.setPreferredSize(new Dimension(200,30));
		txtVille=new JTextField();
		txtVille.setPreferredSize(new Dimension(200,30));
		txtRegion=new JTextField();
		txtRegion.setPreferredSize(new Dimension(200,30));
		
		this.lblId=new JLabel("ID");
		this.lblPrenom=new JLabel("prenom");
		this.lblNom=new JLabel("nom");
		this.lblVille=new JLabel("Ville");
		this.lblRegion=new JLabel("Region");
		this.inputIDPanel.add(this.lblId);
		this.inputIDPanel.add(this.txtID);
		this.inputPrenomPanel.add(this.lblPrenom);
		this.inputPrenomPanel.add(this.txtPrenom);
		this.inputNomPanel.add(this.lblNom);
		this.inputNomPanel.add(this.txtNom);
		this.inputVillePanel.add(this.lblVille);
		this.inputVillePanel.add(this.txtVille);
		this.inputRegionPanel.add(this.lblRegion);
		this.inputRegionPanel.add(this.txtRegion);
		this.inputPanel.setLayout(new GridLayout(5,1));
		this.inputPanel.add(this.inputIDPanel);
		this.inputPanel.add(this.inputPrenomPanel);
		this.inputPanel.add(this.inputNomPanel);
		this.inputPanel.add(this.inputVillePanel);
		this.inputPanel.add(this.inputRegionPanel);
		
		this.btnCancel=new JButton("Cancel");
		this.btnSubmit=new JButton("Submit");
        this.btnSubmit.addActionListener(this);
        this.btnCancel.addActionListener(this);
		this.btnPanel.add(btnSubmit);
		this.btnPanel.add(btnCancel);
		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.add(this.inputPanel,BorderLayout.CENTER);
		dialogPanel.add(this.btnPanel,BorderLayout.SOUTH);
		dialog.setBounds(100, 100, 400, 300);
		dialog.add(dialogPanel);
		dialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		if(source==this.btnSubmit){
			this.ID=Integer.parseInt(this.txtID.getText());
			this.prenom=this.txtPrenom.getText();
			this.nom=this.txtNom.getText();
			this.ville=this.txtVille.getText();
			this.region=this.txtRegion.getText();
			this.txtID.setText("");
			this.txtPrenom.setText("");
			this.txtNom.setText("");
			this.txtVille.setText("");
			this.txtRegion.setText("");
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
	
	public void setVisible(boolean visible){
		this.dialog.setVisible(visible);
	}
	
}
