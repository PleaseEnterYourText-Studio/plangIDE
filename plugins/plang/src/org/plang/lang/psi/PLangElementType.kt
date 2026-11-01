// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang.psi

import com.intellij.psi.tree.IElementType
import org.plang.lang.PLangLanguage

/** Element type for a pLang composite PSI node. */
class PLangElementType(debugName: String) : IElementType(debugName, PLangLanguage)
