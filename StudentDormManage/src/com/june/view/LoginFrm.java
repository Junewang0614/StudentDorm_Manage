package com.june.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.june.dao.AdminDao;
import com.june.dao.StuUserDao;
import com.june.model.Admin;
import com.june.model.StuUser;
import com.june.model.userType;
import com.june.util.Stringutil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JComboBox userTypeComboBox;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
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
	public LoginFrm() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u5BBF\u820D\u7BA1\u7406\u7CFB\u7EDF\u767B\u5F55\u754C\u9762");
		lblNewLabel.setBounds(173, 46, 310, 124);
		lblNewLabel.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 24));
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D");
		lblNewLabel_1.setBounds(205, 160, 57, 72);
		lblNewLabel_1.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 18));
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801");
		lblNewLabel_2.setBounds(214, 237, 36, 37);
		lblNewLabel_2.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 18));
		
		userNameTextField = new JTextField();
		userNameTextField.setBounds(311, 186, 142, 24);
		userNameTextField.setColumns(10);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginAct(arg0);
			}
		});
		loginButton.setBounds(205, 364, 92, 53);
		loginButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetValue();
			}
		});
		resetButton.setBounds(351, 364, 92, 53);
		resetButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_3 = new JLabel("\u7528\u6237\u7C7B\u578B");
		lblNewLabel_3.setBounds(205, 295, 72, 37);
		lblNewLabel_3.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 18));
		
		userTypeComboBox = new JComboBox();
		userTypeComboBox.setBounds(311, 303, 142, 24);
		userTypeComboBox.setModel(new DefaultComboBoxModel(new userType[]{userType.STUDENT,userType.ADMIN}));
		contentPane.setLayout(null);
		contentPane.add(loginButton);
		contentPane.add(resetButton);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_3);
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel_1);
		contentPane.add(userNameTextField);
		contentPane.add(userTypeComboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(311, 245, 142, 29);
		contentPane.add(passwordField);
	}

	protected void loginAct(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String username = userNameTextField.getText().toString();
		String password = passwordField.getText();
		//System.out.println(password);
		userType selectedItem = (userType)userTypeComboBox.getSelectedItem();
		if(Stringutil.isEmpty(username)) {
			JOptionPane.showMessageDialog(this, "用户名不能为空");
			return;
		}
		if(Stringutil.isEmpty(password)) {
			JOptionPane.showMessageDialog(this, "密码不能为空");
			return;
		}
		
		if("学生".equals(selectedItem.getName())) {
			//学生登录
			StuUser stutmp = new StuUser();
			StuUserDao stuDao = new StuUserDao();
			stutmp.setName(username);
			stutmp.setPassword(password);
			StuUser student = null;
			student = stuDao.login(stutmp);
			if(student == null) {
				JOptionPane.showMessageDialog(this,"用户名或密码错误!");
				return;
			}
			this.dispose();
			JOptionPane.showMessageDialog(this, "欢迎【" + selectedItem.getName()+"】:"+student.getName()+"登录本系统");
			new studentMainFrm(student).setVisible(true);
			stuDao.closeDao();
			
		}
		else {
			//舍管登录
			AdminDao adminDao = new AdminDao();
			Admin admintmp = new Admin();
			admintmp.setName(username);
			admintmp.setPassword(password);
			Admin admin = null;
			admin = adminDao.login(admintmp);
			if(admin == null)//没有用户
			{
				JOptionPane.showMessageDialog(this,"用户名或密码错误!");
				return;
			}
			this.dispose();//隐藏界面
			//欢迎界面
			JOptionPane.showMessageDialog(this, "欢迎【" + selectedItem.getName()+"】:"+admin.getName()+"登录本系统");
			new AdminMainFrm(admin).setVisible(true);
			adminDao.closeDao();
		}
		
	}

	protected void resetValue() {
		// TODO Auto-generated method stub
		userNameTextField.setText("");
		passwordField.setText("");
		userTypeComboBox.setSelectedIndex(0);
	}
}
