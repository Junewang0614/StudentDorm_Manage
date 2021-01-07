package com.june.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.june.dao.AdminDao;
import com.june.dao.StudentDao;
import com.june.model.Student;

import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class SelStuFrm extends JInternalFrame {
	private JTextField StuIDtextField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelStuFrm frame = new SelStuFrm();
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
	public SelStuFrm() {
		setTitle("\u5B66\u751F\u4FE1\u606F\u67E5\u8BE2");
		setBounds(100, 100, 703, 179);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u4FE1\u606F\u67E5\u8BE2");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		StuIDtextField = new JTextField();
		StuIDtextField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectDorminfo();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(254)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(158)
							.addComponent(lblNewLabel_1)
							.addGap(37)
							.addComponent(StuIDtextField, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(btnNewButton)))
					.addContainerGap(176, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(StuIDtextField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel_1))
					.addContainerGap(355, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		setClosable(true);
		setIconifiable(true);
	}
	
	protected void selectDorminfo() {
		// TODO Auto-generated method stub
		//1.整理输入参数
		List<Student> stuList = null;
		String stu_id = StuIDtextField.getText(); 
		//2.链接数据库
		
		AdminDao adminDao = new AdminDao();
		//1.先检查学生存不存在
		if(adminDao.StuExists(stu_id) == false)
		{
			adminDao.closeDao();
			JOptionPane.showMessageDialog(this,"学生不存在!");
			return;
		}
		adminDao.closeDao();
		StudentDao stuDao = new StudentDao();
		stuList = stuDao.getStuList(stu_id);
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
}
