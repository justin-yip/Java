/*Justin Yip #7032870
 *Thomas Chapados Muermans #6775179
 *Assignment 4
 *Graphical User Interface, encrypt/decrypt text
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;


public class CipherGUI extends JFrame implements ActionListener,ItemListener {
	
	
	//Declare variables
	private JPanel myPanel;
	private JButton write;
	private JButton clear;
	private JTextArea input;
	private JTextArea translated;
	private JComboBox list = null;
	private String textInput;
	private Encrypt en = new Encrypt();
	private Decrypt de = new Decrypt();
	private DefaultComboBoxModel model = null;
	private String[] items = new String[4];
	private JRadioButton encrypt;
	private JRadioButton decrypt;
	private ButtonGroup rg;
	private Font f = new Font("Arial", Font.PLAIN, 12);
	private Font f2 = new Font("Serif", Font.PLAIN, 12);
	private JCheckBox numEn;
	
	private String encryptSelect = "Encrypt rot5";
	
	//Constructor that build gui
	public CipherGUI(){
		super("Rot-n Encryption/Decryption");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		items[0]="Encrypt rot5";
		items[1]="Encrypt rot7";
		items[2]="Encrypt rot13";
		items[3]="Encrypt rot17";
		model = new DefaultComboBoxModel(items);
		list = new JComboBox(model);
		
		myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
		myPanel.setOpaque(true);
		myPanel.setBackground(Color.GREEN);
		write = new JButton("Encrypt/Decrypt");
		write.setBackground(Color.RED);
		write.setForeground(Color.BLACK);
		clear = new JButton("          Clear         ");
		clear.setBackground(Color.YELLOW);
		clear.setForeground(Color.MAGENTA);
		input = new JTextArea(20, 20);
		input.setBackground(Color.BLACK);
		input.setText("Enter text here");
		input.setForeground(Color.YELLOW);
		input.setFont(f);
		translated = new JTextArea(20, 20);
		translated.setBackground(Color.PINK);
		translated.setForeground(Color.DARK_GRAY);
		translated.setText("Output here");
		translated.setFont(f2);
		encrypt = new JRadioButton("Encrypt");
		encrypt.setBackground(Color.GREEN);
		decrypt = new JRadioButton("Decrypt");
		decrypt.setBackground(Color.GREEN);
		rg = new ButtonGroup();
		rg.add(encrypt);
		rg.add(decrypt);
		numEn = new JCheckBox("Encrypt/Decrypt numbers");
		encrypt.setSelected(true);
	
		list.addItemListener(this);
		
		
		write.addActionListener(		//action listener that read input and converts text in different rot number
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						if(numEn.isSelected()){
							if(encrypt.isSelected()){
								if(encryptSelect.equals(items[0]))
									translated.setText(en.rotNum(5, input.getText()));
								else if(encryptSelect.equals(items[1]))
									translated.setText(en.rotNum(7, input.getText()));
								else if(encryptSelect.equals(items[2]))
									translated.setText(en.rotNum(13, input.getText()));
								else if(encryptSelect.equals(items[3]))
									translated.setText(en.rotNum(17, input.getText()));
							}
							else{
								if(encryptSelect.equals(items[0]))
									translated.setText(de.rotNum(5, input.getText()));
								else if(encryptSelect.equals(items[1]))
									translated.setText(de.rotNum(7, input.getText()));
								else if(encryptSelect.equals(items[2]))
									translated.setText(de.rotNum(13, input.getText()));
								else if(encryptSelect.equals(items[3]))
									translated.setText(de.rotNum(17, input.getText()));
							}
						}
						else if(!(numEn.isSelected())){
							if(encrypt.isSelected()){
								if(encryptSelect.equals(items[0]))
									translated.setText(en.rot(5, input.getText()));
								else if(encryptSelect.equals(items[1]))
									translated.setText(en.rot(7, input.getText()));
								else if(encryptSelect.equals(items[2]))
									translated.setText(en.rot(13, input.getText()));
								else if(encryptSelect.equals(items[3]))
									translated.setText(en.rot(17, input.getText()));
							}
							else{
								if(encryptSelect.equals(items[0]))
									translated.setText(de.rot(5, input.getText()));
								else if(encryptSelect.equals(items[1]))
									translated.setText(de.rot(7, input.getText()));
								else if(encryptSelect.equals(items[2]))
									translated.setText(de.rot(13, input.getText()));
								else if(encryptSelect.equals(items[3]))
									translated.setText(de.rot(17, input.getText()));
							}
							
						}
						
					}
					
				});
		
		clear.addActionListener(		//Action listener that clears text fields
				new ActionListener(){
				public void actionPerformed(ActionEvent e){
					input.setText("");
					translated.setText("");
		}});
		//Adds elements to JPanel
		
		myPanel.add(encrypt);
		myPanel.add(decrypt);
		myPanel.add(Box.createRigidArea(new Dimension(0,5)));
		myPanel.add(input);
		myPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		myPanel.add(list);
		myPanel.add(translated);
		myPanel.add(write);
		myPanel.add(clear);
		myPanel.add(numEn);
		//Adds JPanel to JFrame
		add(myPanel);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	//State change for the ComboBox
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED){
			encryptSelect = (String) list.getSelectedItem();
		}
	}
	
	//Main functions
	public static void main(String[] args) throws IOException{
		CipherGUI mygui = new CipherGUI();
		mygui.setVisible(true);
	}

}
