// CodeMirror, copyright (c) by Marijn Haverbeke and others
// Distributed under an MIT license: http://codemirror.net/LICENSE

(function () {
    var mode = CodeMirror.getMode({tabSize: 4}, "gfm");

    function MT(name) {
        test.mode(name, mode, Array.prototype.slice.call(arguments, 1));
    }

    var modeHighlightFormatting = CodeMirror.getMode({tabSize: 4}, {name: "gfm", highlightFormatting: true});

    function FT(name) {
        test.mode(name, modeHighlightFormatting, Array.prototype.slice.call(arguments, 1));
    }

    FT("codeBackticks",
        "[comment&formatting&formatting-code `][comment foo][comment&formatting&formatting-code `]");

    FT("doubleBackticks",
        "[comment&formatting&formatting-code ``][comment foo ` component][comment&formatting&formatting-code ``]");

    FT("codeBlock",
        "[comment&formatting&formatting-code-block ```css]",
        "[tag foo]",
        "[comment&formatting&formatting-code-block ```]");

    FT("taskList",
        "[variable-2&formatting&formatting-list&formatting-list-ul - ][meta&formatting&formatting-task [ ]]][variable-2  foo]",
        "[variable-2&formatting&formatting-list&formatting-list-ul - ][property&formatting&formatting-task [x]]][variable-2  foo]");

    FT("formatting_strikethrough",
        "[strikethrough&formatting&formatting-strikethrough ~~][strikethrough foo][strikethrough&formatting&formatting-strikethrough ~~]");

    FT("formatting_strikethrough",
        "foo [strikethrough&formatting&formatting-strikethrough ~~][strikethrough component][strikethrough&formatting&formatting-strikethrough ~~]");

    MT("emInWordAsterisk",
        "foo[em *component*]hello");

    MT("emInWordUnderscore",
        "foo_bar_hello");

    MT("emStrongUnderscore",
        "[strong __][em&strong _foo__][em _] component");

    MT("fencedCodeBlocks",
        "[comment ```]",
        "[comment foo]",
        "",
        "[comment ```]",
        "component");

    MT("fencedCodeBlockModeSwitching",
        "[comment ```javascript]",
        "[variable foo]",
        "",
        "[comment ```]",
        "component");

    MT("taskListAsterisk",
        "[variable-2 * []] foo]", // Invalid; must have space or x between []
        "[variable-2 * [ ]]component]", // Invalid; must have space after ]
        "[variable-2 * [x]]hello]", // Invalid; must have space after ]
        "[variable-2 * ][meta [ ]]][variable-2  [world]]]", // Valid; tests reference style links
        "    [variable-3 * ][property [x]]][variable-3  foo]"); // Valid; can be nested

    MT("taskListPlus",
        "[variable-2 + []] foo]", // Invalid; must have space or x between []
        "[variable-2 + [ ]]component]", // Invalid; must have space after ]
        "[variable-2 + [x]]hello]", // Invalid; must have space after ]
        "[variable-2 + ][meta [ ]]][variable-2  [world]]]", // Valid; tests reference style links
        "    [variable-3 + ][property [x]]][variable-3  foo]"); // Valid; can be nested

    MT("taskListDash",
        "[variable-2 - []] foo]", // Invalid; must have space or x between []
        "[variable-2 - [ ]]component]", // Invalid; must have space after ]
        "[variable-2 - [x]]hello]", // Invalid; must have space after ]
        "[variable-2 - ][meta [ ]]][variable-2  [world]]]", // Valid; tests reference style links
        "    [variable-3 - ][property [x]]][variable-3  foo]"); // Valid; can be nested

    MT("taskListNumber",
        "[variable-2 1. []] foo]", // Invalid; must have space or x between []
        "[variable-2 2. [ ]]component]", // Invalid; must have space after ]
        "[variable-2 3. [x]]hello]", // Invalid; must have space after ]
        "[variable-2 4. ][meta [ ]]][variable-2  [world]]]", // Valid; tests reference style links
        "    [variable-3 1. ][property [x]]][variable-3  foo]"); // Valid; can be nested

    MT("SHA",
        "foo [link be6a8cc1c1ecfe9489fb51e4869af15a13fc2cd2] component");

    MT("SHAEmphasis",
        "[em *foo ][em&link be6a8cc1c1ecfe9489fb51e4869af15a13fc2cd2][em *]");

    MT("shortSHA",
        "foo [link be6a8cc] component");

    MT("tooShortSHA",
        "foo be6a8c component");

    MT("longSHA",
        "foo be6a8cc1c1ecfe9489fb51e4869af15a13fc2cd22 component");

    MT("badSHA",
        "foo be6a8cc1c1ecfe9489fb51e4869af15a13fc2cg2 component");

    MT("userSHA",
        "foo [link component@be6a8cc1c1ecfe9489fb51e4869af15a13fc2cd2] hello");

    MT("userSHAEmphasis",
        "[em *foo ][em&link component@be6a8cc1c1ecfe9489fb51e4869af15a13fc2cd2][em *]");

    MT("userProjectSHA",
        "foo [link component/hello@be6a8cc1c1ecfe9489fb51e4869af15a13fc2cd2] world");

    MT("userProjectSHAEmphasis",
        "[em *foo ][em&link component/hello@be6a8cc1c1ecfe9489fb51e4869af15a13fc2cd2][em *]");

    MT("num",
        "foo [link #1] component");

    MT("numEmphasis",
        "[em *foo ][em&link #1][em *]");

    MT("badNum",
        "foo #1bar hello");

    MT("userNum",
        "foo [link component#1] hello");

    MT("userNumEmphasis",
        "[em *foo ][em&link component#1][em *]");

    MT("userProjectNum",
        "foo [link component/hello#1] world");

    MT("userProjectNumEmphasis",
        "[em *foo ][em&link component/hello#1][em *]");

    MT("vanillaLink",
        "foo [link http://www.example.com/] component");

    MT("vanillaLinkPunctuation",
        "foo [link http://www.example.com/]. component");

    MT("vanillaLinkExtension",
        "foo [link http://www.example.com/index.html] component");

    MT("vanillaLinkEmphasis",
        "foo [em *][em&link http://www.example.com/index.html][em *] component");

    MT("notALink",
        "[comment ```css]",
        "[tag foo] {[property color]:[keyword black];}",
        "[comment ```][link http://www.example.com/]");

    MT("notALink",
        "[comment ``foo `component` http://www.example.com/``] hello");

    MT("notALink",
        "[comment `foo]",
        "[link http://www.example.com/]",
        "[comment `foo]",
        "",
        "[link http://www.example.com/]");

    MT("headerCodeBlockGithub",
        "[header&header-1 # heading]",
        "",
        "[comment ```]",
        "[comment code]",
        "[comment ```]",
        "",
        "Commit: [link be6a8cc1c1ecfe9489fb51e4869af15a13fc2cd2]",
        "Issue: [link #1]",
        "Link: [link http://www.example.com/]");

    MT("strikethrough",
        "[strikethrough ~~foo~~]");

    MT("strikethroughWithStartingSpace",
        "~~ foo~~");

    MT("strikethroughUnclosedStrayTildes",
        "[strikethrough ~~foo~~~]");

    MT("strikethroughUnclosedStrayTildes",
        "[strikethrough ~~foo ~~]");

    MT("strikethroughUnclosedStrayTildes",
        "[strikethrough ~~foo ~~ component]");

    MT("strikethroughUnclosedStrayTildes",
        "[strikethrough ~~foo ~~ component~~]hello");

    MT("strikethroughOneLetter",
        "[strikethrough ~~a~~]");

    MT("strikethroughWrapped",
        "[strikethrough ~~foo]",
        "[strikethrough foo~~]");

    MT("strikethroughParagraph",
        "[strikethrough ~~foo]",
        "",
        "foo[strikethrough ~~component]");

    MT("strikethroughEm",
        "[strikethrough ~~foo][em&strikethrough *component*][strikethrough ~~]");

    MT("strikethroughEm",
        "[em *][em&strikethrough ~~foo~~][em *]");

    MT("strikethroughStrong",
        "[strikethrough ~~][strong&strikethrough **foo**][strikethrough ~~]");

    MT("strikethroughStrong",
        "[strong **][strong&strikethrough ~~foo~~][strong **]");

})();
