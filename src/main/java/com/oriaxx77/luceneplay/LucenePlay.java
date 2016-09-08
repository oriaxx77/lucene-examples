package com.oriaxx77.luceneplay;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class LucenePlay {

	private Analyzer analyzer;
	private Directory indexDirectory;
	
	private IndexCreator indexCreator;
	private IndexDocumentFinder indexDocumentFinder;
	
	public LucenePlay(){
		analyzer = new StandardAnalyzer();
		indexDirectory = new RAMDirectory();
		indexCreator = new IndexCreator( analyzer, indexDirectory );
		indexDocumentFinder = new IndexDocumentFinder( analyzer, indexDirectory );
	}

	
	public void createIndices() throws IOException {
		Entity[] entitiesToIndex = { new Entity(0,"apples oranges"), new Entity(1,"oranges"), new Entity(2, "apples") };
		indexCreator.createIndices( entitiesToIndex );
	}
	
	public void findAndPrintDocuments() throws IOException, ParseException {
		String[] queryStrings = { "tags:oranges NOT tags:apples",
				      			  "tags:oranges AND tags:apples",
				      			  "tags:oranges OR tags:apples" };
		QueryResultPrinter queryResultPrinter = new QueryResultPrinter();	
		for ( String queryString: queryStrings ){
			TopDocs docs = indexDocumentFinder.findDocuments(queryString);
			queryResultPrinter.printQueryResult(queryString, docs);
		}
	}
		
	
	// Section: static entry point
	
	public static void main(String[] args) throws Exception {		
		LucenePlay lucenePlay = new LucenePlay();
		lucenePlay.createIndices();
		lucenePlay.findAndPrintDocuments();
	}
	
	
		
	

	

}
