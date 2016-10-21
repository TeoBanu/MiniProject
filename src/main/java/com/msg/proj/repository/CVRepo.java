package com.msg.proj.repository;

import javax.persistence.Query;
import java.util.List;

public class CVRepo<CV, Integer> extends GenericRepo {
    public List<CV> getCVsContainingWord(String word) {
        String queryStr = "select cv from CV cv where cv.text LIKE '%" + word + "%'";
        Query q = em.createQuery(queryStr);
//        query.setParameter("word", "%'" + word + "'%");
        List<CV> cvs = q.getResultList();
        return cvs;
    }

}
