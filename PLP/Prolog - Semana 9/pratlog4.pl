listaMonstros(ListaMonstros) :- 
    ListaMonstros = [monstro("Orc Desnutrido", 5, 3),
                    monstro("Goblin Gigante", 18, 7),
                    monstro("Serprente Venenosa", 8, 4),
                    monstro("Humano Corrompido", 16, 8),
                    monstro("Anão Gigante", 20, 10)
    ].

batalharDungeon((PontosVida, _), [], PontosVida).
batalharDungeon((PontosVida, PontosAtaque), [MonstroAtual|ListaMonstrosSeguintes], VidaPosDungeon) :-
    batalhar((PontosVida, PontosAtaque), MonstroAtual, VidaPosBatalha),
    batalharDungeon((VidaPosBatalha, PontosAtaque), ListaMonstrosSeguintes, ResultadoProximas),
    VidaPosDungeon = ResultadoProximas.

batalhar((PontosVida, PontosAtaque), monstro(_, PontosVidaMonstro, PontosAtaqueMonstro), VidaPosBatalha) :-
    QtdRoundsFloat is PontosVidaMonstro/PontosAtaque,
    QtdRounds is ceiling(QtdRoundsFloat),
    PontosVidaResultante is (PontosVida - (PontosAtaqueMonstro*(QtdRounds-1))),
    VidaPosBatalha = PontosVidaResultante.

qualResultado(Vida, Saida) :- Vida =< 0 -> Saida = "Morre"; Saida = "Sobrevive".

main :- 
    read(Vida),
    read(Ataque),
    listaMonstros(Lista),
    batalharDungeon((Vida, Ataque), Lista, VidaFinal),
    qualResultado(VidaFinal, Saida),
    writeln(Saida),
    halt.