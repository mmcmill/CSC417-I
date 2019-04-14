import java.util.HashMap;
import java.util.Map.Entry;

public class Things {

    HashMap<String, Thing> things;

    public Things ( final HashMap<String, Thing> things ) {
        if ( things == null ) {
            throw new IllegalArgumentException( "Things cannot be null" );
        }  
        this.things = things;
    }

    public HashMap<String, Thing> payload ( final HashMap<String, Thing> old ) {
    	
        final HashMap<String, Thing> out = new HashMap<String, Thing>();
        
        if (old == null)
        	return things;
        else {
            for ( final Entry<String, Thing> oldEntry : old.entrySet() ) {
                final String key = oldEntry.getKey();
                out.put( key, things.get( key ).restrain(old.get( key )));
            }
        }
        return out;
    }
}
