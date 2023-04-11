
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
	
	public String toString() {
		return String.format("{Exercise:" + getName() + "\t Reps:" + getReps() + "}");
	}
}
