package xyz.taotao.search.test;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.junit.Test;

public class SolrClientDelForQuery {


    /**
     * 根据查询删除
     */
    @Test
    public void deleteDocumentByQuery() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.184.130:8080/solr");
        solrServer.deleteByQuery("title:change.me");
        solrServer.commit();
    }


}
