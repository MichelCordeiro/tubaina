package br.com.caelum.bibliography;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.tubaina.bibliography.Bibliography;
import br.com.caelum.tubaina.bibliography.BibliographyEntry;
import br.com.caelum.tubaina.bibliography.BibliographyFactory;
import br.com.caelum.tubaina.bibliography.LatexBibliographyGenerator;

public class LatexBibliographyGeneratorTest {

    private LatexBibliographyGenerator latexBibGenerator;

    @Before
    public void setUp() {
        latexBibGenerator = new LatexBibliographyGenerator();
    }
    
    @Test
    public void shouldGenerateBibContent() throws Exception {
        Bibliography bibliography = new BibliographyFactory().build(new File(
                "src/test/resources/bibliography/bibsimple.xml"));

        String expectedBib = new Scanner(new File("src/test/resources/bibliography/book.bib"))
                .useDelimiter("$$").next();

        assertEquals(expectedBib, latexBibGenerator.generateTextOf(bibliography));
    }

    @Test
    public void shouldGenerateBibContentWithPublisher() throws Exception {

        Bibliography bibliography = new BibliographyFactory().build(new File(
                "src/test/resources/bibliography/bibwithpublisher.xml"));

        String expectedBib = new Scanner(new File(
                "src/test/resources/bibliography/bookwithpublisher.bib")).useDelimiter("$$").next();

        assertEquals(expectedBib, latexBibGenerator.generateTextOf(bibliography));
    }
    
    @Test
    public void shouldGenerateBibWithoutAField() throws Exception {

        Bibliography bibliography = new BibliographyFactory().build(new File(
                "src/test/resources/bibliography/miscwithoutyear.xml"));

        String expectedBib = new Scanner(new File(
                "src/test/resources/bibliography/miscwithoutyear.bib")).useDelimiter("$$").next();

        assertEquals(expectedBib, latexBibGenerator.generateTextOf(bibliography));
    }
    
    @Test
    public void shouldGenerateBibliographyWithEmptyHowPublished() throws Exception {
        BibliographyEntry entry = new BibliographyEntry("author", "title", "1999", null, "article", "label");
        Bibliography bibliography = new Bibliography(Arrays.asList(entry));
        latexBibGenerator.generateTextOf(bibliography);
    }
    
    @Test
    public void shouldGenerateBibliographyWithEmptyHowAuthor() throws Exception {
        BibliographyEntry entry = new BibliographyEntry(null, "title", "1999", null, "article", "label");
        Bibliography bibliography = new Bibliography(Arrays.asList(entry));
        latexBibGenerator.generateTextOf(bibliography);
    }
    
    
}
