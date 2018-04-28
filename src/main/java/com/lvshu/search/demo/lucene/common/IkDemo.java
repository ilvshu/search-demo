package com.lvshu.search.demo.lucene.common;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class IkDemo {
	public static void main(String[] args) {
		String text = "厉害了我的国一经播出，受到各方好评，强烈激发了国人的爱国之情、自豪感！";
		try (IKAnalyzer4Lucene7 ikAnalyzer = new IKAnalyzer4Lucene7(true)){
			TokenStream ts = ikAnalyzer.tokenStream("test", text);
			ts.reset();
			CharTermAttribute charTerm = ts.getAttribute(CharTermAttribute.class);
			while(ts.incrementToken()){
				System.out.println(charTerm.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
