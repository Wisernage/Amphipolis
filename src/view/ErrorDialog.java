package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Toolkit;

import javax.swing.JButton;

public class ErrorDialog extends JFrame {
	private static final long serialVersionUID = 1L;
	public JFrame lastFrame;
	private JPanel contentPane;
	public JLabel ErrorMessage;
	public ErrorDialog() {
		super("Error");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/img/landslide.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
			
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 250);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		ErrorMessage = new JLabel("Invalid Input. Exiting...");
		ErrorMessage.setBounds(160, 20, 300, 100);
		panel.add(ErrorMessage);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(160, 200, 100, 30);
		panel.add(exitButton);
		
		
		setVisible(true);
		exitButton.addActionListener( (e)-> {
			System.exit(0);
		});
		
	}
}