import com.illposed.osc.*;

class JavaSampler2 {
  public static void main(String[] args) {
  	try {
  		OSCPortOut sender = new OSCPortOut();
  		Object arguments[] = new Object[3];
  		arguments[0] = "one";
  		arguments[1] = 0.2f;
  		arguments[2] = 0.0f;
  		
  		OSCMessage msg = new OSCMessage("/play", arguments);
  		try {
  			sender.send(msg);
  		} catch(Exception e) {
  			System.out.println("Error!");
  		}
  	} catch(Exception e) {
  		System.out.println("Error!");
  	}
  }
}