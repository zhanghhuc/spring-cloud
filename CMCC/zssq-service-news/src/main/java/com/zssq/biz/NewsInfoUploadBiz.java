package com.zssq.biz;

import com.zssq.dao.mapper.NewsInfoUploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-04-12.
 */
@Service
public class NewsInfoUploadBiz {

    @Autowired
    private NewsInfoUploadMapper newsInfoUploadMapper;


}
