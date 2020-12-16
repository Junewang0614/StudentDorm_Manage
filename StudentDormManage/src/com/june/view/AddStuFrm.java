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

import com.june.dao.AdminDao;
import com.june.model.Student;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStuFrm extends JInternalFrame {
	private JTextField StuIDtextField;
	private JTextField StuNametextField;
	private JTextField StuRoomtextField;
	private JTextField StuBedtextField;
	private JTextField StuMajortextField;
	private JTextField StuPhonetextField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddStuFrm frame = new AddStuFrm();
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
	public AddStuFrm() {
		setTitle("\u6DFB\u52A0\u5165\u4F4F\u5B66\u751F");
		setBounds(100, 100, 555, 440);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u53F7");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_2 = new JLabel("\u5BBF\u820D\u53F7");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_3 = new JLabel("\u5E8A\u4F4D\u53F7");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_4 = new JLabel("\u4E13\u4E1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_5 = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 17));
		
		StuIDtextField = new JTextField();
		StuIDtextField.setColumns(10);
		
		StuNametextField = new JTextField();
		StuNametextField.setColumns(10);
		
		StuRoomtextField = new JTextField();
		StuRoomtextField.setColumns(10);
		
		StuBedtextField = new JTextField();
		StuBedtextField.setColumns(10);
		
		StuMajortextField = new JTextField();
		StuMajortextField.setColumns(10);
		
		StuPhonetextField = new JTextField();
		StuPhonetextField.setColumns(10);
		
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel_6 = new JLabel("\u6DFB\u52A0\u65B0\u5165\u4F4F\u5B66\u751F");
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddStu();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(109)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(StuIDtextField)
						.addComponent(StuNametextField)
						.addComponent(StuRoomtextField)
						.addComponent(StuBedtextField)
						.addComponent(StuMajortextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
						.addComponent(StuPhonetextField))
					.addContainerGap(135, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(241, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addGap(238))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(227, Short.MAX_VALUE)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addGap(167))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(StuIDtextField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(StuNametextField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(StuRoomtextField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(StuBedtextField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(StuMajortextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(StuPhonetextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}

	protected void AddStu() {
		// TODO Auto-generated method stub
		// 1、获取信息构造学生
		Student stu = new Student();
		stu.setID(StuIDtextField.getText().toString());
		stu.setName(StuNametextField.getText().toString());
		stu.setDom_ID(StuRoomtextField.getText().toString());
		stu.setMajor(StuMajortextField.getText().toString());
		stu.setPhone(StuPhonetextField.getText().toString());
		//system.out.println(stu.getPhone().equals(""));
		//System.out.println(StuBedtextField.getText().toString().equals(""));
		stu.setBed(StuBedtextField.getText().toString());
		
		AdminDao adminDao = new AdminDao();
		int tag = adminDao.AddStu(stu);
		adminDao.closeDao();
		if(tag == 1)
		{
			JOptionPane.showMessageDialog(this,"学生不存在!");
			return;
		}
		if(tag == 2) {
			JOptionPane.showMessageDialog(this,"该学生已经入住登记过!");
			return;
		}
		if(tag == 3) {
			JOptionPane.showMessageDialog(this,"房间不存在!");
			return;
		}
		if(tag == 4) {
			JOptionPane.showMessageDialog(this,"房间已住满!");
			return;
		}
		if(tag == 6) {
			JOptionPane.showMessageDialog(this,"床位不存在!");
			return;
		}
		if(tag == 5) {
			JOptionPane.showMessageDialog(this,"该床位有人!");
			return;
		}
		if(tag == -1) {
			JOptionPane.showMessageDialog(this,"添加失败!");
			return;
		}
		if(tag == 0)
		{
			JOptionPane.showMessageDialog(this,"添加成功!");
		}
		
		
		return;
	}
}
