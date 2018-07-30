package com.manipal.pics.services;

import com.manipal.pics.PicsQueue;
import com.manipal.pics.beans.PicsDataBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PicsPostService {

    @Autowired
    private PicsQueue picsQueue;

    public boolean createFileForGivenData(PicsDataBean picsDataBean) {
       picsQueue.addToQueue(picsDataBean.getPicsFileName());
        return false;
    }
}
