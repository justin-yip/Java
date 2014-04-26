import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class calculator extends JFrame{
	private JPanel mainPanel, panelB;
	private JButton addB,subB,mulB,divB,equal,AC;
	private JTextArea input;
	private JLabel screen;
	private static double totalNum;
	private static String totalScreen = "";
	private static String operator = "";
		
	public gui(){
		super("Calculator");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		
		panelB = new JPanel();
		mainPanel = new JPanel();
		screen = new JLabel("Make a calculation");
		addB = new JButton("+");
		subB = new JButton("-");
		mulB = new JButton("x");
		divB = new JButton("/");
		equal = new JButton("=");
		AC = new JButton("AC");
		input = new JTextArea(2,25);
		
		
		
		addB.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						double resultInt = stringToInt();
						operation(resultInt);
						String resultStr = resultInt+"";
						totalScreen = totalScreen+resultStr+" + ";
						screen.setText(totalScreen);
						input.setText("");
						operator = "+";
					}
				});
		subB.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						double resultInt = stringToInt();
						operation(resultInt);
						String resultStr = resultInt+"";
						totalScreen = totalScreen+resultStr+" - ";
						screen.setText(totalScreen);
						input.setText("");
						operator = "-";
					}
				});
		mulB.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						double resultInt = stringToInt();
						operation(resultInt);
						String resultStr = resultInt+"";
						totalScreen = totalScreen+resultStr+" x ";
						screen.setText(totalScreen);
						input.setText("");
						operator = "x";
					}
				});
		divB.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						double resultInt = stringToInt();
						operation(resultInt);
						String resultStr = resultInt+"";
						totalScreen = totalScreen+resultStr+" / ";
						screen.setText(totalScreen);
						input.setText("");
						operator = "/";
					}
				});
		equal.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						double resultInt = stringToInt();
						operation(resultInt);
						totalScreen = totalNum+"";
						screen.setText("ans "+totalScreen);
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
						screen.setText("Make a calculation");
						input.setText("");
					}
				});
		
		calLayout();
		
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
		panelB.add(addB);
		panelB.add(subB);
		panelB.add(mulB);
		panelB.add(divB);
		panelB.add(equal);
		panelB.add(AC);
		
		mainPanel.add(screen);
		mainPanel.add(input);
		mainPanel.add(panelB);
		add(mainPanel);
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
		calculator Cal  = new calculator();
		Cal.setVisible(true);
	}
}
