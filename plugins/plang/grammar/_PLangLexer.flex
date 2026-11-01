// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
// Lexer grammar for the pLang language (https://github.com/PleaseEnterYourText-Studio/pLang).
// Token set mirrors the reference compiler (include/token.h, src/Lexer.cpp).
package org.plang.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.plang.lang.psi.PLangTokenTypes;
import com.intellij.psi.TokenType;

%%

%{
  public _PLangLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _PLangLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

WHITE_SPACE=[ \t\n\x0B\f\r]+

LINE_COMMENT="//"[^\r\n]*
BLOCK_COMMENT="/*"([^*]|\*+[^*/])*(\*+"/")?
UNTERMINATED_BLOCK_COMMENT="/*"([^*]|\*+[^*/])*\** 

ESCAPE_SEQUENCE=\\[^\r\n]
STRING_LITERAL=\"([^\\\"\r\n]|{ESCAPE_SEQUENCE})*\"
UNTERMINATED_STRING_LITERAL=\"([^\\\"\r\n]|{ESCAPE_SEQUENCE})*
CHAR_LITERAL=\'([^\\\'\r\n]|{ESCAPE_SEQUENCE})*\'
UNTERMINATED_CHAR_LITERAL=\'([^\\\'\r\n]|{ESCAPE_SEQUENCE})*

DIGITS=[0-9][0-9_]*
EXPONENT=[eE][+-]?[0-9]+
HEX_LITERAL=0[xX][0-9a-fA-F_]+
OCT_LITERAL=0[oO][0-7_]+
BIN_LITERAL=0[bB][01_]+
FLOAT_LITERAL=(({DIGITS}\.{DIGITS}?|\.{DIGITS})({EXPONENT})?|{DIGITS}{EXPONENT})[fF]?|{DIGITS}[fF]
INT_LITERAL={DIGITS}(ll|LL|[lL])?

IDENTIFIER=[:letter:]_[:letter:\d_]*

%%

{WHITE_SPACE}                    { return TokenType.WHITE_SPACE; }
{LINE_COMMENT}                   { return PLangTokenTypes.LINE_COMMENT; }
{BLOCK_COMMENT}                  { return PLangTokenTypes.BLOCK_COMMENT; }
{UNTERMINATED_BLOCK_COMMENT}     { return PLangTokenTypes.BLOCK_COMMENT; }

{STRING_LITERAL}                 { return PLangTokenTypes.STRING; }
{UNTERMINATED_STRING_LITERAL}    { return PLangTokenTypes.STRING; }
{CHAR_LITERAL}                   { return PLangTokenTypes.CHAR_LIT; }
{UNTERMINATED_CHAR_LITERAL}      { return PLangTokenTypes.CHAR_LIT; }

{HEX_LITERAL}                    { return PLangTokenTypes.NUMBER; }
{OCT_LITERAL}                    { return PLangTokenTypes.NUMBER; }
{BIN_LITERAL}                    { return PLangTokenTypes.NUMBER; }
{FLOAT_LITERAL}                  { return PLangTokenTypes.NUMBER; }
{INT_LITERAL}                    { return PLangTokenTypes.NUMBER; }

"package"    { return PLangTokenTypes.PACKAGE; }
"import"     { return PLangTokenTypes.IMPORT; }
"var"        { return PLangTokenTypes.VAR; }
"val"        { return PLangTokenTypes.VAL; }
"moved"      { return PLangTokenTypes.MOVED; }
"volatile"   { return PLangTokenTypes.VOLATILE; }
"func"       { return PLangTokenTypes.FUNC; }
"impl"       { return PLangTokenTypes.IMPL; }
"return"     { return PLangTokenTypes.RETURN; }
"using"      { return PLangTokenTypes.USING; }
"struct"     { return PLangTokenTypes.STRUCT; }
"abstract"   { return PLangTokenTypes.ABSTRACT; }
"union"      { return PLangTokenTypes.UNION; }
"align"      { return PLangTokenTypes.ALIGN; }
"enum"       { return PLangTokenTypes.ENUM; }
"pub"        { return PLangTokenTypes.PUB; }
"prt"        { return PLangTokenTypes.PRT; }
"pri"        { return PLangTokenTypes.PRI; }
"this"       { return PLangTokenTypes.THIS; }
"thisType"   { return PLangTokenTypes.THIS_TYPE; }
"type"       { return PLangTokenTypes.TYPE; }
"as"         { return PLangTokenTypes.AS; }
"if"         { return PLangTokenTypes.IF; }
"else"       { return PLangTokenTypes.ELSE; }
"while"      { return PLangTokenTypes.WHILE; }
"for"        { return PLangTokenTypes.FOR; }
"asm"        { return PLangTokenTypes.ASM; }
"lambda"     { return PLangTokenTypes.LAMBDA; }
"goto"       { return PLangTokenTypes.GOTO; }
"label"      { return PLangTokenTypes.LABEL; }
"switch"     { return PLangTokenTypes.SWITCH; }
"case"       { return PLangTokenTypes.CASE; }
"default"    { return PLangTokenTypes.DEFAULT; }
"break"      { return PLangTokenTypes.BREAK; }
"continue"   { return PLangTokenTypes.CONTINUE; }
"sizeof"     { return PLangTokenTypes.SIZEOF; }
"extern"     { return PLangTokenTypes.EXTERN; }
"null"       { return PLangTokenTypes.NULL_LIT; }
"int"        { return PLangTokenTypes.INT_TYPE; }
"char"       { return PLangTokenTypes.CHAR_TYPE; }
"string"     { return PLangTokenTypes.STRING_TYPE; }
"wchar"      { return PLangTokenTypes.WCHAR_TYPE; }
"wstring"    { return PLangTokenTypes.WSTRING_TYPE; }
"bool"       { return PLangTokenTypes.BOOL_TYPE; }
"i8"         { return PLangTokenTypes.I8_TYPE; }
"i16"        { return PLangTokenTypes.I16_TYPE; }
"i32"        { return PLangTokenTypes.I32_TYPE; }
"i64"        { return PLangTokenTypes.I64_TYPE; }
"u8"         { return PLangTokenTypes.U8_TYPE; }
"u16"        { return PLangTokenTypes.U16_TYPE; }
"u32"        { return PLangTokenTypes.U32_TYPE; }
"u64"        { return PLangTokenTypes.U64_TYPE; }
"uint"       { return PLangTokenTypes.UINT_TYPE; }
"f32"        { return PLangTokenTypes.F32_TYPE; }
"f64"        { return PLangTokenTypes.F64_TYPE; }
"true"       { return PLangTokenTypes.TRUE; }
"false"      { return PLangTokenTypes.FALSE; }

"<<="        { return PLangTokenTypes.SHL_ASSIGN; }
">>="        { return PLangTokenTypes.SHR_ASSIGN; }
"->"         { return PLangTokenTypes.ARROW; }
"++"         { return PLangTokenTypes.INC; }
"--"         { return PLangTokenTypes.DEC; }
"=="         { return PLangTokenTypes.EQ; }
"!="         { return PLangTokenTypes.NE; }
"<="         { return PLangTokenTypes.LE; }
">="         { return PLangTokenTypes.GE; }
"&&"         { return PLangTokenTypes.AND; }
"||"         { return PLangTokenTypes.OR; }
"<<"         { return PLangTokenTypes.SHL; }
">>"         { return PLangTokenTypes.SHR; }
"+="         { return PLangTokenTypes.PLUS_ASSIGN; }
"-="         { return PLangTokenTypes.MINUS_ASSIGN; }
"*="         { return PLangTokenTypes.STAR_ASSIGN; }
"/="         { return PLangTokenTypes.SLASH_ASSIGN; }
"%="         { return PLangTokenTypes.PERCENT_ASSIGN; }
"&="         { return PLangTokenTypes.AMP_ASSIGN; }
"|="         { return PLangTokenTypes.PIPE_ASSIGN; }
"^="         { return PLangTokenTypes.CARET_ASSIGN; }

"="          { return PLangTokenTypes.ASSIGN; }
"+"          { return PLangTokenTypes.PLUS; }
"-"          { return PLangTokenTypes.MINUS; }
"*"          { return PLangTokenTypes.STAR; }
"/"          { return PLangTokenTypes.SLASH; }
"%"          { return PLangTokenTypes.PERCENT; }
"<"          { return PLangTokenTypes.LT; }
">"          { return PLangTokenTypes.GT; }
"!"          { return PLangTokenTypes.NOT; }
"&"          { return PLangTokenTypes.AMP; }
"|"          { return PLangTokenTypes.PIPE; }
"^"          { return PLangTokenTypes.CARET; }
"~"          { return PLangTokenTypes.TILDE; }
"?"          { return PLangTokenTypes.QUESTION; }
"@"          { return PLangTokenTypes.AT; }
":"          { return PLangTokenTypes.COLON; }
","          { return PLangTokenTypes.COMMA; }
"."          { return PLangTokenTypes.DOT; }
";"          { return PLangTokenTypes.SEMICOLON; }
"("          { return PLangTokenTypes.LPAREN; }
")"          { return PLangTokenTypes.RPAREN; }
"{"          { return PLangTokenTypes.LBRACE; }
"}"          { return PLangTokenTypes.RBRACE; }
"["          { return PLangTokenTypes.LBRACKET; }
"]"          { return PLangTokenTypes.RBRACKET; }

{IDENTIFIER}                     { return PLangTokenTypes.IDENTIFIER; }

[^]                              { return TokenType.BAD_CHARACTER; }
