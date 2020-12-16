package com.june.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.june.dao.RoomDao;
import com.june.model.Room;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class RoomManageFrm extends JInternalFrame {
	private JTextField keyWordtextField;
	private JTable RoomListTable;
	private JComboBox KeyWordscomboBox;
	private JComboBox StatesComboBox;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RoomManageFrm frame = new RoomManageFrm();
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
	public RoomManageFrm() {
		setTitle("\u5BBF\u820D\u4FE1\u606F\u67E5\u8BE2");
		setBounds(100, 100, 724, 584);
		setClosable(true);
		setIconifiable(true);
		JLabel lblNewLabel = new JLabel("\u5173\u952E\u5B57");
		
		JLabel lblNewLabel_1 = new JLabel("\u5185\u5BB9");
		
		KeyWordscomboBox = new JComboBox();
		KeyWordscomboBox.setModel(new DefaultComboBoxModel(new String[] {"\u4E13\u4E1A", "\u623F\u95F4\u53F7"}));
		
		keyWordtextField = new JTextField();
		keyWordtextField.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("\u72B6\u6001");
		
		StatesComboBox = new JComboBox();
		StatesComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u672A\u9009\u62E9", "\u672A\u6EE1\u5458", "\u6EE1\u5458"}));
		
		JButton SelectButton = new JButton("\u67E5\u8BE2");
		SelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelRoominfo();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_3 = new JLabel("\u5BBF\u820D\u4FE1\u606F\u67E5\u8BE2");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 22));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(142)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(KeyWordscomboBox, 0, 220, Short.MAX_VALUE)
								.addComponent(StatesComboBox, 0, 220, Short.MAX_VALUE)
								.addComponent(keyWordtextField, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
							.addGap(54)
							.addComponent(SelectButton)
							.addGap(73))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(256)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
					.addGap(77))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel_3)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(KeyWordscomboBox, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(keyWordtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(SelectButton))
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(StatesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		RoomListTable = new JTable();
		RoomListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u623F\u95F4\u53F7", "\u623F\u95F4\u72B6\u6001", "\u6210\u5458\u6570\u91CF", "\u5176\u4ED6"
			}
		));
		scrollPane.setViewportView(RoomListTable);
		getContentPane().setLayout(groupLayout);

	}

	protected void SelRoominfo() {
		DefaultTableModel dft = (DefaultTableModel)RoomListTable.getModel();
		dft.setRowCount(0);
		// TODO Auto-generated method stub
		//1.从文本框等获取信息
		String room_id = "";
		String major = "";
		int state = 2;
		String keyword = (String)KeyWordscomboBox.getSelectedItem();
		//System.out.println((String)KeyWordscomboBox.getSelectedItem());ok的
		if("专业".equals(keyword))
		{
			major = keyWordtextField.getText();
		}
		else if("房间号".equals(keyword))
		{
			room_id = keyWordtextField.getText();
		}
		//System.out.println(major);
		keyword = (String)StatesComboBox.getSelectedItem();
		//System.out.println(keyword);
		if("满员".equals(keyword))
		{
			state = 1;
		}
		else if("未满员".equals(keyword)) {
			state = 0;
		}
		
		//2.创建DAO与数据库联系
		RoomDao rd = new RoomDao();
		List<Room> rooms = rd.getRoomList(room_id, state, major);
		rd.closeDao();
		if(rooms == null) {
			JOptionPane.showMessageDialog(this,"未查到结果，请重新确认查询信息");
			return;
		}
		
//		for(Room r : rooms) {
//			r.printinfo();
//		}
			
		//3.得到list进行展示
		for(Room r:rooms) {
			Vector v = new Vector();
			v.add(r.getId());
			String statetmp = "未满员";
			if(r.isRoomstate()) {
				statetmp = "满员";
			}
			v.add(statetmp);
			v.add(r.getNum());
			dft.addRow(v);
		}
	}
}
