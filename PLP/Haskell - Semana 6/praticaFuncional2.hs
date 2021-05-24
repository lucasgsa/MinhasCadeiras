desempenhoCorrendo :: Float -> Float -> String
desempenhoCorrendo km velocidade | km > 10 || velocidade > 12 = "Desempenho excelente"
                                 | km >= 5 = "Desempenho medio"
                                 | otherwise = "Baixo desempenho"

desempenhoPedalando :: Float -> Float -> String
desempenhoPedalando km velocidade | km > 24 || velocidade > 20 = "Desempenho excelente"
                                 | km >= 15 = "Desempenho medio"
                                 | otherwise = "Baixo desempenho"

desempenho :: Int -> Float -> Float -> String
desempenho tipo km tempo | tipo == 1 = desempenhoCorrendo km (velocidade)
                         | tipo == 2 = desempenhoPedalando km (velocidade)
                         | otherwise = "Tipo desconhecido."
                         where velocidade = km/tempo

main = do
    tipo <- getLine
    km <- getLine
    tempo <- getLine
    let tipoInteger = read tipo :: Int
    let kmFloat = read km :: Float
    let tempoFloat = read tempo :: Float
    putStrLn (desempenho tipoInteger kmFloat tempoFloat)