// Lucas Gabriel Soares de Almeida
// Display de 7 seguimentos

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

    // Entradas
    logic signed [6:0] decimalInput; // De 0 até 63
    logic signed [4:0] notaInput; // De 0 até 15
    logic mostrarSituacao; // 1 ou 0, 1 se é para mostrar a situação A, F ou P, e 0 se é para mostrar a nota.

    // Conectar entradas
    always_comb begin
      decimalInput <= SWI[5:0];
      notaInput <= SWI[3:0];
      mostrarSituacao <= SWI[7];
    end

    // Saidas
    logic d0, d1, d2, d3, d4, d5, d6;

    // Conectar saidas do display
    always_comb begin
      SEG[0] <= d0;
      SEG[1] <= d1;
      SEG[2] <= d2;
      SEG[3] <= d3;
      SEG[4] <= d4;
      SEG[5] <= d5;
      SEG[6] <= d6;
    end

  // Display de 7 seguimentos
    always_comb begin
    // Se quiser que mostre a situação da nota.
    if (mostrarSituacao) begin
      // Se a nota for invalida, ou seja, não está no intervalo de 0 a 9.
      if (notaInput > 9) begin
        d0 <= 0;
        d1 <= 0;
        d2 <= 0;
        d3 <= 0;
        d4 <= 0;
        d5 <= 0;
        d6 <= 0;
      end  
      // Se tirar uma nota maior ou igual a 7, mostra A.
      else if (notaInput >= 7) begin
        d0 <= 1;
        d1 <= 1;
        d2 <= 1;
        d3 <= 0;
        d4 <= 1;
        d5 <= 1;
        d6 <= 1;
      end
      // Se tirar uma nota maior ou igual a 4, e menor que 7, mostra F.
      else if (notaInput >= 4) begin
        d0 <= 1;
        d1 <= 0;
        d2 <= 0;
        d3 <= 0;
        d4 <= 1;
        d5 <= 1;
        d6 <= 1;
      end
      // Se não for nenhum desses casos, então é que foi menor que 4, logo, mostra P.
      else begin
        d0 <= 1;
        d1 <= 1;
        d2 <= 0;
        d3 <= 0;
        d4 <= 1;
        d5 <= 1;
        d6 <= 1;
      end
    end
    // Se quiser mostrar os valores
    else begin
      case (decimalInput)
          // 0
          0: begin
              d0 <= 1;
              d1 <= 1;
              d2 <= 1;
              d3 <= 1;
              d4 <= 1;
              d5 <= 1;
              d6 <= 0;
          end
          // 1
          1: begin
              d0 <= 0;
              d1 <= 1;
              d2 <= 1;
              d3 <= 0;
              d4 <= 0;
              d5 <= 0;
              d6 <= 0;
          end
          // 2
          2: begin
              d0 <= 1;
              d1 <= 1;
              d2 <= 0;
              d3 <= 1;
              d4 <= 1;
              d5 <= 0;
              d6 <= 1;
          end
          // 3
          3: begin
              d0 <= 1;
              d1 <= 1;
              d2 <= 1;
              d3 <= 1;
              d4 <= 0;
              d5 <= 0;
              d6 <= 1;
          end
          // 4
          4: begin
              d0 <= 0;
              d1 <= 1;
              d2 <= 1;
              d3 <= 0;
              d4 <= 0;
              d5 <= 1;
              d6 <= 1;
          end
          // 5
          5: begin
              d0 <= 1;
              d1 <= 0;
              d2 <= 1;
              d3 <= 1;
              d4 <= 0;
              d5 <= 1;
              d6 <= 1;
          end
          // 6
          6: begin
              d0 <= 1;
              d1 <= 0;
              d2 <= 1;
              d3 <= 1;
              d4 <= 1;
              d5 <= 1;
              d6 <= 1;
          end
          // 7
          7: begin
              d0 <= 1;
              d1 <= 1;
              d2 <= 1;
              d3 <= 0;
              d4 <= 0;
              d5 <= 0;
              d6 <= 0;
          end
          // 8
          8: begin
              d0 <= 1;
              d1 <= 1;
              d2 <= 1;
              d3 <= 1;
              d4 <= 1;
              d5 <= 1;
              d6 <= 1;
          end
          // 9
          9: begin
              d0 <= 1;
              d1 <= 1;
              d2 <= 1;
              d3 <= 1;
              d4 <= 0;
              d5 <= 1;
              d6 <= 1;
          end
      // Coloque as entradas w,x,y,z em chaves SWI[3:0] e mostre os dígitos hexadecimais correspondentes no display
          // A
          10: begin
                  d0 <= 1;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 0;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // b
          11: begin
                  d0 <= 0;
                  d1 <= 0;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // C
          12: begin
                  d0 <= 1;
                  d1 <= 0;
                  d2 <= 0;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 0;
            end
          // d
          13: begin
                  d0 <= 0;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 0;
                  d6 <= 1;
            end
          // E
          14: begin
                  d0 <= 1;
                  d1 <= 0;
                  d2 <= 0;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // F
          15: begin
                  d0 <= 1;
                  d1 <= 0;
                  d2 <= 0;
                  d3 <= 0;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
      // Coloque as entradas em chaves SWI[5:0] e mostre, a partir do valor 16 da entrada, os símbolos A até o no display	
          16: begin
                  d0 <= 1;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 0;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // b
          17: begin
                  d0 <= 0;
                  d1 <= 0;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // C
          18: begin
                  d0 <= 1;
                  d1 <= 0;
                  d2 <= 0;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 0;
            end
          // c
          19: begin
                  d0 <= 0;
                  d1 <= 0;
                  d2 <= 0;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 0;
                  d6 <= 1;
            end
          // d
          20: begin
                  d0 <= 0;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 0;
                  d6 <= 1;
            end
          // E
          21: begin
                  d0 <= 1;
                  d1 <= 0;
                  d2 <= 0;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // F
          22: begin
                  d0 <= 1;
                  d1 <= 0;
                  d2 <= 0;
                  d3 <= 0;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // 9
          23: begin
                  d0 <= 1;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 0;
                  d5 <= 1;
                  d6 <= 1;
            end
          // H
          24: begin
                  d0 <= 0;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 0;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // h
          25: begin
                  d0 <= 0;
                  d1 <= 0;
                  d2 <= 1;
                  d3 <= 0;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // i
          26: begin
                  d0 <= 0;
                  d1 <= 0;
                  d2 <= 1;
                  d3 <= 0;
                  d4 <= 0;
                  d5 <= 0;
                  d6 <= 0;
            end
          // l
          27: begin
                  d0 <= 0;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 0;
                  d4 <= 0;
                  d5 <= 0;
                  d6 <= 0;
            end
          // J
          28: begin
                  d0 <= 0;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 0;
                  d6 <= 0;
            end
          // L
          29: begin
                  d0 <= 0;
                  d1 <= 0;
                  d2 <= 0;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 0;
            end
          // n
          30: begin
                  d0 <= 0;
                  d1 <= 0;
                  d2 <= 1;
                  d3 <= 0;
                  d4 <= 1;
                  d5 <= 0;
                  d6 <= 1;
            end
          // O
          31: begin
              d0 <= 1;
              d1 <= 1;
              d2 <= 1;
              d3 <= 1;
              d4 <= 1;
              d5 <= 1;
              d6 <= 0;
            end
          // o
          32: begin
              d0 <= 0;
              d1 <= 0;
              d2 <= 1;
              d3 <= 1;
              d4 <= 1;
              d5 <= 0;
              d6 <= 1;
            end
          // P
          33: begin
              d0 <= 1;
              d1 <= 1;
              d2 <= 0;
              d3 <= 0;
              d4 <= 1;
              d5 <= 1;
              d6 <= 1;
            end
          // q
          34: begin
              d0 <= 1;
              d1 <= 1;
              d2 <= 1;
              d3 <= 0;
              d4 <= 0;
              d5 <= 1;
              d6 <= 1;
            end
          // r
          35: begin
              d0 <= 0;
              d1 <= 0;
              d2 <= 0;
              d3 <= 0;
              d4 <= 1;
              d5 <= 0;
              d6 <= 1;
            end
          // S
          36: begin
                  d0 <= 1;
                  d1 <= 0;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 0;
                  d5 <= 1;
                  d6 <= 1;
            end
          // t
          37: begin
                  d0 <= 0;
                  d1 <= 0;
                  d2 <= 0;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 1;
            end
          // U
          38: begin
                  d0 <= 0;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 1;
                  d6 <= 0;
            end
          // u
          39: begin
                  d0 <= 0;
                  d1 <= 0;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 1;
                  d5 <= 0;
                  d6 <= 0;
            end
          // y
          40: begin
                  d0 <= 0;
                  d1 <= 1;
                  d2 <= 1;
                  d3 <= 1;
                  d4 <= 0;
                  d5 <= 1;
                  d6 <= 1;
            end
          // º
          41: begin
                  d0 <= 1;
                  d1 <= 1;
                  d2 <= 0;
                  d3 <= 0;
                  d4 <= 0;
                  d5 <= 1;
                  d6 <= 1;
            end
          default: begin
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
    end

endmodule
