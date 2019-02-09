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

setN: newN
  n := newN!

getN
  ^n!

setMU: newMU
  mu := newMU!

getMU
  ^mu!
  
setm2: newm2
  m2 := newm2!

getm2
  ^m2!

setSD: newSD
  sd := newSD!

getSD
  ^sd!

setlo: newlo
  lo := newlo!
  
getlo
  ^lo!
  
sethi: newhi
  hi := newhi!
  
gethi
  ^hi!


numInc: t hasX: x 
  | d |
  (x = '?')
    ifTrue: [ ^x ].
  t setN: (t getN + 1).
  d := (x - t getMU).
  t setMU: ( t getMU + (d/t getN)).
  t setm2: (t getm2 + d*(x - t getMU) ).
  "if x is greater than t.hi, then t.hi = x and return"
  (x > t gethi)
    ifTrue: [
      t sethi: x. 
      ^x.
  ].
  (x < t getlo) 
    ifTrue: [
    t setlo: x.
    ^x.
  ].
  (t getN >= 2)
     ifTrue: [
       t setSD: (t getm2/(t getN - 1 + 10e-32)) sqrt.
       ^x.
     ].
  
  ^x!
  
  numDec: t hasX:x 
  |d|
  (x = '?')
    ifTrue: [ ^x ].
  (t getN = 1)
    ifTrue: [ ^x ].
  t setN: (t getN - 1).
  d := x - t getMU.
  t setMU: (t getMU - d/t getN).
  t setm2: (t getm2 - d*(x- t getMU)).
  (t getN >= 2)
    ifTrue: [
      t setSD: (t getm2/(t getN - 1 + 10e-32) ) sqrt.
      ^x.
    ].
  ^x!
  

"1b2: create "
eject: aBlock 
    | newCollection |
    newCollection := self copyEmpty.
    self
        do: [:element | (aBlock select: element isEmpty) ifTrue: [newCollection add: element]].
    ^newCollection!
!