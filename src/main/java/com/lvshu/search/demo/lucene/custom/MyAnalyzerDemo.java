package com.lvshu.search.demo.lucene.custom;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

/**
 * 自定义分词器
 * @author xu
 *
 */
public class MyAnalyzerDemo extends Analyzer{
	
	
	
	
	public static void main(String[] args) {
		String text = "An AttributeSource contains a list of different AttributeImpls, and methods to add and get them. ";
		Analyzer analyzer = new MyAnalyzerDemo();
		TokenStream stream = analyzer.tokenStream("test", text);
		MyAttribute myAttribute =  stream.getAttribute(MyAttribute.class);
		try {
			stream.reset();
			while(stream.incrementToken()){
				System.out.println(myAttribute.getString()+"|");
			}
			stream.end();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			analyzer.close();
		}
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer source = new Tokenizer() {
			private MyAttribute attr = addAttribute(MyAttribute.class);
			private char[] buffer = new char[255];
			private int length = 0 ;
			private int c;
			@Override
			public boolean incrementToken() throws IOException {
				clearAttributes();
				length = 0;
				while(true){
					int c = input.read();
					if(c == -1){
						if(length > 0){
							attr.setChars(buffer, length);
							return true;
						}else{							
							return false;
						}
					}
					if(Character.isWhitespace(c)){
						if(length > 0){
							attr.setChars(buffer, length);
							return true;
						}
					}
					buffer[length++] = (char)c;
				}
			}
		};
		TokenFilter result = new TokenFilter(source) {
			MyAttribute attr = addAttribute(MyAttribute.class);
			
			
			@Override
			public boolean incrementToken() throws IOException {
				boolean res = input.incrementToken();
				if(res){
					char[] chars = attr.getChars();
					int length = attr.getLength();
					if(length > 0){
						for(int i=0;i<length;i++){
							chars[i] = Character.toLowerCase(chars[i]);
						}
					}
				}
				return res;
			}
		};
		return new TokenStreamComponents(source, result);
	}
	

	
}



