module bank

sig Banco {
	contas: set Conta
}

abstract sig Conta {
}

sig Poupanca extends Conta {
}

sig ContaCorrente extends Conta {
}

sig ContaVIP in Conta {
}

fact formulas {
	#Banco > 1
	all b: Banco | temContas[b]
	all c: Conta | one c.~contas 
}

pred temContas[b:Banco]{
	some contasBanco[b]
}

fun contasBanco[b:Banco]: set Conta {
	b.contas
}

pred show(){}

run show for 5
