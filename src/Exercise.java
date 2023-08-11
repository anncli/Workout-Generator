
public class Exercise {
	String name = "";
	int reps = 0;
	
	public Exercise() {
		this.name = "";
		this.reps = 0;
	}
	
	public Exercise(String name, int reps) {
		this.name = name;
		this.reps = reps;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public String getName() {
		return name;
	}
	
	public int getReps() {
		return reps;
	}
	
	public String toString(boolean GUI) {
		if (GUI) {
			return String.format(getName() + ";" + getReps());
		}
		return String.format(getName() + "\t" + getReps());
	}
}
