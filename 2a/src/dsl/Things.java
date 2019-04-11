import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Things {

    // key is the name of the thing
    HashMap<String, Thing> things;
    // String is the name of the things in order by the rank of the thing
    ArrayList<String>      order;

    public Things ( final HashMap<String, Thing> things ) {
        if ( things == null ) {
            throw new IllegalArgumentException( "Things cannot be null" );
        }
        
        this.things = things;
        
        order = new ArrayList<String>();

        ArrayList<String> keyList = 
        		new ArrayList<String>(Arrays.asList(things.keySet().toArray(new String[things.keySet().size()])));
        
        System.out.println("KeyList size: " + keyList.size());
        for (String key : keyList) {
        	if (order.size() == 0)
        		order.add(key);
        	else { 
        		for (int j = 0; j < order.size(); j++) {
        			if (j == order.size() - 1) {
        				order.add(order.size(), key);
        				break;
        			}
        			else if (things.get(key).rank <= things.get(order.get(j)).rank) {
        			order.add(j, key);
        			break;
        			}
        		} 
        	}
        }
        System.out.println("order size: " + order.size());
    }
    
    public void printOrder() {
        for (String key : order) {
        	System.out.println(key + " " + things.get(key).rank);
        }
    }

    private String getKey ( final Thing value ) {
        for ( final Entry<String, Thing> entry : this.things.entrySet() ) {
            if ( entry.getValue().equals( value ) ) {
                return entry.getKey();
            }
        }
        return null;
    }

    public HashMap<String, Thing> payload ( final HashMap<String, Thing> old ) {
        final HashMap<String, Thing> out = new HashMap<String, Thing>();
        
        for ( final String key : this.order ) {
            out.put( key, this.things.get( key ) );
        }
        // possibly just return HashMap parameter

        if ( old != null ) {
            for ( final Entry<String, Thing> oldEntry : old.entrySet() ) {
                final String key = oldEntry.getKey();
                out.put( key, things.get( key ).restrain(old.get( key )));
            }
        }

        return out;

    }

    public ArrayList<Thing> toList () {
        final ArrayList<Thing> listThings = new ArrayList<Thing>();
        for ( final String key : order ) {
            listThings.add( things.get( key ) );
        }
        return listThings;
    }
}
