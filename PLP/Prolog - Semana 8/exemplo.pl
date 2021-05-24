dobro(X,R) :- R is X*2.
main :-
  read(X),
  dobro(X,Y),
  write(Y),
  halt.