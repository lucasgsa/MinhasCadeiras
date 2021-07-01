avaliacaoToString(avaliacao(UsuarioNome, JogoNome, Nota, Comentario), StringSaida) :-
    colorString(UsuarioNome, "blue", ConcUsuarioNome),
    colorString(" - ", "magenta", ConcTraco), 
    colorString(JogoNome, "red", ConcJogoNome),
    colorString("Comentario: ", "yellow", ConcComentario),
    format(atom(NotaFormatada), "~1f", [Nota]),
    concatenate([ConcUsuarioNome, ConcTraco, ConcJogoNome, ConcTraco, "Nota: ", NotaFormatada, "\n",
        ConcComentario, Comentario, "\n\n"
        ], StringSaida).

construirAvaliacao(Nome, Jogo, Nota, Comentario, avaliacao(Nome, Jogo, Nota, Comentario)).

getJogoNomeAvaliacao(avaliacao(_,X,_,_), X).
getUsuarioNomeAvaliacao(avaliacao(X,_,_,_), X).
getNotaAvaliacao(avaliacao(_,_,X,_), X).

mediaAvaliacoesJogo(NomeJogoProcurado, ListaAvaliacoes, MediaSaida) :-
    filterAvaliacoesJogo(NomeJogoProcurado, ListaAvaliacoes, Avaliacoes),
    somaAvaliacoes(Avaliacoes, SomaAvaliacoes),
    length(Avaliacoes, QtdAvaliacoes),
    calculaMedia(SomaAvaliacoes, QtdAvaliacoes, MediaSaida).

calculaMedia(Soma, Qtd, Saida) :- 
    Qtd*1.0 =:= 0.0 -> Saida = 0;
    Saida is Soma/Qtd.

somaAvaliacoes([], 0).
somaAvaliacoes([X|XS], SomaSaida) :-
    somaAvaliacoes(XS, SomaProx),
    getNotaAvaliacao(X, NotaAvaliacaoAtual),
    SomaSaida is NotaAvaliacaoAtual + SomaProx.

filterAvaliacoesJogo(_, [], []).
filterAvaliacoesJogo(NomeJogoProcurado, [X|XS], AvaliacoesSaida) :-
    getJogoNomeAvaliacao(X, NomeJogoAtual),
    string_lower(NomeJogoAtual, NomeJogoAtualLowerCase),
    string_lower(NomeJogoProcurado, NomeJogoProcuradoLowerCase),
    NomeJogoAtualLowerCase = NomeJogoProcuradoLowerCase -> 
        filterAvaliacoesJogo(NomeJogoProcurado, XS, SaidaProx),
        append([X], SaidaProx, AvaliacoesSaida);

        filterAvaliacoesJogo(NomeJogoProcurado, XS, AvaliacoesSaida).

filterAvaliacoesPorUsuario(_, [], []).
filterAvaliacoesPorUsuario(NomeUsuarioProcurado, [X|XS], AvaliacoesSaida) :-
    getUsuarioNomeAvaliacao(X, NomeUsuarioAtual),
    string_lower(NomeUsuarioProcurado, NomeUsuarioProcuradoLowerCase),
    string_lower(NomeUsuarioAtual, NomeUsuarioAtualLowerCase),
    NomeUsuarioAtualLowerCase = NomeUsuarioProcuradoLowerCase ->
        filterAvaliacoesPorUsuario(NomeUsuarioProcurado, XS, SaidaProx),
        append([X], SaidaProx, AvaliacoesSaida);

        filterAvaliacoesPorUsuario(NomeUsuarioProcurado, XS, AvaliacoesSaida).

formatarAvaliacao(avaliacao(Nome, Jogo, Nota, Comentario), Result):-
    string_concat(Nome, "|", Parte1), 
    string_concat(Parte1, Jogo, Parte2),
    string_concat(Parte2, "|", Parte3),
    string_concat(Parte3, Nota, Parte4),
    string_concat(Parte4, "|", Parte5), 
    string_concat(Parte5, Comentario, Parte6),
    string_concat(Parte6, "\n", Result).

salvarAvaliacao(NovaAvaliacao):-
    open('dados/avaliacoes.txt',append,Stream),
    formatarAvaliacao(NovaAvaliacao, AvaliacaoFormatada),
    write(Stream,AvaliacaoFormatada),
    close(Stream).
