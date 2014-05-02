import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class calculator extends JFrame implements ActionListener{
	private JPanel mainPanel, panel, screens;
	private JButton addB,subB,mulB,divB,equal,AC,b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bSign,bDec,bDel,bPi;
	private JLabel screen, input;
	private static double totalNum = 0;
	private static String totalScreen = "";
	private static String operator = "";
	private String empty = " 0";
		
	public calculator(){
		super("calculator");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		mainPanel = new JPanel();
		screens = new JPanel();
		panel = new JPanel();
		screen = new JLabel("");
		input = new JLabel(empty);
		addB = new JButton("+");
		subB = new JButton("-");
		mulB = new JButton("x");
		divB = new JButton("/");
		equal = new JButton("=");
		bDel = new JButton("Del");
		bSign = new JButton("+/-");
		bDec = new JButton(".");
		bPi = new JButton("pi");
		AC = new JButton("AC");
		b0 = new JButton("0");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		
		mainPanel.setLayout(new BorderLayout());
		panel.setLayout(new GridLayout(5,4,6,6));
		screens.setLayout(new GridLayout(2,1));
		
		screen.setOpaque(true);														//add menu -> change color of calculator
		input.setOpaque(true);														//add vertical scroll to screen
		panel.setBackground(Color.lightGray);
		screen.setBackground(Color.lightGray);
		input.setBackground(Color.lightGray);
		screen.setPreferredSize(new Dimension(0, 30));
		
		input.setHorizontalAlignment(SwingConstants.RIGHT);
		screen.setHorizontalAlignment(SwingConstants.RIGHT);
		//input.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//screen.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				
		addB.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						actionOperator("+");
					}
				});
		subB.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						actionOperator("-");
					}
				});
		mulB.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						actionOperator("x");
					}
				});
		divB.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						actionOperator("/");
					}
				});
		equal.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						double resultInt = stringToInt();
						operation(resultInt);
						if(totalNum%1==0)
							totalScreen = (int)totalNum+"";
						else
							totalScreen = totalNum+"";
						screen.setText("Answer:");
						input.setText(totalScreen);
						operator = "";
						totalNum = 0;
						totalScreen = "";
					}
				});
		AC.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						totalNum = 0;
						totalScreen = "";
						screen.setText("");
						input.setText(empty);
					}
				});
		bPi.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						input.setText(Math.PI+"");
					}
				});
		bSign.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(input.getText().charAt(0)=='-'){				
							String positive = input.getText().substring(1);
							input.setText(positive);										
						}else{										
							input.setText("-"+input.getText());		
						}
					}
				});
		bDec.addActionListener(											
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(!input.getText().contains(".")){
							if(input.getText().equals(empty)){
								input.setText("0.");
							}else
								input.setText(input.getText()+".");
						}
					}
				});
		bDel.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(input.getText().length()>1){
							String del = input.getText().substring(0,input.getText().length()-1);
							input.setText(del);
						}else
							input.setText(empty);
					}
				});
		calLayout();
		
		
	}
	public void actionPerformed(ActionEvent e){
		String value = ((JButton) e.getSource()).getActionCommand();
		if(input.getText().equals(empty))
			input.setText(value);
		else if((input.getText().equals("0")||input.getText().equals(empty))&&value.equals("0"))
			input.setText(value);
		else
			input.setText(input.getText()+value);	    
	}
	public void actionOperator(String op){
		if(input.getText().equals(empty)){
			char in = ' ';
			if(totalScreen.length()>2)
				in = totalScreen.charAt(totalScreen.length()-2);
			if(in=='+'||in=='-'||in=='x'||in=='/'){
				totalScreen = totalScreen.substring(0,totalScreen.length()-3);
				totalScreen = totalScreen+" "+op+" ";
				if(in=='+'||in=='-')
					operation(0);
				if(in=='x'||in=='/')
					operation(1);
			}
			screen.setText(totalScreen);
			operator = op;
		}else{
			double resultNum = stringToInt();
			operation(resultNum);
			String resultStr;
			if(resultNum%1==0)
				resultStr = (int)resultNum+"";
			else
				resultStr = resultNum+"";
			totalScreen = totalScreen+resultStr+" "+op+" ";
			screen.setText(totalScreen);
			input.setText(empty);
			operator = op;
		}
	}
	public void operation(double toOp){		
		if(operator.equals("+"))
			totalNum += toOp;
		else if (operator.equals("-"))
			totalNum -= toOp;
		else if (operator.equals("x"))
			totalNum *= toOp;
		else if (operator.equals("/"))
			totalNum /= toOp;
		else if (operator.equals(""))
			totalNum = toOp;
		else
			System.out.print("operation doesnt exist");
	}
	public void calLayout(){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	
		panel.add(screen);
		panel.add(input);
		
		panel.add(AC,gbc);
		panel.add(bSign,gbc);
		panel.add(bDel,gbc);
		panel.add(divB,gbc);
		
		panel.add(b7,gbc);
		panel.add(b8,gbc);
		panel.add(b9,gbc);
		panel.add(mulB,gbc);

		panel.add(b4,gbc);
		panel.add(b5,gbc);
		panel.add(b6,gbc);
		panel.add(subB,gbc);
		
		panel.add(b1,gbc);
		panel.add(b2,gbc);
		panel.add(b3,gbc);
		panel.add(addB,gbc);
		
		panel.add(b0,gbc);
		panel.add(bDec,gbc);
		panel.add(bPi,gbc);
		panel.add(equal,gbc);
		
		screens.add(screen);
		screens.add(input);
		mainPanel.add(screens, BorderLayout.NORTH);
		mainPanel.add(panel, BorderLayout.CENTER);
		add(mainPanel);
		//add(panel);
	}
	public double stringToInt(){
		double numInput = 0;
		double position;
		String stringNum = input.getText();
		char[] charNum = stringNum.toCharArray();
		boolean dot = false;
		boolean negative = false;
		if(charNum[0]=='-')
			negative=true;
		int decimalAt = charNum.length;
		for(int j=0;j<charNum.length;j++){
			if(charNum[j]=='.')
				if(!dot){
					decimalAt=j;
					dot=true;
				}
				else
						System.out.println("impossible");									// NUMBER DOES NOT EXIST
		}
		int k;
		if(negative)
			k=1;
		else
			k=0;
		for(int i=k;i<charNum.length;i++){
			if(i<decimalAt){
				double digit = (int)charNum[i]-48;
				position = Math.pow(10,decimalAt-i-1);
				numInput += position*digit;	
			}else if(i>decimalAt){
				double digit = (int)charNum[i]-48;
				position = Math.pow(10,decimalAt-i);
				numInput += position*digit;	
			}
		}
		if(negative)
			numInput = 0-numInput;
		return numInput;
	}
	
	public static void main(String[] args) {
		calculator GUI  = new calculator();
		GUI.setVisible(true);
	}
}
