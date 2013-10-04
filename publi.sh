#!/bin/sh
usage() {
  echo "publi.sh <filenamewithoutextension>"
  exit 0
}

fileNotFound() {
  echo "File $1 does not exist. Nothing to do..."
  exit 0
}

[ -n "$1" ] && FILE=$1 || usage
[ -e "$FILE".tex ] || fileNotFound $FILE.tex

latex $FILE && \
   latex $FILE && \
   latex $FILE && \
   dvips $FILE.dvi -o && \
   ps2pdf $FILE.ps && \
   echo && \
   latex $FILE |grep "LaTeX Warning" |cat && \
   echo && \
   echo $FILE.pdf created... && \
   rm $FILE.ps
