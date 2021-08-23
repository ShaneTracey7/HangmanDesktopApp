package swingHangman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.lang.String;
import java.net.URL;
import java.util.Scanner;

public class attempt implements ActionListener, Styles, WindowListener 
{
	//public static JTextField ue_word;
	//public static JLabel label2;
	public static JFrame frame;
	public static JFrame frame2;  //for the win/lose windows
	
	//letter guess n display
	public static JButton button;
	public static JTextField input;
	public static JTextField output;
	public static String spaces;
	public static JLabel status;
	
	//wrong letters
	public static JLabel wronglist;
	public static JLabel wltext;
	
	//word list files (used only in StartWindow class)
	static String filename1 = "resources/animals.txt";
	static String filename2 = "resources/usa.txt";
	static String filename3 = "resources/verbs.txt";
	
	//stuff for hangman images
	static JLabel noose;
	static JLabel head;
	static JLabel torso;		
	static JLabel leftarm;
	static JLabel rightarm;
	static JLabel leftleg;
	static JLabel rightleg;
	
	//gameplay stuff
	public static String word;
	static int correctg;
	static int hangman;
	static int len;
	static char [] arr;
	public static String OGword;
	
	//stuff for password
	public static JButton button2;
	public static JButton button3;
	public static JLabel shownWord;
	public static String password = "1738";
	public static JPasswordField pwd;
	
	ArrayList<String> wlist = new ArrayList<String>(); //wrong letter guess list
	ArrayList<String> rlist = new ArrayList<String>(); //right letter guess list
	String special = "!@#$%^&*()[]{}<>-+=_,.//\';\\|:?";
	String numbers ="0123456789";
	
	static String filename;
	
	static Clip clip_playing;
	
	static String ding_sound = "resources/a_tone.wav";
	static String win_sound = "resources/congrats_HM.wav";
	static String lose_sound = "resources/badday_HM.wav";
	
	
	public attempt() 
	{
		//does nothing
	}
	
	public static void main(String [] args)  throws FileNotFoundException, IOException 
	{
		setup(); // does the setup for the game
				
	}

	//response to an action being performed
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String s = e.getActionCommand();
		if(s =="Enter")
		{
			String lguess = input.getText();
			//char cguess = lguess.charAt(0);
			CharSequence cguess = lguess;
			
			if (lguess.length() == 0)  //nothing entered
			{
				status.setVisible(true);
				status.setText("Forgot the letter ya goof!");
				input.setText("");
			}
			else if(lguess.length() > 1) // too many charaters
			{
				status.setVisible(true);
				status.setText("Only one character... stooopid!");
				input.setText("");
			}
			else if(numbers.contains(cguess)) //numbers entered
			{
				status.setVisible(true);
				status.setText("Letters ... not numbers bozo!");
				input.setText("");
			}
			else if(special.contains(cguess)) //special characters entered
			{
				status.setVisible(true);
				status.setText("What planet are you on?");
				input.setText("");
			}
			
			else 
			{
			lguess = lguess.toUpperCase();
			//char guess = str.charAt(0); //string to char conversion
			
			
			int index = word.indexOf(lguess);
			 if(index >= 0)  //correct guess
			 {	 
				 status.setVisible(false);
				 do
			 	 {
				 playSound(ding_sound);
             
				 char letter = lguess.charAt(0);  // str to char
				 char[] arr2 = word.toCharArray();
				 arr2 [index] = '_';       // changes selected letter to '-'
				 word = new String(arr2);
				 arr[index*2] = letter;
				 correctg++;
				 index = word.indexOf(lguess);
			 	 }while(index >= 0);
			 	 
			     rlist.add(lguess + " "); //add to right letter list
			 		
			 
				 if(correctg == len) 
				 {
					 System.out.println(arr);
					 System.out.println("Congratulations... You have won the game!!");
					// playSound(win_sound);
					 URL url8 = attempt.class.getResource("/posty.jpg");
					 basicFrame("Congrats","You have won!",url8 , win_sound);
					 
					 // new
					 
					 // new
				
				 }
			 }
			 else
			 {
				 if(wlist.contains(lguess + " ") || rlist.contains(lguess + " "))
				 {
					 status.setVisible(true);
					 status.setText("Dumb dumb ... double guess!");
					 System.out.println("Dumb dumb ... double guess!");
				 }
				 else
				 {
				status.setVisible(false);
				 System.out.println("That letter isn't in the word!");
			 	 hangman -= 1;
			 	dspHM(hangman);
			 	wlist.add(lguess + " ");  // adds letter to wrong letter list
			 	if(hangman == 0)
			 	{
			 	System.out.println("Uh oh! You lost!\n The word was: ");// + OGword);
			 	//playSound(lose_sound);
			 	URL url9 = attempt.class.getResource("/sad_posty.png");
			 	basicFrame("Loser!!!","The word was " + OGword,url9, lose_sound);
			 	
			 // new 
			 	
				 // new
			 	
			 	
			 	
			 	}
				}
			 }
			 System.out.println(arr);
			 System.out.println("Wrong letters: " + wlist);
			
			wronglist.setText(new String("" + wlist));
		
		
			 String clear ="";
			 input.setText(clear);
			 spaces = new String(arr);
			output.setText(spaces);
		
		}
		}
		else if(s == "Check")
		{
			char[] guess = pwd.getPassword();
			String g = new String(guess);
			System.out.println(g);
			if(g.equals(password))  //correct password
			{
				shownWord.setText(OGword);
				shownWord.setVisible(true);
				pwd.setText("");
			}
			else
			{
				//incorrect password
				pwd.setText("");
			}
			
		}
		else if(s == "Clear")
		{	
			shownWord.setVisible(false);
				//clears word
		}
		else
		{
			//do nothing
		}
		
	}
	
	//returns char array to keep track of letters entered
		public static char[] set  ()
		{
			String word5 = word.concat(word); // doubles size of string
			int len2 = word5.length();
			char[] arr = word5.toCharArray();
			for(int count = 0;count<len2; count+=2)
			{
				arr[count] = '_';
				arr[count+1] = ' ';
			}
			return arr;
		}
	
	//displays correct amount of limbs on hangman throught game
	public static void dspHM (int count)
	{
		noose.setVisible(true);
		switch(count)
		{
		case 0:
		{
			head.setVisible(false);
			torso.setVisible(false);
			leftarm.setVisible(false);
			rightarm.setVisible(false);
			leftleg.setVisible(false);
			rightleg.setVisible(false);
		
		break;
		}
		case 1:
		{
			head.setVisible(true);
			torso.setVisible(false);
			leftarm.setVisible(false);
			rightarm.setVisible(false);
			leftleg.setVisible(false);
			rightleg.setVisible(false);
			
		break;
		}
		case 2:
		{
			head.setVisible(true);
			torso.setVisible(true);
			leftarm.setVisible(false);
			rightarm.setVisible(false);
			leftleg.setVisible(false);
			rightleg.setVisible(false);
			
		break;
		}
		case 3:
		{
			head.setVisible(true);
			torso.setVisible(true);
			leftarm.setVisible(true);
			rightarm.setVisible(true);
			leftleg.setVisible(false);
			rightleg.setVisible(false);
			
		break;
		}
		case 4:
		{
			head.setVisible(true);
			torso.setVisible(true);
			leftarm.setVisible(true);
			rightarm.setVisible(true);
			leftleg.setVisible(true);
			rightleg.setVisible(false);
		
			
		break;
		}
		case 5:
		{
			head.setVisible(true);
			torso.setVisible(true);
			leftarm.setVisible(true);
			rightarm.setVisible(true);
			leftleg.setVisible(true);
			rightleg.setVisible(true);
		
			
		break;
		}  
		}
	}
	
	//gets random word from selected wordlist file
	public static String goodword(String filename) throws FileNotFoundException, IOException
	{
		ArrayList<String> wordlist = new ArrayList<>();
		wordlist = wordlist(filename, wordlist); //loading file to arraylist function
		String word = randomW(wordlist); //random word function
		return word;
	}
	
	//adds all word from wordlist file to Arraylist
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
	
	//gets random word from arraylist
	public static String randomW (ArrayList<String> wordlist)
	{
		int range = wordlist.size() - 1;
		System.out.println(range);
		Random n = new Random();
		int num = n.nextInt(range);    //range for random number is 0 (inclusive) to num (exclusive)
		String word = wordlist.get(num);
		return word;
	}
	
	//creates basic frame used for end of game
	public static void basicFrame(String str, String str2, URL image, String song)
	{
		frame2 = new JFrame("HangMan");
		frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  // EXIT V. DISPOSE
		frame2.setSize(300, 200);
		// puts frame in center of screen upon opening
		frame2.setLocationRelativeTo(null);
		frame2.setResizable(false); 
		JPanel panel = new JPanel();
		panel.setLayout(null);  //absolute positioning
		frame2.add(panel);
		
		JLabel label = new JLabel(str);
		label.setBounds(25,10,250,50);
		label.setFont(font4);
		label.setBorder(border);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.white);
		
		JLabel label2 = new JLabel(str2);
		label2.setBounds(10,100,290,50);
		label2.setFont(font3);
		label2.setForeground(Color.white);
		label2.setHorizontalAlignment(JLabel.CENTER);
		
		ImageIcon pic = new ImageIcon(image);
		JLabel picture = new JLabel(pic);
		picture.setBounds(30,10,240,160);
		
		
		panel.setBackground(Color.MAGENTA);
		
		panel.add(label);
		panel.add(label2);
		panel.add(picture);
		frame2.setVisible(true);
		
		playSound(song);
		
		frame2.addWindowListener( new WindowListener() {
			
			@Override 
		    public void windowClosing(WindowEvent e) 
			{
				clip_playing.stop(); // stops music from playing on closing of window
				//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); //closes main frame
				setup();
				
			}

			@Override 
		    public void windowOpened(WindowEvent e) {
				
				frame.dispose();  //new
				word = null; // new
			}

		    @Override 
		    public void windowClosed(WindowEvent e) {}

		    @Override 
		    public void windowIconified(WindowEvent e) {}

		    @Override 
		    public void windowDeiconified(WindowEvent e) {}

		    @Override 
		    public void windowActivated(WindowEvent e) {}

		    @Override 
		    public void windowDeactivated(WindowEvent e) {}
			
		});
		
		
	}
	
	//plays any .wav file 
	public static void playSound(String name)
	{
		try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(name).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip_playing =  clip;
            
        }catch(Exception x) 
		 { 
       	 x.printStackTrace(); 
        }
	}
	
	
	
	
	//new  not necessary
	
	@Override 
    public void windowClosing(WindowEvent e) {}

	@Override 
    public void windowOpened(WindowEvent e) {}

    @Override 
    public void windowClosed(WindowEvent e) {}

    @Override 
    public void windowIconified(WindowEvent e) {}

    @Override 
    public void windowDeiconified(WindowEvent e) {}

    @Override 
    public void windowActivated(WindowEvent e) {}

    @Override 
    public void windowDeactivated(WindowEvent e) {}
    
	//new
	
    public static void setup() {
  
  		filename = "resources/animals.txt";
  		hangman = 5;  // hangman parts counter    		       
  		correctg = 0; // correct guesses counter
  	
  		frame = new JFrame("HangMan");
  		frame.setVisible(false);
  		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		frame.setSize(900, 600);
  		frame.setLocationRelativeTo(null); // puts frame in center of screen upon opening
  		frame.setResizable(false); 
  		JPanel panel = new JPanel();
  		panel.setLayout(null);  //absolute positioning
  		frame.add(panel);
  		
  		// Title and style
  		JLabel label = new JLabel ("HangMan");
  		label.setFont(font10);
  		label.setBounds(300,10,300,110);
  		label.setForeground(Color.green);
  		label.setBorder(border2);
  		
  		JLabel signature = new JLabel ("a Shane Tracey creation");
  		signature.setBounds(750,10,150,30);
  		//
  		signature.setFont(font8);
  		signature.setForeground(Color.black);
  		
  		//wrong letter area
  		wltext = new JLabel ("Wrong Letters:");
  		wltext.setFont(font9);  
  		wltext.setBounds(675,310,150,30);
  		wltext.setForeground(Color.white);
  		
  		wronglist = new JLabel ();
  		wronglist.setFont(font7);
  		wronglist.setBounds(630,350,240,50);
  		wronglist.setForeground(Color.white);
  		wronglist.setBorder(border2);
  		
  		//letter guess and display area
  		JLabel labelb = new JLabel ("Enter letter guess:");
  		labelb.setBounds(10,250,250,30);
  		labelb.setFont(font7);
  		labelb.setForeground(Color.white);
  		
  		input = new JTextField();
  		input.setBounds(70,300,80,80);
  		input.setFont(font6);
  		input.setBorder(border2);
  		input.requestFocus();
  		
  		button = new JButton("Enter");
  		button.setBounds(85,400,50,20);
  		
  		status = new JLabel();
  		status.setBounds(175,310,200,30);
  		status.setVisible(false);
  		status.setBorder(border3);
  		status.setForeground(Color.yellow);
  		status.setFont(font8);
  		status.setHorizontalAlignment(JTextField.CENTER);
  		
  		output = new JTextField();
  		output.setBounds(150,450,600,100);
  		output.setFont(font5);
  		output.setBorder(border);
  		output.setBackground(Color.gray);
  		output.setForeground(Color.white);
  		output.setHorizontalAlignment(JTextField.CENTER);
  		output.setEditable(false);
  		
  		attempt a = new attempt();
  		
  		//hangman images
  		URL url1 = attempt.class.getResource("/noose.png");
  		ImageIcon n = new ImageIcon(url1);
  		noose = new JLabel(n);
  		noose.setBounds(250,75,533,400);
  		
  		URL url2 = attempt.class.getResource("/head.png"); //necessary for JAR File
  		ImageIcon h = new ImageIcon(url2);
  		head = new JLabel(h);
  		head.setBounds(425,180,50,50);
  		head.setVisible(false);
  		
  		URL url3 = attempt.class.getResource("/torso.png");
  		ImageIcon t = new ImageIcon(url3);
  		torso = new JLabel(t);
  		torso.setBounds(425,230,50,90);
  	
  		URL url4 = attempt.class.getResource("/arm.png");
  		ImageIcon lar = new ImageIcon(url4);
  		leftarm = new JLabel(lar);
  		leftarm.setBounds(400,230,14,80);
  		
  		URL url5 = attempt.class.getResource("/arm.png");
  		ImageIcon rar = new ImageIcon(url5);
  		rightarm = new JLabel(rar);
  		rightarm.setBounds(485,230,14,80);
  		
  		URL url6 = attempt.class.getResource("/leftleg.png");
  		ImageIcon ll = new ImageIcon(url6);
  		leftleg = new JLabel(ll);
  		leftleg.setBounds(425,320,20,80);
  		
  		URL url7 = attempt.class.getResource("/rightleg.png");
  		ImageIcon rl = new ImageIcon(url7);
  		rightleg = new JLabel(rl);
  		rightleg.setBounds(455,320,20,80);
  		
  		//for password 
  		pwd = new JPasswordField();
  		pwd.setBounds(10,10,150,20);
  		
  		button2 = new JButton("Check");
  		button2.setBounds(20,50,50,20);
  		
  		button3 = new JButton("Clear");
  		button3.setBounds(80,50,50,20);
  		
  		shownWord = new JLabel ();
  		shownWord.setBounds(10,80,125,30);
  		shownWord.setForeground(Color.white);
  		shownWord.setBorder(border2);
  		shownWord.setVisible(false);
  		
  		//action listeners
  		button.addActionListener(a);
  		button2.addActionListener(a);
  		button3.addActionListener(a);
  		
  		//password
  		panel.add(pwd);
  		panel.add(button2);
  		panel.add(button3);
  		panel.add(shownWord);
  		//letter guess n display
  		panel.add(button);
  		panel.add(status);
  		panel.add(input);
  		panel.add(output);
  		panel.add(labelb);
  		//Title and style
  		panel.add(label);
  		panel.add(signature);
  		//wrong letter
  		panel.add(wronglist);
  		panel.add(wltext);
  		//hangman images
  		panel.add(noose);
  		panel.add(head);
  		panel.add(torso);
  		panel.add(rightarm);
  		panel.add(leftarm);
  		panel.add(leftleg);
  		panel.add(rightleg);
  		
  		panel.setBackground(Color.DARK_GRAY);
  		dspHM(hangman); //displays hangman
  		
  		/* making stuff non-static*/
  		//StartWindow start = new StartWindow();
  		StartWindow.startup(frame); //opens game setting and starts the game
  		
  		word = StartWindow.word1;      //  --
  		while (word == null)
  		{
  			word = StartWindow.word1;
  		}
  		

  		word = word.toUpperCase();
  		len = word.length();
  		OGword = word;
  		
  		arr = set();  // sets 
  		spaces = new String(arr);
  		output.setText(spaces);
  		
  		
  		
  		
    }
   
	
	
}
	



/* To do
 
 - currently uses the word that was previously entered as the word to guess.

 
 
 
 
 
 
 
 
 */



	
	
	
	
	
