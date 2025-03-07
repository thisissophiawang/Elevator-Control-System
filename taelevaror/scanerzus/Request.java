package scanerzus;

public class Request implements RequestInterface {
  private final int startFloor;
  private final int endFloor;
  
  public Request(int startFloor, int endFloor) {
    this.startFloor = startFloor;
    this.endFloor = endFloor;
  }
  
  public int getStartFloor() {
    return this.startFloor;
  }
  
  public int getEndFloor() {
    return this.endFloor;
  }
  
  public String toString() {
    return this.startFloor + "->" + this.endFloor;
  }
}
