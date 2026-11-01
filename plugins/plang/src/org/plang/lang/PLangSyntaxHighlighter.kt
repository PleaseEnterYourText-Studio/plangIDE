// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import org.plang.lang.lexer.PLangLexer
import org.plang.lang.psi.PLangTokenTypes

/** Maps pLang token types to text attributes. */
class PLangSyntaxHighlighter : SyntaxHighlighterBase() {

  override fun getHighlightingLexer(): Lexer = PLangLexer()

  override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> = pack(mapKey(tokenType))

  companion object {
    val KEYWORD: TextAttributesKey = createTextAttributesKey("PLANG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
    val NUMBER: TextAttributesKey = createTextAttributesKey("PLANG_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
    val STRING: TextAttributesKey = createTextAttributesKey("PLANG_STRING", DefaultLanguageHighlighterColors.STRING)
    val LINE_COMMENT: TextAttributesKey =
      createTextAttributesKey("PLANG_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val BLOCK_COMMENT: TextAttributesKey =
      createTextAttributesKey("PLANG_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
    val OPERATION_SIGN: TextAttributesKey =
      createTextAttributesKey("PLANG_OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN)
    val PARENTHESES: TextAttributesKey =
      createTextAttributesKey("PLANG_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
    val BRACES: TextAttributesKey = createTextAttributesKey("PLANG_BRACES", DefaultLanguageHighlighterColors.BRACES)
    val BRACKETS: TextAttributesKey = createTextAttributesKey("PLANG_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
    val SEMICOLON: TextAttributesKey = createTextAttributesKey("PLANG_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
    val COMMA: TextAttributesKey = createTextAttributesKey("PLANG_COMMA", DefaultLanguageHighlighterColors.COMMA)
    val DOT: TextAttributesKey = createTextAttributesKey("PLANG_DOT", DefaultLanguageHighlighterColors.DOT)
    val IDENTIFIER: TextAttributesKey =
      createTextAttributesKey("PLANG_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
    val BAD_CHARACTER: TextAttributesKey =
      createTextAttributesKey("PLANG_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

    private fun mapKey(tokenType: IElementType): TextAttributesKey? = when (tokenType) {
      PLangTokenTypes.LINE_COMMENT -> LINE_COMMENT
      PLangTokenTypes.BLOCK_COMMENT -> BLOCK_COMMENT
      PLangTokenTypes.NUMBER -> NUMBER
      PLangTokenTypes.STRING, PLangTokenTypes.CHAR_LIT -> STRING
      PLangTokenTypes.IDENTIFIER -> IDENTIFIER
      PLangTokenTypes.LPAREN, PLangTokenTypes.RPAREN -> PARENTHESES
      PLangTokenTypes.LBRACE, PLangTokenTypes.RBRACE -> BRACES
      PLangTokenTypes.LBRACKET, PLangTokenTypes.RBRACKET -> BRACKETS
      PLangTokenTypes.SEMICOLON -> SEMICOLON
      PLangTokenTypes.COMMA -> COMMA
      PLangTokenTypes.DOT, PLangTokenTypes.COLON -> DOT
      TokenType.BAD_CHARACTER -> BAD_CHARACTER
      else -> when {
        PLangTokenTypes.KEYWORDS.contains(tokenType) -> KEYWORD
        PLangTokenTypes.OPERATORS.contains(tokenType) -> OPERATION_SIGN
        else -> null
      }
    }
  }
}
