module CarregaArquivos where
    import Util as Util
    import Jogo as Jogo
    import Usuario as Usuario
    import Avaliacao as Avaliacao
    import Data.List.Split (splitOn)
    import qualified System.IO.Strict as Strict

    -- / Le o arquivo de dados de usuarios, e retorna uma lista de string que cada uma armazena um usuario.
    lerArquivoUsuarios :: String -> IO([String])
    lerArquivoUsuarios path = do
        arquivo <- Strict.readFile path
        let listaUsuarios = lines arquivo
        return listaUsuarios

    carregarUsuarios :: [String] -> [Usuario]
    carregarUsuarios lista = [decodeUsuario x | x <- lista]

    decodeUsuario :: String -> Usuario
    decodeUsuario line = Usuario {
                            Usuario.nickname = line
    }

    -- / Le o arquivo de dados de avaliações, e retorna uma lista de string que cada uma armazena uma avaliação.
    lerArquivoAvaliacoes :: String -> IO([String])
    lerArquivoAvaliacoes path = do
        arquivo <- Strict.readFile path
        let listaAvaliacoes = lines arquivo
        return listaAvaliacoes
    
    -- / Retorna uma lista com as avaliações a partir de uma lista de strings contendo as avaliações.
    carregarAvaliacoes :: [String] -> [Avaliacao]
    carregarAvaliacoes lista = [decodeAvaliacao x | x <- lista]
    
    -- / A partir da string gerada da avaliação, retorna a Avaliação.
    decodeAvaliacao :: String -> Avaliacao
    decodeAvaliacao line = Avaliacao {
                                Avaliacao.usuario = paramAvaliacao!!0,
                                Avaliacao.jogo = paramAvaliacao!!1,
                                Avaliacao.nota = read (paramAvaliacao!!2) :: Double,
                                Avaliacao.comentario = paramAvaliacao!!3
                            }
                        where paramAvaliacao = splitOn "|" line

    -- / Le o arquivo de dados de jogos, e retorna uma lista de strings que armazena cada jogo.
    lerArquivoJogos :: String -> IO([String])
    lerArquivoJogos path = do
        arquivo <- Strict.readFile path
        let listaJogos = lines arquivo
        return listaJogos

    -- / Retorna uma lista com os jogos a partir de uma lista de strings contendo os jogos.
    carregarJogos :: [String] -> [Jogo]
    carregarJogos lista = [decodeJogo x | x <- lista]

    -- / A partir da string gerada do jogo, retorna o Jogo.
    decodeJogo :: String -> Jogo
    decodeJogo line = Jogo {
                        Jogo.nome = paramJogo!!0,
                        Jogo.categorias = Util.parseStringList (paramJogo!!1),
                        Jogo.reqMinimos = Util.parseStringList (paramJogo!!2),
                        Jogo.plataforma = paramJogo!!3,
                        Jogo.preco = read (paramJogo!!4) :: Double,
                        Jogo.online = if (paramJogo!!5 == "True") then True else False,
                        Jogo.anoLancamento = read (paramJogo!!6) :: Integer
                    }
                where paramJogo = splitOn "|" line