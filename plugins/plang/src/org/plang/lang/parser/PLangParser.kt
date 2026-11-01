// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
// Hand-written tolerant parser that corresponds to grammar/PLang.bnf.
// It builds the top-level PSI structure (declarations, blocks, parens, brackets).
// It never reports a syntax error, so partially typed code stays usable.
package org.plang.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import org.plang.lang.psi.PLangTokenTypes
import org.plang.lang.psi.PLangTypes

class PLangParser : PsiParser {
  override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
    val fileMarker = builder.mark()
    parseFile(builder)
    fileMarker.done(root)
    return builder.treeBuilt
  }

  private fun parseFile(b: PsiBuilder) {
    while (!b.eof()) {
      when (b.tokenType) {
        PLangTokenTypes.PACKAGE -> parseDeclaration(b, PLangTypes.PACKAGE_DECL)
        PLangTokenTypes.IMPORT -> parseDeclaration(b, PLangTypes.IMPORT_DECL)
        PLangTokenTypes.USING -> parseDeclaration(b, PLangTypes.USING_DECL)
        PLangTokenTypes.STRUCT, PLangTokenTypes.ABSTRACT,
        PLangTokenTypes.UNION, PLangTokenTypes.ENUM,
        PLangTokenTypes.ALIGN -> parseDeclaration(b, PLangTypes.TYPE_DECL)
        PLangTokenTypes.IMPL -> parseDeclaration(b, PLangTypes.IMPL_DECL)
        PLangTokenTypes.FUNC -> parseDeclaration(b, PLangTypes.FUNC_DECL)
        PLangTokenTypes.EXTERN -> parseDeclaration(b, PLangTypes.EXTERN_DECL)
        PLangTokenTypes.ASM -> parseDeclaration(b, PLangTypes.ASM_BLOCK)
        PLangTokenTypes.VAR, PLangTokenTypes.VAL, PLangTokenTypes.MOVED, PLangTokenTypes.VOLATILE,
        PLangTokenTypes.PUB, PLangTokenTypes.PRT, PLangTokenTypes.PRI -> parseDeclaration(b, PLangTypes.VAR_DECL)
        PLangTokenTypes.LBRACE -> parseBlock(b)
        PLangTokenTypes.RBRACE -> b.advanceLexer() // stray closing brace
        else -> parseDeclaration(b, PLangTypes.STATEMENT)
      }
    }
  }

  /** Consumes a declaration up to the next top-level `;` or the end of its block. */
  private fun parseDeclaration(b: PsiBuilder, type: IElementType) {
    val marker = b.mark()
    while (!b.eof()) {
      when (b.tokenType) {
        PLangTokenTypes.LBRACE -> parseBlock(b)
        PLangTokenTypes.LPAREN -> parseBalanced(b, PLangTypes.PARAM_LIST, PLangTokenTypes.LPAREN, PLangTokenTypes.RPAREN)
        PLangTokenTypes.LBRACKET -> parseBalanced(b, PLangTypes.BRACKET_EXPR, PLangTokenTypes.LBRACKET, PLangTokenTypes.RBRACKET)
        PLangTokenTypes.SEMICOLON -> {
          b.advanceLexer()
          break
        }
        PLangTokenTypes.RBRACE -> break
        else -> b.advanceLexer()
      }
    }
    marker.done(type)
  }

  private fun parseBlock(b: PsiBuilder) {
    val marker = b.mark()
    b.advanceLexer() // consume `{`
    while (!b.eof()) {
      when (b.tokenType) {
        PLangTokenTypes.LBRACE -> parseBlock(b)
        PLangTokenTypes.LPAREN -> parseBalanced(b, PLangTypes.PAREN_EXPR, PLangTokenTypes.LPAREN, PLangTokenTypes.RPAREN)
        PLangTokenTypes.LBRACKET -> parseBalanced(b, PLangTypes.BRACKET_EXPR, PLangTokenTypes.LBRACKET, PLangTokenTypes.RBRACKET)
        PLangTokenTypes.RBRACE -> {
          b.advanceLexer()
          marker.done(PLangTypes.BLOCK)
          return
        }
        else -> b.advanceLexer()
      }
    }
    marker.done(PLangTypes.BLOCK)
  }

  /** Consumes a balanced group of `open` ... `close`, with nesting. */
  private fun parseBalanced(b: PsiBuilder, type: IElementType, open: IElementType, close: IElementType) {
    val marker = b.mark()
    var depth = 0
    while (!b.eof()) {
      val token = b.tokenType
      if (token === open) {
        depth++
      }
      else if (token === close) {
        depth--
      }
      b.advanceLexer()
      if (depth == 0) break
    }
    marker.done(type)
  }
}
