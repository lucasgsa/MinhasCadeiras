module Usuario where 
import Util

data Usuario = Usuario {
  nickname:: String
} deriving (Show, Eq, Ord)

salvarUsuario :: Usuario.Usuario -> IO()
salvarUsuario usuario = do
  -- TODO: É necessário veriricar se já existe um usuário com esse nick (Case Insensitive)
  let usuarioStr = Usuario.nickname usuario
  appendFile "dados/usuarios.txt" (usuarioStr ++  "\n")
  return ()

existeUsuario :: String -> [Usuario] -> Bool
existeUsuario _ [] = False
existeUsuario nomeUsuario (x:xs) = ((Util.toLowerString (Usuario.nickname x)) == (Util.toLowerString nomeUsuario)) || existeUsuario nomeUsuario xs

