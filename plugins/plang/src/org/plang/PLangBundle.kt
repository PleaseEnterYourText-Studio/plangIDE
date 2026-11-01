// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang

import com.intellij.DynamicBundle
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.PropertyKey

private const val BUNDLE = "messages.PLangBundle"

/** Resource bundle of the pLang plugin. */
object PLangBundle : DynamicBundle(BUNDLE) {
  @Nls
  fun message(key: @PropertyKey(resourceBundle = BUNDLE) String, vararg params: Any): String = getMessage(key, *params)
}
