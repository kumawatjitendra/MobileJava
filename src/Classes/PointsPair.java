package Classes;

public class PointsPair {

	String pointA;
	public String getPointA() {
		return pointA;
	}
	public void setPointA(String pointA) {
		this.pointA = pointA;
	}
	String pointB;
	public String getPointB() {
		return pointB;
	}
	public void setPointB(String pointB) {
		this.pointB = pointB;
	}
	public Boolean isEqual(PointsPair P)
	{
		if(this.pointA == P.pointA && this.pointB == P.pointB)
			return true;
		else
			return false;
	}
}
