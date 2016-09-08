package com.oriaxx77.luceneplay;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

public class IndexDocumentFinder {
	
	private Analyzer analyzer;
	private Directory indexDirectory;
	
	public IndexDocumentFinder( Analyzer analyzer, Directory indexDirectory ){
		this.analyzer = analyzer;
		this.indexDirectory = indexDirectory;
	}
	
	public TopDocs findDocuments( String queryString ) throws IOException, ParseException {
		IndexReader indexReader = null;
		QueryParser queryParser = new QueryParser( "tags", analyzer );
		try {
			indexReader = DirectoryReader.open( indexDirectory );
			IndexSearcher indexSearcher = new IndexSearcher( indexReader );
			return runQuery( indexSearcher, queryParser, queryString );
		} finally {
			if ( indexReader != null )
				indexReader.close();
		}
	}
	
	private TopDocs runQuery( IndexSearcher indexSearcher, QueryParser queryParser, String queryString ) throws ParseException, IOException {
		Query query = queryParser.parse( queryString );
		TopDocs docs = indexSearcher.search(query, 5);		
		return docs;
	}
	
	

}
