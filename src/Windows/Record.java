package Windows;
import java.io.Serializable;
public class Record implements Comparable<Record>, Serializable{
	private static final long serialVersionUID = 1L;
	private int score;
	private String name;
	
	public Record(int score,String name){
		this.score=score;
		this.name=name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Record o) {
		// TODO Auto-generated method stub
		return o.getScore()-score;
	}

}
