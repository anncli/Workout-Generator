import java.io.*;
import java.util.*;

public class WorkoutGenerator extends Exercise {
	
	public static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		/***************
		 * PROMPT USER *
		 ***************/
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
		System.out.println();
		
		
		
		/**********************
		 * GENERATE EXERCISES *
		 **********************/
		ArrayList<Exercise> exercises = new ArrayList<>();
		for(int i = 0; i < num; i++) {
			exercises.add(new Exercise());
		}
		generateExercises(exercises, num, muscle);
		
		//Print Workout
		int counter = 0;
		System.out.println("Here is your " + muscle + " workout");
		System.out.println("EXERCISE \tREPS");
		for(int i = 0; i < exercises.size(); i++) {
			System.out.println(exercises.get(i).toString(false));
			counter++;
			if(counter == 1 && intensity.equalsIgnoreCase("light")) {
				System.out.println("REST \t\t20 seconds");
				counter = 0;
			}
			else if(counter == 2 && intensity.equalsIgnoreCase("moderate")) {
				System.out.println("REST \t\t30 seconds");
				counter = 0;
			}
			else if(counter == 3 && intensity.equalsIgnoreCase("intense")) {
				System.out.println("REST \t\t30 seconds");
				counter = 0;
			}
		}
	}
	
	//Randomize numbers
	public static void generateExercises(ArrayList<Exercise> list, int numExercises,
			String muscle) throws FileNotFoundException {
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
		for(int i = 0; i < numExercises; i++) {
			list.get(i).setName(pullName(rowList[i], muscle));
		}
		
		//Add rep counts
		for(int i = 0; i < numExercises; i++) {
			list.get(i).setReps(generateReps(rowList[i], muscle));
		}
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
	
	public static String pullName(int row, String muscle) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(getFileName(muscle));
		fileScanner.useDelimiter(",");
		for(int i = 0; i < row; i++) {
			fileScanner.nextLine();
		}
		
		String exercise = fileScanner.next();
		
		fileScanner.close();  //closes the scanner  

		return exercise;
	}
	
	public static int generateReps(int row, String muscle) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(getFileName(muscle));
		fileScanner.useDelimiter(",");
		for(int i = 0; i < row; i++) {
			fileScanner.nextLine();
		}
		
		fileScanner.next();
		
		int min = fileScanner.nextInt();
		fileScanner.useDelimiter("");
		fileScanner.next();
		fileScanner.useDelimiter("\r\n");
		int max = fileScanner.nextInt() + 1;
		
		Random rng = new Random();
		int reps = rng.nextInt(max-min) + min;

		return reps;
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
