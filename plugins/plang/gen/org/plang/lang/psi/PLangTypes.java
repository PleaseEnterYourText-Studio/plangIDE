// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
// Composite element types for pLang. Mirrors grammar/PLang.bnf.
// Regenerate with Grammar-Kit when the grammar changes.
package org.plang.lang.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface PLangTypes {
  IElementType PACKAGE_DECL = new PLangElementType("PACKAGE_DECL");
  IElementType IMPORT_DECL = new PLangElementType("IMPORT_DECL");
  IElementType USING_DECL = new PLangElementType("USING_DECL");
  IElementType TYPE_DECL = new PLangElementType("TYPE_DECL");
  IElementType IMPL_DECL = new PLangElementType("IMPL_DECL");
  IElementType FUNC_DECL = new PLangElementType("FUNC_DECL");
  IElementType EXTERN_DECL = new PLangElementType("EXTERN_DECL");
  IElementType VAR_DECL = new PLangElementType("VAR_DECL");
  IElementType PARAM_LIST = new PLangElementType("PARAM_LIST");
  IElementType PAREN_EXPR = new PLangElementType("PAREN_EXPR");
  IElementType BRACKET_EXPR = new PLangElementType("BRACKET_EXPR");
  IElementType BLOCK = new PLangElementType("BLOCK");
  IElementType STATEMENT = new PLangElementType("STATEMENT");
  IElementType ASM_BLOCK = new PLangElementType("ASM_BLOCK");

  final class Factory {
    public static PsiElement createElement(ASTNode node) {
      return new ASTWrapperPsiElement(node);
    }
  }
}
