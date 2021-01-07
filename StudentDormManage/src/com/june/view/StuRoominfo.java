package com.june.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.june.model.Student;

public class StuRoominfo extends JFrame {

	private JPanel contentPane;
	private JTable StuListTable;
	private JLabel TitleLabel; 
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StuRoominfo frame = new StuRoominfo();
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
	public StuRoominfo(List<Student> stuList,String domid) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		TitleLabel = new JLabel(domid+"宿舍成员信息");
		TitleLabel.setFont(new Font("思源黑体", Font.BOLD, 30));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(218)
					.addComponent(TitleLabel, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addGap(229))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
					.addGap(26))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addComponent(TitleLabel, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addGap(4))
		);
		
		StuListTable = new JTable();
		StuListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u5BBF\u820D\u53F7", "\u5E8A\u4F4D\u53F7", "\u4E13\u4E1A", "\u8054\u7CFB\u65B9\u5F0F"
			}
		));
		StuListTable.getTableHeader().setFont(new Font("Dialog", 0, 19));
		StuListTable.setRowHeight(30);
		StuListTable.setFont(new Font("Dialog", 0, 19));
		scrollPane.setViewportView(StuListTable);
		contentPane.setLayout(gl_contentPane);
		
		showList(stuList);
	}
	
	private void showList(List<Student> stuList) {
		// TODO Auto-generated method stub
		DefaultTableModel dft = (DefaultTableModel)StuListTable.getModel();
		dft.setRowCount(0);
		for(Student stu:stuList) {
			Vector v = new Vector();
			v.add(stu.getID());
			v.add(stu.getName());
			v.add(stu.getDom_ID());
			v.add(stu.getBed());
			v.add(stu.getMajor());
			v.add(stu.getPhone());
			dft.addRow(v);
		}
	}
}
