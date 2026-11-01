// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang.lexer

import com.intellij.lexer.FlexAdapter

/** Lexer adapter for the JFlex-generated pLang lexer. */
class PLangLexer : FlexAdapter(_PLangLexer())
