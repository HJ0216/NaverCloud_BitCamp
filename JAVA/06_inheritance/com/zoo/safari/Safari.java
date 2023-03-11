package com.zoo.safari; // com.zoo와 다른 pkg

import com.zoo.*;

public class Safari extends Zoo {

	public static void main(String[] args) {
		Zoo zoo  = new Zoo();
		zoo.tiger(); // public
//		zoo.giraffe(); // protected
		// extends Zoo: child class에서 접근
		
		Safari s = new Safari();
		s.tiger();
		s.giraffe();
		
		Zoo zs = new Safari();
		zs.tiger();
//		zs.giraffe();
		
		Safari sz = (Safari) new Zoo();
		sz.tiger();
		sz.giraffe();
//		zoo.elephant(); // default
//		zoo.lion(); // private

	}
	
}
