#!/bin/sh
usage() {
  echo "publi2.sh <output> <title> <input_1> [... <input_n>]"
  echo "e.g. publi2.sh d19.pdf \"Concurrency II: executors\" d18.tex d19.tex"
  exit 0
}

fileNotFound() {
  echo "File $1 does not exist. Nothing to do..."
  exit 0
}

fileError() {
  echo $1
  exit 0
}


if [ -z "$3" ]; then # Three compulsory args
  usage;
fi
OUTPUT="$1"
TITLE="$2"
TMPFILE="tmptmp"
# TODO: check if output exists and ask if user wants to rewrite
shift
shift
echo > $TMPFILE.tex
echo '\\documentclass{article}' >> $TMPFILE.tex
echo '\\usepackage[dvips]{geometry}' >> $TMPFILE.tex
echo '\\usepackage{fancyvrb}' >> $TMPFILE.tex
echo '\\usepackage[dvips]{graphicx}' >> $TMPFILE.tex
echo '\\usepackage{framed}' >> $TMPFILE.tex
echo '\\usepackage{amssymb,amsmath}' >> $TMPFILE.tex
echo '\\begin{document}' >> $TMPFILE.tex
echo '\\title{'$TITLE'}' >> $TMPFILE.tex
echo '\\author{}' >> $TMPFILE.tex
echo '\\date{}' >> $TMPFILE.tex
echo '\\maketitle' >> $TMPFILE.tex
for INPUT in $*; do
  if [ -f $INPUT ]; then
    echo '\\input{' $INPUT '}' >> $TMPFILE.tex
  else 
    echo "File $INPUT could not be read. Skipping..."
    sleep 1
  fi
done
echo '\\end{document}' >> $TMPFILE.tex

latex $TMPFILE.tex && \
   latex $TMPFILE.tex && \
   latex $TMPFILE.tex && \
   dvips $TMPFILE.dvi -o && \
   ps2pdf $TMPFILE.ps && \
   mv $TMPFILE.pdf $OUTPUT && \
   echo && \
   latex $TMPFILE.tex |grep "LaTeX Warning" |cat && \
   echo && \
   echo $OUTPUT created... && \
   rm $TMPFILE.*
