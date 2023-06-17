package com.kce.service;

public class ScientificCalculator {
	import java.util.Scanner;

	public class ScientificCalculator {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        
	        System.out.println("Scientific Calculator");
	        System.out.println(" "); 
	        
	        while (true) {
	            System.out.println("Enter an expression (or 'quit' to exit):");
	            String input = scanner.nextLine();
	            
	            if (input.equalsIgnoreCase("quit")) {
	                break;
	            }
	            
	            try {
	                double result = evaluateExpression(input);
	                System.out.println("Result: " + result);
	            } catch (Exception e) {
	                System.out.println("Invalid expression. Please try again.");
	            }
	        }
	        
	        scanner.close();
	    }
	    
	    public static double evaluateExpression(String expression) {
	        expression = expression.replaceAll("\\s+", "").toLowerCase();
	        
	        expression = expression.replaceAll("sin", "Math.sin");
	        expression = expression.replaceAll("cos", "Math.cos");
	        expression = expression.replaceAll("tan", "Math.tan");
	        expression = expression.replaceAll("log", "Math.log10");
	        expression = expression.replaceAll("ln", "Math.log");
	        expression = expression.replaceAll("pi", "Math.PI");
	        
	        return (double) eval(expression);
	    }
	    
	    public static Object eval(final String str) {
	        return new Object() {
	            int pos = -1, ch;
	            
	            void nextChar() {
	                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	            }
	            
	            boolean eat(int charToEat) {
	                while (ch == ' ') nextChar();
	                if (ch == charToEat) {
	                    nextChar();
	                    return true;
	                }
	                return false;
	            }
	            
	            double parse() {
	                nextChar();
	                double x = parseExpression();
	                if (pos < str.length()) {
	                    throw new RuntimeException("Unexpected: " + (char)ch);
	                }
	                return x;
	            }
	            
	            double parseExpression() {
	                double x = parseTerm();
	                for (;;) {
	                    if (eat('+')) x += parseTerm();
	                    else if (eat('-')) x -= parseTerm();
	                    else return x;
	                }
	            }
	            
	            double parseTerm() {
	                double x = parseFactor();
	                for (;;) {
	                    if (eat('*')) x *= parseFactor();
	                    else if (eat('/')) x /= parseFactor();
	                    else return x;
	                }
	            }
	            
	            double parseFactor() {
	                if (eat('+')) return parseFactor(); 
	                if (eat('-')) return -parseFactor(); 
	                
	                double x;
	                int startPos = this.pos;
	                if (eat('(')) {
	                    x = parseExpression();
	                    eat(')');
	                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
	                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                    x = Double.parseDouble(str.substring(startPos, this.pos));
	                } else if (ch >= 'a' && ch <= 'z') { 
	                    while (ch >= 'a' && ch <= 'z') nextChar();
	                    String func = str.substring(startPos, this.pos);
	                    x = parseFactor();
	                    if (func.equals("sqrt"))
	           

}
