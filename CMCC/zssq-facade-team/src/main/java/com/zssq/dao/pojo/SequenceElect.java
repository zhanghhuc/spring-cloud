package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * @ClassName SequenceElect
 * @Description 用于生成评选编码
 * @author JiPengChun
 * @date 2017年6月7日 下午3:34:55
 * @version 1.0
 * @since JDK 1.7
 */
public class SequenceElect implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}