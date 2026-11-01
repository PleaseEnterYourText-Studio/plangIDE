// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import org.plang.lang.PLangFileType
import org.plang.lang.PLangLanguage

/** PSI file for a pLang source file. */
class PLangFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, PLangLanguage) {
  override fun getFileType(): PLangFileType = PLangFileType
  override fun toString(): String = "pLang File"
}
