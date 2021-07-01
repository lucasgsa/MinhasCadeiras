module Util where
    import Data.List.Split (splitOn)
    import Data.Char (isLetter, toLower)

    color :: String -> Bool -> String -> String
    color "nocolor" _ _   = "\ESC[0m"
    color "black"   bold texto = (if bold then "\ESC[1m" else "\ESC[0m") ++ "\ESC[30m" ++ texto ++ "\ESC[0m"
    color "red"     bold texto = (if bold then "\ESC[1m" else "\ESC[0m") ++ "\ESC[31m" ++ texto ++ "\ESC[0m"
    color "green"   bold texto = (if bold then "\ESC[1m" else "\ESC[0m") ++ "\ESC[32m" ++ texto ++ "\ESC[0m"
    color "yellow"  bold texto = (if bold then "\ESC[1m" else "\ESC[0m") ++ "\ESC[33m" ++ texto ++ "\ESC[0m"
    color "blue"    bold texto = (if bold then "\ESC[1m" else "\ESC[0m") ++ "\ESC[34m" ++ texto ++ "\ESC[0m"
    color "magenta" bold texto = (if bold then "\ESC[1m" else "\ESC[0m") ++ "\ESC[35m" ++ texto ++ "\ESC[0m"
    color "cyan"    bold texto = (if bold then "\ESC[1m" else "\ESC[0m") ++ "\ESC[36m" ++ texto ++ "\ESC[0m"
    color "white"   bold texto = (if bold then "\ESC[1m" else "\ESC[0m") ++ "\ESC[37m" ++ texto ++ "\ESC[0m"

    -- Dado uma string ex: "[1,2,3]" a função retira os colchetes, e aplica um split, ou seja, retornaria [1,2,3].
    parseStringList :: String -> [String]
    parseStringList linha = splitOn "," (retiraBracketsStringList linha)

    -- Retira o primeiro e último caractere de uma String, ou seja, para o caso de "[1,2,3]", retorna "1,2,3".
    retiraBracketsStringList :: String -> String
    retiraBracketsStringList linha = reverse(drop 1(reverse (drop 1 linha)))

    toLowerString :: [Char] -> [Char]
    toLowerString str = [ toLower x | x <- str]