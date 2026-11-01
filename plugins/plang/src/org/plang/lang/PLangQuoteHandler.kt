// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import org.plang.lang.psi.PLangTokenTypes

/** Quote handler for pLang strings and chars. */
class PLangQuoteHandler : SimpleTokenSetQuoteHandler(PLangTokenTypes.STRING, PLangTokenTypes.CHAR_LIT)
