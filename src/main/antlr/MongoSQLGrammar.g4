grammar MongoSQLGrammar;

@parser::members
{
    public java.util.HashMap<String, Double> memory = new java.util.HashMap<String, Double>();
    @Override
    public void notifyErrorListeners(Token offendingToken, String msg, RecognitionException ex) {
        throw new RuntimeException(msg);
    }
}

@lexer::members
{
    @Override
    public void recover(RecognitionException ex) {
        throw new RuntimeException(ex.getMessage());
    }
}

select
    : SELECT columns FROM table=IDENTIFIER where offset? limit? EOF
    ;

columns
    : ALL #allColumns
    | columnsList+=IDENTIFIER (',' columnsList+=IDENTIFIER)* #specificColumns
    ;

where
    : (WHERE predicates+=predicate (AND predicates+=predicate)*)?
    ;

offset
    : OFFSET INTEGER
    ;

limit
    : LIMIT INTEGER
    ;

predicate
    : noBracesPredicate #noBraces
    | '(' predicate ')' #bracesPredicate
    ;

noBracesPredicate
    : column=IDENTIFIER booleanBinaryOperator operand #leftPredicate
    | operand booleanBinaryOperator column=IDENTIFIER #rightPredicate
    ;

operand
    : number #numberOperand
    | STRING_LITERAL #stringOperand
    ;

number
    : FLOAT
    | INTEGER
    ;

booleanBinaryOperator
    : GREATER
    | EQUAL
    | LESS
    | NOT_EQUAL
    ;

STRING_LITERAL
    : '"' (~'"' | '\\"')* '"'
    | '\'' (~'\'' | '\\\'')* '\''
    ;

GREATER : '>';
EQUAL : '=';
LESS : '<';
NOT_EQUAL : '<>';

SELECT : S E L E C T;
FROM : F R O M;
WHERE : W H E R E;
AND : A N D;
OFFSET : O F F S E T;
LIMIT : L I M I T;

ALL : '*';

IDENTIFIER : [a-zA-Z_] [a-zA-Z_0-9]*;

FLOAT : '-'? DIGIT+ '.' DIGIT+;
INTEGER : '-'? DIGIT+;

SPACES
 : [ \u000B\t\r\n] -> channel(HIDDEN)
 ;

ErrorCharacter
    : .
    ;

fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];
