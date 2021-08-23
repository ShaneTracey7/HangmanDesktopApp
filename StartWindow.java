package swingHangman;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StartWindow implements Styles
{
	public static String word1;  //used to be static
	public static String genre;
	public static String filename;  // needed for arrays
	public static  URL filename1;	// needed for jar file
	public static  File filename2;  //
	
	
	//major revisions (making class methods non-static)
	
	public StartWindow() {
		
	}
	
	public static void startup (JFrame frame)
	{
		
		JFrame frame2 = new JFrame("Hangman");
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setSize(300, 300);
		frame2.setLocationRelativeTo(null); // puts frame in center of screen upon opening
		frame2.setResizable(false); 
		JPanel panel = new JPanel();
		panel .setLayout(null);
		frame2.add(panel);
		panel.setBackground(Color.darkGray);
		
		
		
		JLabel label = new JLabel("  Gameplay Settings");
		label.setBounds(70,20,160, 40);
		label.setForeground(Color.white);
		label.setFont(font1);
		label.setBorder(border);
			
		JLabel label2 = new JLabel("Select option:");
		label2.setBounds(10,115,100, 25);
		label2.setForeground(Color.white);
		label2.setFont(font2);
		
		JLabel label3 = new JLabel("Select wordlist:");
		label3.setBounds(10,165,100, 25);
		label3.setForeground(Color.white);
		label3.setFont(font2);
		label3.setVisible(false);
		
		JLabel label4 = new JLabel("Enter Word:");
		label4.setBounds(10,165,100, 25);
		label4.setForeground(Color.white);
		label4.setFont(font2);
		label4.setVisible(true);
		
		JButton button = new JButton("Start Game");
		button.setBounds(90,210,120, 30);
		button.setForeground(Color.black);
		
		JButton buttonE = new JButton("Exit");   //new
		buttonE.setBounds(100,240,100, 30);
		buttonE.setForeground(Color.black);
		
		
		String [] wordlists = {" ","animals", "verbs", "all words"};
		JComboBox wlists = new JComboBox(wordlists);
		wlists.setBounds(110,165,150,25);
		wlists.setVisible(false);
		
		String [] options = {" ","random word", "enter word"};
		JComboBox option = new JComboBox(options);
		option.setBounds(110,115,150,25);
		
		
		JTextField ue_word = new JTextField();
		ue_word.setBounds(110,165,150,25);
		ue_word.setFont(font2);
		ue_word.setBorder(border2);
		ue_word.setVisible(true);
		
		panel.add(label);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(button);
		panel.add(buttonE);  //new
		panel.add(ue_word);
		panel.add(wlists);
		panel.add(option);
		frame2.setVisible(true);
		
		//new
		buttonE.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		
		
		//new
		
		
		
		
		
		
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("entered actionlistenr");
				Object cb1 = option.getSelectedItem();
				cb1.toString();
				if(cb1 == "random word")
				{
					option.setEnabled(false);
					ue_word.setVisible(false);
					label4.setVisible(false);
					wlists.setVisible(true);
					label3.setVisible(true);
					Object cb2 = wlists.getSelectedItem();
					cb2.toString();
					if(cb2 == "animals")
					{
						//labelwl.setText("Wordlist: Animals");
						genre = "Wordlist: Animals";
						
						frame.setVisible(true);
						filename = "resources/animals.txt";
						filename1 = StartWindow.class.getResource("/animals.txt");
						try {
							URI fnw = filename1.toURI();
							filename2 = new File(fnw);
						} catch (URISyntaxException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						try {
							word1 = goodword(filename);
							System.out.println(word1);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							System.out.println("filenotfound");
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("io");
							e1.printStackTrace();
						}
						
						frame2.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
					else if(cb2 == "verbs")
					{
						//labelwl.setText("Wordlist: Verbs");
						genre = "Wordlist: Verbs";
						
						frame.setVisible(true);
						filename = "resources/verbs.txt";
						filename1 = StartWindow.class.getResource("/verbs.txt");
						try {
							URI fnw = filename1.toURI();
							filename2 = new File(fnw);
						} catch (URISyntaxException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						
						try {
							word1 = goodword(filename); //og = filename
							System.out.println(word1);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							System.out.println("filenotfound");
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch bloc
							System.out.println("io");
							e1.printStackTrace();
						}
						
						frame2.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
					else if(cb2 == "all words")
					{
						//labelwl.setText("Wordlist: All Words");
						genre = "Wordlist: All Words";
						
						frame.setVisible(true);
						filename = "resources/usa.txt";
						filename1 = StartWindow.class.getResource("/usa.txt");
						try {
							URI fnw = filename1.toURI();
							filename2 = new File(fnw);
						} catch (URISyntaxException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						try {
							word1 = goodword(filename);
							System.out.println(word1);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							System.out.println("filenotfound");
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("io");
							e1.printStackTrace();
						}
						
						frame2.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
					else
					{
						// do nothing since blank
					}
				}
				else if (cb1 == "enter word") 
				{
					//everything freezes if u dont enter word along with selection
					System.out.println("entered word");
					option.setEnabled(false);
					label4.setVisible(true);
					wlists.setVisible(false);
					ue_word.setVisible(true);
					ue_word.setEnabled(true);
					
					//genre = "User entered";
					
					
					word1 = ue_word.getText();
					while(word1.length() == 0)  // empty jtextfield
					{
					// do nothing while 
						}
					System.out.println(word1);
					frame.setVisible(true);
					
					frame2.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
				else
				{
					// do nothing since blank
				}
				
			}
			
		} 
		);
		
		
		/*
		panel.add(label);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(button);
		panel.add(ue_word);
		panel.add(wlists);
		panel.add(option);
		frame2.setVisible(true);
		*/
		//System.out.println(word);
		
		
	}
	
	
	
	
	
	
	
//methods
	
	public static String goodword(String filename) throws FileNotFoundException, IOException
	{
		ArrayList<String> wordlist = new ArrayList<>();
		//loading file to arraylist function
		wordlist = wordlist(filename, wordlist);
		//random word function
		String word = randomW(wordlist);
		return word;
	}
	
	/*
	public String goodword(String filename) throws FileNotFoundException, IOException
	{
		ArrayList<String> wordlist = new ArrayList<>();
		//loading file to arraylist function
		wordlist = wordlist1(filename, wordlist);
		//random word function
		String word = randomW(wordlist);
		return word;
	}
	*/
	
	public static ArrayList<String> wordlist (String filename, ArrayList<String> fwordlist) throws FileNotFoundException, IOException
	{
		
		 
		try (FileReader f = new FileReader(filename)) {
		    StringBuffer sb = new StringBuffer();
		    while (f.ready()) {
		        char c = (char) f.read();
		        if (c == '\n') {
		            fwordlist.add(sb.toString());
		            sb = new StringBuffer();
		        } else {
		            sb.append(c);
		        }
		    }
		    if (sb.length() > 0) {
		        fwordlist.add(sb.toString());
		    }
		}       
		return fwordlist;
	}
	
	/*
	public ArrayList<String> wordlist1 (String filename, ArrayList<String> fwordlist)
			throws IOException
			{
			  InputStream is = getClass().getResourceAsStream(filename);
			  InputStreamReader isr = new InputStreamReader(is);
			  BufferedReader br = new BufferedReader(isr);
			  StringBuffer sb = new StringBuffer();
			  String line;
			  while ((line = br.readLine()) != null) 
			  {
			    sb.append(line);
			    fwordlist.add(sb.toString());
			  }
			  br.close();
			  isr.close();
			  is.close();
			  return fwordlist;
			}
			*/
	
	//was static
	public static String randomW (ArrayList<String> wordlist)
	{
		int range = wordlist.size() - 1;
		System.out.println(range);   //prints out number of words in list
		Random n = new Random();
		int num = n.nextInt(range);    //range for random number is 0 (inclusive) to num (exclusive)
		String word = wordlist.get(num);
		return word;
	}
	
	
}
