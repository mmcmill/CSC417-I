Magic sub: #Num has: 'n mu m2 sd id lo hi txt w'

! Num methods ! 
init: text 
  self n: 0 ;
       mu: 0 ;
       m2: 0 ;
       sd: 0;
       lo: 10e32 ;
       hi: -1*10e32 ;
       txt: text;
       w: 1 ;
       id: self printString !


numInc: t hasX: x hasD:d 
  (x = '?')
    ifTrue: [ ^x ].
  t.n := t.n + 1.
  d := x - t.mu.
  t.mu := t.mu + (d/t.n).
  t.m2 := t.m2 + d*(x - t.mu).
  "if x is greater than t.hi, then t.hi = x and return"
  (x > t.hi)
    ifTrue: [
      t.hi := x. 
      ^x
  ].
  (x < t.lo) 
    ifTrue: [
    t.lo := x.
    ^x
  ].
  (t.n >= 2)
  ifTrue: [
    t.sd := (t.m2/(t.n - 1 + 10e-32)) raisedTo 1/2.
    ^x
  ].
  ^x.
  !
  
  numDec: t hasX:x hasD:d 
  (x = '?')
    ifTrue: [ ^x ].
  (t.n = 1)
    ifTrue: [ ^x ].
  t.n := t.n - 1.
  d := x - tmu.
  t.mu := t.mu - d/t.n.
  t.m2 := t.m2 - d*(x- t.mu).
  (t.n >= 2)
    ifTrue: [
      t.sd := (t.m2/(t.n - 1 + 10e-32))^0.5.
      ^x.
    ].
  ^x .
  !
!
  
  