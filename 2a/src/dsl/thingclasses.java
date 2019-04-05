abstract class Thing {
  int init = 0;
  int lo = 0;
  int hi = 100;
  String txt = "thing";
  
  Thing(int init, int lo, int hi, String txt) {
     if (init > 0) {
      this.init = init;
    } else if (lo > 0){
      this.init = lo;
    }
    
    setLo(lo);
    setHi(hi);
    setTxt(txt);
    
  }
  
  void setLo(int lo){
    if (lo < 0) {
      throw new IllegalArgumentException("lo cannot be negative");
    }
    // zero is default value.
    if (lo == 0) { 
      return;
    }
    
    this.lo = lo;
    if(this.init >= this.lo) {
      this.lo = this.init;
    }
  }
  
  void setHi(int hi){
    if (hi < 0) {
      throw new IllegalArgumentException("hi cannot be negative");
    }
    
   // zero is default value
    if (hi == 0) { 
      return;
    }
    
    this.hi = hi;
    if(this.init >= this.hi) {
      this.lo = this.init*2;
    }
  }
  
  void setTxt(String txt) {
    if( txt != null) {
      this.txt = txt;
    }
  }
  
  int restrain(int x){
    return Math.max(this.lo, Math.min(this.hi, x));
  }
  
  abstract int rank();
  
}

class Percent extends Thing {

    static int PERCENT_RANK = 4;
    
    Percent ( int init, int lo, int hi, String txt ) {
        super( init, lo, hi, txt );
    }

    @Override
    int rank () {
        return PERCENT_RANK;
    }  
}

class Flow extends Thing {

    static int FLOW_RANK = 3;
    
    Flow ( int init, int lo, int hi, String txt ) {
        super( init, lo, hi, txt );
    }

    @Override
    int rank () {
        return FLOW_RANK;
    }  
}

class Stock extends Thing {

    static int STOCK_RANK = 2;
    
    Stock ( int init, int lo, int hi, String txt ) {
        super( init, lo, hi, txt );
    }

    @Override
    int rank () {
        return STOCK_RANK;
    }  
}

class Aux extends Thing {

    static int AUX_RANK = 1;
    
    Aux ( int init, int lo, int hi, String txt ) {
        super( init, lo, hi, txt );
    }

    @Override
    int rank () {
        return AUX_RANK;
    }  
}