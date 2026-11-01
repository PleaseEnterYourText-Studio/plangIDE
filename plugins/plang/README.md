<!-- Copyright 2026 pLang IDE contributors. Use of this source code is governed by the Apache 2.0 license. -->

# pLang IDE and the pLang plugin

pLang IDE is an IDE for the [pLang](https://github.com/PleaseEnterYourText-Studio/pLang) language.
It is a derivative work of the IntelliJ Platform and IntelliJ IDEA Community Edition.
The source code stays under the Apache License 2.0.
See `NOTICE.txt` in the repository root for the full attribution.

pLang IDE is not a product of JetBrains s.r.o.
The build replaces the JetBrains product name, the icons, and the splash screen with neutral resources.

## The pLang plugin

The plugin in this directory adds pLang language support to the IDE:

- The file type for `.plang` sources.
- A lexer and a parser that build the PSI tree.
- Syntax highlighting with a color settings page.
- Brace matching, quotes, and line and block comments.

Directory layout:

- `grammar/` has the JFlex lexer and the Grammar-Kit grammar sources.
- `gen/` has the generated lexer and the token types. These files are checked in.
- `src/` has the plugin source code.
- `resources/` has `plugin.xml`, the icons, and the message bundle.

## Build

The prerequisites are the same as for IntelliJ IDEA Community Edition.
See `README.md` in the repository root.

1. Get the external plugin sources: `getPlugins.bat --shallow`.
2. Sync the project model to Bazel: `build\jpsModelToBazelCommunityOnly.cmd`.
3. Build only the plugin: `.\bazel.cmd build //plugins/plang:plang`.
4. Build the Windows installer: `.\installers.cmd`.

The installer and the ZIP archive go to `out\plang-ide\artifacts\`.

## Bundled plugins

The build keeps the bundled plugins of the IntelliJ Community source tree.
All of them are under the Apache License 2.0.
The build adds no proprietary JetBrains plugin.

## Known limits

- The parser accepts every token stream and does not report syntax errors yet.
- The pre-built native launchers keep the resources that the upstream build signs them with.
- Some UI texts from upstream plugins can still mention IntelliJ IDEA.
