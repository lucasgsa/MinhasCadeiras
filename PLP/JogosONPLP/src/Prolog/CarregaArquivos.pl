listaAvaliacoes(Path, ListaAvaliacoes) :-
    carregarAvaliacoes(Path, ListaBruta),
    processarListaBrutaAvaliacoes(ListaBruta, ListaAvaliacoes).

processarListaBrutaAvaliacoes([], []).
processarListaBrutaAvaliacoes([Atual|XS], ListaAvaliacoes) :-
    split_string(Atual, "|", " | ", AtualDividido),
    nth0(0, AtualDividido, Nome),
    nth0(1, AtualDividido, Jogo),
    nth0(2, AtualDividido, NotaBruta),
    atom_number(NotaBruta, Nota),
    
    nth0(3, AtualDividido, Comentario),
    construirAvaliacao(Nome, Jogo, Nota, Comentario, AvaliacaoAtual),
    processarListaBrutaAvaliacoes(XS, ListaProx),
    append([AvaliacaoAtual], ListaProx, ListaAvaliacoes).

carregarAvaliacoes(Path, Avaliacoes) :-
    open(Path, read, Stream),
    read_file(Stream,ListaAvaliacoesString),
    Avaliacoes = ListaAvaliacoesString,
    close(Stream).

listaJogos(Path, ListaJogos) :-
    carregarJogos(Path, ListaBruta),
    processarListaBrutaJogos(ListaBruta, ListaJogos).

processarListaBrutaJogos([], []).
processarListaBrutaJogos([Atual|XS], ListaJogos) :-
    split_string(Atual, "|", " | ", AtualDividido),

    nth0(0, AtualDividido, Nome),
    
    nth0(1, AtualDividido, CategoriasBruto),
    split_string(CategoriasBruto, ",", ", ", Categorias),

    nth0(2, AtualDividido, ReqMinimosBruto),
    split_string(ReqMinimosBruto, ",", ", ", ReqMinimos),

    nth0(3, AtualDividido, Plataforma),
    
    nth0(4, AtualDividido, PrecoBruto),
    atom_number(PrecoBruto, Preco),

    nth0(5, AtualDividido, IsOnlineBruto),
    atom_number(IsOnlineBruto, IsOnline),

    nth0(6, AtualDividido, AnoLancamentoBruto),
    atom_number(AnoLancamentoBruto, AnoLancamento),

    contruirJogo(Nome, Categorias, ReqMinimos, Plataforma, Preco, IsOnline, AnoLancamento, JogoAtual),
    processarListaBrutaJogos(XS, ListaProx),
    append([JogoAtual], ListaProx, ListaJogos).

carregarJogos(Path, Jogos) :-
    open(Path, read, Stream),
    read_file(Stream,ListaJogosString),
    Jogos = ListaJogosString,
    close(Stream).

listaUsuarios(Path, ListaUsuarios) :-
    carregarUsuarios(Path, ListaBruta),
    processarListaBrutaUsuarios(ListaBruta, ListaUsuarios).

processarListaBrutaUsuarios([], []).
processarListaBrutaUsuarios([Atual|XS], ListaUsuarios) :-
    construirUsuario(Atual, UsuarioAtual),
    processarListaBrutaUsuarios(XS, ListaProx),
    append([UsuarioAtual], ListaProx, ListaUsuarios).


carregarUsuarios(Path, Usuarios) :-
    open(Path, read, Stream),
    read_file(Stream,ListaUsuarios),
    Usuarios = ListaUsuarios,
    close(Stream).