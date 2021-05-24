module escola

sig Professor {
	admira: set Aluno
}

sig Aluno {
	admira: set Professor
}

one sig Maria extends Aluno{}

sig Aula {
	assistida: set Aluno
}

fact admirar {	
	all p: Professor | p in Maria.admira
	some p: Professor | Maria in p.admira
}

fact aulas {
	all au: Aula | #au.assistida < #Aluno
	all al: Aluno | #assistida.al < #Aula 
}

pred show(){}

run show for 5
