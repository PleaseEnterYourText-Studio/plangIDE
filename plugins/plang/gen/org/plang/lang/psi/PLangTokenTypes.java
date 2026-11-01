// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
// Token types for pLang. Mirrors grammar/_PLangLexer.flex and grammar/PLang.bnf.
// Regenerate with Grammar-Kit when the grammar changes.
package org.plang.lang.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface PLangTokenTypes {
  IElementType LINE_COMMENT = new PLangTokenType("LINE_COMMENT");
  IElementType BLOCK_COMMENT = new PLangTokenType("BLOCK_COMMENT");

  IElementType IDENTIFIER = new PLangTokenType("IDENTIFIER");
  IElementType NUMBER = new PLangTokenType("NUMBER");
  IElementType STRING = new PLangTokenType("STRING");
  IElementType CHAR_LIT = new PLangTokenType("CHAR_LIT");

  IElementType PACKAGE = new PLangTokenType("PACKAGE");
  IElementType IMPORT = new PLangTokenType("IMPORT");
  IElementType VAR = new PLangTokenType("VAR");
  IElementType VAL = new PLangTokenType("VAL");
  IElementType MOVED = new PLangTokenType("MOVED");
  IElementType VOLATILE = new PLangTokenType("VOLATILE");
  IElementType FUNC = new PLangTokenType("FUNC");
  IElementType IMPL = new PLangTokenType("IMPL");
  IElementType RETURN = new PLangTokenType("RETURN");
  IElementType USING = new PLangTokenType("USING");
  IElementType STRUCT = new PLangTokenType("STRUCT");
  IElementType ABSTRACT = new PLangTokenType("ABSTRACT");
  IElementType UNION = new PLangTokenType("UNION");
  IElementType ALIGN = new PLangTokenType("ALIGN");
  IElementType ENUM = new PLangTokenType("ENUM");
  IElementType PUB = new PLangTokenType("PUB");
  IElementType PRT = new PLangTokenType("PRT");
  IElementType PRI = new PLangTokenType("PRI");
  IElementType THIS = new PLangTokenType("THIS");
  IElementType THIS_TYPE = new PLangTokenType("THIS_TYPE");
  IElementType TYPE = new PLangTokenType("TYPE");
  IElementType AS = new PLangTokenType("AS");
  IElementType IF = new PLangTokenType("IF");
  IElementType ELSE = new PLangTokenType("ELSE");
  IElementType WHILE = new PLangTokenType("WHILE");
  IElementType FOR = new PLangTokenType("FOR");
  IElementType ASM = new PLangTokenType("ASM");
  IElementType LAMBDA = new PLangTokenType("LAMBDA");
  IElementType GOTO = new PLangTokenType("GOTO");
  IElementType LABEL = new PLangTokenType("LABEL");
  IElementType SWITCH = new PLangTokenType("SWITCH");
  IElementType CASE = new PLangTokenType("CASE");
  IElementType DEFAULT = new PLangTokenType("DEFAULT");
  IElementType BREAK = new PLangTokenType("BREAK");
  IElementType CONTINUE = new PLangTokenType("CONTINUE");
  IElementType SIZEOF = new PLangTokenType("SIZEOF");
  IElementType EXTERN = new PLangTokenType("EXTERN");
  IElementType NULL_LIT = new PLangTokenType("NULL_LIT");

  IElementType INT_TYPE = new PLangTokenType("INT_TYPE");
  IElementType CHAR_TYPE = new PLangTokenType("CHAR_TYPE");
  IElementType STRING_TYPE = new PLangTokenType("STRING_TYPE");
  IElementType WCHAR_TYPE = new PLangTokenType("WCHAR_TYPE");
  IElementType WSTRING_TYPE = new PLangTokenType("WSTRING_TYPE");
  IElementType BOOL_TYPE = new PLangTokenType("BOOL_TYPE");
  IElementType I8_TYPE = new PLangTokenType("I8_TYPE");
  IElementType I16_TYPE = new PLangTokenType("I16_TYPE");
  IElementType I32_TYPE = new PLangTokenType("I32_TYPE");
  IElementType I64_TYPE = new PLangTokenType("I64_TYPE");
  IElementType U8_TYPE = new PLangTokenType("U8_TYPE");
  IElementType U16_TYPE = new PLangTokenType("U16_TYPE");
  IElementType U32_TYPE = new PLangTokenType("U32_TYPE");
  IElementType U64_TYPE = new PLangTokenType("U64_TYPE");
  IElementType UINT_TYPE = new PLangTokenType("UINT_TYPE");
  IElementType F32_TYPE = new PLangTokenType("F32_TYPE");
  IElementType F64_TYPE = new PLangTokenType("F64_TYPE");
  IElementType TRUE = new PLangTokenType("TRUE");
  IElementType FALSE = new PLangTokenType("FALSE");

  IElementType ASSIGN = new PLangTokenType("ASSIGN");
  IElementType PLUS = new PLangTokenType("PLUS");
  IElementType MINUS = new PLangTokenType("MINUS");
  IElementType STAR = new PLangTokenType("STAR");
  IElementType SLASH = new PLangTokenType("SLASH");
  IElementType PERCENT = new PLangTokenType("PERCENT");
  IElementType EQ = new PLangTokenType("EQ");
  IElementType NE = new PLangTokenType("NE");
  IElementType LT = new PLangTokenType("LT");
  IElementType LE = new PLangTokenType("LE");
  IElementType GT = new PLangTokenType("GT");
  IElementType GE = new PLangTokenType("GE");
  IElementType AND = new PLangTokenType("AND");
  IElementType OR = new PLangTokenType("OR");
  IElementType NOT = new PLangTokenType("NOT");
  IElementType AMP = new PLangTokenType("AMP");
  IElementType PIPE = new PLangTokenType("PIPE");
  IElementType CARET = new PLangTokenType("CARET");
  IElementType TILDE = new PLangTokenType("TILDE");
  IElementType SHL = new PLangTokenType("SHL");
  IElementType SHR = new PLangTokenType("SHR");
  IElementType QUESTION = new PLangTokenType("QUESTION");
  IElementType PLUS_ASSIGN = new PLangTokenType("PLUS_ASSIGN");
  IElementType MINUS_ASSIGN = new PLangTokenType("MINUS_ASSIGN");
  IElementType STAR_ASSIGN = new PLangTokenType("STAR_ASSIGN");
  IElementType SLASH_ASSIGN = new PLangTokenType("SLASH_ASSIGN");
  IElementType PERCENT_ASSIGN = new PLangTokenType("PERCENT_ASSIGN");
  IElementType SHL_ASSIGN = new PLangTokenType("SHL_ASSIGN");
  IElementType SHR_ASSIGN = new PLangTokenType("SHR_ASSIGN");
  IElementType AMP_ASSIGN = new PLangTokenType("AMP_ASSIGN");
  IElementType PIPE_ASSIGN = new PLangTokenType("PIPE_ASSIGN");
  IElementType CARET_ASSIGN = new PLangTokenType("CARET_ASSIGN");
  IElementType INC = new PLangTokenType("INC");
  IElementType DEC = new PLangTokenType("DEC");
  IElementType ARROW = new PLangTokenType("ARROW");
  IElementType AT = new PLangTokenType("AT");
  IElementType COLON = new PLangTokenType("COLON");
  IElementType COMMA = new PLangTokenType("COMMA");
  IElementType DOT = new PLangTokenType("DOT");
  IElementType LPAREN = new PLangTokenType("LPAREN");
  IElementType RPAREN = new PLangTokenType("RPAREN");
  IElementType LBRACE = new PLangTokenType("LBRACE");
  IElementType RBRACE = new PLangTokenType("RBRACE");
  IElementType LBRACKET = new PLangTokenType("LBRACKET");
  IElementType RBRACKET = new PLangTokenType("RBRACKET");
  IElementType SEMICOLON = new PLangTokenType("SEMICOLON");

  TokenSet COMMENTS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT);
  TokenSet STRINGS = TokenSet.create(STRING, CHAR_LIT);
  TokenSet WHITESPACES = TokenSet.create(TokenType.WHITE_SPACE);

  TokenSet KEYWORDS = TokenSet.create(
    PACKAGE, IMPORT, VAR, VAL, MOVED, VOLATILE, FUNC, IMPL, RETURN, USING,
    STRUCT, ABSTRACT, UNION, ALIGN, ENUM, PUB, PRT, PRI, THIS, THIS_TYPE, TYPE, AS,
    IF, ELSE, WHILE, FOR, ASM, LAMBDA, GOTO, LABEL, SWITCH, CASE, DEFAULT,
    BREAK, CONTINUE, SIZEOF, EXTERN, NULL_LIT,
    INT_TYPE, CHAR_TYPE, STRING_TYPE, WCHAR_TYPE, WSTRING_TYPE, BOOL_TYPE,
    I8_TYPE, I16_TYPE, I32_TYPE, I64_TYPE,
    U8_TYPE, U16_TYPE, U32_TYPE, U64_TYPE, UINT_TYPE, F32_TYPE, F64_TYPE,
    TRUE, FALSE
  );

  TokenSet OPERATORS = TokenSet.create(
    ASSIGN, PLUS, MINUS, STAR, SLASH, PERCENT, EQ, NE, LT, LE, GT, GE,
    AND, OR, NOT, AMP, PIPE, CARET, TILDE, SHL, SHR, QUESTION,
    PLUS_ASSIGN, MINUS_ASSIGN, STAR_ASSIGN, SLASH_ASSIGN, PERCENT_ASSIGN,
    SHL_ASSIGN, SHR_ASSIGN, AMP_ASSIGN, PIPE_ASSIGN, CARET_ASSIGN,
    INC, DEC, ARROW, AT
  );
}
