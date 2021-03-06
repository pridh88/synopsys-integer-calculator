## Introduction
Simple command-line calculator implemented using ANTL4 to parse and evaluate an expression.
Returns Integer result.

## Environment setup:
#### ANTLR4
#### Apache Maven 3.2.3
#### Minimum JDK version: 1.8

## Project Structure
###-src/main/antlr4
####--CalcGrammar.g4: Calculator grammar file, describe rules for parsing the input expression, matches the string to be evaluated against specified lexer tokens.
###-target/generated-sources
####--package:antlr4
#####---antl4 generated classes for Lexer, Parser, BaseVisitor and BaseListener
######Note: This path is set as source path.

###-src/main
####--package: com.synopsys.homework
#####---AppCalculator.java: Main class, that takes two command line arguments
######1.Integer expression to be evaluated
######2.Logging level
#####---EvaluateExpressionVisitor: Extends and implements the methods of CalcGrammarBaseVisitor generated by antlr4
#####---LogLevelEnum: Enum of supported Logging Levels
####--package: com.synopsys.homework.exception
#####---CalculatorException: Custom exception handler class
####--package: com.synopsys.homework.utils
#####---CliOptionUtil: utility method to set the supported logging level

###-src/test/java
####--package: com.snopsys.homework
#####---AppCalculatorTest: unit tests of AppCalculator.java
#####---CliOptionUtilTest: unit tests for CliOptionUtil.ava


##Sample output:

### 1. logging set to OFF
/target$java -jar integer-calculator-program-0.0.1-SNAPSHOT-jar-with-dependencies.jar
 "mult(add(2, 2), div(9, 3))" OFF
12
### 2. default logging INFO
/target$java -jar integer-calculator-program-0.0.1-SNAPSHOT-jar-with-dependencies.jar "add(1,2)"
19:08:17.246 [main] DEBUG com.synopsys.homework.AppCalculator - Inside main, expression to be evaluated: add(1,2)
19:08:17.256 [main] DEBUG com.synopsys.homework.AppCalculator - Inside evaluate(), expression to be evaluated: add(1,2)
19:08:17.404 [main] DEBUG com.synopsys.homework.EvaluateExpressionVisitor - Inside visitNumExp..
19:08:17.405 [main] INFO com.synopsys.homework.EvaluateExpressionVisitor - The numerical value is, 1
19:08:17.406 [main] DEBUG com.synopsys.homework.EvaluateExpressionVisitor - Inside visitNumExp..
19:08:17.407 [main] INFO com.synopsys.homework.EvaluateExpressionVisitor - The numerical value is, 2
19:08:17.408 [main] DEBUG com.synopsys.homework.EvaluateExpressionVisitor - Adding values 1 and 2
19:08:17.409 [main] INFO com.synopsys.homework.EvaluateExpressionVisitor - The result of addition is 3
3

### 3. Invalid expression
/target$java -jar integer-calculator-program-0.0.1-SNAPSHOT-jar-with-dependencies.jar "div("a", 2)" ERROR
19:10:42.220 [main] ERROR com.synopsys.homework.EvaluateExpressionVisitor - Operands cannot be null
19:10:42.226 [main] ERROR com.synopsys.homework.AppCalculator - Invalid expression, couldn't be evaluated
