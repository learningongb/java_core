SRCDIR:=src
OUTDIR:=out
DOCDIR:=doc

all:
	javac -sourcepath ./${SRCDIR}/ -d ${OUTDIR} src/ru/gb/notes/Main.java

clean:
	rm -rf ${OUTDIR}
	rm -rf ${DOCDIR}

doc:
	 javadoc -d ${DOCDIR}/ -sourcepath ${SRCDIR} -cp ${OUTDIR} -subpackages ru
