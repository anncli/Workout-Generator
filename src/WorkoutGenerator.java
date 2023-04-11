import java.io.*;
import java.util.*;

public class WorkoutGenerator {
	
	public static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		//Prompt for muscle group
		System.out.println("Which muscle group would you like to make a workout for?");
		System.out.print("Options: [arms] [core] [legs]: ");
		String muscle = userInput.next();
		while(!muscle.equalsIgnoreCase("arms") && !muscle.equalsIgnoreCase("core") 
				&& !muscle.equalsIgnoreCase("legs")) {
			System.out.println("That is not a valid muscle group");
			System.out.println("Which muscle group would you like to make a workout for?");
			System.out.print("Options: [arms] [core] [legs]: ");
			muscle = userInput.next();
		}
		System.out.println();
		
		//Prompt for number of exercises
		System.out.println("How many exercises do you want in the workout?");
		System.out.print("Please input a numerical number between 5 and 10 inclusive: ");
		int num = userInput.nextInt();
		while(num < 5 || num > 10) {
			System.out.println("That is not a valid number of exercises");
			System.out.print("Please input a numerical number between 5 and 10 inclusive: ");
			num = userInput.nextInt();
		}
		System.out.println();
		
		//Prompt for intensity
		System.out.println("How intense do you want the workout to be?");
		System.out.print("Options: [light] [moderate] [intense]: ");
		String intensity = userInput.next();
		while(!intensity.equalsIgnoreCase("light") && !intensity.equalsIgnoreCase("moderate") 
				&& !intensity.equalsIgnoreCase("intense")) {
			System.out.println("That is not a valid intensity");
			System.out.println("How intense do you want the workout to be?");
			System.out.print("Options: [light] [moderate] [intense]: ");
			intensity = userInput.next();
		}
		System.out.println();
		System.out.println("Generating workout...");
		
		ArrayList<String> exercises = new ArrayList<>(generateExercises(num, muscle));
		System.out.println(exercises.toString());
	}
	
	//Randomize numbers
	public static ArrayList<String> generateExercises(int numExercises, String muscle) throws FileNotFoundException {
		//Shuffle list of rows
		Integer[] intArray = new Integer[getFileLength(muscle)];
		for(int i = 0; i < intArray.length; i++) {
			intArray[i] = i;
		}
		List<Integer> intList = Arrays.asList(intArray);
		Collections.shuffle(intList);
		intList.toArray(intArray);
		
		//Get the IDs for the exercises
		int[] rowList = new int[numExercises];
		for(int i = 0; i < numExercises; i++) {
			rowList[i] = intArray[i];
		}
		
		//Add exercise names
		ArrayList<String> exercises = new ArrayList<String>();
		for(int i : rowList) {
			exercises.add(getExercise(i, muscle));
		}
		
		return exercises;
	}
	
	public static int getFileLength(String muscle) throws FileNotFoundException {
		int lineCount = 0;
		
		Scanner fileScanner = new Scanner(getFileName(muscle));
		while(fileScanner.hasNextLine()) {
			lineCount++;
			fileScanner.nextLine();
		}
		
		fileScanner.close();
		
		return lineCount;
	}
	
	public static String getExercise(int row, String muscle) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(getFileName(muscle));
		fileScanner.useDelimiter(",");
		for(int i = 0; i < row; i++) {
			fileScanner.nextLine();
		}
		
		String exercise = fileScanner.next();
		
		fileScanner.close();  //closes the scanner  

		return exercise;
	}
	
	public static File getFileName(String muscle) throws FileNotFoundException {
		File fileName = new File("armExercises.csv");
		
		if(muscle.equals("core")) {
			fileName = new File("coreExercises.csv");
		}
		else if(muscle.equals("legs")) {
			fileName = new File("legExercises.csv");
		}
		
		return fileName;
	}

}
