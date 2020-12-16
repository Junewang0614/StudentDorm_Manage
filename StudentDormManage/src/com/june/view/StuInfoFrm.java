package com.june.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.june.model.Student;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class StuInfoFrm extends JFrame {

	private JPanel contentPane;
	private Student stu;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StuInfoFrm frame = new StuInfoFrm();
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
	public StuInfoFrm(Student student) {
		this.stu = student;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u4E2A\u4EBA\u4FE1\u606F");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_2 = new JLabel("\u5BBF\u820D\u53F7");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_3 = new JLabel("\u5E8A\u4F4D\u53F7");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_4 = new JLabel("\u4E13\u4E1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_5 = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_6 = new JLabel("\u5B66\u53F7");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel StuIDLabel = new JLabel("");
		StuIDLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		StuIDLabel.setText(stu.getID());
		
		JLabel StunameLabel = new JLabel("");
		StunameLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		StunameLabel.setText(stu.getName());
		
		JLabel StuRoomLabel = new JLabel("");
		StuRoomLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		StuRoomLabel.setText(stu.getDom_ID());
		
		JLabel StuBedLabel = new JLabel("");
		StuBedLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		StuBedLabel.setText(stu.getBed());
		
		JLabel StuMajorLabel_7 = new JLabel("");
		StuMajorLabel_7.setFont(new Font("宋体", Font.PLAIN, 18));
		StuMajorLabel_7.setText(stu.getMajor());
		
		JLabel StuPhoneLabel = new JLabel("");
		StuPhoneLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		if(stu.getPhone() == "" || stu.getPhone() == null) {
			StuPhoneLabel.setText("未登记联系方式");
		}
		else {
			StuPhoneLabel.setText(stu.getPhone());
		}
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(139)
							.addComponent(lblNewLabel_4))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(130)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_3)
									.addComponent(lblNewLabel_2))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(138)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_6))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(69)
									.addComponent(StuIDLabel))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(54)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(StunameLabel)
										.addComponent(StuRoomLabel)
										.addComponent(StuBedLabel)
										.addComponent(StuMajorLabel_7)
										.addComponent(StuPhoneLabel)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(177)
							.addComponent(lblNewLabel)))
					.addContainerGap(195, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(StuIDLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(StunameLabel))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(StuRoomLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(StuBedLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(StuMajorLabel_7))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5)
						.addComponent(StuPhoneLabel))
					.addGap(99))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
