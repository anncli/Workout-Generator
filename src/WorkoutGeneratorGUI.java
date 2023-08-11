import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WorkoutGeneratorGUI extends WorkoutGenerator implements ActionListener{
	
	private JFrame frame;
	private JPanel panel;
	
	//MUSCLE GROUP
	String selectedMuscle = "";
	private JButton armsButton;
	private JButton coreButton;
	private JButton legsButton;
	private JLabel selectedMuscleLabel;
	
	//EXERCISE COUNT
	int exerciseCount = 7; //default value
	private JButton incrementButton;
	private JButton decrementButton;
	private JLabel exerciseCountLabel;
	
	//INTENSITY
	String intensity = "";
	private JButton lightButton;
	private JButton moderateButton;
	private JButton intenseButton;
	private JLabel intensityLabel;
	
	private JButton generateButton;
	
	JLabel blank = new JLabel("");

	
	public WorkoutGeneratorGUI() {
		frame = new JFrame();
		
		/************************
		 * INITIALIZING OBJECTS *
		 ************************/
		armsButton = new JButton("Arms");
		coreButton = new JButton("Core");
		legsButton = new JButton("Legs");
		armsButton.addActionListener(this);
		coreButton.addActionListener(this);
		legsButton.addActionListener(this);
		JLabel muscleGroupPrompt = new JLabel("Please select a muscle group");
		selectedMuscleLabel = new JLabel("Muscle Group: ");
		
		incrementButton = new JButton("+");
		decrementButton = new JButton("-");
		incrementButton.addActionListener(this);
		decrementButton.addActionListener(this);
		JLabel numExercisesPrompt = new JLabel("Please choose # of exercises (5-10)");
		exerciseCountLabel = new JLabel("# Exercises: 7 (Default)");
		
		lightButton = new JButton("Light");
		moderateButton = new JButton("Moderate");
		intenseButton = new JButton("Intense");
		lightButton.addActionListener(this);
		moderateButton.addActionListener(this);
		intenseButton.addActionListener(this);
		JLabel intensityPrompt = new JLabel("Please select an intensity");
		intensityLabel = new JLabel("Intensity: ");
		
		generateButton = new JButton("GENERATE WORKOUT");
		generateButton.setBackground(Color.GRAY);
		generateButton.addActionListener(this);
		
		
		/***********************************
		 * CREATE AND ADD OBJECTS TO PANEL *
		 ***********************************/
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));
		panel.setLayout(new GridLayout(0, 1));
		
		//Muscle Groups
		panel.add(muscleGroupPrompt);
		panel.add(armsButton);
		panel.add(coreButton);
		panel.add(legsButton);
		panel.add(selectedMuscleLabel);
		
		//Num Exercises
		panel.add(numExercisesPrompt);
		panel.add(incrementButton);
		panel.add(decrementButton);
		panel.add(exerciseCountLabel);
		
		//Intensity
		panel.add(intensityPrompt);
		panel.add(lightButton);
		panel.add(moderateButton);
		panel.add(intenseButton);
		panel.add(intensityLabel);
		
		panel.add(blank); //spacer
		panel.add(generateButton);
		
		
		/*******************
		 * FREAME SETTINGS *
		 *******************/
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Workout Maker");
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new WorkoutGeneratorGUI();

	}
	
	public void callGenerator() throws FileNotFoundException {
		//Generate exercises using WorkoutGenerator
		ArrayList<Exercise> exercises = new ArrayList<>();
		for(int i = 0; i < exerciseCount; i++) {
			exercises.add(new Exercise());
		}
		generateExercises(exercises, exerciseCount, selectedMuscle.toLowerCase());
		
		//Adjust panel layout for printing exercises
		panel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 0));
		GridLayout layout = new GridLayout(0, 2);
		layout.setHgap(25);
		panel.setLayout(layout);
		
		//Add column titles to panel
		JLabel tableTitle = new JLabel("EXERCISE");
		panel.add(tableTitle);
		tableTitle = new JLabel("REPS");
		panel.add(tableTitle);
		
		//Create JLabels for exercises
		JLabel exercise;
		JLabel reps;
		JLabel rest;
		
		//Print exercises
		int counter = 0;
		for(int i = 0; i < exercises.size(); i++) {
			//Split elements into separate exercise and reps labels
			exercise = new JLabel(exercises.get(i).toString(true).split(";")[0]);
			reps = new JLabel(exercises.get(i).toString(true).split(";")[1]);
			panel.add(exercise);
			panel.add(reps);
			
			counter++;
			//Add rest depending on intensity
			if(counter == 1 && intensity.equalsIgnoreCase("light")) {
				rest = new JLabel("REST 20 seconds");
				panel.add(rest);
				JLabel blank = new JLabel("");
				panel.add(blank);
				counter = 0;
			}
			else if(counter == 2 && intensity.equalsIgnoreCase("moderate")) {
				rest = new JLabel("REST 30 seconds");
				panel.add(rest);
				JLabel blank = new JLabel("");
				panel.add(blank);
				counter = 0;
			}
			else if(counter == 3 && intensity.equalsIgnoreCase("intense")) {
				rest = new JLabel("REST 30 seconds");
				panel.add(rest);
				JLabel blank = new JLabel("");
				panel.add(blank);
				counter = 0;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == armsButton) {
			selectedMuscle = "Arms";
		}
		else if (e.getSource() == coreButton) {
			selectedMuscle = "Core";
		}
		else if (e.getSource() == legsButton) {
			selectedMuscle = "Legs";
		}
		
		if (e.getSource() == incrementButton) {
			if (exerciseCount < 10) {
				exerciseCount++;
			}
		}
		else if (e.getSource() == decrementButton) {
			if (exerciseCount > 5) {
				exerciseCount--;
			}
		}
		
		if (e.getSource() == lightButton) {
			intensity = "Light";
		}
		else if (e.getSource() == moderateButton) {
			intensity = "Moderate";
		}
		else if (e.getSource() == intenseButton) {
			intensity = "Intense";
		}
		
		if (!selectedMuscle.equals("") && !intensity.equals("")) {
			generateButton.setBackground(Color.GREEN);
			if (e.getSource() == generateButton) {
				//Clear panel to prepare for printing exercises
				panel.removeAll();
				panel.updateUI();
				try {
					callGenerator();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
		}
			
		selectedMuscleLabel.setText("Muscle Group: " + selectedMuscle);
		exerciseCountLabel.setText("# Exercises: " + exerciseCount);
		intensityLabel.setText("Intensity: " + intensity);
	}

}
