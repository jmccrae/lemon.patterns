#!/bin/bash

die () {
    echo >&2 "$@"
    exit 1
}

cd src/main/bnfc
bnfc -java1.5 -p net.lemonmodel.patterns parser.cf || die "BNFC Failed"
cd ../java/net/lemonmodel/patterns/parser
jlex Yylex || die "JLex Failed"
cup parser.cup  || die "CUP Failed"
perl -pi -e 's/ident_1/ident_/g' Absyn/EPrefix.java
perl -pi -e 's/fulluri_2/fulluri_/g' Absyn/EPrefix.java

