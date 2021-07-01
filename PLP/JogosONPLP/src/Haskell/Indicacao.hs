module Indicacao where
    import Util
    import Jogo
    import Avaliacao
    import Listagem
    import Usuario
    import Data.List.Split (splitOn)

    -- / 11. Um usuário poderá pedir uma indicação de um jogo (A partir das avaliações do usuário).
    -- Dado o nome do usuário, a lista de avaliações e a lista de jogos, deve ser retornado uma String representando o jogo indicado pelo sistema.
    pedirIndicacao :: String -> [Avaliacao] -> [Jogo] -> [Usuario] -> String
    pedirIndicacao nomeUser avaliacoes jogos usuarios = if (Usuario.existeUsuario nomeUser usuarios) 
                                                        then show (indicarJogo nomeUser avaliacoes jogos)
                                                        else Util.color "red" True "Usuário inexistente!"

    -- / Dado o nome do usuário, a lista de avaliações e a lista de jogos, deve ser retornado o jogo indicado.
    indicarJogo :: String -> [Avaliacao] -> [Jogo] -> Jogo
    indicarJogo nomeUser avaliacoes jogos = do 
                                            let jogosNaoAvaliados = retiraAvaliado jogos avaliacoes nomeUser
                                            let tupla = geraTuplaPontuacao (Listagem.ordenaAvaliacoes jogosNaoAvaliados avaliacoes)
                                            let tuplaFinal = adicionarSimilaridadesLista nomeUser avaliacoes jogos tupla
                                            fst ((reverse (ordenaTupla tuplaFinal))!!0)  

    -- / Ordena a tupla de pontuações.
    ordenaTupla :: [(Jogo, Double)] -> [(Jogo, Double)]
    ordenaTupla [] = []
    ordenaTupla (s:xs) = ordenaTupla [x | x <- xs, (snd x) < (snd s)]
                        ++ [s] ++
                         ordenaTupla [x | x <- xs, (snd x) >= (snd s)]

    -- / Dada uma tupla por exemplo [(Jogo1, 0), (Jogo2, 0), (Jogo3, 0)]
    -- A função retorna a tupla pontuada de acordo com os jogos avaliados pelo usuario para cada jogo.
    -- Ex: [(Jogo1, 102.2), (Jogo2, 93), (Jogo3, 105.1)]
    -- Nesse caso, o jogo que indicaria seria o Jogo3
    adicionarSimilaridadesLista :: String -> [Avaliacao] -> [Jogo] -> [(Jogo, Double)] -> [(Jogo, Double)]
    adicionarSimilaridadesLista nomeUser avaliacoes [x] tupla = adicionarSimilaridades x (Avaliacao.notaDada nomeUser (Jogo.nome x) avaliacoes) tupla
    adicionarSimilaridadesLista nomeUser avaliacoes (x:xs) tupla = adicionarSimilaridades x (Avaliacao.notaDada nomeUser (Jogo.nome x) avaliacoes)  (adicionarSimilaridadesLista nomeUser avaliacoes xs tupla) 

    -- /  Dada uma lista de jogos, uma lista de avaliações e o nome de usuário, retorna apenas os jogos que não foram ainda avaliados pelo usuário.
    retiraAvaliado :: [Jogo] -> [Avaliacao] -> String -> [Jogo]
    retiraAvaliado [] _ nomeUser = []
    retiraAvaliado (x:xs) avaliacoes nomeUser = if ((Jogo.nome x) `elem` (Avaliacao.avaliadosPor nomeUser avaliacoes))
                                                then retiraAvaliado xs avaliacoes nomeUser 
                                                else x : (retiraAvaliado xs avaliacoes nomeUser)

    -- / A partir de uma lista de jogos [Jogo1, Jogo2, Jogo3]
    -- gera uma lista de tuplas para pontuação [(Jogo1, 0), (Jogo2, 0), (Jogo3, 0)]
    geraTuplaPontuacao :: [Jogo] -> [(Jogo, Double)]
    geraTuplaPontuacao [] = []
    geraTuplaPontuacao (x:xs) = ( x , 0) : (geraTuplaPontuacao xs)

    -- / A partir de um jogo, e uma tupla, pontua os jogos a partir da similaridade do jogo e a nota dada ao jogo pelo usuário.
    -- Ex: [(Jogo1, 0), (Jogo2, 0), (Jogo3, 0)] com Jogo4
    -- Similaridades: Jogo1 = 10, Jogo2 = 13, Jogo3 = 8
    -- Notas: Jogo1 = 8.1, Jogo2 = 9, Jogo3 = 7
    -- A tupla de pontuações gerada será [(Jogo1, 81), (Jogo2, 117), (Jogo3, 56)]
    adicionarSimilaridades :: Jogo -> Double -> [(Jogo, Double)] -> [(Jogo, Double)]
    adicionarSimilaridades _ nota [] = []
    adicionarSimilaridades jogo nota (x:xs) = ( fst (x)  , (((similaridadeJogo jogo (fst x)) * nota) + snd(x)) ) : (adicionarSimilaridades jogo nota xs)

    -- / Retorna um double com a similaridade entre dois jogos, cada categoria igual soma 2.5 pontos.
    -- cada plataforma igual soma 1 ponto.
    -- se os dois forem online ou os dois forem offline, soma 1 ponto.
    -- se a diferença da data de lançamento dos dois for menor que 5, soma 1 ponto.
    similaridadeJogo :: Jogo -> Jogo -> Double
    similaridadeJogo jogo1 jogo2 = (qtdCount (Jogo.categorias jogo1) (Jogo.categorias jogo2))*2.5
                                  + (qtdCount (splitOn "/" (Jogo.plataforma jogo1)) (splitOn "/" (Jogo.plataforma jogo2)))
                                  + (if (Jogo.online jogo1) == (Jogo.online jogo2) then 1 else 0)
                                  + (if integerModule((Jogo.anoLancamento jogo1) - (Jogo.anoLancamento jogo2)) <= 5 then 1 else 0)

    -- / Retorna a quantidade de valores da Lista1 em Lista2.
    qtdCount :: [String] -> [String] -> Double
    qtdCount [] _ = 0
    qtdCount (x:xs) listaGeral = if x `elem` listaGeral
                                then 1 + qtdCount xs listaGeral
                                else qtdCount xs listaGeral

    -- / Retorna o módulo do número, 
    -- ex: -10 retorna +10
    -- ex: +10 retorna +10
    integerModule :: Integer -> Integer
    integerModule x | x < 0 = x*(-1)
                    | otherwise = x