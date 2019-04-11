

public class Thing {

    protected double    init = 0;
    protected int    	lo   = 0;
    protected int   	hi   = 100;
    protected String 	txt  = "thing";
    protected int 		rank = 0;

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
    	this.txt = txt;
    }
    
    Thing (String txt, double init) {
    	this.txt = txt;
    	this.init = init;
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
        	if (txt.equals(""))
        		throw new IllegalArgumentException("txt cannot be empty");
        	else
        		this.txt = txt;
        } else 
        	throw new IllegalArgumentException("txt cannot be null");
    }
    
    void setInit ( final double init) {
    	if (init < 0)
    		throw new IllegalArgumentException("init can not be negative");
    	
    	this.init = init;
    }
    
    double getInit() {
    	return init;
    }
    
    int getLo() {
    	return lo;
    }
    
    int getHi() {
    	return hi;
    }
    
    int getRank() {
    	return rank;
    }

    Thing restrain ( Thing thing ) {
        thing.setInit(Math.max( this.lo, Math.min( this.hi, thing.init)));
        return thing;
    }

}

class Percent extends Thing {

    static int PERCENT_RANK = 4;

    Percent ( final double init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
        rank = getRank();
    }
    
    Percent (String txt) {
    	super( txt );
    	rank = getRank();
    }
    
    Percent (String txt, int x) {
    	super ( txt, x);
    	rank = getRank();
    }
    
    public int getRank() {
    	return PERCENT_RANK;
    }

	public int compareTo(Thing otherThing) {
		if (otherThing == null) {
			throw new IllegalArgumentException("comparison with null");
		}

		if (PERCENT_RANK < otherThing.getRank())
			return -1;
		else if (PERCENT_RANK > otherThing.getRank())
			return 1;
		else
			return 0;
	}

}

class Flow extends Thing {

    static int FLOW_RANK = 3;

    Flow ( final double init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    Flow (String txt) {
    	super( txt );
    }

    public int getRank () {
        return FLOW_RANK;
    }
    
	public int compareTo(Thing otherThing) {
		if (otherThing == null) {
			throw new IllegalArgumentException("comparison with null");
		}

		if (FLOW_RANK < otherThing.getRank())
			return -1;
		else if (FLOW_RANK > otherThing.getRank())
			return 1;
		else
			return 0;
	}
}

class Stock extends Thing {

    static int STOCK_RANK = 2;

    Stock ( final double init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    Stock(String txt, int x) {
    	super(txt, x);
    }

    int getRank () {
        return STOCK_RANK;
    }
    
	public int compareTo(Thing otherThing) {
		if (otherThing == null) {
			throw new IllegalArgumentException("comparison with null");
		}

		if (STOCK_RANK < otherThing.getRank())
			return -1;
		else if (STOCK_RANK > otherThing.getRank())
			return 1;
		else
			return 0;
	}
}

class Aux extends Thing {

    static int AUX_RANK = 1;

    Aux ( final double init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    Aux (String txt, int x) {
    	super ( txt, x );
    }
    
    Aux (String txt) {
    	super ( txt );
    }
    
	public int compareTo(Thing otherThing) {
		if (otherThing == null) {
			throw new IllegalArgumentException("comparison with null");
		}

		if (AUX_RANK < otherThing.getRank())
			return -1;
		else if (AUX_RANK > otherThing.getRank())
			return 1;
		else
			return 0;
	}
}
