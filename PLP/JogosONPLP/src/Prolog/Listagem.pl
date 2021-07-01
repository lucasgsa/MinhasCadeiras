% 3. Deve ser possível listar todos os jogos disponíveis;
listarJogos([], StringSaida) :-
    colorString("Nenhum Jogo Encontrado", "red", StringSaida).
listarJogos(ListaJogos, StringSaida) :- listarJogosAux(ListaJogos, StringSaida).

listarJogosAux([], "").
listarJogosAux([X|XS], StringSaida) :- 
    jogoToString(X, JogoString), 
    listarJogosAux(XS, ResultProx),
    atom_concat(JogoString, ResultProx, StringSaida).  

%  4. Deve ser possível listar os últimos jogos cadastrados no sistema;
listarUltimosJogosCadastrados(ListaJogos, StringSaida) :-
    reverse(ListaJogos, ListaJogosInvertida),
    take(5, ListaJogosInvertida, UltimosJogos),
    listarJogos(UltimosJogos, StringSaida).

% 5. Deve ser possível listar os jogos por categoria;
listarJogosCategoria(CategoriaProcurada, ListaJogos, StringSaida) :-
    filterCategorias(CategoriaProcurada, ListaJogos, JogosFiltrados),
    listarJogos(JogosFiltrados, StringSaida).

% 6. Deve ser possível listar os jogos em ordem de lançamento.
listarJogosOrdemLancamento(ListaJogos, StringSaida) :-
    ordem_lancamento_insert_sort(ListaJogos, ListaOrdenada),
    reverse(ListaOrdenada, ListaOrdenadaInvertida),
    listarJogos(ListaOrdenadaInvertida, StringSaida).

% 7. Deve ser possível listar os jogos com melhores avaliações;
listarJogoOrdemAvaliacoes(ListaJogos, ListaAvaliacoes, StringSaida) :-
   ordem_avaliacoes_insert_sort(ListaAvaliacoes, ListaJogos, ListaOrdenada),
   reverse(ListaOrdenada, ListaOrdenadaInvertida),
   listarJogos(ListaOrdenadaInvertida, StringSaida).

% 8. Deve ser possível listar as avaliações de um jogo. 
listarAvaliacoesJogo(JogoNomeProcurado, ListaAvaliacoes, ListaJogos, StringSaida) :-
    existeJogo(JogoNomeProcurado, ListaJogos, ExisteJogo),
    ExisteJogo =:= 1 -> 
        filterAvaliacoesJogo(JogoNomeProcurado, ListaAvaliacoes, Avaliacoes),
        mediaAvaliacoesJogo(JogoNomeProcurado, ListaAvaliacoes, MediaJogo),
        listarAvaliacoes(Avaliacoes, AvaliacoesSaida),
        format(atom(MediaJogoFormatada), "~2f", [MediaJogo]),
        colorString(MediaJogoFormatada, "blue", MediaJogoFormatadaColor),

        concatenate(["Media de ", JogoNomeProcurado, ": "], MediaDeString),
        colorString(MediaDeString, "green", MediaDeStringColor),

        concatenate([
            MediaDeStringColor, MediaJogoFormatadaColor, "\n\n",
            AvaliacoesSaida
            ], StringSaida);
        
        StringSaida = "Jogo nao encontrado.".

listarAvaliacoes([], StringSaida) :-
    colorString("Nenhuma avaliacao encontrada.", "red", StringSaida).
listarAvaliacoes(ListaAvaliacoes, StringSaida) :-
    listarAvaliacoesAux(ListaAvaliacoes, StringSaida).

listarAvaliacoesAux([], "").
listarAvaliacoesAux([X|XS], StringSaida) :-
    avaliacaoToString(X, AvaliacaoString),
    listarAvaliacoesAux(XS, ResultProx),
    atom_concat(AvaliacaoString, ResultProx, StringSaida).

% 10. Deve ser possível listar as avaliações de um usuário.
listarAvaliacoesUsuario(UsuarioNomeProcurado, ListaUsuarios, ListaAvaliacoes, StringSaida) :-
    existeUsuario(UsuarioNomeProcurado, ListaUsuarios, ExisteUsuario),
    ExisteUsuario =:= 0 -> 
        StringSaida = "Usuario inexistente.";

        listarAvaliacoesUsuarioAux(UsuarioNomeProcurado, ListaAvaliacoes, StringSaida).
    
listarAvaliacoesUsuarioAux(UsuarioNomeProcurado, ListaAvaliacoes, StringSaida) :-
    filterAvaliacoesPorUsuario(UsuarioNomeProcurado, ListaAvaliacoes, Avaliacoes),
    listarAvaliacoes(Avaliacoes, StringSaida).