asteriscString(0, Result) :- Result = "".
asteriscString(NumAtual, Result) :- 
    sub1(NumAtual, NumProx),
    asteriscString(NumProx, ResultProx),
    atom_concat("*", ResultProx, ResultString),
    Result = ResultString.

sub1(Num, Result) :- Result is (Num-1).
sum1(Num, Result) :- Result is (Num+1).

asteriscTriangle(X, X) :- 
    asteriscString(X, Result),
    write(Result).
asteriscTriangle(Atual, Max) :-
    asteriscString(Atual, Result),
    writeln(Result),
    sum1(Atual, ResultProx),
    asteriscTriangle(ResultProx, Max).

main :-
    read(NumInicial),
    asteriscTriangle(0, NumInicial),
    halt.