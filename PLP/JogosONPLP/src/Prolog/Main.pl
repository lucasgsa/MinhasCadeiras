:- include('Util.pl').
:- include('Listagem.pl').
:- include('CarregaArquivos.pl').

main :- 
    letreiroInicial,
    menu.


letreiroInicial:- 
    tty_clear,
    writeln("============================"),
    writeln("DESCUBRA SEU JOGO PREFERIDO!"),
    writeln("============================").


menu:-
    tty_clear,
    writeln("Informe a opção desejada:"),nl,
    write("1. Cadastrar um Usuário "),nl,
    write("2. Cadastrar um Jogo"),nl,
    write("3. Listar Jogos"),nl,
    write("4. Listar as avali1ações de um Jogo"),nl,
    write("5. Avaliar um Jogo"),nl,
    write("6. Mostrar avaliações de um usuário"),nl,
    write("7. Sair"),nl,
    write(""),
    lerNumero(Input),
    opcao(Input).
    

lerString(X):- read_line_to_codes(user_input, E), atom_string(E,X).
lerNumero(Numero):- read_line_to_codes(user_input, E), atom_string(E,X), atom_number(X,Numero).


/*-----------------OPÇÃO ESCOLHIDA-------------------*/

opcao(1):- 
    cadastrarUsuario.
    
opcao(2):- 
    cadastrarJogo.
    
opcao(3):- 
    listarJogosMenu.

opcao(4):- 
    listarAvaliacoes.   

opcao(5):- 
    avaliarJogo.

opcao(6):- 
    listarAvaliacoesUsuario.

opcao(7):- 
    writeln("Programa Finalizado...").

opcao(N):-
    write("Opção "), write(N), write(" Inválida"),nl,
    write("Pressione Enter para continuar."),nl,
    lerString(_),
    menu.

/*---------------- CADASTRO DE USUARIO --------------------*/

cadastrarUsuario:-
    tty_clear,
    listaUsuarios("dados/usuarios.txt", ListaUsuarios),
    writeln("Cadastro de Usuário:"),
    writeln("Insira o nome do Usuário: "),
    lerString(Nome),
    construirUsuario(Nome, Usuario),
    existeUsuario(Nome, ListaUsuarios, Resposta),
    cadastrarUsuarioAux(Usuario, Resposta).

cadastrarUsuarioAux(_, 1) :-
    writeln("Usuario já existente!"),
    pressToContinue.

cadastrarUsuarioAux(Usuario, 0) :-
    salvarUsuario(Usuario),
    writeln("Usuario cadastrado com sucesso!"),
    pressToContinue.


/*-----------------CADASTRO DE JOGO ------------------*/

cadastrarJogo:- 
    tty_clear,
    listaJogos("dados/jogos.txt", ListaJogos),
    writeln("Cadastrar um Jogo:"),
    writeln("Insira o nome do Jogo:"),
    lerString(NomeJogo),
    writeln("Insira as categorias separadas por virgula: "),
    lerString(Categorias),
    writeln("Insira os requisitos minimos: "),
    lerString(Requisitos),
    writeln("Insira a plataforma:"),
    lerString(Plataforma),
    writeln("Insira o preço: "),
    lerNumero(Preco),
    writeln("O jogo é online? (s/n) "),
    lerString(IsOnline),
    processarIsOnline(IsOnline, IsOnlineResponse),
    writeln("Qual o ano de lançamento"),
    lerNumero(Ano),
    contruirJogo(NomeJogo, Categorias, Requisitos, Plataforma, Preco, IsOnlineResponse, Ano, Jogo),
    existeJogo(NomeJogo, ListaJogos, Resposta),
    cadastrarJogoAux(Jogo, Resposta).

cadastrarJogoAux(Jogo, 0) :-
    salvarJogo(Jogo),
    writeln("Jogo cadastrado com sucesso"),
    pressToContinue.

cadastrarJogoAux(_, 1) :-
    writeln("Jogo já está cadastrado."),
    pressToContinue.

processarIsOnline("s", 1).
processarIsOnline("n", 0).
processarIsOnline(_, 2). 
    

/*------------------ LISTAGEM DE JOGOS -----------------------*/

listarJogosMenu:-
    tty_clear,
    ttyflush,
    writeln("Informe a opção desejada para listagem:"),nl,
    write("1. Listar todos os jogos disponíveis "),nl,
    write("2. Listar os últimos 5 jogos cadastrados"),nl,
    write("3. Listar os jogos por categoria"),nl,
    write("4. Listar os jogos por ordem de ano de Lançamento"),nl,
    write("5. Listar os jogos com as melhores avaliações"),nl,
    write("6. Sair"),nl,
    write(""),
    lerNumero(Input),
    listarJogos(Input).



listarJogos(1):-
    listaJogos("dados/jogos.txt", ListaJogos),
    writeln(""),
    writeln("Jogos disponíveis:"),
    listarJogos(ListaJogos, SaidaString),
    writeln(SaidaString),
    write("Pressione Enter para continuar."),nl,
    lerString(_),
    listarJogosMenu.

listarJogos(2):-
    listaJogos("dados/jogos.txt", ListaJogos),
    writeln("Últimos 5 jogos:"),
    listarUltimosJogosCadastrados(ListaJogos, SaidaString),
    writeln(SaidaString),
    write("Pressione Enter para continuar."),nl,
    lerString(_),
    listarJogosMenu.

listarJogos(3):-
    listaJogos("dados/jogos.txt", ListaJogos),
    writeln("Categoria que deseja ver:"),
    lerString(Categoria),
    listarJogosCategoria(Categoria, ListaJogos, SaidaString),
    writeln(SaidaString),
    write("Pressione Enter para continuar."),nl,
    lerString(_),
    listarJogosMenu.

listarJogos(4):-
    listaJogos("dados/jogos.txt", ListaJogos),
    writeln("Jogos por ano de lançamento:"),
    listarJogosOrdemLancamento(ListaJogos, SaidaString),
    writeln(SaidaString),
    write("Pressione Enter para continuar."),nl,
    lerString(_),
    listarJogosMenu.

listarJogos(5):-
    listaJogos("dados/jogos.txt", ListaJogos),
    listaAvaliacoes("dados/avaliacoes.txt", ListaAvaliacoes),
    writeln("Jogos com as melhores avaliações:"),
    listarJogoOrdemAvaliacoes(ListaJogos, ListaAvaliacoes, SaidaString),
    writeln(SaidaString),
    write("Pressione Enter para continuar."),nl,
    lerString(_),
    listarJogosMenu.

listarJogos(6):-
    menu.

listarJogo(N):-
    write("Opção "), write(N), write(" Inválida"),nl,
    write("Pressione Enter para continuar."),nl,
    lerString(_),
    menu.


/*--------------- LISTAGEM DAS AVALIAÇÕES -------------*/

listarAvaliacoes:- 
    listaJogos("dados/jogos.txt", ListaJogos),
    listaAvaliacoes("dados/avaliacoes.txt", ListaAvaliacoes),
    writeln("Insira o nome do jogo que você deseja ver suas avaliações:" ),
    lerString(NomeJogo),
    listarAvaliacoesJogo(NomeJogo, ListaAvaliacoes, ListaJogos, SaidaString),
    writeln(SaidaString),
    pressToContinue.



/*-------------- AVALIAR UM JOGO --------------- */

avaliarJogo:-
    writeln("Insira o nome do Usuário:"),
    lerString(NomeUsuario),
    writeln("Insira o nome do Jogo: "),
    lerString(NomeJogo),
    writeln("Insira a nota do Jogo: "),
    lerNumero(Nota),
    writeln("Insira um comentário: "),
    lerString(Comentario),
    construirAvaliacao(NomeUsuario, NomeJogo, Nota, Comentario, Avaliacao),
    salvarAvaliacao(Avaliacao),
    writeln("Avaliação cadastrada com sucesso"),
    pressToContinue.


/*------  LISTAGEM DE AVALIAÇÃO DE UM USUARIO -------*/

listarAvaliacoesUsuario:-
    listaAvaliacoes("dados/avaliacoes.txt", ListaAvaliacoes),
    listaUsuarios("dados/usuarios.txt", ListaUsuarios),
    writeln("Insira o nome do Usuário: "),
    lerString(Nome),
    listarAvaliacoesUsuario(Nome, ListaUsuarios, ListaAvaliacoes, SaidaString),
    writeln(SaidaString),
    pressToContinue.

pressToContinue:-
    writeln("Pressione Enter para continuar.."),
    lerString(_),
    tty_clear,
    menu.