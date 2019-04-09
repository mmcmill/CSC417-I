public abstract class Thing implements Comparable<Thing> {
	//understand init
    protected double    init = 0;
    protected int    	lo   = 0;
    protected int   	hi   = 100;
    protected String 	txt  = "thing";

    Thing ( final double init, final int lo, final int hi, final String txt ) {
        if ( init > 0 ) {
            this.init = init;
        }
        else if ( lo > 0 ) {
            this.init = lo;
        }

        setLo( lo );
        setHi( hi );
        setTxt( txt );		

    }
    
    Thing (String txt) {
    	setTxt( txt );
    }
    
    Thing (String txt, int x) {
    	setTxt( txt );
    	setHi( hi );
    }

    void setLo ( final int lo ) {
        if ( lo < 0 ) {
            throw new IllegalArgumentException( "lo cannot be negative" );
        } 
        // zero is default value.
        if ( lo == 0 ) {
            return;
        }

        this.lo = lo;
        if ( this.init >= this.lo ) {
            this.lo = (int) this.init;
        }
    }

    void setHi ( final int hi ) {
        if ( hi < 0 ) {
            throw new IllegalArgumentException( "hi cannot be negative" );
        } 

        // zero is default value
        if ( hi == 0 ) {
            return;
        }

        this.hi = hi;
        if ( this.init >= this.hi ) {
            this.lo = (int) this.init * 2;
        }
    }

    void setTxt ( final String txt ) {
        if ( txt != null ) {
            this.txt = txt;
        } else if (txt.equals("")) {
        	throw new IllegalArgumentException("txt cannot be empty");
        } else {
        	throw new IllegalArgumentException("txt cannot be null");
        }
    }

    //make static
    //change to input Thing, restrain the lo with x and return the edited Thing
    int restrain ( final int x ) {
        return Math.max( this.lo, Math.min( this.hi, x ) );
    }

    abstract int rank ();

}

class Percent extends Thing {

    static int PERCENT_RANK = 4;

    Percent ( final int init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }
    
    Percent (String txt) {
    	super( txt );
    }
    
    Percent (String txt, int x) {
    	super ( txt, x);
    }

    @Override
    int rank () {
        return PERCENT_RANK;
    }

	@Override
	public int compareTo(Thing otherThing) {
		if (otherThing == null) {
			throw new IllegalArgumentException("comparison with null");
		}

		if (PERCENT_RANK < otherThing.rank())
			return -1;
		else if (PERCENT_RANK > otherThing.rank())
			return 1;
		else
			return 0;
	}

}

class Flow extends Thing {

    static int FLOW_RANK = 3;

    Flow ( final int init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    Flow (String txt) {
    	super( txt );
    }

    @Override
    int rank () {
        return FLOW_RANK;
    }
    
	@Override
	public int compareTo(Thing otherThing) {
		if (otherThing == null) {
			throw new IllegalArgumentException("comparison with null");
		}

		if (FLOW_RANK < otherThing.rank())
			return -1;
		else if (FLOW_RANK > otherThing.rank())
			return 1;
		else
			return 0;
	}
}

class Stock extends Thing {

    static int STOCK_RANK = 2;

    Stock ( final int init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    Stock(String txt, int x) {
    	super(txt, x);
    }

    @Override
    int rank () {
        return STOCK_RANK;
    }
    
	@Override
	public int compareTo(Thing otherThing) {
		if (otherThing == null) {
			throw new IllegalArgumentException("comparison with null");
		}

		if (STOCK_RANK < otherThing.rank())
			return -1;
		else if (STOCK_RANK > otherThing.rank())
			return 1;
		else
			return 0;
	}
}

class Aux extends Thing {

    static int AUX_RANK = 1;

    Aux ( final int init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    Aux (String txt, int x) {
    	super ( txt, x );
    }
    
    Aux (String txt) {
    	super ( txt );
    }

    @Override
    int rank () {
        return AUX_RANK;
    }
    
	@Override
	public int compareTo(Thing otherThing) {
		if (otherThing == null) {
			throw new IllegalArgumentException("comparison with null");
		}

		if (AUX_RANK < otherThing.rank())
			return -1;
		else if (AUX_RANK > otherThing.rank())
			return 1;
		else
			return 0;
	}
}
