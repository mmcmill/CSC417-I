!Collection methods !
b4now: aBlock
  | i j |
	i := self copyEmpty.
  j := self copyEmpty.
  2 to: (self size) do: [:a| aBlock value: i.
		i := self at: (a - 1). aBlock value: j. j:= self at: a. ].
  !