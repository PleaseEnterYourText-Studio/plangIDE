// Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license.
package org.plang.lang

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import org.plang.PLangBundle
import javax.swing.Icon

/** Color settings page for pLang. */
class PLangColorSettingsPage : ColorSettingsPage {
  override fun getIcon(): Icon = PLangIcons.FILE

  override fun getHighlighter(): PLangSyntaxHighlighter = PLangSyntaxHighlighter()

  override fun getDemoText(): String = """
    package demo;
    import std.io;

    /* Block comment */
    // Line comment

    struct Point {
      pub val: int x;
      pub val: int y;
    }

    func add(val: int a, val: int b) : int {
      return a + b;
    }

    func main() : int {
      var: i32 value = 0xFF;
      var: f64 pi = 3.14;
      var: string name = "plang";
      var: char c = 'x';
      var: bool flag = true;
      if (value >= 10 && flag) {
        io.printInt(add(value, 1));
      }
      return 0;
    }
  """.trimIndent()

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDisplayName(): String = PLangBundle.message("color.settings.plang.name")

  companion object {
    private val DESCRIPTORS = arrayOf(
      AttributesDescriptor("Keyword", PLangSyntaxHighlighter.KEYWORD),
      AttributesDescriptor("Number", PLangSyntaxHighlighter.NUMBER),
      AttributesDescriptor("String//Text", PLangSyntaxHighlighter.STRING),
      AttributesDescriptor("Comment//Line comment", PLangSyntaxHighlighter.LINE_COMMENT),
      AttributesDescriptor("Comment//Block comment", PLangSyntaxHighlighter.BLOCK_COMMENT),
      AttributesDescriptor("Operator", PLangSyntaxHighlighter.OPERATION_SIGN),
      AttributesDescriptor("Braces and Operators//Parentheses", PLangSyntaxHighlighter.PARENTHESES),
      AttributesDescriptor("Braces and Operators//Braces", PLangSyntaxHighlighter.BRACES),
      AttributesDescriptor("Braces and Operators//Brackets", PLangSyntaxHighlighter.BRACKETS),
      AttributesDescriptor("Braces and Operators//Semicolon", PLangSyntaxHighlighter.SEMICOLON),
      AttributesDescriptor("Braces and Operators//Comma", PLangSyntaxHighlighter.COMMA),
      AttributesDescriptor("Braces and Operators//Dot", PLangSyntaxHighlighter.DOT),
      AttributesDescriptor("Identifier", PLangSyntaxHighlighter.IDENTIFIER),
      AttributesDescriptor("Bad character", PLangSyntaxHighlighter.BAD_CHARACTER),
    )
  }
}
