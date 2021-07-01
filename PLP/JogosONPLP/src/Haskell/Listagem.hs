module Listagem where
    import Util
    import Jogo
    import Avaliacao

    -- / 3. Deve ser possível listar todos os jogos disponíveis
    listarJogos :: [Jogo.Jogo] -> String
    listarJogos x = Util.color "blue" True "Todos os jogos: " ++ "\n" ++ listarJogosAux x

    listarJogosAux :: [Jogo.Jogo] -> String
    listarJogosAux [] = "Nenhum jogo encontrado."
    listarJogosAux [x] = show x
    listarJogosAux (x:xs) = show x ++ "\n\n" ++ listarJogosAux xs

    -- / 4. Deve ser possível listar os últimos jogos cadastrados no sistema;
    listarUltimosJogos :: [Jogo.Jogo ] -> String
    listarUltimosJogos listaJogos = listarJogosAux(take 5 (reverse listaJogos))

    -- / 5. Deve ser possível listar os jogos por categoria;
    listarJogosCategoria :: [Char] -> [Jogo] -> [Char]
    listarJogosCategoria categoria listaJogos = Util.color "blue" True ("Jogos da categoria "++categoria++": ") ++ "\n"
                                                ++listarJogosAux (filterCategoria categoria listaJogos)

    filterCategoria :: String -> [Jogo.Jogo] -> [Jogo.Jogo]
    filterCategoria categoria listaJogos = [ x | x <- listaJogos, categoriaContem categoria x]

    categoriaContem :: String -> Jogo -> Bool
    categoriaContem categoria jogo = (Util.toLowerString categoria) `elem` [ Util.toLowerString x | x <- Jogo.categorias jogo]

    -- / 6. Deve ser possível listar os jogos em ordem de lançamento.

    listarJogosPorAnoLancamento :: [Jogo.Jogo ] -> String
    listarJogosPorAnoLancamento listarJogos = listarJogosAux(take 10 (reverse(mergeSort listarJogos)))

    -- ordena pelo ano de lançamento
    mergeSort :: [Jogo.Jogo] -> [Jogo]
    mergeSort [] = []
    mergeSort xs
        | length xs == 1 = xs
        | otherwise = merge (mergeSort (primeiraParte xs)) (mergeSort (segundaParte xs))

    merge :: [Jogo.Jogo] -> [Jogo] -> [Jogo]
    merge xs [] = xs
    merge [] ys = ys
    merge (x:xs) (y:ys)
        | Jogo.anoLancamento x <= Jogo.anoLancamento y = x:merge xs (y:ys)
        | otherwise = y:merge (x:xs) ys

    primeiraParte :: [Jogo] -> [Jogo]
    primeiraParte  xs = let { n = length xs } in take (div n 2) xs

    segundaParte :: [Jogo] -> [Jogo]
    segundaParte xs = let { n = length xs } in drop (div n 2) xs

    -- / 8 - Deve ser possível listar as avaliações de um jogo. 
    listarAvaliacoesJogo :: String -> [Jogo] -> [Avaliacao] -> String
    listarAvaliacoesJogo _ _ []= "Não há avaliações no sistema."
    listarAvaliacoesJogo nomeJogo jogos avaliacoes
        |Jogo.existeJogo nomeJogo jogos = Util.color "red" True ("Avaliações do jogo "++Util.color "white" True nomeJogo ++ ":\n")
                                                        ++ (Util.color "green" False "Nota média do jogo: " ++ show (getMediaJogo nomeJogo avaliacoes) ++ "\n")
                                                        ++ listarAvaliacoesJogoAux nomeJogo avaliacoes
        |listarAvaliacoesJogoAux nomeJogo avaliacoes == "" = Util.color "red" True ("O jogo "++ nomeJogo ++ " não possui avaliações no sistema.")
        |otherwise = Util.color "red" True ("O jogo "++ nomeJogo ++ " não está cadastrado.")

    listarAvaliacoesJogoAux :: String -> [Avaliacao] -> String
    listarAvaliacoesJogoAux _ [] = ""
    listarAvaliacoesJogoAux nomeJogo (x:xs) = if (Util.toLowerString nomeJogo) == (Util.toLowerString (Avaliacao.jogo x))
                                            then show x ++ "\n\n" ++ listarAvaliacoesJogoAux nomeJogo xs
                                            else listarAvaliacoesJogoAux nomeJogo xs

    -- Retorna a média de um jogo.
    getMediaJogo :: String -> [Avaliacao] -> Double
    getMediaJogo _ [] = 0.0
    getMediaJogo nomeJogo avaliacoes = do
                                    let aux = getAvaliacoesJogo nomeJogo avaliacoes
                                    if snd aux == 0 then 0.0 else fst aux/ fromIntegral (snd aux)

    -- Retorna uma tupla, com o primeiro elemento contendo a soma das notas, e o segundo a quantidade de avaliacoes.
    -- Ou seja, a media é so fazer fst/snd.
    getAvaliacoesJogo :: String -> [Avaliacao] -> (Double, Integer)
    getAvaliacoesJogo _ [] = (0.0, 0)
    getAvaliacoesJogo nomeJogo (x:xs) = do
                                    let atual = (if ((Util.toLowerString nomeJogo) == (Util.toLowerString (Avaliacao.jogo x)))
                                        then (Avaliacao.nota x, 1) else (0,0))
                                    (fst atual + fst prox, snd atual + snd prox)
                                    where prox = getAvaliacoesJogo nomeJogo xs

    -- / 10. Deve ser possível listar as avaliações de um usuário.
    listarAvaliacoesUsuario :: String -> [Avaliacao] -> String
    listarAvaliacoesUsuario nomeUser avaliacoes = do
                                                    Util.color "red" True ("Avaliações do usuário "++Util.color "white" True nomeUser ++ ":\n")
                                                    ++ if ((length avaliacoesUser) == 0)
                                                        then 
                                                            Util.color "yellow" False ("Nada foi avaliado ainda!")
                                                        else 
                                                            listarAvaliacoesUsuarioAux avaliacoesUser
                                                    where avaliacoesUser = (avaliadosPorAux nomeUser avaliacoes)

    listarAvaliacoesUsuarioAux :: [Avaliacao] -> String
    listarAvaliacoesUsuarioAux [] = ""
    listarAvaliacoesUsuarioAux (x:xs) = show (x) ++ "\n" 
                                        ++ listarAvaliacoesUsuarioAux xs

    -- 7. Deve ser possível listar os jogos com melhores avaliações;
    listaAvaliacoesOrdenada :: [Jogo.Jogo] -> [Avaliacao]-> String
    listaAvaliacoesOrdenada listaJogos listaAvaliacoes = listarJogosAux(reverse (ordenaAvaliacoes listaJogos listaAvaliacoes))

    -- / Ordena pela nota do jogo.
    ordenaAvaliacoes :: [Jogo.Jogo] -> [Avaliacao] -> [Jogo.Jogo]
    ordenaAvaliacoes [] _ = []
    ordenaAvaliacoes (s:xs) z = ordenaAvaliacoes [x|x <- xs, getMediaJogo (Jogo.nome x) z < getMediaJogo (Jogo.nome s) z] z
                                    ++ [s] ++
                                ordenaAvaliacoes [x|x <- xs,getMediaJogo (Jogo.nome x) z >= getMediaJogo (Jogo.nome s) z] z