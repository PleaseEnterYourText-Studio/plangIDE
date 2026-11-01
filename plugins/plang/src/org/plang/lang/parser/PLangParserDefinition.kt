// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.plang.lang.PLangLanguage
import org.plang.lang.lexer.PLangLexer
import org.plang.lang.psi.PLangFile
import org.plang.lang.psi.PLangTokenTypes
import org.plang.lang.psi.PLangTypes

/** Binds the pLang lexer and parser to the platform. */
class PLangParserDefinition : ParserDefinition {
  override fun createLexer(project: Project?): Lexer = PLangLexer()

  override fun createParser(project: Project?): PsiParser = PLangParser()

  override fun getFileNodeType(): IFileElementType = FILE

  override fun getCommentTokens(): TokenSet = PLangTokenTypes.COMMENTS

  override fun getStringLiteralElements(): TokenSet = PLangTokenTypes.STRINGS

  override fun getWhitespaceTokens(): TokenSet = PLangTokenTypes.WHITESPACES

  override fun createElement(node: ASTNode): PsiElement = PLangTypes.Factory.createElement(node)

  override fun createFile(viewProvider: FileViewProvider): PsiFile = PLangFile(viewProvider)

  override fun spaceExistenceTypeBetweenTokens(left: ASTNode, right: ASTNode): ParserDefinition.SpaceRequirements =
    ParserDefinition.SpaceRequirements.MAY

  companion object {
    @JvmField
    val FILE: IFileElementType = IFileElementType(PLangLanguage)
  }
}
