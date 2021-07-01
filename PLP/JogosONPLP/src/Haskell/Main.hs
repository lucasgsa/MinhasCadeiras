import qualified Avaliacao as Avaliacao
import qualified CarregaArquivos as CarregaArquivos
import qualified Indicacao as Indicacao
import qualified Jogo as Jogo
import qualified Listagem as Listagem
import qualified Usuario as Usuario
import qualified Util as Util
import Data.List.Split ( splitOn )
import Data.Map as Map ( fromList, Map)
import Data.List
import System.IO.Unsafe(unsafeDupablePerformIO)
import Data.Typeable
import Data.Time.Calendar
import System.Process
import System.Info
import System.Directory
import System.FilePath.Posix

main :: IO ()
main = do      
    checarECriaArquivos  
    opcao  

checarECriaArquivos :: IO ()
checarECriaArquivos = do
    createDirectoryIfMissing True $ takeDirectory "dados/"
    existeUsuarios <- doesFileExist "dados/usuarios.txt"
    existeJogos <- doesFileExist "dados/jogos.txt"
    existeAvaliacoes <- doesFileExist "dados/avaliacoes.txt"
    if (not existeAvaliacoes) 
        then do 
            writeFile "dados/avaliacoes.txt" ""
            putStrLn ""
        else 
            (putStrLn "")
    if (not existeJogos) 
            then do 
                writeFile "dados/jogos.txt" ""
                putStrLn ""
            else 
                (putStrLn "")
    if (not existeUsuarios) 
            then do 
                writeFile "dados/usuarios.txt" ""
                putStrLn ""
            else 
                (putStrLn "")

aguardar :: IO()
aguardar = do
            putStrLn (Util.color "magenta" True "  Pressione ENTER para continuar...")
            enter <- getLine
            putStrLn ""

limparTela :: IO()
limparTela = do
                _ <- system comando
                return ()
            where comando = if (System.Info.os == "mingw32") then "cls" else "clear"
    
-- Método que retorna apenas um letreiro
aplicativo:: IO()
aplicativo = do
    putStrLn (Util.color "yellow" True "\n\n================================\n"
              ++ (Util.color "yellow" True "=========== ") ++  (Util.color "blue" False "JOGOS ") ++ (Util.color "green" True "ON") ++ (Util.color "yellow" True " ===========\n")
              ++ (Util.color "cyan" False "  Descubra seu jogo preferido!\n")
              ++ (Util.color "yellow" True "================================")
              )


-- Método que retorna o menu inicial
menu :: IO()
menu = do
    limparTela
    aplicativo
    putStr (Util.color "magenta" True "\nInforme a opção desejada: \n\n")    
    putStrLn (
        (Util.color "green" True "1. ") ++ "Cadastrar um Usuário\n" ++ 
        (Util.color "green" True "2. ") ++ "Cadastrar um Jogo\n" ++
        (Util.color "green" True "3. ") ++ "Listar Jogos\n" ++
        (Util.color "green" True "4. ") ++ "Listar as avaliações de um Jogo\n" ++
        (Util.color "green" True "5. ") ++ "Avaliar um Jogo\n" ++
        (Util.color "green" True "6. ") ++ "Pedir Indicação de um Jogo\n" ++
        (Util.color "green" True "7. ") ++ "Mostrar avaliações de um usuário\n" ++
        (Util.color "green" True "8. ") ++ "Sair\n"
        )

-- Método que permite o usuario digitar o numero que indica o que ele quer realizar.
opcao :: IO()
opcao = do
    menu

    putStr (Util.color "yellow" True " > ")
    input <- getLine

    if input == "1" then do
        cadastrarUsuario
    else if input == "2" then do        
        cadastrarJogo
    else if input == "3" then do
        listagemJogos    
    else if input == "4" then do
        listarAvaliacoesJogo
    else if input == "5" then do
        avaliarJogo
    else if input == "6" then do
        pedirIndicacaoJogo
    else if input == "7" then do
        listarAvaliacoesUsuario
    else if input == "8" then do 
        putStrLn((Util.color "magenta" True "Obrigado por utilizar nosso sistema."))
        putStrLn((Util.color "magenta" True "       Até a próxima ") ++ (Util.color "green" True ":)"))
        putStrLn((Util.color "red" True "     Programa finalizado!"))
    else do
       putStrLn((Util.color "red" True "Opção inválida"))
       aguardar
       opcao

-- Método que cadastra o Usuario, capturando o nome do Usuário.
cadastrarUsuario :: IO()
cadastrarUsuario = do
    usuarios <- CarregaArquivos.lerArquivoAvaliacoes "dados/usuarios.txt"
    let listaUsuarios = CarregaArquivos.carregarUsuarios usuarios
    putStrLn ("Cadastro de Usuário:")
    putStr ("   Insira o nome do Usuário: ")
    nome <- getLine
    if nome == "" then do 
        putStr(Util.color "red" False "   O nome não pode ser vazio.")
        aguardar
        cadastrarUsuario
    else if Usuario.existeUsuario nome listaUsuarios then do 
        putStrLn(Util.color "red" False "   Usuário já cadastrado.")
    else do 
        let novoUsuario = Usuario.Usuario{
            Usuario.nickname = nome
        }
        Usuario.salvarUsuario novoUsuario
        putStrLn(Util.color "green" False "   Usuário cadastrado com sucesso")
    aguardar
    opcao

-- Método que cadastra um jogo capturando o nome, as categorias, os requisitos minimos, a plataforma, 
-- o preco, o ano de lançamento do jogo e se é online ou não. 
cadastrarJogo :: IO()
cadastrarJogo = do
    jogos <- CarregaArquivos.lerArquivoJogos "dados/jogos.txt"
    let listaJogos = CarregaArquivos.carregarJogos jogos
    putStrLn ("Cadastrar um Jogo:\n")
    putStr("  Insira o nome do Jogo: ")
    nome <- getLine
    putStr("  Insira as categorias separadas por virgula: ")
    categorias <- getLine
    putStr("  Insira os requisitos minimo: ")
    reqMin <- getLine
    putStr("  Insira a plataforma: ")
    plataforma <- getLine
    putStr("  Insira o preco: ")
    preco <- getLine
    putStr("  O jogo é online? (s/n) ")
    isOnline <- getLine
    putStr("  Qual é o ano de lançamento? ")
    ano <- getLine

    

    let novoJogo = Jogo.Jogo{
        Jogo.nome = nome,
        Jogo.categorias = splitOn "," categorias,
        Jogo.reqMinimos = splitOn "," reqMin,
        Jogo.plataforma = plataforma,
        Jogo.preco = read(preco) :: Double,
        Jogo.online = if (isOnline == "s") then True else False,
        Jogo.anoLancamento = read(ano) :: Integer
    }

    if Jogo.existeJogo nome listaJogos  then do 
        putStrLn(Util.color "red" False "  Jogo já cadastrado")
    else do 
        Jogo.salvarJogo novoJogo
        putStrLn(Util.color "green" False "  Jogo cadastrado com sucesso")
    aguardar
    opcao


-- Metódo que retorna um menu dos tipos de listagem de jogos possiveis. 
listagemJogos:: IO()
listagemJogos = do 
    limparTela
    aplicativo
    putStr (Util.color "magenta" True "\nInforme a opção desejada para listagem: \n\n")    
    putStrLn (
        (Util.color "green" True "1. ") ++ "Listar todos os jogos disponíveis\n" ++ 
        (Util.color "green" True "2. ") ++ "Listar os últimos 5 jogos cadastrados\n" ++
        (Util.color "green" True "3. ") ++ "Listar os jogos por categoria\n" ++
        (Util.color "green" True "4. ") ++ "Listar os jogos por ordem de ano de Lançamento\n" ++
        (Util.color "green" True "5. ") ++ "Listar os jogos com as melhores avaliações\n" ++
        (Util.color "green" True "6. ") ++ "Sair \n" )
    opcaoListagem
  

-- Método que retorna as listagens de jogos de acordo com aquilo que usuário escolheu.
opcaoListagem:: IO()
opcaoListagem = do 
    jogos <- CarregaArquivos.lerArquivoJogos "dados/jogos.txt"
    let listaJogos = CarregaArquivos.carregarJogos jogos

    avaliacoes <- CarregaArquivos.lerArquivoAvaliacoes "dados/avaliacoes.txt"
    let listaAvaliacoes = CarregaArquivos.carregarAvaliacoes avaliacoes

    input <- getLine

    if input == "1" then do
        putStrLn("Jogos disponíveis:\n")
        putStrLn (Listagem.listarJogos listaJogos)
        aguardar
        listagemJogos
        
    else if input == "2" then do        
        putStrLn("Últimos 5 jogos:\n")
        putStrLn (Listagem.listarUltimosJogos listaJogos)
        aguardar
        listagemJogos
    else if input == "3" then do
        putStr("  Categoria que deseja ver: ")
        categoriaEscolhida <- getLine
        putStrLn (Listagem.listarJogosCategoria categoriaEscolhida listaJogos)
        aguardar
        listagemJogos

    else if input == "4" then do
        putStrLn("Jogos por ano de lançamento:\n")
        putStrLn (Listagem.listarJogosPorAnoLancamento listaJogos)
        aguardar
        listagemJogos
    else if input == "5" then do
        putStrLn("Jogos com as melhores avaliações:\n")
        putStrLn (Listagem.listaAvaliacoesOrdenada  listaJogos listaAvaliacoes)
        aguardar
        listagemJogos
    else if input == "6" then do 
        opcao   
    else do
        putStrLn(Util.color "red" False "  Opção inválida")
        aguardar
        opcao

-- Método que retorna as listagens de avaliações de um determinado jogo. 
listarAvaliacoesJogo:: IO()
listarAvaliacoesJogo = do 
    avaliacoes <- CarregaArquivos.lerArquivoAvaliacoes "dados/avaliacoes.txt"
    let listaAvaliacoes = CarregaArquivos.carregarAvaliacoes avaliacoes

    jogos <- CarregaArquivos.lerArquivoJogos "dados/jogos.txt"
    let listaJogos = CarregaArquivos.carregarJogos jogos
    
    putStr("\n    Insira o nome do jogo que você deseja ver suas avaliações: ")
    jogo <- getLine 
    if Jogo.existeJogo jogo listaJogos then do
        putStrLn("")
        putStrLn(Listagem.listarAvaliacoesJogo jogo listaJogos listaAvaliacoes)
        aguardar
        opcao
    else do 
        putStrLn(Util.color "red" False "  Jogo não cadastrado")
        aguardar
        opcao

-- Método que cadastra uma avaliação de um determinado jogo, capturando o nome do usuário que esta avaliando
-- o nome do jogo, a nota da avaliação e o comentario dessa avaliação.
avaliarJogo :: IO()
avaliarJogo = do
    jogos <- CarregaArquivos.lerArquivoJogos "dados/jogos.txt"
    let listaJogos = CarregaArquivos.carregarJogos jogos

    usuarios <- CarregaArquivos.lerArquivoAvaliacoes "dados/usuarios.txt"
    let listaUsuarios = CarregaArquivos.carregarUsuarios usuarios

    
    putStr("\n    Insira o nome do Usuário: ")
    nomeUsuario <- getLine
    putStr("  Insira o nome do Jogo: ")
    nomeJogo <- getLine
    putStr("  Insira a nota do Jogo: ")
    nota <- getLine
    putStr("  Insira um comentario: ")
    comentario <- getLine

    if Jogo.existeJogo nomeJogo listaJogos then do
        if Usuario.existeUsuario nomeUsuario listaUsuarios then do 
            let novaAvaliacao = Avaliacao.Avaliacao{
                Avaliacao.usuario = nomeUsuario,
                Avaliacao.jogo = nomeJogo,
                Avaliacao.nota = read(nota),
                Avaliacao.comentario = comentario
            }
            Avaliacao.salvarAvaliacao novaAvaliacao
            putStrLn(Util.color "green" False "  Avaliação cadastrada com sucesso")
            aguardar
            opcao
        else do 
            putStrLn(Util.color "red" False "  Usuario não cadastrado.")
            aguardar
            opcao
    else do 
        putStrLn(Util.color "red" False "  Jogo não cadastrado.")
        aguardar
        opcao

listarAvaliacoesUsuario :: IO()
listarAvaliacoesUsuario = do
    avaliacoes <- CarregaArquivos.lerArquivoAvaliacoes "dados/avaliacoes.txt"
    let listaAvaliacoes = CarregaArquivos.carregarAvaliacoes avaliacoes

    putStr("\n    Insira o nome do usuário: ")
    nome <- getLine 
    putStrLn(Listagem.listarAvaliacoesUsuario nome listaAvaliacoes)
    aguardar
    opcao

-- Método que retorna as indicações para um determinado usuário.
pedirIndicacaoJogo :: IO()
pedirIndicacaoJogo = do 
    jogos <- CarregaArquivos.lerArquivoJogos "dados/jogos.txt"
    avaliacoes <- CarregaArquivos.lerArquivoAvaliacoes "dados/avaliacoes.txt"
    usuarios <- CarregaArquivos.lerArquivoAvaliacoes "dados/usuarios.txt"

    let listaJogos = CarregaArquivos.carregarJogos jogos
    let listaAvaliacoes = CarregaArquivos.carregarAvaliacoes avaliacoes
    let listaUsuarios = CarregaArquivos.carregarUsuarios usuarios

    putStr("\n    Insira o nome do usuário: ")
    nome <- getLine 
    putStrLn(Indicacao.pedirIndicacao nome listaAvaliacoes listaJogos listaUsuarios)
    aguardar
    opcao
   