// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import org.plang.lang.psi.PLangTokenTypes

/** Matches pLang braces, parentheses, and brackets. */
class PLangBraceMatcher : PairedBraceMatcher {
  override fun getPairs(): Array<BracePair> = arrayOf(
    BracePair(PLangTokenTypes.LBRACE, PLangTokenTypes.RBRACE, true),
    BracePair(PLangTokenTypes.LPAREN, PLangTokenTypes.RPAREN, false),
    BracePair(PLangTokenTypes.LBRACKET, PLangTokenTypes.RBRACKET, false),
  )

  override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true

  override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int = openingBraceOffset
}
