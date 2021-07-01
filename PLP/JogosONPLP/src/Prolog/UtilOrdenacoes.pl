ordem_lancamento_insert_sort(List,Sorted):-
    ordem_lancamento_i_sort(List,[],Sorted).

ordem_lancamento_i_sort([],Acc,Acc).
ordem_lancamento_i_sort([H|T],Acc,Sorted):-
    ordem_lancamento_insert(H,Acc,NAcc),ordem_lancamento_i_sort(T,NAcc,Sorted).
   
ordem_lancamento_insert(X,[Y|T],[Y|NT]) :-
    getAnoLancamentoJogo(X, AnoX),
    getAnoLancamentoJogo(Y, AnoY),
    AnoX>AnoY,ordem_lancamento_insert(X,T,NT).

ordem_lancamento_insert(X,[Y|T],[X,Y|T]) :-
    getAnoLancamentoJogo(X, AnoX),
    getAnoLancamentoJogo(Y, AnoY),
    AnoX=<AnoY.
ordem_lancamento_insert(X,[],[X]).


ordem_avaliacoes_insert_sort(ListaAvaliacoes, List, Sorted):-
    ordem_avaliacoes_i_sort(ListaAvaliacoes, List,[],Sorted).

ordem_avaliacoes_i_sort(_, [],Acc,Acc).

ordem_avaliacoes_i_sort(ListaAvaliacoes, [H|T],Acc,Sorted):-
    ordem_avaliacoes_insert(ListaAvaliacoes, H,Acc,NAcc),ordem_avaliacoes_i_sort(ListaAvaliacoes, T,NAcc,Sorted).
   
ordem_avaliacoes_insert(ListaAvaliacoes, X,[Y|T],[Y|NT]):-
    getNomeJogo(X, NomeJogoX),
    getNomeJogo(Y, NomeJogoY),
    mediaAvaliacoesJogo(NomeJogoX, ListaAvaliacoes, MediaJogoX),
    mediaAvaliacoesJogo(NomeJogoY, ListaAvaliacoes, MediaJogoY),
    MediaJogoX>MediaJogoY,
    ordem_avaliacoes_insert(ListaAvaliacoes, X,T,NT).

ordem_avaliacoes_insert(ListaAvaliacoes, X,[Y|T],[X,Y|T]):-
    getNomeJogo(X, NomeJogoX),
    getNomeJogo(Y, NomeJogoY),
    mediaAvaliacoesJogo(NomeJogoX, ListaAvaliacoes, MediaJogoX),
    mediaAvaliacoesJogo(NomeJogoY, ListaAvaliacoes, MediaJogoY),
    MediaJogoX=<MediaJogoY.
ordem_avaliacoes_insert(_,X,[],[X]).