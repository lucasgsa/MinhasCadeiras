-- / Retira uma dupla de chaves da string.
-- Ou seja, tira a primeira dupla de "{}" que achar.
-- @ Exemplos
-- ghci > retiraDupla "{}{}"
-- {}
-- ghci > retiraDupla "}}{{"
-- }}{{
retiraDupla :: String -> String
retiraDupla [] = ""
retiraDupla (x:xs) | xs == [] = [x]
                   | (x == '{' && (head xs) == '}') = (tail xs)
                   | otherwise = [x]++(retiraDupla xs)

-- / Retira recursivamente as duplas de "{}", ou seja, sempre que conseguir retirar uma dupla, ele tenta novamente
-- até que não seja mais possível retirar nenhuma dupla.
recursiveRetiraDupla :: String -> String    
recursiveRetiraDupla x | ((retiraDupla x) /= x) = recursiveRetiraDupla (retiraDupla x)
                       | otherwise = x
                    
-- / Retorna a quantidade de caracteres da string.   
contaCaracteres :: String -> Int
contaCaracteres [] = 0
contaCaracteres (x:xs) = 1 + (contaCaracteres xs)

{- Recebe uma entrada, usa a função recursiva de retirar as duplas
caso a saida ainda tenha caracteres sobrando, significa que não é válida. -}
main = do
    entrada <- getLine
    let saida = (recursiveRetiraDupla entrada)
    let valido = (contaCaracteres saida) == 0
    putStrLn ("O programa "++entrada++" "++(if (valido) then "eh valido" else "nao eh valido"))