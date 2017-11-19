package com.zssq.shiro.mysecurity.mysubjectfactory;

import org.apache.shiro.mgt.DefaultSubjectFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;

public class MySubjectFactory extends DefaultSubjectFactory {

	@Override
	public Subject createSubject(SubjectContext subjectContext) {

		// 不创建session
		subjectContext.setSessionCreationEnabled(false);
		
		return super.createSubject(subjectContext);
	}
	

}
