package oop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Panel;

public class LandingPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnStart;
	private JLabel lblWelcome, lblTitle, lblSub;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LandingPage frame = new LandingPage();
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
	public LandingPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OSfinalsGUI().setVisible(true); //starts the ano, main gui
				dispose();   // closes this one
			}
		});
		
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStart.setForeground(new Color(0, 0, 0));
		btnStart.setBackground(new Color(152, 251, 152));
		btnStart.setBounds(160, 171, 109, 38);
		contentPane.add(btnStart);
		
		lblWelcome = new JLabel("Welcome to");
		lblWelcome.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblWelcome.setBounds(173, 28, 79, 18);
		contentPane.add(lblWelcome);
		
		lblTitle = new JLabel("Memory Allocation Visualizer");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(69, 59, 288, 49);
		contentPane.add(lblTitle);
		
		lblSub = new JLabel("First Fit & Best Fit Simulator");
		lblSub.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSub.setBounds(132, 126, 167, 14);
		contentPane.add(lblSub);

	}
}
