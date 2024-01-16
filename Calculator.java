public class Calculator {
    Double A = null ;
    Double B = null;
    Double result = null;
    public String operand = null;

    public void setOperand(String operand){
        this.operand = operand;
    }

    public void save(double displayText){
        if (A == null){
            A = displayText;
        } else if (B == null) {
            B = displayText;
        }
    }

    public void performCalc(){

        if (A != null && B != null) {
            switch (operand) {
                case "+":
                    result = A + B;
                    A += B;
                    B = null;
                    break;
                case "-":
                    result = A - B;
                    A -= B;
                    B = null;
                    break;
                case "*":
                    result = A * B;
                    A *= B;
                    B = null;
                    break;
                case "/":
                    result = A / B;
                    A /= B;
                    B = null;
                    break;
            }
        }

        if (A != null && operand != null && operand.equals("sqrt")) {
            result = Math.sqrt(A);
            A = Math.sqrt(A);
        }

        if (A != null && operand != null && operand.equals("sqr")) {
            result = A * A;
            A = A * A;
        }
    }

    public String resultString(Double result){

        if(result == result.intValue()){
            int out = result.intValue();
            return Integer.toString(out);
        } else {
            return result.toString();
        }
    }

    public void clearAll(){
        A = null;
        B = null;
        result = null;
        operand = null;
    }
}
