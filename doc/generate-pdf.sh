#!/bin/sh
# Enter files without their prefix
MD_FILES[0]="procedure"
MD_FILES[1]="use-case-diagram"
MD_FILES[2]="story-cards"
#MD_FILES[N]="fileN" if the file is called 'fileN.md'


echo "The file will be outputted to ./pdf"
for FILE in ${MD_FILES[*]}
do
    echo "Attempting to generate '$FILE.md'"
    pandoc -f markdown+link_attributes -t latex  -V geometry:a4paper -o pdf/$FILE.pdf --filter pandoc-plantuml $FILE.md
done

echo "Finished atttempt at compiling documents."
