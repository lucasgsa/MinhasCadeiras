module Jogo where
import Util

data Jogo = Jogo {
  nome :: String,
  categorias :: [String],
  reqMinimos :: [String],
  plataforma :: String,
  preco :: Double,
  online :: Bool,
  anoLancamento :: Integer
} deriving (Eq,Ord)

instance Show Jogo where
  show (Jogo nome categorias reqMinimos plataforma preco online anoLancamento) = Util.color "green" True "  Título: " ++ Util.color "cyan" False nome ++ "\n"
                                                                   ++ Util.color "white" False ("   Plataforma: " ++ plataforma) ++ "\n"
                                                                   ++ Util.color "white" False ("   Requisitos Mínimos: " ++ (convertArrayToString reqMinimos)) ++ "\n"
                                                                   ++ Util.color "white" False ("   Gêneros: " ++ (convertArrayToString categorias)) ++ "\n"
                                                                   ++ Util.color "white" False ("   Ano lançamento: " ++ show anoLancamento) ++ "\n"
                                                                   ++ Util.color "yellow" False ("   " ++ (if online then "Cooperativo online" else "Um jogador")) ++ "\n"
                                                                   ++ Util.color "red" True ("   Preço: " ++ (if (preco /= 0) then ("R$" ++ show preco) else "Grátis"))
convertArrayToString :: [String] -> String
convertArrayToString [h] = h
convertArrayToString (h:t) = h ++ "," ++ convertArrayToString t

getArrayToString :: [String] -> String
getArrayToString array = "[" ++ convertArrayToString array ++ "]"

salvarJogo :: Jogo.Jogo -> IO()
salvarJogo jogo = do
  -- TODO: É necessário veriricar se já existe um jogo com esse nome (Case Insensitive)
  let jogoStr = Jogo.nome jogo ++ "|" ++ getArrayToString (Jogo.categorias jogo) ++ "|" ++ getArrayToString (Jogo.reqMinimos jogo) ++ "|" ++ Jogo.plataforma jogo ++ "|" ++ show (Jogo.preco jogo) ++ "|" ++ show (Jogo.online jogo) ++ "|" ++ show (Jogo.anoLancamento jogo)
  appendFile "dados/jogos.txt" (jogoStr ++ "\n")
  return ()

existeJogo :: String -> [Jogo] -> Bool
existeJogo _ [] = False
existeJogo nomeJogo (x:xs) = ( (Util.toLowerString (Jogo.nome x)) == (Util.toLowerString nomeJogo) )
                             || existeJogo nomeJogo xs