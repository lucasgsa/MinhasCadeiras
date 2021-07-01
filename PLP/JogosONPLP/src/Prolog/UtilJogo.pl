jogoToString(jogo(NomeJogo, CategoriasJogo, ReqMinimosJogo, PlataformaJogo, PrecoJogo, IsOnlineJogo, AnoLancamentoJogo), StringSaida) :- 
    tipoIsOnline(IsOnlineJogo, TipoOnline),
    precoString(PrecoJogo, PrecoString),
    listToString(ReqMinimosJogo, ReqMinimosString),
    listToString(CategoriasJogo, CategoriasString),
    colorString("Titulo: ", "red", ConcTituloString),
    colorString("Plataforma: ", "green", ConcPlataformaString),
    colorString("Requisitos minimos: ", "green", ConcReqMinimosString),
    colorString("Generos: ", "green", ConcGenerosString),
    colorString("Ano Lancamento: ", "green", ConcAnoLancamentoString),
    colorString(TipoOnline, "yellow", ConcTipoOnline),
    colorString("Preco: ", "red", ConcPrecoString),
    concatenate(
        [
        ConcTituloString, NomeJogo, "\n",
        "   ", ConcPlataformaString, PlataformaJogo, "\n",
        "   ", ConcReqMinimosString, ReqMinimosString, "\n",
        "   ", ConcGenerosString, CategoriasString, "\n",
        "   ", ConcAnoLancamentoString, AnoLancamentoJogo, "\n",
        "   ", ConcTipoOnline, "\n",
        "   ", ConcPrecoString,  PrecoString, "\n\n"
        ]
        , StringSaida).

contruirJogo(NomeJogo, CategoriasJogo, ReqMinimosJogo, PlataformaJogo, PrecoJogo, IsOnlineJogo, AnoLancamentoJogo, jogo(NomeJogo, CategoriasJogo, ReqMinimosJogo, PlataformaJogo, PrecoJogo, IsOnlineJogo, AnoLancamentoJogo)).

getNomeJogo(jogo(X,_,_,_,_,_,_), X).
getCategoriasJogo(jogo(_,X,_,_,_,_,_), X).
getAnoLancamentoJogo(jogo(_,_,_,_,_,_,X), X).

tipoIsOnline(0, "Um Jogador").
tipoIsOnline(1, "Cooperativo online").
tipoIsOnline(_, "IsOnline nao informado corretamente.").

precoString(X, Y) :-
    X*1.0 =:= 0.0 -> Y = "Gratis";
    format(atom(PrecoFormatado), "~2f", [X]),
    atom_concat("R$ ", PrecoFormatado, Y).

existeJogo(_,[], 0).
existeJogo(NomeJogoProcurado, [X|XS], Resposta) :-
    getNomeJogo(X, NomeJogoAtual),
    string_lower(NomeJogoAtual, NomeJogoAtualLowerCase),
    string_lower(NomeJogoProcurado, NomeJogoProcuradoLowerCase),
    NomeJogoAtualLowerCase = NomeJogoProcuradoLowerCase -> 
        Resposta = 1; 
        existeJogo(NomeJogoProcurado, XS, Resposta).

filterCategorias(_, [], []).
filterCategorias(CategoriaProcurada, [X|XS], JogosSaida) :-
    getCategoriasJogo(X, CategoriasJogo),
    listContains(CategoriasJogo, CategoriaProcurada, ContemCategoria),
    ContemCategoria =:= 1 -> 
        filterCategorias(CategoriaProcurada, XS, SaidaProxima),
        append([X], SaidaProxima, JogosSaida); 

        filterCategorias(CategoriaProcurada, XS, SaidaProxima),
        JogosSaida = SaidaProxima.


formatarJogo(jogo(NomeJogo, CategoriasJogo, ReqMinimosJogo, PlataformaJogo, PrecoJogo, IsOnlineJogo, AnoLancamentoJogo), Result) :-
    string_concat(NomeJogo, "|", Parte1), 
    string_concat(Parte1, CategoriasJogo, Parte2),
    string_concat(Parte2, "|", Parte3),
    string_concat(Parte3, ReqMinimosJogo, Parte4),
    string_concat(Parte4, "|", Parte5), 
    string_concat(Parte5, PlataformaJogo, Parte6),
    string_concat(Parte6, "|", Parte7),
    string_concat(Parte7, PrecoJogo, Parte8),
    string_concat(Parte8, "|", Parte9),
    string_concat(Parte9, IsOnlineJogo, Parte10),
    string_concat(Parte10, "|", Parte11),
    string_concat(Parte11, AnoLancamentoJogo, Parte12),
    string_concat(Parte12, "\n", Result).


salvarJogo(NovoJogo):-
    open('dados/jogos.txt',append,Stream),
    formatarJogo(NovoJogo, JogoFormatado),
    write(Stream,JogoFormatado),
    close(Stream).
