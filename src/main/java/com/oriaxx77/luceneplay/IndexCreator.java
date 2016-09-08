package com.oriaxx77.luceneplay;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

public class IndexCreator {
	
	private Analyzer analyzer;
	private Directory indexDirectory;
	
	public IndexCreator( Analyzer analyzer, Directory indexDirectory ){
		this.analyzer = analyzer;
		this.indexDirectory = indexDirectory;
	}
	
	public void createIndices( Entity[] entities ) throws IOException {		
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig( analyzer );
		IndexWriter indexWriter = null;
		try
		{
			indexWriter = new IndexWriter( indexDirectory, indexWriterConfig );  
			createAndIndexDocuments( indexWriter, entities );
		}
		finally
		{
			if ( indexWriter != null )
				indexWriter.close(); 
		}
	}
	
	private void createAndIndexDocuments( IndexWriter indexWriter, Entity[] entities ) throws IOException{ 
		for ( Entity entity: entities ){
			Document document = createDocument( entity.getTags(), entity.getId() );
			indexWriter.addDocument( document );
		}
	}
	
	private Document createDocument( String tags, int id){
		Document document = new Document();
		Field idField = new StringField( "id", "" + id, Field.Store.YES );
		document.add( idField );
		Field tagsField = new TextField( "tags", tags , Field.Store.YES );
		document.add( tagsField );
		return document;
	}
}
