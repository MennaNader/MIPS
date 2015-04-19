
public class ALU {
	
	public int BinToDec(String Bin){
		return Integer.parseInt(Bin,2);
	}
	
	
	
	public void add(){
		Wires.aluResult = Integer.toBinaryString(BinToDec(Wires.aluIn1) + BinToDec(Wires.aluIn2));
	}
	
	public void subtract(){
		Wires.aluResult = Integer.toBinaryString(BinToDec(Wires.aluIn1) - BinToDec(Wires.aluIn2));		

	}
	
	public void multiply(){
		Wires.aluResult = Integer.toBinaryString(BinToDec(Wires.aluIn1) * BinToDec(Wires.aluIn2));
	}
	
	public void and(){
		Wires.aluResult = Integer.toBinaryString(BinToDec(Wires.aluIn1) & BinToDec(Wires.aluIn2));
	}
	
	public void or() {
		Wires.aluResult = Integer.toBinaryString(BinToDec(Wires.aluIn1) | BinToDec(Wires.aluIn2));

	}
	public void slt(){
		Wires.aluResult = (BinToDec(Wires.aluIn1)<BinToDec(Wires.aluIn2))? "00000000000000000000000000000000": "1111111111111111111111111111111";
	}
	
	public void AluOperation(){
		if(Wires.aluConOut.equals("0010")){
			add();
		}
		else {
			if(Wires.aluConOut.equals("0110")){
				subtract();
			}
			else {
				if(Wires.aluConOut.equals("0000")){
					and();
				}	
				else {
					if(Wires.aluConOut.equals("0001")){
						or();
					}	
					else {
						slt();
					}
				}
			}
		}
	}
	

}
