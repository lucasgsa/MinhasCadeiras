// Lucas Gabriel Soares de Almeida
// http://lad.dsc.ufcg.edu.br/loac/index.php?n=OAC.Divi

parameter divide_by=100000000;  // divisor do clock de referência
// A frequencia do clock de referencia é 50 MHz.
// A frequencia de clk_2 será de  50 MHz / divide_by

parameter NINSTR_BITS = 32;
parameter NBITS_TOP = 8, NREGS_TOP = 32, NBITS_LCD = 64;
module top(input  logic clk_2,
           input  logic [NBITS_TOP-1:0] SWI,
           output logic [NBITS_TOP-1:0] LED,
           output logic [NBITS_TOP-1:0] SEG,
           output logic [NBITS_LCD-1:0] lcd_a, lcd_b,
           output logic [NINSTR_BITS-1:0] lcd_instruction,
           output logic [NBITS_TOP-1:0] lcd_registrador [0:NREGS_TOP-1],
           output logic [NBITS_TOP-1:0] lcd_pc, lcd_SrcA, lcd_SrcB,
             lcd_ALUResult, lcd_Result, lcd_WriteData, lcd_ReadData, 
           output logic lcd_MemWrite, lcd_Branch, lcd_MemtoReg, lcd_RegWrite);

  always_comb begin
    lcd_WriteData <= SWI;
    lcd_pc <= 'h12;
    lcd_instruction <= 'h34567890;
    lcd_SrcA <= 'hab;
    lcd_SrcB <= 'hcd;
    lcd_ALUResult <= 'hef;
    lcd_Result <= 'h11;
    lcd_ReadData <= 'h33;
    lcd_MemWrite <= SWI[0];
    lcd_Branch <= SWI[1];
    lcd_MemtoReg <= SWI[2];
    lcd_RegWrite <= SWI[3];
    for(int i=0; i<NREGS_TOP; i++)
       if(i != NREGS_TOP/2-1) lcd_registrador[i] <= i+i*16;
       else                   lcd_registrador[i] <= ~SWI;
    lcd_a <= {56'h1234567890ABCD, SWI};
  end

  // Quantidade de quociente
  parameter QtdQuociente = 4;

  // Digitos fracionais
  parameter QtdFracionais = 15;

  // 1 bit hexadecimal é representado em 4 bits binários
  parameter QtdBitsHexadecimal = 4;

  // Quantidade digitos inteiros hexadecimais
  parameter QtdInteiro = 1;

  // Tamanho fator de escala
  parameter tSize = 1 + QtdBitsHexadecimal*QtdFracionais;

  // Tamanho dividendo (valor constante 4)
  parameter mSize = 2+tSize;

  // Fator de escala
  // Como parameter, ele não se comporta adequadamente já que está em binário e não em decimal.
  parameter t = 1 << (QtdBitsHexadecimal*QtdFracionais);

  // 4 já escalado
  // Como parameter, ele não se comporta adequadamente já que está em binário e não em decimal.
  parameter m = 4 * (1 << (QtdBitsHexadecimal*QtdFracionais));

  // Tamanho do divisor em bits
  parameter aSize = 8;

  // Divisor
  logic [aSize-1:0] a;

  // Tamanho de 16 displays hex, e cada hex armazena 4 bits.
  parameter sizeSaidaHex = QtdInteiro+QtdFracionais * QtdBitsHexadecimal;

  // Resultado
  logic [sizeSaidaHex-1:0] v;

  // Conectando entrada
  always_comb begin
    a <= SWI;
  end

  always_comb begin
    // Zerando saída para novo cálculo
    v = 0;
    // For indo de 0 até QtdQuociente-1
    for(int n=0; n < QtdQuociente; n=n+1) begin
      // Utilizando fórmula vn = m/a - m/(a+2) + m/(a+4) - m/(a+6) +... , a ∈ ℕ, n ∈ { 2, 4, 6,... };
      v = v + (m/(a+4*n) - m/(a+4*n+2));
    end
  end

  always_comb begin
    lcd_b <= v;
  end

endmodule