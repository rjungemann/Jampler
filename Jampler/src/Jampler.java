import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.*;
import net.sf.json.*;
import com.illposed.osc.*;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.SamplePlayer;
import net.beadsproject.beads.events.KillTrigger;
import net.beadsproject.beads.data.Sample;

class JavaSampler {
	private static AudioContext ac;
	private static HashMap<String, Sample> samples;
	
  public static void main(String[] args) {
  	ac = new AudioContext();
  	samples = null;

		try {
			OSCPortIn receiver = new OSCPortIn(OSCPortIn.defaultSCOSCPort());
			
			receiver.addListener("/load_samples", new OSCListener() {
				public void acceptMessage(java.util.Date time, OSCMessage message) {
					Object[] arguments = message.getArguments();
					
					String url = (String)arguments[0];
					
					samples = loadSamples(loadStringFromUrl(url));

			  	System.out.println("Finished loading!");
				}
			});
			receiver.addListener("/play", new OSCListener() {
				public void acceptMessage(java.util.Date time, OSCMessage message) {
					Object[] arguments = message.getArguments();
					
					String name = (String)arguments[0];
					Float velocity = (Float)arguments[1];
					Float panning = (Float)arguments[2];
					
					System.out.println(name);
					
					play(name, velocity, panning);
				}
			});
			receiver.startListening();
		} catch(Exception e) {}
  }
  public static String loadStringFromUrl(String uri) {
  	String full = "";
  	
  	try {
      URL url = new URL(uri);
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
      String str;
      
      while((str = in.readLine()) != null) {
          full = full.concat(str).concat("\n");
      }
	    in.close();
	  } catch (MalformedURLException e) {} catch (IOException e) {}
	  
	  return full;
  }
  public static HashMap<String, Sample> loadSamples(String json) {
  	JSONObject paths = (JSONObject)JSONSerializer.toJSON(json);
  	JSONArray names = paths.names();
  	
  	HashMap<String, Sample> ps = new HashMap<String, Sample>();
  	
  	for(int i = 0; i < names.size(); i++) {
  		String name = names.getString(i);
  		String path = paths.getString(name);
  		
  		try {
  			URL url = new URL(path);
  			InputStream reader = url.openStream();
  			
  			try {
  				Sample sample = new Sample(reader);
      		
      		ps.put(name, sample);
  			} catch(UnsupportedAudioFileException e) {
  				
  			}
  	  } catch (MalformedURLException e) {} catch (IOException e) {}
  	}
  	return ps;
  }
  public static void play(String key, float gain, float panning) {
  	Gain g = new Gain(ac, 2, new Envelope(ac, gain));
  	Sample s = samples.get(key);
  	SamplePlayer p = new SamplePlayer(ac, s);
  	
    ((Envelope)g.getGainEnvelope()).addSegment(gain, p.getSample().getLength(), new KillTrigger(g));
  	
		g.addInput(p);
		ac.out.addInput(g);
		ac.start();
  }
}