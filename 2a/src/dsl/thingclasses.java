abstract class Thing implements Comparable<Thing> {
    int    init = 0;
    int    lo   = 0;
    int    hi   = 100;
    String txt  = "thing";

    Thing ( final int init, final int lo, final int hi, final String txt ) {
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
            this.lo = this.init;
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
            this.lo = this.init * 2;
        }
    }

    void setTxt ( final String txt ) {
        if ( txt != null ) {
            this.txt = txt;
        }
    }

    int restrain ( final int x ) {
        return Math.max( this.lo, Math.min( this.hi, x ) );
    }

    abstract int rank ();

    @Override
    public int compare ( final Thing o1, final Thing o2 ) {
        if ( o1.rank() < o2.rank() ) {
            return -1;
        }
        else if ( o1.rank() > o2.rank() ) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

class Percent extends Thing {

    static int PERCENT_RANK = 4;

    Percent ( final int init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    @Override
    int rank () {
        return PERCENT_RANK;
    }

}

class Flow extends Thing {

    static int FLOW_RANK = 3;

    Flow ( final int init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    @Override
    int rank () {
        return FLOW_RANK;
    }
}

class Stock extends Thing {

    static int STOCK_RANK = 2;

    Stock ( final int init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    @Override
    int rank () {
        return STOCK_RANK;
    }
}

class Aux extends Thing {

    static int AUX_RANK = 1;

    Aux ( final int init, final int lo, final int hi, final String txt ) {
        super( init, lo, hi, txt );
    }

    @Override
    int rank () {
        return AUX_RANK;
    }
}
