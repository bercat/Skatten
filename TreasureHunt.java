package forelesning;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TreasureHunt extends JFrame implements ActionListener {
	private int dimX = 4, dimY = 4;
	private int secretX, secretY;	//skattens posisjon
	private JButton btnRetry;
	private JButton btnExit;
	private JButton[][] buttons;	//skatteknapper
	private JPanel pnlButtons;		//knappepanel
	private JPanel pnlTreasures;	//skattepanel

	public TreasureHunt() {
		setTitle("Skattejakt");
		
		pnlTreasures = new JPanel();
		pnlTreasures.setLayout(new GridLayout(dimX, dimY));
		
		buttons = new JButton[dimX][dimY];
		for (int i = 0; i < dimX; i++)
			for (int j = 0; j < dimY; j++) {
				buttons[i][j] = new JButton(" ? ");
				buttons[i][j].addActionListener(this);
				pnlTreasures.add(buttons[i][j]);
			}
		add(pnlTreasures, BorderLayout.CENTER);
		
		pnlButtons = new JPanel(new GridLayout(1, 2));
		btnRetry = new JButton("Prøv på nytt");
		btnExit = new JButton("Avslutt");
		btnRetry.addActionListener(this);
		btnExit.addActionListener(this);
		pnlButtons.add(btnRetry);
		pnlButtons.add(btnExit);
		add(pnlButtons, BorderLayout.SOUTH);
		
		secretX = (int) (Math.random() * dimX);
		secretY = (int) (Math.random() * dimY);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TreasureHunt();
	}

	public void actionPerformed(ActionEvent arg) {
		JButton source = (JButton) arg.getSource();
		if (source == btnExit)
			System.exit(0);
		if (source == btnRetry) {
			for (int i = 0; i < dimX; i++) {
				for (int j = 0; j < dimY; j++) {
					buttons[i][j].setText(" ? ");
					buttons[i][j].setEnabled(true);;
				}
			}
			secretX = (int) (Math.random() * dimX);
			secretY = (int) (Math.random() * dimY);
		} else {
			if (source != buttons[secretX][secretY]) {
				source.setText(":-(");
				source.setEnabled(false);
			} else {
				for (int i = 0; i < dimX; i++) {
					for (int j = 0; j < dimY; j++) {
						buttons[i][j].setText(" ");
						buttons[i][j].setEnabled(false);
					}
				}
				source.setText("$");
			}
		}
	}
}
