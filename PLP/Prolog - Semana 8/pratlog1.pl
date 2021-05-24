listaHoteis(Lista) :- Lista = [
hotel("Grand Hyatt", 5000, 4520, 660, true, true),
hotel("Holiday Inn", 2000, 250, 400, true, true),
hotel("Breezes", 300, 60, 300, false, false),
hotel("Warwick Paradise", 245, 42, 150, true, true),
hotel("Coral Harbour", 75, 27, 120, false, true),
hotel("Club Peace", 159, 89, 70, true, true),
hotel("Tingum", 625, 245, 90, false, true),
hotel("Abaco Beach", 150, 70, 150, true, true),
hotel("Sandyport", 700, 89, 40, true, true)
].

achaHotel(_,[], Result) :- Result = "".
achaHotel(Verba, [Head|Tail], Result) :-
    getHotelCapacidade(Head, CapacidadeHotel),
    getHotelQtdHospedes(Head, QtdHospedes),
    getHotelDiaria(Head, Diaria),
    getHotelVistaMar(Head, VistaMarResult),
    getHotelCafeAlmoco(Head, CafeAlmocoResult),
    naoLotado(CapacidadeHotel, QtdHospedes, NaoLotadoResult),
    temDinheiro(Verba, Diaria, TemDinheiroResult),
    NaoLotadoResult, TemDinheiroResult, VistaMarResult, CafeAlmocoResult -> Result = Head; achaHotel(Verba, Tail, Result).

getHotelNome(hotel(Nome,_,_,_,_,_), Nome).
getHotelCapacidade(hotel(_,Capacidade,_,_,_,_), Capacidade).
getHotelQtdHospedes(hotel(_,_,QtdHospedes,_,_,_), QtdHospedes).
getHotelDiaria(hotel(_,_,_,Diaria,_,_), Diaria).
getHotelVistaMar(hotel(_,_,_,_,VistaMar,_), VistaMar).
getHotelCafeAlmoco(hotel(_,_,_,_,_,CafeAlmoco), CafeAlmoco).

porcentagem(Capacidade, QtdHospedes, Result) :- Result is QtdHospedes*100/Capacidade.
naoLotado(Capacidade, QtdHospedes, Result) :-
    porcentagem(Capacidade, QtdHospedes, Porcentagem),
    Result = (Porcentagem < 80).

temDinheiro(Verba, Diaria, Result) :- 
    verbaNecessaria(Diaria, VerbaNecessaria),
    Result = (VerbaNecessaria =< Verba).
verbaNecessaria(Diaria, Result) :- Result is Diaria*4.

main :-
    read(Verba),
    listaHoteis(Lista),
    achaHotel(Verba, Lista, Result),
    getHotelNome(Result, NomeHotel),
    write(NomeHotel),
    halt.