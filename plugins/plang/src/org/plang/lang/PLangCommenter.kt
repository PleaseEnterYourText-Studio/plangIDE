// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang

import com.intellij.lang.Commenter

/** Commenter for pLang: `//` line comments and `/* ... */` block comments. */
class PLangCommenter : Commenter {
  override fun getLineCommentPrefix(): String = "//"
  override fun getBlockCommentPrefix(): String = "/*"
  override fun getBlockCommentSuffix(): String = "*/"
  override fun getCommentedBlockCommentPrefix(): String? = null
  override fun getCommentedBlockCommentSuffix(): String? = null
}
