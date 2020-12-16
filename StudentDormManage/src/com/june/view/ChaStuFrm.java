package com.june.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.june.dao.AdminDao;
import com.june.model.Student;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChaStuFrm extends JInternalFrame {
	private JTextField StuIDtextField;
	private JTextField OldRoomtextField;
	private JTextField NewRoomtextField;
	private JTextField NewBedtextField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChaStuFrm frame = new ChaStuFrm();
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
	public ChaStuFrm() {
		setTitle("\u5B66\u751F\u5BBF\u820D\u53D8\u52A8");
		setBounds(100, 100, 593, 450);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u5BBF\u820D\u53D8\u52A8");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));
		lblNewLabel.setBounds(232, 45, 155, 39);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(153, 116, 72, 18);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u65E7\u5BBF\u820D\u53F7");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(137, 160, 84, 18);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u65B0\u5BBF\u820D\u53F7");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(137, 215, 88, 18);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u5E8A\u4F4D\u53F7");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(137, 264, 72, 18);
		getContentPane().add(lblNewLabel_4);
		
		StuIDtextField = new JTextField();
		StuIDtextField.setBounds(249, 115, 177, 24);
		getContentPane().add(StuIDtextField);
		StuIDtextField.setColumns(10);
		
		OldRoomtextField = new JTextField();
		OldRoomtextField.setBounds(249, 159, 177, 24);
		getContentPane().add(OldRoomtextField);
		OldRoomtextField.setColumns(10);
		
		NewRoomtextField = new JTextField();
		NewRoomtextField.setBounds(249, 214, 177, 24);
		getContentPane().add(NewRoomtextField);
		NewRoomtextField.setColumns(10);
		
		NewBedtextField = new JTextField();
		NewBedtextField.setBounds(249, 263, 177, 24);
		getContentPane().add(NewBedtextField);
		NewBedtextField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChaStu();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(241, 320, 125, 48);
		getContentPane().add(btnNewButton);
		
		setClosable(true);
		setIconifiable(true);

	}

	protected void ChaStu() {
		// TODO Auto-generated method stub
		//0.检验信息是否填全
		if(StuIDtextField.getText().equals("") || OldRoomtextField.getText().equals("") || NewBedtextField.getText().equals("") || NewRoomtextField.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this, "请将信息填写完整!");
			return;
		}
		//1.获取输入信息构造stu
		Student stutemp = new Student();
		stutemp.setID(StuIDtextField.getText().toString());
		stutemp.setDom_ID(NewRoomtextField.getText().toString());
		stutemp.setBed(NewBedtextField.getText().toString());
		String oldroom = OldRoomtextField.getText().toString();
		//2.数据库连接dao
		AdminDao adm = new AdminDao();
		int tag = adm.ChaStu(stutemp, oldroom);
		adm.closeDao();
		//3.根据不同返回确定不同行为
		if(tag == 1)
		{
			JOptionPane.showMessageDialog(this, "学生不存在!");
			return;
		}
		if(tag == 2)
		{
			JOptionPane.showMessageDialog(this, "宿舍不存在!");
			return;
		}
		if(tag == 3)
		{
			JOptionPane.showMessageDialog(this, "新房间已满!");
			return;
		}
		if(tag == 4)
		{
			JOptionPane.showMessageDialog(this, "该床位有人!");
			return;
		}
		if(tag == -1)
		{
			JOptionPane.showMessageDialog(this, "修改失败!");
			return;
		}
		if(tag == 0)
			JOptionPane.showMessageDialog(this, "修改成功");
		
		return;
	}

}
