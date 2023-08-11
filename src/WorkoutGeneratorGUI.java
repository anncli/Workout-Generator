import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class WorkoutGeneratorGUI implements ActionListener{
	
	String selectedMuscle = "";
	private JFrame frame;
	private JLabel selectedMuscleGroup;
	private JPanel panel;
	
	private JButton armsButton;
	private JButton coreButton;
	private JButton legsButton;
	
	int exerciseCount = 7;
	private JButton increment;
	private JButton decrement;
	private JLabel numExercises;
	
	String intensity = "";
	private JButton lightButton;
	private JButton moderateButton;
	private JButton intenseButton;
	private JLabel intensityLabel;
	
	private JButton generateButton;
	
	private JLabel blank = new JLabel("");
	
	public WorkoutGeneratorGUI() {
		frame = new JFrame();
		
		armsButton = new JButton("Arms");
		coreButton = new JButton("Core");
		legsButton = new JButton("Legs");
		armsButton.addActionListener(this);
		coreButton.addActionListener(this);
		legsButton.addActionListener(this);
		JLabel muscleGroupPrompt = new JLabel("Please select a muscle group");
		selectedMuscleGroup = new JLabel("Muscle Group: ");
		
		increment = new JButton("+");
		decrement = new JButton("-");
		increment.addActionListener(this);
		decrement.addActionListener(this);
		JLabel numExercisesPrompt = new JLabel("Please choose # of exercises (5-10)");
		numExercises = new JLabel("# Exercises: 7 (Default)");
		
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
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));
		panel.setLayout(new GridLayout(0, 1));
		
		//Muscle Groups
		panel.add(muscleGroupPrompt);
		panel.setLayout(new GridLayout(0, 1));
		panel.add(armsButton);
		panel.add(coreButton);
		panel.add(legsButton);
		panel.add(selectedMuscleGroup);
		
		//Num Exercises
		panel.add(numExercisesPrompt);
		panel.add(increment);
		panel.add(decrement);
		panel.add(numExercises);
		
		//Intensity
		panel.add(intensityPrompt);
		panel.add(lightButton);
		panel.add(moderateButton);
		panel.add(intenseButton);
		panel.add(intensityLabel);
		panel.add(blank);
		
		//Generate
		panel.add(generateButton);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Workout Maker");
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new WorkoutGeneratorGUI();

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
		
		if (e.getSource() == increment) {
			if (exerciseCount < 10) {
				exerciseCount++;
			}
		}
		else if (e.getSource() == decrement) {
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
				panel.removeAll();
				panel.updateUI();
			}
			
		}
			
		selectedMuscleGroup.setText("Muscle Group: " + selectedMuscle);
		numExercises.setText("# Exercises: " + exerciseCount);
		intensityLabel.setText("Intensity: " + intensity);
	}

}
