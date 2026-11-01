// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
// Hand-written tolerant parser that corresponds to grammar/PLang.bnf.
// It builds the top-level PSI structure (declarations, blocks, parens, brackets).
// It reports only structural errors, so partially typed code stays usable.
package org.plang.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import org.plang.PLangBundle
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
        PLangTokenTypes.PACKAGE -> parseDeclaration(b, PLangTypes.PACKAGE_DECL, requiresSemicolon = true)
        PLangTokenTypes.IMPORT -> parseDeclaration(b, PLangTypes.IMPORT_DECL, requiresSemicolon = true)
        PLangTokenTypes.USING -> parseDeclaration(b, PLangTypes.USING_DECL, requiresSemicolon = true)
        PLangTokenTypes.STRUCT, PLangTokenTypes.ABSTRACT,
        PLangTokenTypes.UNION, PLangTokenTypes.ENUM,
        PLangTokenTypes.ALIGN -> parseDeclaration(b, PLangTypes.TYPE_DECL, requiresSemicolon = false)
        PLangTokenTypes.IMPL -> parseDeclaration(b, PLangTypes.IMPL_DECL, requiresSemicolon = false)
        PLangTokenTypes.FUNC -> parseDeclaration(b, PLangTypes.FUNC_DECL, requiresSemicolon = false)
        PLangTokenTypes.EXTERN -> parseDeclaration(b, PLangTypes.EXTERN_DECL, requiresSemicolon = true)
        PLangTokenTypes.ASM -> parseDeclaration(b, PLangTypes.ASM_BLOCK, requiresSemicolon = false)
        PLangTokenTypes.VAR, PLangTokenTypes.VAL, PLangTokenTypes.MOVED, PLangTokenTypes.VOLATILE,
        PLangTokenTypes.PUB, PLangTokenTypes.PRT, PLangTokenTypes.PRI -> parseDeclaration(b, PLangTypes.VAR_DECL, requiresSemicolon = true)
        PLangTokenTypes.LBRACE -> parseBlock(b)
        PLangTokenTypes.RBRACE -> reportStrayClosingBrace(b)
        else -> parseDeclaration(b, PLangTypes.STATEMENT, requiresSemicolon = true)
      }
    }
  }

  private fun reportStrayClosingBrace(b: PsiBuilder) {
    b.error(PLangBundle.message("error.rbrace.unexpected"))
    b.advanceLexer()
  }

  /**
   * Consumes a declaration up to the next top-level `;` or the end of its block.
   * When [requiresSemicolon] is true, a declaration that ends without `;` gets an error.
   * A declaration that contains a block needs no `;`, because the block closes it.
   */
  private fun parseDeclaration(b: PsiBuilder, type: IElementType, requiresSemicolon: Boolean) {
    val marker = b.mark()
    var semicolonFound = false
    var blockFound = false
    while (!b.eof()) {
      when (b.tokenType) {
        PLangTokenTypes.LBRACE -> {
          blockFound = true
          parseBlock(b)
          break // a body block ends the declaration
        }
        PLangTokenTypes.LPAREN -> parseBalanced(b, PLangTypes.PARAM_LIST, PLangTokenTypes.LPAREN, PLangTokenTypes.RPAREN)
        PLangTokenTypes.LBRACKET -> parseBalanced(b, PLangTypes.BRACKET_EXPR, PLangTokenTypes.LBRACKET, PLangTokenTypes.RBRACKET)
        PLangTokenTypes.SEMICOLON -> {
          b.advanceLexer()
          semicolonFound = true
          break
        }
        PLangTokenTypes.RBRACE -> break
        else -> b.advanceLexer()
      }
    }
    if (requiresSemicolon && !semicolonFound && !blockFound) {
      b.error(PLangBundle.message("error.semicolon.expected"))
    }
    marker.done(type)
  }

  private fun parseBlock(b: PsiBuilder) {
    val marker = b.mark()
    b.advanceLexer() // consume `{`
    var closed = false
    while (!b.eof()) {
      when (b.tokenType) {
        PLangTokenTypes.LBRACE -> parseBlock(b)
        PLangTokenTypes.LPAREN -> parseBalanced(b, PLangTypes.PAREN_EXPR, PLangTokenTypes.LPAREN, PLangTokenTypes.RPAREN)
        PLangTokenTypes.LBRACKET -> parseBalanced(b, PLangTypes.BRACKET_EXPR, PLangTokenTypes.LBRACKET, PLangTokenTypes.RBRACKET)
        PLangTokenTypes.RBRACE -> {
          b.advanceLexer()
          closed = true
          break
        }
        else -> b.advanceLexer()
      }
    }
    if (!closed) {
      b.error(PLangBundle.message("error.rbrace.expected"))
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
    if (depth > 0) {
      val key = if (close === PLangTokenTypes.RPAREN) "error.rparen.expected" else "error.rbracket.expected"
      b.error(PLangBundle.message(key))
    }
    marker.done(type)
  }
}
