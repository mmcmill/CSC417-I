import java.lang.math.*;


class Thing {
  int init = 0;
  int lo = 0;
  int hi = 100;
  String txt = "thing";
  
  Thing(int init, int lo, int hi, String txt) {
     if (init != null) {
      this.init = init;
    } else if (lo != null){
      this.init = lo;
    } else {
      this.init = 
     }
    
    setLo(lo);
    setHi(hi);
    setTxt(txt);
    
  }
  
  void setLo(int lo){
    // null is allowed, since the class has a default value. makes the argument optional.
    if (lo == null) { 
      return;
    }
    if (lo < 0) {
      throw new IllegalArgumentException("lo cannot be negative");
    }
    
    this.lo = lo;
    if(this.init >= this.lo) {
      this.lo = this.init;
    }
  }
  
  void setHi(int hi){
    // null is allowed, since the class has a default value. makes the argument optional.
    if (hi == null) { 
      return;
    }
    if (hi < 0) {
      throw new IllegalArgumentException("hi cannot be negative");
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