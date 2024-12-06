package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Toolkit;

import javax.swing.JButton;

public class EndDialog extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel Score1, Score2, Score3, Score4;
	public EndDialog() {
		super("Score Menu");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/img/professor.png")));
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
		
		setScore1(new JLabel(""));
		getScore1().setBounds(160, 20, 300, 30);
		panel.add(getScore1());
		
		setScore2(new JLabel(""));
		getScore2().setBounds(160, 60, 300, 30);
		panel.add(getScore2());
		
		setScore3(new JLabel(""));
		getScore3().setBounds(160, 100, 300, 30);
		panel.add(getScore3());
		
		setScore4(new JLabel(""));
		getScore4().setBounds(160, 140, 300, 30);
		panel.add(getScore4());
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(160, 200, 100, 30);
		panel.add(exitButton);
		
		setVisible(true);
		exitButton.addActionListener( (e)-> {
			System.exit(0);
		});
		
	}
	public JLabel getScore1() {
		return Score1;
	}
	public void setScore1(JLabel score1) {
		Score1 = score1;
	}
	public JLabel getScore2() {
		return Score2;
	}
	public void setScore2(JLabel score2) {
		Score2 = score2;
	}
	public JLabel getScore3() {
		return Score3;
	}
	public void setScore3(JLabel score3) {
		Score3 = score3;
	}
	public JLabel getScore4() {
		return Score4;
	}
	public void setScore4(JLabel score4) {
		Score4 = score4;
	}
}
