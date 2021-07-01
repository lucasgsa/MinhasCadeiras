// Lucas Gabriel Soares de Almeida
// Soma e Multiplicação http://lad.dsc.ufcg.edu.br/loac/index.php?n=OAC.Soma


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
    lcd_b <= {SWI, 56'hFEDCBA09876543};
  end

  // Dúvida: Não há diferença em somar naturais e inteiros, já que tenho que o bit mais a esquerda é o de sinal, e portanto realizando a mesma operação resulta na mesma saida.
  // O overflow e underflow não muda, já que independente da forma de tratar o número, como natural ou inteiro, estouro binario vai ser sempre um estouro, sendo o minimo 0000 e máximo 1111, por exemplo.

  // Entradas
    parameter number1Size = 3;
    parameter number2Size = 3;

    parameter ULAsize = 1;

    logic signed [number1Size:0] decimalInput1;
    logic signed [number2Size:0] decimalInput2;
    logic [ULAsize:0] F;

  // Conectar entradas
    always_comb begin
      decimalInput1 <= SWI[2:0];
      decimalInput2 <= SWI[7:5];
      F = SWI[4:3];
    end

  // Saidas

    parameter sizeSumAndSub = 3;
    parameter sizeMultiplicacao = 6;

    parameter maxInteiro = 3;
    parameter minInteiro = -4;

    logic d0, d1, d2, d3, d4, d5, d6;
    logic sinalNegativo;
    logic estouro;
    logic signed [sizeSumAndSub-1:0] resultado;
    logic signed [sizeMultiplicacao-1:0] resultadoMult;

  // Conectar saidas
    always_comb begin
      SEG[0] <= d0;
      SEG[1] <= d1;
      SEG[2] <= d2;
      SEG[3] <= d3;
      SEG[4] <= d4;
      SEG[5] <= d5;
      SEG[6] <= d6;
      SEG[7] <= sinalNegativo;

      // Zera as leds.
      LED[7:0] <= 0;
      
      LED[7] <= estouro;
      if (F == 0 || F == 1) LED[sizeSumAndSub-1:0] <= resultado;
      else LED[sizeMultiplicacao-1:0] <= resultadoMult;
    end

    always_comb begin

      case (F)

        // Soma
        0: begin
          reg signed [number1Size-1:0] number1;
          reg signed [number1Size-1:0] number2;
          reg signed [2:0] tempResult;
          number1 = decimalInput1;
          number2 = decimalInput2;
          tempResult = number1 + number2;
          resultadoMult = 0;
          // Verifica overflow e underflow
          if (number1+number2 > maxInteiro || number1+number2 < minInteiro) begin
            estouro = 1;
            resultado = 0;
          end
          else begin
            estouro = 0;
            resultado = tempResult;
          end
        end

        // Subtração
        1: begin
          reg signed [number1Size-1:0] number1;
          reg signed [number1Size-1:0] number2;
          reg signed [2:0] tempResult;
          number1 = decimalInput1;
          number2 = decimalInput2;
          tempResult = number1 - number2;
          resultadoMult = 0;
          // Verifica overflow e underflow
          if (number1-number2 > maxInteiro || number1-number2 < minInteiro) begin
            estouro = 1;
            resultado = 0;
          end
          else begin
            estouro = 0;
            resultado = tempResult;
          end
        end

        // Numeros naturais
        2: begin
          reg [number1Size-1:0] number1nat;
          reg [number2Size-1:0] number2nat;
          reg [sizeMultiplicacao-1:0] multnat;
          estouro = 0;
          number1nat = decimalInput1;
          number2nat = decimalInput2;
          multnat = number1nat*number2nat;
          resultadoMult = multnat;
          resultado = 0;
        end

        // Números inteiros
        default: begin
          reg signed [number1Size-1:0] number1;
          reg signed [number1Size-1:0] number2;
          reg signed [sizeMultiplicacao-1:0] mult;
          estouro = 0;
          number1 = decimalInput1;
          number2 = decimalInput2;
          mult = number1*number2;
          resultadoMult = mult;
          resultado = 0;
        end
      endcase
    end

    // Mostrar valor inteiro no display com sinal negativo de -4 a 3.
    always_comb begin
        reg signed [number1Size-1:0] number;
        reg isNegativo;

        if (decimalInput1[2] == 1'b1) isNegativo = 1;
        else isNegativo = 0;

        number = decimalInput1;

        sinalNegativo = isNegativo;
        case (number)
          (-4): begin
            d0 <= 0;
            d1 <= 1;
            d2 <= 1;
            d3 <= 0;
            d4 <= 0;
            d5 <= 1;
            d6 <= 1;
          end
          (-3): begin
            d0 <= 1;
            d1 <= 1;
            d2 <= 1;
            d3 <= 1;
            d4 <= 0;
            d5 <= 0;
            d6 <= 1;
          end
          (-2): begin
            d0 <= 1;
            d1 <= 1;
            d2 <= 0;
            d3 <= 1;
            d4 <= 1;
            d5 <= 0;
            d6 <= 1;
          end
          (-1): begin
            d0 <= 0;
            d1 <= 1;
            d2 <= 1;
            d3 <= 0;
            d4 <= 0;
            d5 <= 0;
            d6 <= 0;
          end
          (1): begin
            d0 <= 0;
            d1 <= 1;
            d2 <= 1;
            d3 <= 0;
            d4 <= 0;
            d5 <= 0;
            d6 <= 0;
          end
          (2): begin
            d0 <= 1;
            d1 <= 1;
            d2 <= 0;
            d3 <= 1;
            d4 <= 1;
            d5 <= 0;
            d6 <= 1;
          end
          (3): begin
            d0 <= 1;
            d1 <= 1;
            d2 <= 1;
            d3 <= 1;
            d4 <= 0;
            d5 <= 0;
            d6 <= 1;
          end
          default begin
            d0 <= 0;
            d1 <= 0;
            d2 <= 0;
            d3 <= 0;
            d4 <= 0;
            d5 <= 0;
            d6 <= 0;
          end
        endcase
    end

endmodule