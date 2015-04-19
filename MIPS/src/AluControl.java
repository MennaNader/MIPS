
public class AluControl {

	public void aluControlOutput(){
		switch(Wires.aluOp){
		case "00": Wires.aluConOut = "0010";break;
		case "01": Wires.aluConOut = "0110";break;
		case "10": if(Wires.aluConIn.equals("100000"))Wires.aluConOut = "0010";
		else {
			if(Wires.aluConIn.equals("100010"))Wires.aluConOut = "0110";
			else {
				if(Wires.aluConIn.equals("100100"))Wires.aluConOut = "0000";
				else {
					if(Wires.aluConIn.equals("100101"))Wires.aluConOut = "0001";
					else {
						if(Wires.aluConIn.equals("101010"))Wires.aluConOut = "0111";

					}
				}
			}
		}break;

		}
	}

}
