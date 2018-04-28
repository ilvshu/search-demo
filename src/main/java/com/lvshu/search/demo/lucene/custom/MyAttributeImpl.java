package com.lvshu.search.demo.lucene.custom;

import org.apache.lucene.util.AttributeImpl;
import org.apache.lucene.util.AttributeReflector;

public  class MyAttributeImpl extends AttributeImpl implements MyAttribute{
	private char[] charTem = new char[255];
	private int length = 0;

	@Override
	public void setChars(char[] buffer, int length) {
		this.length = length;
		if(length > 0){
			System.arraycopy(buffer, 0, charTem, 0, length);
		}
	}

	@Override
	public char[] getChars() {
		
		return this.charTem;
	}

	@Override
	public int getLength() {
		
		return this.length;
	}
	
	@Override
	public String getString() {
		if (this.length > 0) {
			return new String(this.charTem, 0, length);
		}
		return null;
	}

	@Override
	public void clear() {
		this.length = 0;
	}

	@Override
	public void reflectWith(AttributeReflector reflector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copyTo(AttributeImpl target) {
		// TODO Auto-generated method stub
		
	}
	
}