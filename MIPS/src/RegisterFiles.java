public class RegisterFiles {

	Register [] regs = new Register[32];

	public RegisterFiles(){

		regs[0] = new Register("$zero","00000000000000000000000000000000");
		regs[1] = new Register("$at","00000000000000000000000000000000");
		regs[2] = new Register("$v0","00000000000000000000000000000000");
		regs[3] = new Register("$v1","00000000000000000000000000000000");
		regs[4] = new Register("$a0","00000000000000000000000000000000");
		regs[5] = new Register("$a1","00000000000000000000000000000000");
		regs[6] = new Register("$a2","00000000000000000000000000000000");
		regs[7] = new Register("$a3","00000000000000000000000000000000");
		regs[8] = new Register("$t0","00000000000000000000000000000000");
		regs[9] = new Register("$t1","00000000000000000000000000000000");
		regs[10] = new Register("$t2","00000000000000000000000000000000");
		regs[11] = new Register("$t3","00000000000000000000000000000000");
		regs[12] = new Register("$t4","00000000000000000000000000000000");
		regs[13] = new Register("$t5","00000000000000000000000000000000");
		regs[14] = new Register("$t6","00000000000000000000000000000000");
		regs[15] = new Register("$t7","00000000000000000000000000000000");
		regs[16] = new Register("$s0","00000000000000000000000000000000");
		regs[17] = new Register("$s1","00000000000000000000000000000000");
		regs[18] = new Register("$s2","00000000000000000000000000000000");
		regs[19] = new Register("$s3","00000000000000000000000000000000");
		regs[20] = new Register("$s4","00000000000000000000000000000000");
		regs[21] = new Register("$s5","00000000000000000000000000000000");
		regs[22] = new Register("$s6","00000000000000000000000000000000");
		regs[23] = new Register("$s7","00000000000000000000000000000000");
		regs[24] = new Register("$t8","00000000000000000000000000000000");
		regs[25] = new Register("$t9","00000000000000000000000000000000");
		regs[26] = new Register("$k0","00000000000000000000000000000000");
		regs[27] = new Register("$k1","00000000000000000000000000000000");
		regs[28] = new Register("$gp","00000000000000000000000000000000");
		regs[29] = new Register("$sp","00000000000000000000000000000000");
		regs[30] = new Register("$fp","00000000000000000000000000000000");
		regs[31] = new Register("$ra","00000000000000000000000000000000");
	}

	public void readData1() {
		String readReg1 = Wires.readReg1;
		switch(readReg1) {
		case "00000": Wires.aluIn1 = regs[0].value;break;
		case "00001": Wires.aluIn1 = regs[1].value;break;
		case "00010": Wires.aluIn1 = regs[2].value;break;
		case "00011": Wires.aluIn1 = regs[3].value;break;
		case "00100": Wires.aluIn1 = regs[4].value;break;
		case "00101": Wires.aluIn1 = regs[5].value;break;
		case "00110": Wires.aluIn1 = regs[6].value;break;
		case "00111": Wires.aluIn1 = regs[7].value;break;
		case "01000": Wires.aluIn1 = regs[8].value;break;
		case "01001": Wires.aluIn1 = regs[9].value;break;
		case "01010": Wires.aluIn1 = regs[10].value;break;
		case "01011": Wires.aluIn1 = regs[11].value;break;
		case "01100": Wires.aluIn1 = regs[12].value;break;
		case "01101": Wires.aluIn1 = regs[13].value;break;
		case "01110": Wires.aluIn1 = regs[14].value;break;
		case "01111": Wires.aluIn1 = regs[15].value;break;
		case "10000": Wires.aluIn1 = regs[16].value;break;
		case "10001": Wires.aluIn1 = regs[17].value;break;
		case "10010": Wires.aluIn1 = regs[18].value;break;
		case "10011": Wires.aluIn1 = regs[19].value;break;
		case "10100": Wires.aluIn1 = regs[20].value;break;
		case "10101": Wires.aluIn1 = regs[21].value;break;
		case "10110": Wires.aluIn1 = regs[22].value;break;
		case "10111": Wires.aluIn1 = regs[23].value;break;
		case "11000": Wires.aluIn1 = regs[24].value;break;
		case "11001": Wires.aluIn1 = regs[25].value;break;
		case "11010": Wires.aluIn1 = regs[26].value;break;
		case "11011": Wires.aluIn1 = regs[27].value;break;
		case "11100": Wires.aluIn1 = regs[28].value;break;
		case "11101": Wires.aluIn1 = regs[29].value;break;
		case "11110": Wires.aluIn1 = regs[30].value;break;
		case "11111": Wires.aluIn1 = regs[31].value;break;
		}
	}

	public void readData2() {
		String readReg2 = Wires.readReg2;
		if(Wires.aluSrc == 0) {
			switch(readReg2) {
			case "00000": Wires.aluIn2 = regs[0].value;break;
			case "00001": Wires.aluIn2 = regs[1].value;break;
			case "00010": Wires.aluIn2 = regs[2].value;break;
			case "00011": Wires.aluIn2 = regs[3].value;break;
			case "00100": Wires.aluIn2 = regs[4].value;break;
			case "00101": Wires.aluIn2 = regs[5].value;break;
			case "00110": Wires.aluIn2 = regs[6].value;break;
			case "00111": Wires.aluIn2 = regs[7].value;break;
			case "01000": Wires.aluIn2 = regs[8].value;break;
			case "01001": Wires.aluIn2 = regs[9].value;break;
			case "01010": Wires.aluIn2 = regs[10].value;break;
			case "01011": Wires.aluIn2 = regs[11].value;break;
			case "01100": Wires.aluIn2 = regs[12].value;break;
			case "01101": Wires.aluIn2 = regs[13].value;break;
			case "01110": Wires.aluIn2 = regs[14].value;break;
			case "01111": Wires.aluIn2 = regs[15].value;break;
			case "10000": Wires.aluIn2 = regs[16].value;break;
			case "10001": Wires.aluIn2 = regs[17].value;break;
			case "10010": Wires.aluIn2 = regs[18].value;break;
			case "10011": Wires.aluIn2 = regs[19].value;break;
			case "10100": Wires.aluIn2 = regs[20].value;break;
			case "10101": Wires.aluIn2 = regs[21].value;break;
			case "10110": Wires.aluIn2 = regs[22].value;break;
			case "10111": Wires.aluIn2 = regs[23].value;break;
			case "11000": Wires.aluIn2 = regs[24].value;break;
			case "11001": Wires.aluIn2 = regs[25].value;break;
			case "11010": Wires.aluIn2 = regs[26].value;break;
			case "11011": Wires.aluIn2= regs[27].value;break;
			case "11100": Wires.aluIn2 = regs[28].value;break;
			case "11101": Wires.aluIn2 = regs[29].value;break;
			case "11110": Wires.aluIn2 = regs[30].value;break;
			case "11111": Wires.aluIn2 = regs[31].value;break;
			}
		}

		else {
			signExtend();
		}
	}

	public void signExtend() {
		String signExtendi = Wires.signExtendi;
		if(signExtendi.charAt(0)==0){
			Wires.aluIn2 = "0000000000000000" + signExtendi;
		}
		else {
			Wires.aluIn2 = "1111111111111111" + signExtendi;
		}
	}

	public void writeData() {
		int regNum = -1;
		if (Wires.regDes == 0) {
			switch(Wires.readReg2) {
			case "00000": regNum = 0;break;
			case "00001": regNum = 1;break;
			case "00010": regNum = 2;break;
			case "00011": regNum = 3;break;
			case "00100": regNum = 4;break;
			case "00101": regNum = 5;break;
			case "00110": regNum = 6;break;
			case "00111": regNum = 7;break;
			case "01000": regNum = 8;break;
			case "01001": regNum = 9;break;
			case "01010": regNum = 10;break;
			case "01011": regNum = 11;break;
			case "01100": regNum = 12;break;
			case "01101": regNum = 13;break;
			case "01110": regNum = 14;break;
			case "01111": regNum = 15;break;
			case "10000": regNum = 16;break;
			case "10001": regNum = 17;break;
			case "10010": regNum = 18;break;
			case "10011": regNum = 19;break;
			case "10100": regNum = 20;break;
			case "10101": regNum = 21;break;
			case "10110": regNum = 22;break;
			case "10111": regNum = 23;break;
			case "11000": regNum = 24;break;
			case "11001": regNum = 25;break;
			case "11010": regNum = 26;break;
			case "11011": regNum = 27;break;
			case "11100": regNum = 28;break;
			case "11101": regNum = 29;break;
			case "11110": regNum = 30;break;
			case "11111": regNum = 31;break;
			}

		}
		else {
			switch(Wires.writeReg) {
			case "00000": regNum = 0;break;
			case "00001": regNum = 1;break;
			case "00010": regNum = 2;break;
			case "00011": regNum = 3;break;
			case "00100": regNum = 4;break;
			case "00101": regNum = 5;break;
			case "00110": regNum = 6;break;
			case "00111": regNum = 7;break;
			case "01000": regNum = 8;break;
			case "01001": regNum = 9;break;
			case "01010": regNum = 10;break;
			case "01011": regNum = 11;break;
			case "01100": regNum = 12;break;
			case "01101": regNum = 13;break;
			case "01110": regNum = 14;break;
			case "01111": regNum = 15;break;
			case "10000": regNum = 16;break;
			case "10001": regNum = 17;break;
			case "10010": regNum = 18;break;
			case "10011": regNum = 19;break;
			case "10100": regNum = 20;break;
			case "10101": regNum = 21;break;
			case "10110": regNum = 22;break;
			case "10111": regNum = 23;break;
			case "11000": regNum = 24;break;
			case "11001": regNum = 25;break;
			case "11010": regNum = 26;break;
			case "11011": regNum = 27;break;
			case "11100": regNum = 28;break;
			case "11101": regNum = 29;break;
			case "11110": regNum = 30;break;
			case "11111": regNum = 31;break;
			}

		}

		if(Wires.regWrite==1) {
			if(regNum==0){
				regs[regNum].value = "00000000000000000000000000000000";

			}
			else {
				regs[regNum].value = Wires.writeData;
			}
		}
	}
}


