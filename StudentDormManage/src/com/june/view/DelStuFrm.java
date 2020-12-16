package com.june.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.june.dao.AdminDao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DelStuFrm extends JInternalFrame {
	private JTextField StuIDtextField;
	private JTextField RoomtextField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DelStuFrm frame = new DelStuFrm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DelStuFrm() {
		setTitle("\u5220\u9664\u5165\u4F4F\u5B66\u751F");
		setBounds(100, 100, 598, 365);
		
		JLabel lblNewLabel = new JLabel("\u5220\u9664\u5165\u4F4F\u5B66\u751F");
		lblNewLabel.setBounds(224, 58, 132, 30);
		lblNewLabel.setFont(new Font("΢���ź�", Font.BOLD, 22));
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");
		lblNewLabel_1.setBounds(163, 125, 40, 24);
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		
		StuIDtextField = new JTextField();
		StuIDtextField.setBounds(260, 127, 160, 24);
		StuIDtextField.setColumns(10);
		
		RoomtextField = new JTextField();
		RoomtextField.setBounds(260, 192, 160, 24);
		RoomtextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BBF\u820D\u53F7");
		lblNewLabel_2.setBounds(163, 190, 60, 24);
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 20));
		getContentPane().setLayout(null);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(StuIDtextField);
		getContentPane().add(RoomtextField);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("\u5220\u9664");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DelStu();
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 18));
		btnNewButton.setBounds(234, 248, 122, 54);
		getContentPane().add(btnNewButton);
		
		setClosable(true);
		setIconifiable(true);

	}

	protected void DelStu() {
		// TODO Auto-generated method stub
		//ɾ��ѧ������
		//���Ȼ�ȡ�������Ϣ,������Ϣȱһ����
		String sid = StuIDtextField.getText().toString();
		String rid = RoomtextField.getText().toString();
		if(rid.equals("") || sid.equals("")) {
			JOptionPane.showMessageDialog(this,"�뽫��Ϣ��д����!");
			return;
		}
		//����dao���в���
		AdminDao amd = new AdminDao();
		int tag = amd.DelStu(sid, rid);
		amd.closeDao();
		//�Բ�ͬ�ķ��ؽ���в�ͬ�ķ���
		if(tag == 1)
		{
			JOptionPane.showMessageDialog(this, "ѧ��������!");
			return;
		}
		if(tag == 2) {
			JOptionPane.showMessageDialog(this, "���᲻����!");
			return;
		}
		if (tag == 3) {
			JOptionPane.showMessageDialog(this, "ѧ���������ϵ��ƥ�䣬��ȷ���Ƿ��������!");
			return;
			
		}
		if(tag == -1) {
			JOptionPane.showMessageDialog(this, "ɾ��ʧ��");
			return;
		}
		if(tag == 0) {
			JOptionPane.showMessageDialog(this, "ɾ���ɹ�");
		}
		
		return;
	}
}
