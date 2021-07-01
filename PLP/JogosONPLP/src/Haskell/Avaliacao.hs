module Avaliacao where
import Util as Util

data Avaliacao = Avaliacao {
  usuario :: String,
  jogo :: String,
  nota :: Double,
  comentario :: String
} deriving (Eq,Ord)

instance Show Avaliacao where
  show (Avaliacao usuario jogo nota comentario) = Util.color "blue" True usuario ++ Util.color "magenta" False " - " ++ Util.color "red" False jogo ++ Util.color "magenta" False " - " ++"Nota: " ++  show nota ++ "\n"
                                                  ++ Util.color "yellow" True " Comentário: " ++ comentario

-- / Retorna os jogos avaliados pelo usuário.
avaliadosPor :: String -> [Avaliacao] -> [String]
avaliadosPor usuario avaliacoes = [Avaliacao.jogo x | x <- avaliadosPorAux usuario avaliacoes]

avaliadosPorAux :: String -> [Avaliacao] -> [Avaliacao]
avaliadosPorAux _ [] = []
avaliadosPorAux usuario (x:xs) = if (Util.toLowerString (Avaliacao.usuario x)) == (Util.toLowerString usuario)
                              then x : (avaliadosPorAux usuario xs)
                              else (avaliadosPorAux usuario xs)

-- / Retorna a nota que foi dada por um usuário a um jogo.
notaDada :: String -> String -> [Avaliacao] -> Double
notaDada _ _ [] = 0
notaDada user jogo (x:xs) = if ((Util.toLowerString (Avaliacao.usuario x)) == (Util.toLowerString user)) 
                              && ((Util.toLowerString (Avaliacao.jogo x)) == (Util.toLowerString jogo))
                            then Avaliacao.nota x
                            else notaDada user jogo xs

salvarAvaliacao :: Avaliacao.Avaliacao -> IO()
salvarAvaliacao avaliacao =  do
  -- TODO: Verificar se o jogo e o usuário estão cadastrados (Case Insensitive)
  let avaliacaoStr = Avaliacao.usuario avaliacao ++ "|" ++ Avaliacao.jogo avaliacao ++ "|" ++  show (Avaliacao.nota avaliacao) ++ "|" ++  Avaliacao.comentario avaliacao
  appendFile "dados/avaliacoes.txt" (avaliacaoStr ++ "\n")
  return ()