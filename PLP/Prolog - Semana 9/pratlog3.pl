tamanho([], Tamanho) :- Tamanho = 0.
tamanho([_|XS], Tamanho) :- 
    tamanho(XS, TamanhoProx),
    Tamanho is 1 + TamanhoProx.

somatoriaNotas([], Soma) :- Soma = 0.
somatoriaNotas([X|XS], Soma) :-
    somatoriaNotas(XS, SomaProx),
    Soma is X*4 + SomaProx.

main :-
    read(Lista),
    tamanho(Lista, TamanhoLista),
    CS is TamanhoLista*4,
    somatoriaNotas(Lista, Somatoria),
    Resultado is Somatoria/CS,
    writeln(Resultado),
    halt.
