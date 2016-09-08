package com.oriaxx77.luceneplay;

import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class QueryResultPrinter {

	public void printQueryResult( String queryString, TopDocs docs ){
		printSearchHeader( queryString );
		printSearchResult( docs );
	}
	
	private void printSearchHeader( String queryString ){
		System.out.println( "QueryString: "+ queryString );
	}
	
	private void printSearchResult( TopDocs docs ){
		for ( ScoreDoc doc :docs.scoreDocs ){
			System.out.println( doc );
		}
		System.out.println( "" );
	}
}
