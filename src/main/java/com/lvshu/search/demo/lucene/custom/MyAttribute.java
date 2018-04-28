package com.lvshu.search.demo.lucene.custom;

import org.apache.lucene.util.Attribute;

interface MyAttribute extends Attribute{
	void setChars(char[] buffer,int length);
	
	char[] getChars();
	
	String toString();
	
	int getLength();

	String getString();
}