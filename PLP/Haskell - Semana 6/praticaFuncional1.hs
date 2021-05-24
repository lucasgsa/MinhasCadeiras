diasParaAtingir :: Int -> Int -> Int
diasParaAtingir atual total | atual <= 0 = -1
                            | atual >= total = 0
                            | otherwise = 3 + diasParaAtingir (atual*3) (total)

main = do
    doentesAtual <- getLine
    populacaoTotal <- getLine
    let doentesInteger = read doentesAtual :: Int
    let populacaoInteger = read populacaoTotal :: Int
    putStrLn ((show (diasParaAtingir doentesInteger populacaoInteger)) ++ " dias")