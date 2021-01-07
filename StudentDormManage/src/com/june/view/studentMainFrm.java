package com.june.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.june.dao.StudentDao;
import com.june.model.Admin;
import com.june.model.StuUser;
import com.june.model.Student;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class studentMainFrm extends JFrame {

	private JPanel contentPane;
	public static StuUser student;
	//public static Admin admin;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					studentMainFrm frame = new studentMainFrm();
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
	public studentMainFrm(StuUser student) {
		//this.admin = admin;
		this.student = student;
		setTitle("\u5B66\u751F\u7528\u6237\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("\u67E5\u770B\u4E2A\u4EBA\u4F4F\u5BBF\u4FE1\u606F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				selectStuInfo();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("\u67E5\u770B\u6240\u5728\u5BBF\u820D\u4FE1\u606F");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectDorminfo();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(135)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(172, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(79)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(55)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void selectDorminfo() {
		// TODO Auto-generated method stub
		//1.整理输入参数
		List<Student> stuList = null;
		//2.链接数据库
		StudentDao stuDao = new StudentDao();
		stuList = stuDao.getStuList(student.getName());
		stuDao.closeDao();
		//3.整理表示
		if(stuList == null)
		{
			JOptionPane.showMessageDialog(this,"用户还未登记在宿舍!");
			return;
		}
		else {
			//1.获取宿舍号
			String domid = stuList.get(0).getDom_ID();
			new StuRoominfo(stuList, domid).setVisible(true);
		}
		
	}

	//查看学生个人信息
	protected void selectStuInfo() {
		// TODO Auto-generated method stub
		Student stu = null;
		StudentDao stuDao = new StudentDao();
		stu = stuDao.stuInfoSelect(student.getName());
		stuDao.closeDao();
		if(stu == null) {
			JOptionPane.showMessageDialog(this,"用户还未登记在宿舍!");
			return;
		}
		new StuInfoFrm(stu).setVisible(true);
		
	}
}
