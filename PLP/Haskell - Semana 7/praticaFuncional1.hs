subs :: Char -> Char -> String -> String
subs l1 l2 x = [if (i == l1) then l2 else i | i <- x]
                        
main = do
    frase <- getLine
    letra1 <- getChar
    getLine
    letra2 <- getChar
    putStrLn (subs letra1 letra2 frase)