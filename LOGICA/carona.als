module CaronaUFCG

abstract sig HorarioIda {}
abstract sig HorarioSaida {}

one sig Ida7h30 extends HorarioIda {}
one sig Ida9h30 extends HorarioIda {}
one sig Ida13h30 extends HorarioIda {}
one sig Ida15h30 extends HorarioIda {}

one sig Saida10h00 extends HorarioSaida {}
one sig Saida12h00 extends HorarioSaida {}
one sig Saida16h00 extends HorarioSaida {}
one sig Saida18h00 extends HorarioSaida {}

sig Regiao {
}

sig Motorista in Usuario {
	ofereceuCaronaIda: set Passageiro,
	ofereceuCaronaSaida: set Passageiro
}
sig Passageiro in Usuario {
	recebeuCaronaIda: lone Motorista,
	recebeuCaronaSaida: lone Motorista
}

sig Usuario {
	regiao: one Regiao,
	ida: one HorarioIda,
	saida: one HorarioSaida,
}

sig Aluno extends Usuario {}

sig Professor extends Usuario {}

sig Servidor extends Usuario {}

fact tipo {

	// Ou exclusivo, ou o usuario eh motorista ou o usuario eh passageiro.
	all user: Usuario | (user in Motorista && !(user in Passageiro)) or (user in Passageiro && !(user in Motorista))
}

fact ida {
	// Todo motorista tem no maximo 3 passageiros.
	all mot: Motorista | vagasCarroIda[mot]

	// Todo motorista da mesma regiao e horario oferece carona.
	all mot: Motorista | (all user: Passageiro | ofereceuCaronaIda[mot, user] <=> naMesmaIda[mot, user])


	// Se o passageiro recebeu carona, o motorista ofereceu carona a ele.
	// O passageiro nao eh obrigado a ter aceitado a carona.
	all pas: Passageiro | ofereceuCaronaIda[(pas.recebeuCaronaIda), pas]
}

fact saida {
	// Todo motorista tem no maximo 3 passageiros.
	all mot: Motorista | vagasCarroSaida[mot]

	// Todo motorista da mesma regiao e horario oferece carona.
	all mot: Motorista | (all user: Passageiro | ofereceuCaronaSaida[mot, user] <=> naMesmaSaida[mot, user])

	// Se o passageiro recebeu carona, o motorista ofereceu carona a ele.
	// O passageiro nao eh obrigado a ter aceitado a carona.
	all pas: Passageiro | ofereceuCaronaSaida[(pas.recebeuCaronaSaida), pas]
}

// Estao na mesma regiao e na mesma hora de saida.
pred naMesmaSaida[user1: Usuario, user2: Usuario] {
	(user1.regiao in user2.regiao) and (user1.saida in user2.saida)
}

// Estao na mesma regiao e na mesma hora de ida.
pred naMesmaIda[user1: Usuario, user2: Usuario] {
	(user1.regiao in user2.regiao) and (user1.ida in user2.ida)
}

// Motorista ofereceu carona a passageiro na ida.
pred ofereceuCaronaIda[mot: Motorista, pas: Passageiro] {
	pas in mot.ofereceuCaronaIda
}

// Motorista ofereceu carona a passageiro na saida.
pred ofereceuCaronaSaida[mot: Motorista, pas: Passageiro] {
	pas in mot.ofereceuCaronaSaida
}

// O motorista tem no máximo 3 passageiros de ida.
pred vagasCarroIda[mot: Motorista] {
	#recebeuCaronaIda.mot <= 3
}

// O motorista tem no máximo 3 passageiros de saida.
pred vagasCarroSaida[mot: Motorista] {
	#recebeuCaronaSaida.mot <= 3 
}

pred show(){}

run show for 20
