.PHONY: all
ML = pdflatex -shell-escape -interaction=nonstopmode -output-directory=aux

all: create clean

create:
	@echo "Making latex pdf"
	mkdir aux || ${ML} *.tex

clean:
	@echo "Moving PDF file to source folder"
	mv aux/*.pdf .
