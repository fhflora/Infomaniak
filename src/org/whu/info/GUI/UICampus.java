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

public class UICampus implements ActionListener{

	JDialog dialog;
	JPanel dialogPanel;
	JPanel inputPanel;
	JPanel inputVillePanel;
	JPanel inputRegionPanel;
	JPanel inputCapcaitePanel;
	JPanel btnPanel;
	JTextField txtVille;
	JTextField txtRegion;
	JTextField txtCapacite;
	
	JLabel lblVille;
	JLabel lblRegion;
	JLabel lblCapacite;
	JButton btnSubmit;
	JButton btnCancel;
	
	String ville;
	String region;
	int capacite;
	

	UICampus(JFrame frame){
		dialog= new JDialog(frame,"Add a new campus",true);
		dialogPanel= new JPanel();
		this.inputPanel=new JPanel();
		this.btnPanel=new JPanel();
		
		this.inputVillePanel=new JPanel();
		this.inputRegionPanel=new JPanel();
		this.inputCapcaitePanel=new JPanel();
		txtVille=new JTextField();
		txtVille.setPreferredSize(new Dimension(200,30));
		txtRegion=new JTextField();
		txtRegion.setPreferredSize(new Dimension(200,30));
		txtCapacite=new JTextField();
		txtCapacite.setPreferredSize(new Dimension(200,30));
		this.lblVille=new JLabel("Ville");
		this.lblRegion=new JLabel("Region");
		this.lblCapacite=new JLabel("Capacite");
		this.inputVillePanel.add(this.lblVille);
		this.inputVillePanel.add(this.txtVille);
		this.inputRegionPanel.add(this.lblRegion);
		this.inputRegionPanel.add(this.txtRegion);
		this.inputCapcaitePanel.add(this.lblCapacite);
		this.inputCapcaitePanel.add(this.txtCapacite);
		this.inputPanel.setLayout(new GridLayout(3,1));
		this.inputPanel.add(this.inputVillePanel);
		this.inputPanel.add(this.inputRegionPanel);
		this.inputPanel.add(this.inputCapcaitePanel);
		
		this.btnCancel=new JButton("Cancel");
		this.btnSubmit=new JButton("Submit");
        this.btnSubmit.addActionListener(this);
        this.btnCancel.addActionListener(this);
		this.btnPanel.add(btnSubmit);
		this.btnPanel.add(btnCancel);
		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.add(this.inputPanel,BorderLayout.CENTER);
		dialogPanel.add(this.btnPanel,BorderLayout.SOUTH);
		dialog.setBounds(100, 100, 300, 200);
		dialog.add(dialogPanel);
		dialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		if(source==this.btnSubmit){
			this.ville=this.txtVille.getText();
			this.region=this.txtRegion.getText();
			this.capacite=Integer.parseInt(this.txtCapacite.getText());
			this.txtVille.setText("");
			this.txtRegion.setText("");
			this.txtCapacite.setText("");
			this.dialog.setVisible(false);
		}
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

	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
}
