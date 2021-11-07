package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTimeThread extends JDialog {
	private JPanel jPanel = new JPanel(new GridBagLayout());
	private JLabel descricaoHora1 = new JLabel("Thread #1"); 
	private JLabel descricaoHora2 = new JLabel("Thread #2");
	private JTextField mostraHora1 = new JTextField();
	private JTextField mostraHora2 = new JTextField();
	private JButton jButton1 = new JButton("Start");
	private JButton jButton2 = new JButton("Stop");
	
	private Runnable thread1 = new Runnable() {

		@Override
		public void run() {
			while(true){
				mostraHora1.setText(new SimpleDateFormat("dd/MM/yyyy hh:MM.s")
						.format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	};
	
	private Runnable thread2 = new Runnable() {

		@Override
		public void run() {
			while(true){
				mostraHora2.setText(new SimpleDateFormat("dd:mm:yyyy hh:MM.s")
						.format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	};
	
	
	
	private Thread thread1Time;
	private Thread thread2Time;
	
	public TelaTimeThread() {
		setTitle("Tela Time Thread");
		setSize(new DimensionUIResource(240, 240));
		setLocationRelativeTo(null);
		setResizable(false);
		// -----------------
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = 0;
		grid.gridy = 0;
		grid.gridwidth = 2;
		grid.anchor = grid.WEST;
		grid.insets = new Insets(5,10,5,5);
		// ------

		descricaoHora1.setPreferredSize(new DimensionUIResource(250, 25));
		jPanel.add(descricaoHora1, grid);
		//------
		mostraHora1.setPreferredSize(new DimensionUIResource(250, 25));
		mostraHora1.setEditable(false);
		grid.gridy ++;
		jPanel.add(mostraHora1, grid);
		// ----------------------
		descricaoHora2.setPreferredSize(new DimensionUIResource(250,  25));
		grid.gridy ++;
		jPanel.add(descricaoHora2, grid);
		// -----------------------------
		mostraHora2.setPreferredSize(new DimensionUIResource(250, 25));
		mostraHora2.setEditable(false);
		grid.gridy ++;
		jPanel.add(mostraHora2, grid);
		// --======================
		grid.gridwidth = 1;
		grid.gridy ++;
		jButton1.setPreferredSize(new DimensionUIResource(92,25));
		jPanel.add(jButton1, grid);
		// _____________________
		jButton2.setPreferredSize(new DimensionUIResource(92, 25));
		grid.gridx ++;
		jPanel.add(jButton2, grid);,
		
		jButton2.setEnabled(false);
		// -----------------
		jButton1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				thread1Time = new Thread(thread1);
				thread1Time.start();
				thread2Time = new Thread(thread2);
				thread2Time.start();
				jButton1.setEnabled(false);
				jButton2.setEnabled(true);
				
			}
			
		});
		// ----------------------
		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thread1Time.stop();
				thread2Time.stop();
				jButton1.setEnabled(true);
				jButton2.setEnabled(false);
					
			}
			
		});
		
		// --------------------------------
		
		this.add(jPanel, BorderLayout.WEST);

		setVisible(true);
	}
	
	
	
	
}
