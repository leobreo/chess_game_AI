# To generate final document: 'make Doc-Name.pdf'
# And to clean up garbage: 'make clean'
# @author: I. S. Kocic

Anforderungsanalyse.pdf: anforderungsanalyse.md
	pandoc -f markdown+link_attributes -t html \
		-o pdf/Anforderungsanalyse.pdf \
		--template=Github.html5 \
		--metadata title=Anforderungsanalyse \
		-V geometry:a4paper \
		--filter pandoc-plantuml \
		anforderungsanalyse.md

Vorgehensplan.pdf: procedure.md
	pandoc -f markdown+link_attributes -t html \
		-o pdf/Vorgehensplan.pdf \
		--template=Github.html5 \
		--metadata title="SwEng Praktikum (G-13)" \
		-V geometry:a4paper \
		--filter pandoc-plantuml \
		procedure.md

Architecture.pdf:
	pandoc -f markdown+link_attributes -t html \
		-o pdf/Architecture.pdf \
		--template=Github.html5 \
		--metadata title="Architecture Documentation" \
		-V geometry:a4paper \
		--filter pandoc-plantuml \
		architecture-doc.md

User-Manual.pdf:
	pandoc -f markdown+link_attributes -t html \
		-o pdf/User-Manual.pdf \
		--template=Github.html5 \
		--metadata title="User Manual" \
		-V geometry:a4paper \
		--filter pandoc-plantuml \
		user-manual.md

anforderungsanalyse.md:
	touch anforderungsanalyse.md && \
	cat use-case-diagram.md >> anforderungsanalyse.md && \
	cat story-cards.md >> anforderungsanalyse.md

clean:
	-rm anforderungsanalyse.md
	-rm *.html
	-rm -r plantuml-images

all: Vorgehensplan.pdf Anforderungsanalyse.pdf
