grammar CalcGrammar;

@lexer::header {
package antlr4;
}


@parser::header {
package antlr4;
}

prog   
	: exp
    ;

exp 
    :   INT  #numExp
    |   ID #idExp
    | 	ADD LP exp COMMA exp RP # addExp
    |	SUB LP exp COMMA exp RP #subExp
    |	MULT LP exp COMMA exp RP #multExp
    |	DIV LP exp COMMA exp RP #divExp
    | 	LET LP ID COMMA exp COMMA exp RP #letExp
    ;
                  
                  
LP : '(';
COMMA : ',';
RP : ')';
ADD : 'add';
SUB : 'sub';
MULT : 'mult';
DIV : 'div';
LET : 'let';

ID  :   ('a'..'z'|'A'..'Z')+ ;
INT :   '-'?'0'..'9'+ ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t'|'\n'|'\r') -> skip;