package com.june.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopPaneUI;

import com.june.model.Admin;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class AdminMainFrm extends JFrame {

	private JPanel contentPane;
	public static Admin admin;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrm frame = new AdminMainFrm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminMainFrm(Admin admin) {
		this.admin = admin;
		setTitle("\u820D\u7BA1\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.activeCaption);
		
		JButton DelectStuButton = new JButton("\u5220\u9664\u642C\u79BB\u5B66\u751F");
		DelectStuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				DelStuFrm dsf = new DelStuFrm();
				dsf.setVisible(true);
				desktopPane.add(dsf);
			}
		});
		
		JButton ChangeStuButton = new JButton("\u5B66\u751F\u5BBF\u820D\u53D8\u52A8");
		ChangeStuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ChaStuFrm csf = new ChaStuFrm();
				csf.setVisible(true);
				desktopPane.add(csf);
			}
		});
		
		JButton StuSelectButton = new JButton("\u5B66\u751F\u4FE1\u606F\u67E5\u8BE2");
		StuSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelStuFrm ssf = new SelStuFrm();
				ssf.setVisible(true);
				desktopPane.add(ssf);
			}
		});
		
		JButton SelRoomButton = new JButton("\u623F\u95F4\u4FE1\u606F\u67E5\u8BE2");
		SelRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				RoomManageFrm rmf = new RoomManageFrm();
				rmf.setVisible(true);
				desktopPane.add(rmf);
			}
		});
		
		JButton AddStuButton = new JButton("\u6DFB\u52A0\u5165\u4F4F\u5B66\u751F");
		AddStuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStuFrm asf = new AddStuFrm();
				asf.setVisible(true);
				desktopPane.add(asf);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(SelRoomButton, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
							.addGap(63)
							.addComponent(AddStuButton, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
							.addGap(61)
							.addComponent(DelectStuButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(63)
							.addComponent(ChangeStuButton, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
							.addGap(58)
							.addComponent(StuSelectButton, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ChangeStuButton)
						.addComponent(DelectStuButton)
						.addComponent(StuSelectButton)
						.addComponent(SelRoomButton)
						.addComponent(AddStuButton))
					.addGap(18)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
