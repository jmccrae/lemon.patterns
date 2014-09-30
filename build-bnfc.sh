#!/bin/bash

die () {
    echo >&2 "$@"
    exit 1
}

cd src/main/bnfc
bnfc -java1.5 -p net.lemonmodel.patterns parser.cf || die "BNFC Failed"
cd ../java/net/lemonmodel/patterns/parser
perl -pi -e 's/\\x5E/^/g' Yylex
jflex_fixup=no
jflex --version |grep 'JFlex 1.5' && jflex_fixup=yes
if [ $jflex_fixup = "yes" ];then
  perl -pi -e 's/<YYINITIAL>\/ /<YYINITIAL>\\\/ /' Yylex
  perl -pi -e 's/yy_buffer,yy_buffer_index/zzBuffer,zzCurrentPos/' Yylex.java
fi
jlex Yylex || die "JLex Failed"
cup parser.cup  || die "CUP Failed"
perl -pi -e 's/ident_1/ident_/g' Absyn/EPrefix.java
perl -pi -e 's/fulluri_2/fulluri_/g' Absyn/EPrefix.java
rm `find . -name \*.bak`

