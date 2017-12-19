import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;

public class WindowApplication extends JFrame
{
	protected colorWindow colorBox;
	protected JLabel labelRed;
	protected JLabel labelGreen;
	protected JLabel labelBlue;

	protected JTextField dataRed;
	protected JTextField dataGreen;
	protected JTextField dataBlue;
	
	protected JButton plusRed;
	protected JButton minusRed;
	protected JButton plusGreen;
	protected JButton minusGreen;
	protected JButton plusBlue;
	protected JButton minusBlue;

	protected JButton saveButton;
	protected JButton resetButton;

	protected JList colorList;
	protected int colorListIndex;
	protected int actualLengthOfArray;

	protected static int tempRed;
	protected static int tempGreen;
	protected static int tempBlue;

	protected static String[] stringArray;
	protected static int[] redArray;
	protected static int[] greenArray;
	protected static int[] blueArray;

	public static void main(String argv []) throws IOException
	{
		new WindowApplication("Window Application");
	}
	class colorWindow extends JComponent
	{
		public void paint(Graphics g)
		{
			Dimension d = getSize();

			g.setColor(new Color(tempRed, tempGreen, tempBlue));
			g.fillRect(1, 1, d.width - 2, d.height - 2);
		}
	}

	public WindowApplication(String title) throws IOException
	{
		super(title);

		//IO STUFF
		stringArray = new String[100];
		redArray = new int[100];
		greenArray = new int[100];
		blueArray = new int[100];

		FileInputStream stream = new FileInputStream("colorData.txt");  
		InputStreamReader reader = new InputStreamReader(stream); 
		StreamTokenizer tokens = new StreamTokenizer(reader);
		String tempString;
		int	red;
		int green;
		int blue;
		int index = 0;

		while (tokens.nextToken() != tokens.TT_EOF) 
		{  
			tempString = (String) tokens.sval;
			stringArray[index] = tempString;
			tokens.nextToken();
			red = (int) tokens.nval;
			redArray[index] = red;
			tokens.nextToken();
			green = (int) tokens.nval;
			greenArray[index] = green;
			tokens.nextToken();
			blue = (int) tokens.nval;
			blueArray[index] = blue;
			actualLengthOfArray++;
			index++;
		} 
		stream.close();

		setBounds(100, 100, 400, 400);
		addWindowListener(new WindowDestroyer());

		colorBox = new colorWindow();  

		labelRed = new JLabel("Red:");
		dataRed = new JTextField("0");
		plusRed = new JButton("+");
		minusRed = new JButton("-");

		plusRed.addActionListener(new buttonPress());
		minusRed.addActionListener(new buttonPress());

		labelGreen = new JLabel("Green:");
		dataGreen = new JTextField("0");
		plusGreen = new JButton("+");
		minusGreen = new JButton("-");

		plusGreen.addActionListener(new buttonPress());
		minusGreen.addActionListener(new buttonPress());

		labelBlue = new JLabel("Blue:");
		dataBlue = new JTextField("0");
		plusBlue = new JButton("+");
		minusBlue = new JButton("-");

		plusBlue.addActionListener(new buttonPress());
		minusBlue.addActionListener(new buttonPress());

		saveButton = new JButton("Save");
		resetButton = new JButton("Reset");

		saveButton.addActionListener(new buttonPress());
		resetButton.addActionListener(new buttonPress());

		colorList = new JList();
        colorList.setListData(stringArray);  
        colorList.addListSelectionListener(new listAction());

        tempRed = Integer.parseInt(dataRed.getText().toString());
        tempGreen = Integer.parseInt(dataGreen.getText().toString());
        tempBlue  = Integer.parseInt(dataBlue.getText().toString());

		// let’s also specify the arrangement of components by hand
		getContentPane().setLayout(null);
		
		getContentPane().add(colorBox);

		getContentPane().add(labelRed);
		getContentPane().add(dataRed);
		getContentPane().add(plusRed);
		getContentPane().add(minusRed);

		getContentPane().add(labelGreen);
		getContentPane().add(dataGreen);
		getContentPane().add(plusGreen);
		getContentPane().add(minusGreen);		

		getContentPane().add(labelBlue);
		getContentPane().add(dataBlue);
		getContentPane().add(plusBlue);
		getContentPane().add(minusBlue);

		getContentPane().add(saveButton);
		getContentPane().add(resetButton);

		getContentPane().add(colorList); 

		colorBox.setBounds(10, 10, 270, 200);

		labelRed.setBounds(40, 220, 50, 30);
		dataRed.setBounds(90, 220, 50, 30);
		plusRed.setBounds(140, 220, 60, 30);
		minusRed.setBounds(200, 220, 60, 30);

		labelGreen.setBounds(40, 260, 50, 30);
		dataGreen.setBounds(90, 260, 50, 30);
		plusGreen.setBounds(140, 260, 60, 30);
		minusGreen.setBounds(200, 260, 60, 30);

		labelBlue.setBounds(40, 300, 50, 30);
		dataBlue.setBounds(90, 300, 50, 30);
		plusBlue.setBounds(140, 300, 60, 30);
		minusBlue.setBounds(200, 300, 60, 30);

		saveButton.setBounds(70, 340, 80, 30);
		resetButton.setBounds(160, 340, 80, 30);

		colorList.setBounds(300, 10, 80, 350); 

		setVisible(true);
	}

	private class listAction implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			if ( e.getSource() == colorList )
			{
				if ( !e.getValueIsAdjusting() )
				{
					colorListIndex = colorList.getSelectedIndex();
					tempRed = redArray[colorListIndex];
					tempGreen = greenArray[colorListIndex];
					tempBlue = blueArray[colorListIndex];

					dataRed.setText(Integer.toString(tempRed));
					dataGreen.setText(Integer.toString(tempGreen));
					dataBlue.setText(Integer.toString(tempBlue));

					colorBox.paint(colorBox.getGraphics());
				}
			}
		}
	}

	// Define action listener                                       
	private class buttonPress implements ActionListener 
	{      
		public void actionPerformed(ActionEvent e)
		{
			setTitle("Window Application*");

			//red
			if ( e.getSource() == plusRed )
			{
				tempRed += 5;
				dataRed.setText(Integer.toString(tempRed));
				if (Integer.parseInt(dataRed.getText().toString()) < 0)
					dataRed.setText("0");

				else if (Integer.parseInt(dataRed.getText().toString()) > 255)
					dataRed.setText("255");
			}
			else if ( e.getSource() == minusRed )
			{
				tempRed -= 5;
				dataRed.setText(Integer.toString(tempRed));
				if (Integer.parseInt(dataRed.getText().toString()) < 0)
					dataRed.setText("0");

				else if (Integer.parseInt(dataRed.getText().toString()) > 255)
					dataRed.setText("255");
			}
			//green
			else if ( e.getSource() == plusGreen )
			{
				tempGreen += 5;
				dataGreen.setText(Integer.toString(tempGreen));
				if (Integer.parseInt(dataGreen.getText().toString()) < 0)
					dataGreen.setText("0");

				else if (Integer.parseInt(dataGreen.getText().toString()) > 255)
					dataGreen.setText("255");
			}
			else if ( e.getSource() == minusGreen )
			{
				tempGreen -= 5;
				dataGreen.setText(Integer.toString(tempGreen));
				if (Integer.parseInt(dataGreen.getText().toString()) < 0)
					dataGreen.setText("0");

				else if (Integer.parseInt(dataGreen.getText().toString()) > 255)
					dataGreen.setText("255");
			}
			//blue
			else if ( e.getSource() == plusBlue )
			{
				tempBlue += 5;
				dataBlue.setText(Integer.toString(tempBlue));
				if (Integer.parseInt(dataBlue.getText().toString()) < 0)
					dataBlue.setText("0");

				else if (Integer.parseInt(dataBlue.getText().toString()) > 255)
					dataBlue.setText("255");
			}
			else if ( e.getSource() == minusBlue )
			{
				tempBlue -= 5;
				dataBlue.setText(Integer.toString(tempBlue));
				if (Integer.parseInt(dataBlue.getText().toString()) < 0)
					dataBlue.setText("0");

				else if (Integer.parseInt(dataBlue.getText().toString()) > 255)
					dataBlue.setText("255");
			}
			else if ( e.getSource() == saveButton )
			{
				setTitle("Window Application");
				tempRed = Integer.parseInt(dataRed.getText().toString());
				tempGreen = Integer.parseInt(dataGreen.getText().toString());
				tempBlue = Integer.parseInt(dataBlue.getText().toString());

				redArray[colorListIndex] = tempRed;
				greenArray[colorListIndex] = tempGreen;
				blueArray[colorListIndex] = tempBlue;
			}
			else if ( e.getSource() == resetButton )
			{
				setTitle("Window Application");
				tempRed = redArray[colorListIndex];
				tempGreen = greenArray[colorListIndex];
				tempBlue = blueArray[colorListIndex];

				dataRed.setText(Integer.toString(tempRed));
				dataGreen.setText(Integer.toString(tempGreen));
				dataBlue.setText(Integer.toString(tempBlue));
			}
			colorBox.paint(colorBox.getGraphics());
		}
	}                        

	//output
	public void outputBOI() throws IOException 
	{ 
		FileOutputStream ostream = new FileOutputStream("colorData.txt");
		PrintWriter writer = new PrintWriter(ostream);
		
		for (int i = 0; i < actualLengthOfArray; i++) 
		{  
			writer.println(stringArray[i] + " " + redArray[i] + " " + greenArray[i] + " " + blueArray[i]);  
		} 
		writer.flush();
		ostream.close();
	}

	// Define window adapter                                       
	private class WindowDestroyer extends WindowAdapter 
	{   
		public void windowClosing(WindowEvent e) 
		{    
			try
			{
				outputBOI();
			}
			catch(IOException blah)
			{

			}
			System.exit(0);  
		}                                                             
	}                                                              
}