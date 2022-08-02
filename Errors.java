package errors;

public class Errors {

	//Runtime error - Void should be removed because this method must return a result
	public static double FtoC (double fahrenheit) { 
		//Syntax error - Data type incorrect - change from (int) to (double)
		//Logic error - formula is incorrect, it is minus 32, not +30
        double celsius = (fahrenheit - 32) * 5/9;
        double roundingOff = Math.round(celsius*100);
        double finalAnswer = roundingOff/100;
        
	    
    //Syntax / Compilation error - Need (;) at end of statement
    return finalAnswer;
    //Syntax / Compilation error - Need ( } ) at end of class
	
	}

	public static void main(String[] args) {
		//Syntax / Compilation error - Need (;) at end of statement
        System.out.println("Fahrenheit to Celsius converter:");
        
        //Syntax error - Data type incorrect - change from (int) to (double)
        double degreesFahrenheit = 54.3;
        
        //Syntax error  (degrees) should be (degreesFahrenheit)
        double degreesCelsius = FtoC(degreesFahrenheit);
        
        System.out.println(degreesFahrenheit + "°F = " + degreesCelsius + "°C");
        
    }
}

// Once you've corrected all the errors, the answer should be 12.39°C