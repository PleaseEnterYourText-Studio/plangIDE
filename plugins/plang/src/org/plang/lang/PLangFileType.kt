// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import org.plang.PLangBundle
import javax.swing.Icon

/** File type for pLang source files (extension `plang`). */
object PLangFileType : LanguageFileType(PLangLanguage) {
  override fun getName(): String = "pLang"
  override fun getDescription(): String = PLangBundle.message("plang.filetype.description")
  override fun getDefaultExtension(): String = "plang"
  override fun getIcon(): Icon = PLangIcons.FILE
}
