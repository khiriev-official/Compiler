import java.util.Scanner;

public class CompilerTest {

    public static void main(String[] args) {

        CompilerTest compilerTest = new CompilerTest();

        //test for correct translation of a command
        //test for correct translation of operands
        //test for correct translation of command+operand into a 16 bit instruction
        //Soon:
        //test for checking length of instructionString
        //test for checking grammatic errors
        boolean correct = false;
        boolean detailed = false;
        Compiler compiler = new Compiler();
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want detailed report of tests? true/false: \n");
        //detailed = scan.nextBoolean();

        String[] commands = {"RET", "LDA1", "MRA", "LDA2", "ADD", "INC", "LOOP", "DEC", "MUL", "ADC", "ADD2", "LDA3",
                "LDA4"}; //len 13, means there are 13 commands not 14, len outputs counting from 1
        int[] cmdType = new int[commands.length];

        for (int i = 0; i < cmdType.length; i++) cmdType[i] = i;

        int commandToCompare;
        // Test 1: String command to Type command
        for (int i = 0; i < commands.length; i++) {
            commandToCompare = compiler.commandStringToType(commands[i]);

            if (cmdType[i] == commandToCompare) {
                correct = true;
            } else {
                correct = false;
            }

            if (detailed) System.out.println(String.format("%s : %d - %b", commands[i], commandToCompare, correct));
         }
        if (!correct) System.out.println("Test 1 failed. Program outputs wrong Type of Command");


        String[] operands = {"ax", "bx", "cx", "dx", "dh", "dl", "ch1", "", "32"};
        int[] operandAddress = {0, 1, 2, 3, 4, 5, 21, 0, 32};
        // Test 2: String operand to Type command

        correct = false;
        int operandToCompare;
        for (int i = 0; i < operands.length; i++) {
            operandToCompare = compiler.operandStringToAddress(operands[i]);

            if (operandToCompare == operandAddress[i]) {
                correct = true;
            } else {
                correct = false;
            }

            if (detailed) System.out.println(String.format("%s : %d - %b", operands[i], operandToCompare, correct));
        }

        if (!correct) System.out.println("Test 2 failed. Program outputs wrong Address of Operand");

        // Test 3: translation of whole programm into 16 bit String instruction
        compilerTest.instructionStringToBinaryStringTest();

        // Test 3.1: translation of command into 8 bit instruction
        String binaryString;
        for (int i = 0; i < commands.length; i++) {
            binaryString = compiler.commandStringToBinaryString(commands[i]);

            System.out.print(binaryString + " : " + commands[i]);
            System.out.println();
        }

        //Test 3.2: translation of operand into 8 bit instruction
        String addressBinary;
        for (int i = 0; i < operands.length; i++) {
            addressBinary = compiler.operandStringToBinaryString(operands[i]);

            System.out.print(addressBinary + " : " + operands[i]);
            System.out.println();
        }

        //Test 4: translation of whole programm into 16 bit instruction
        compilerTest.instructionStringToBinaryTest();

        compilerTest.finalTest();
    }

    public boolean instructionStringToBinaryTest() {
        boolean correct = false;
        Compiler compiler = new Compiler();
        String binaryString;
        int binaryInstruction = 0;

        String[] instructionsString = {"LDA1 bx", // 1, 1; 257
                "RET", // 0, 0; 0
                "MUL dh", // 8, 4; 2052
                "LOOP 6", // 6, 6; 1542
                "ADD2 ax", // 10, 0; 2560
                "LDA4 32", // 12, 32; 3104
                "MRA ch1", // 2, 21; 533
                "INC cx", // 5, 2; 1282
                "LDA2 dl", // 3, 5; 773
                "ADD ch1", // 4, 21; 1045
                "DEC dx", // 7, 3; 1795
                "ADC ax", // 9, 0; 2304
                "ADD2 0", // 10, 0; 2560
                "LDA3 cx", // 11, 2; 2818
                "LDA4 dh", // 12, 4; 3076
        };

        for (int i = 0; i < instructionsString.length; i++) {
            binaryString = compiler.instructionStringToBinaryString(instructionsString[i]);
            //System.out.println(String.format("%s : %s", binaryString, instructionsString[i]));
            binaryInstruction = compiler.instructionBinaryStringToBinary(binaryString);
            System.out.println(String.format("%s : %s : %d", instructionsString[i], binaryString, binaryInstruction));
        }

        return correct;
    }

    public boolean instructionStringToBinaryStringTest() {
        boolean correct = false;
        Compiler compiler = new Compiler();
        String binaryString;

        String[] instructionsString = {"LDA1 bx", // 1, 1; 257
                "RET", // 0, 0; 0
                "MUL dh", // 8, 4; 2052
                "LOOP 6", // 6, 6; 1542
                "ADD2 ax", // 10, 0; 2560
                "LDA4 32", // 12, 32; 3104
                "MRA ch1", // 2, 21; 533
                "INC cx", // 5, 2; 1282
                "LDA2 dl", // 3, 5; 773
                "ADD ch1", // 4, 21; 1045
                "DEC dx", // 7, 3; 1795
                "ADC ax", // 9, 0; 2304
                "ADD2 0", // 10, 0; 2560
                "LDA3 cx", // 11, 2; 2818
                "LDA4 dh", // 12, 4; 3076
        };

        for (int i = 0; i < instructionsString.length; i++) {
            binaryString = compiler.instructionStringToBinaryString(instructionsString[i]);
            //System.out.println(String.format("%s : %s", binaryString, instructionsString[i]));
            System.out.println(String.format("%s", binaryString));
        }

        return correct;
    }

    public boolean finalTest() {
        boolean correct = false;
        Compiler compiler = new Compiler();

        String[] commands = {"LDA1 bx", // 1, 1; 257
                "RET", // 0, 0; 0
                "MUL dh", // 8, 4; 2052
                "LOOP 6", // 6, 6; 1542
                "ADD2 ax", // 10, 0; 2560
                "LDA4 32", // 12, 32; 3104
                "MRA ch1", // 2, 21; 533
                "INC cx", // 5, 2; 1282
                "LDA2 dl", // 3, 5; 773
                "ADD ch1", // 4, 21; 1045
                "DEC dx", // 7, 3; 1795
                "ADC ax", // 9, 0; 2304
                "ADD2 0", // 10, 0; 2560
                "LDA3 cx", // 11, 2; 2818
                "LDA4 dh", // 12, 4; 3076
        };

        int[] instructionsInt = compiler.compileInstructions(commands);
        System.out.println("Instructions: \n[");
        for (int i = 0; i < instructionsInt.length; i++) {
            System.out.println(instructionsInt[i]);
        }
        System.out.println("]");

        return correct;
    }


}
