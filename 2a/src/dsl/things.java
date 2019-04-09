import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

public class Things {

    // key is the name of the thing
    HashMap<String, Thing> things;
    // String is the name of the things in order by the rank of the thing
    ArrayList<String>      order;

    Things ( final HashMap<String, Thing> things ) {
        if ( things == null ) {
            throw new IllegalArgumentException( "Things cannot be null" );
        }
        this.things = things;

        // sort the things by rank, using comparator in Thing
        final SortedSet<Thing> sortedByRank = new TreeSet<Thing>( this.things.values() );

        for ( final Thing value : sortedByRank ) {
            order.add( getKey( value ) );
        }

        // set the name for the things to be the keys
        for ( final String key : this.order ) {
            this.things.get( key ).setTxt( key );
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

    HashMap<String, Thing> payload ( final HashMap<String, Thing> old ) {
        final HashMap<String, Thing> out = new HashMap<String, Thing>();
        for ( final String key : this.order ) {
            out.put( key, this.things.get( key ).init );
        }

        if ( old != null ) {
            for ( final Entry<String, Thing> oldEntry : old.entrySet() ) {
                final String key = oldEntry.getKey();

                out.put( key, things.get( key ).restrain( old.get( key ) ) );
            }
        }

        return out;

    }

    ArrayList<Thing> toList () {
        final ArrayList<Thing> listThings = new ArrayList<Thing>();
        for ( final String key : order ) {
            listThings.add( things.get( key ) );
        }
        return listThings;
    }
}
